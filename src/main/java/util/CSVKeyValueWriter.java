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
import java.io.PrintWriter;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author debmalyajash
 *
 */
public class CSVKeyValueWriter {

	/**
	 * This will write Key and value from CSV file.
	 * 
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		if (args.length > 0) {
			String outputFileName = args[0].replace(".csv", "_JSON.csv");
			PrintWriter writer = new PrintWriter(outputFileName);
			int count = 0;
			try (CSVReader reader = new CSVReader(new FileReader(args[0]))) {
				String[] eachLine = reader.readNext();
				boolean firstLine = true;
				while (eachLine != null) {
					if (!firstLine) {
						String key = "\""+args[1]+"_" + eachLine[0] + "\":";
						// First column is key and value is <second column>|<third column>|<fourth column>
						String value = "\"" + eachLine[1] + "|" + eachLine[2] + "|" + eachLine[3]+"\",";
						writer.println(key + value);
						count++;
						
					} else {
						firstLine = false;
					}
					eachLine = reader.readNext();
					
					
					
				}
			} finally {
				writer.flush();
				writer.close();
				System.out.println(outputFileName + " wrote " + count + " records.");
			}
		} else {
			System.err.println("Usage : CSVKeyValueWriter <fileName> <PrefixForKeyValue>");
		}

	}

}
