package com.accenture.bars.factory;

import java.io.File;

import com.accenture.bars.exception.BarsException;
import com.accenture.bars.file.AbstractInputFile;
import com.accenture.bars.file.CSVInputFileImpl;
import com.accenture.bars.file.TextInputFileImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputFileFactory {

    private static final Logger log = LoggerFactory.getLogger(InputFileFactory.class);
    private static InputFileFactory factory;

    private InputFileFactory() {
    }

    public static InputFileFactory getInstance() {
        if (factory == null) {
            factory = new InputFileFactory();
        }
        return factory;
    }

    public AbstractInputFile getInputFile(File file) throws BarsException {

        if (file.getName().endsWith("txt")) {
            if (file.length() == 0) {
                throw new BarsException(BarsException.NO_REQUESTS_TO_READ);
            } else {
                return new TextInputFileImpl();
            }
        } else if (file.getName().endsWith("csv")) {
            if (file.length() == 0) {
                throw new BarsException(BarsException.NO_REQUESTS_TO_READ);
            } else {
                return new CSVInputFileImpl();
            }
        } else {
            log.error(BarsException.FILE_NOT_SUPPORTED);
            throw new BarsException(BarsException.FILE_NOT_SUPPORTED);
        }
    }

}
