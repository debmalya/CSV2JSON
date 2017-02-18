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
package csv2json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import com.google.gson.stream.JsonWriter;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author debmalyajash
 *
 */
public class CSV2JSONConverter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * It takes a CSV file and convert it to JSON. First line of the CSV file is
	 * the column header.
	 * 
	 * @param csvInputFile
	 *            - CSV input file.
	 * @param jsonOutputFile
	 *            - Output JSON file. Output directory must be existing and have write access to it.
	 * @throws Exception
	 */
	public void convert(final String csvInputFile, final String jsonOutputFile) throws Exception {
		if (csvInputFile != null && jsonOutputFile != null) {

			PrintWriter writer = null;
			JsonWriter jsonWriter = null;
			int rowCount =1;

			try (CSVReader reader = new CSVReader(new FileReader(csvInputFile))) {

				
				writer = new PrintWriter(jsonOutputFile);
			    jsonWriter = new JsonWriter(writer);
			    jsonWriter.setIndent("    ");
				jsonWriter.setHtmlSafe(true);
				jsonWriter.setLenient(true);

				boolean readFirstLine = false;
				// To store each line of the file.
				String[] eachLine = reader.readNext();
				// To store column header.
				String[] columnHeader = null;

				
				
				jsonWriter.beginArray();				
				while (eachLine != null) {

					if (eachLine.length > 0) {
						if (!readFirstLine) {
							//
							columnHeader = eachLine;
							readFirstLine = true;
						} else {
							// Convert each row to JSON object.
							jsonWriter.beginObject();
							jsonWriter.name("row").value(String.valueOf(rowCount++));

							
							for (int columnIndex = 0; columnIndex < eachLine.length; columnIndex++) {
								// converting each column
								jsonWriter.name(columnHeader[columnIndex]).value(eachLine[columnIndex]);
								// end of converting each column
							}
							
							jsonWriter.endObject();
							// End of converting each row.
							

						}
					}
					eachLine = reader.readNext();
				}
				jsonWriter.endArray();
				
				
			} catch (FileNotFoundException e) {
				throw new Exception(e.getMessage(), e);
			} finally {
				
				if (jsonWriter != null) {
					jsonWriter.flush();
					jsonWriter.close();
				}
				if (writer != null) {
					writer.flush();
					writer.close();
				}
				
				
			}

		} else {
			throw new IllegalArgumentException("CSV input file, JSON output file name cannot be null.");
		}
	}

}
