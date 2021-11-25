package com.accenture.bars.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.accenture.bars.domain.Request;
import com.accenture.bars.repository.BillingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.bars.domain.Record;
import com.accenture.bars.exception.BarsException;

@RestController
public class BarsController {

	private static final Logger log = LoggerFactory.getLogger(BarsController.class);

	@Autowired
	private FileProcessor fileProcessor;

	@Autowired
	BillingRepository billingRepository;

	@GetMapping("/bars")
	public List<Record> requestBilling(@RequestParam String filePath) throws BarsException, IOException {

		log.info("============> FilePath: " + filePath);
		log.info("File to process: C:/BARS_TEST/" + filePath);
		File file = new File("C:/BARS_TEST/" + filePath);

		List<Request> requests = fileProcessor.execute(file);
		log.info("Successfully processed Request File");

		return fileProcessor.retrieveRecordfromDB(requests);
	}
}
