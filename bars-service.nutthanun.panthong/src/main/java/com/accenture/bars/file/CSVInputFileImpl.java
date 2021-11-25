package com.accenture.bars.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.accenture.bars.entity.Billing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accenture.bars.domain.Request;
import com.accenture.bars.exception.BarsException;
import org.springframework.util.Assert;

public class CSVInputFileImpl extends AbstractInputFile {

    private static final Logger log = LoggerFactory.getLogger(CSVInputFileImpl.class);
    final int DATE_LENGTH = 8;

    public CSVInputFileImpl() {
    }

    public CSVInputFileImpl(File file) {
        super(file);
//		this.log = log;
    }

    @Override
    public List<Request> readFile() throws IOException, BarsException {
        List<Request> requests = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(this.getFile()))) {

            int billingCycle;
            LocalDate startDate;
            LocalDate endDate;
            String line = "";
            int n = 1;

            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                log.info("===> Is now processing request row :" + n + "<===");

                billingCycle = Integer.parseInt(arr[0]);
                if (billingCycle < this.MIN_BILLING_CYCLE || billingCycle > this.MAX_BILLING_CYCLE) {
                    log.error(BarsException.BILLING_CYCLE_NOT_ON_RANGE + n + ".");
                    throw new BarsException(BarsException.BILLING_CYCLE_NOT_ON_RANGE + n + ".");
                }

                String startDateFmt = arr[1];
                if (!isValidDate(startDateFmt)) {
                    log.error(BarsException.INVALID_START_DATE_FORMAT + n + ".");
                    throw new BarsException(BarsException.INVALID_START_DATE_FORMAT + n + ".");
                }

                String endDateFmt = arr[2];
                if (!isValidDate(endDateFmt)) {
                    log.error(BarsException.INVALID_END_DATE_FORMAT + n + ".");
                    throw new BarsException(BarsException.INVALID_END_DATE_FORMAT + n + ".");
                }

                if (isValidDate(startDateFmt) && isValidDate(endDateFmt) && billingCycle <=
                        this.MAX_BILLING_CYCLE && billingCycle >= this.MIN_BILLING_CYCLE) {
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

    public static boolean isValidDate(String inDate) {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        df.setLenient(false);
        try {
            df.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
}
