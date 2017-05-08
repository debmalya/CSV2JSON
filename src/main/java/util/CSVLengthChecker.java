/**
 * Copyright 2015-2016 Debmalya Jash
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author debmalyajash
 *
 */
public class CSVLengthChecker {

	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		if (args.length > 0) {
			if (args.length == 1) {
				checkLength(args[0]);
			} else if (args.length == 2) {
				checkLength(args[0], args[1]);
			} else if (args.length == 3) {
				checkValue(args[0], args[1],args[2]);
			}
		} else {
			System.err.println("Usage : java CSVLengthChecker <file_name>");
		}

	}

	/**
	 * @param string
	 * @param string2
	 * @param colIndex
	 * @throws IOException 
	 */
	private static void checkValue(String fileNameWithPath, String separator, String colIndex) throws IOException {
		FileReader inputFile = new FileReader(fileNameWithPath);
		List<String[]> allLines = null;
		if (separator != null && separator.length() > 0) {
			try (CSVReader reader = new CSVReader(inputFile, separator.charAt(0))) {
				allLines = reader.readAll();
			}
		} else {
			try (CSVReader reader = new CSVReader(inputFile)) {
				allLines = reader.readAll();
			}
		}
		
		int checkingIndex = Integer.parseInt(colIndex);
		for (String[] eachRecord:allLines) {
			System.out.println(eachRecord[checkingIndex]);
		}
		
	}

	/**
	 * @param fileNameWithPath
	 *            - file name with path.
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static void checkLength(String fileNameWithPath) throws FileNotFoundException, IOException {
		checkLength(fileNameWithPath, ",");

	}

	/**
	 * @param fileNameWithPath
	 *            file name with path.
	 * @param separator
	 *            column separator.
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static void checkLength(String fileNameWithPath, String separator)
			throws FileNotFoundException, IOException {
		FileReader inputFile = new FileReader(fileNameWithPath);
		List<String[]> allLines = null;
		if (separator != null && separator.length() > 0) {
			try (CSVReader reader = new CSVReader(inputFile, separator.charAt(0))) {
				allLines = reader.readAll();
			}
		} else {
			try (CSVReader reader = new CSVReader(inputFile)) {
				allLines = reader.readAll();
			}
		}

		Map<Integer, Integer> linelengthMap = new HashMap<>();
		for (String[] eachLine : allLines) {
			Integer count = linelengthMap.get(eachLine.length);
			System.out.println("Each line length :" + eachLine.length);
			if (count == null) {
				count = 0;
			}
			++count;
			linelengthMap.put(eachLine.length, count);
		}
		
		System.out.println("Total number lines : " + allLines.size() );
		System.out.println(linelengthMap);

	}

}
