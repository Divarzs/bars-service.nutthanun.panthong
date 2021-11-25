package com.accenture.bars.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accenture.bars.domain.Request;
import com.accenture.bars.exception.BarsException;
import org.springframework.util.Assert;

public class TextInputFileImpl extends AbstractInputFile {

    private static final Logger log = LoggerFactory.getLogger(TextInputFileImpl.class);
    final int DATE_LENGTH = 8;
    final int FIRST_INDEX_OF_BILL_CYCLE = 0;
    final int LAST_INDEX_OF_BILL_CYCLE = 2;
    final int LAST_INDEX_OF_START_DATE = 10;
    final int LAST_INDEX_OF_END_DATE = 18;

    final static String DATE_FORMAT = "MMddyyyy";
    private Connection con;

    public TextInputFileImpl() {
    }

    public TextInputFileImpl(File file) {
        super(file);
    }

    @Override
    public List<Request> readFile() throws IOException, BarsException {

        List<Request> requests = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(this.getFile()))) {

            int billingCycle;
            LocalDate startDate;
            LocalDate endDate;
            String line = "";
            int n = 1;

            while ((line = br.readLine()) != null) {
                log.info("===> Is now processing request row :" + n + "<===");

                billingCycle = Integer.parseInt(line.substring(FIRST_INDEX_OF_BILL_CYCLE,
                        LAST_INDEX_OF_BILL_CYCLE));

                if (billingCycle < this.MIN_BILLING_CYCLE || billingCycle > this.MAX_BILLING_CYCLE) {
                    log.error(BarsException.BILLING_CYCLE_NOT_ON_RANGE + n + ".");
                    throw new BarsException(BarsException.BILLING_CYCLE_NOT_ON_RANGE + n + ".");
                }

                String startDateFmt = line.substring(LAST_INDEX_OF_BILL_CYCLE, LAST_INDEX_OF_START_DATE)
                        .replaceAll("/", "").replaceAll(" ", "");
                if (!startDateFmt.matches("[0-9]+") || startDateFmt.length() != DATE_LENGTH) {
                    log.error(BarsException.INVALID_START_DATE_FORMAT + n + ".");
                    throw new BarsException(BarsException.INVALID_START_DATE_FORMAT + n + ".");
                }

                String endDateFmt = line.substring(LAST_INDEX_OF_START_DATE, LAST_INDEX_OF_END_DATE)
                        .replaceAll("/", "").replaceAll(" ", "");
                if (!endDateFmt.matches("[0-9]+") || endDateFmt.length() != DATE_LENGTH) {
                    log.error(BarsException.INVALID_END_DATE_FORMAT + n + ".");
                    throw new BarsException(BarsException.INVALID_END_DATE_FORMAT + n + ".");
                }

                if (billingCycle >= this.MIN_BILLING_CYCLE && billingCycle <= this.MIN_BILLING_CYCLE &&
                        startDateFmt.length() == DATE_LENGTH && endDateFmt.length() == DATE_LENGTH) {
                    startDate = LocalDate.parse(startDateFmt, formatter);
                    endDate = LocalDate.parse(endDateFmt, formatter);
                    requests.add(new Request(billingCycle, startDate, endDate));
                }
                n++;
            }

            if (line == null && n == 1) {
                log.error(BarsException.NO_REQUESTS_TO_READ);
                throw new BarsException(BarsException.NO_REQUESTS_TO_READ);
            }

            return requests;
        }
    }
}
