package com.accenture.bars.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.accenture.bars.domain.Request;
import com.accenture.bars.exception.BarsException;

public abstract class AbstractInputFile {

	public final int MIN_BILLING_CYCLE = 1;
	public final int MAX_BILLING_CYCLE = 12;
	private File file;

	public AbstractInputFile() {

	}

	public AbstractInputFile(File file) {
		super();
		this.file = file;
	}

	public abstract List<Request> readFile() throws IOException, BarsException;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
