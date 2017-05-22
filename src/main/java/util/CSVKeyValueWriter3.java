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

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author debmalyajash
 *
 */
public class CSVKeyValueWriter3 {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if (args.length > 1) {
			PrintWriter writer = new PrintWriter(args[0].replace(".csv", "_json.csv"));
			long count = 0;
			StringBuilder valueEntries = new StringBuilder();
			StringBuilder keyEntries = new StringBuilder();

			String[] keys = args[1].split(",");
			int[] keyIndexes = new int[keys.length];
			for (int i = 0; i < keys.length; i++) {
				keyIndexes[i] = Integer.parseInt(keys[i]);
			}

			String[] valueCols = args[2].split(",");
			int[] valuesIndexes = new int[valueCols.length];
			for (int i = 0; i < valueCols.length; i++) {
				valuesIndexes[i] = Integer.parseInt(valueCols[i]);
			}

			try (CSVReader reader = new CSVReader(new FileReader(args[0]))) {
				String[] eachLine = reader.readNext();
				boolean isFirstLine = true;
				while (eachLine != null) {
					if (!isFirstLine) {
						keyEntries.delete(0, keyEntries.length());
						valueEntries.delete(0, valueEntries.length());
						for (int i = 0; i < keyIndexes.length; i++) {
							if (i > 0) {
								keyEntries.append("|");
							}
							keyEntries.append(eachLine[keyIndexes[i]]);
						}

						for (int i = 0; i < valuesIndexes.length; i++) {
							if (i > 0) {
								valueEntries.append("|");
							}
							valueEntries.append(eachLine[valuesIndexes[i]]);
						}
						writer.println("\""+keyEntries.toString()+"\":"+"\""+valueEntries.toString()+"\",");
						count++;
						
					} else {
						isFirstLine = false;
					}
					eachLine = reader.readNext();
				}
			} finally {
				if (writer != null) {
					writer.flush();
					writer.close();
				}
				System.out.println("Wrote " + count + " records.");
			}
		} else {
			System.err.println(
					"Usage CSVKeyValueWrtier3 <input file name> <key index column> <value index columns separated by ,>");
		}

	}

}
