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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * @author debmalyajash
 *
 */
public class CSV2JSONConverterTest {

	/**
	 * Test method for
	 * {@link csv2json.CSV2JSONConverter#convert(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testConvert() {
		CSV2JSONConverter converter = new CSV2JSONConverter();
		BufferedReader reader = null;
		try {
			converter.convert("./src/test/resources/input/EPL.csv", "./src/test/resources/output/EPL.json");
			reader = new BufferedReader(new FileReader("./src/test/resources/output/EPL.json"));

			String eachLine = null;
			JsonParser parser = new JsonParser();
			StringBuilder sb = new StringBuilder();
			while ((eachLine = reader.readLine()) != null) {
				sb.append(eachLine);
			}
			try {
				JsonArray parsedObject = (JsonArray) parser.parse(sb.toString());
			} catch (JsonSyntaxException jse) {

				jse.printStackTrace();
				Assert.assertFalse(jse.getMessage(), true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertFalse(e.getMessage(), true);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
