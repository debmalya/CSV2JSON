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

import com.google.gson.JsonArray;

/**
 * @author debmalyajash
 *
 */
public class JSONUtil {

	/**
	 * 
	 * @param arr java.lang.String[] . 
	 * @return 
	 */
	public JsonArray convert(final String[] arr) {
		JsonArray jsonArr = new JsonArray();
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				jsonArr.add(arr[i]);
			}
		}
		return jsonArr;
	}
}
