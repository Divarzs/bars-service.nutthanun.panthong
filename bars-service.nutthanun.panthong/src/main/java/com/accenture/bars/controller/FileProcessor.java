package com.accenture.bars.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.accenture.bars.exception.BarsException;
import com.accenture.bars.factory.InputFileFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.bars.domain.Record;
import com.accenture.bars.domain.Request;
import com.accenture.bars.entity.Billing;
import com.accenture.bars.file.AbstractInputFile;
import com.accenture.bars.repository.BillingRepository;

@Component
public class FileProcessor {

    @Autowired
    private BillingRepository billingRepository;

    public List<Request> execute(File file) throws BarsException, IOException {

        InputFileFactory instance = InputFileFactory.getInstance();
        AbstractInputFile inputFile = instance.getInputFile(file);
        inputFile.setFile(file);

        List<Request> requests = inputFile.readFile();
        return requests;
    }

    public List<Record> retrieveRecordfromDB(List<Request> requests) throws BarsException {

        List<Record> records = new ArrayList<>();

        for (Request request : requests) {

            Billing billing = billingRepository.findByBillingCycleAndStartDateAndEndDate(
                    request.getBillingCycle(), request.getStartDate(), request.getEndDate());

            if (billing != null) {
                Record record = new Record(billing.getBillingCycle(), billing.getStartDate()
                        , billing.getEndDate(), billing.getAccountId().getAccountName()
                        , billing.getAccountId().getCustomerId().getFirstName()
                        , billing.getAccountId().getCustomerId().getLastName(), billing.getAmount());

                records.add(record);
            } else {
                throw new BarsException(BarsException.NO_RECORDS_TO_WRITE);
            }
        }
        return records;
    }

    public void writeOutput(List<Record> records) {

    }
}
