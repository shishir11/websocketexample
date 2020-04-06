package com.upstox.stock.viewer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.upstox.stock.viewer.utility.PropertyFileReader;
/**
 * 
 * @author shishir.sarkar
 * This class responsible to read trade data in specific interval.
 */
@Component
public class ReadFileLineByLineUsingScanner {
	private static final String FILE_PATH = "file-path";
	private Scanner scan;

	public ReadFileLineByLineUsingScanner() {
		super();
		try {
			this.scan = new Scanner(new File(PropertyFileReader.getInstance().getProperty(FILE_PATH)));
		} catch (FileNotFoundException e) {
		}
	}

	public String getNextTradeData() {
		if (scan.hasNext())
			return scan.nextLine();
		else
			return Optional.empty().toString();
	}
}