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
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

/**
 * @author debmalyajash
 *
 */
public class JSONUtil {

	public static void main(String[] args) {
		JsonArray myArray = new JsonArray();
		myArray.add(new JsonPrimitive(0));
		System.out.println(myArray);
		myArray.add(new JsonPrimitive(1));
		System.out.println(myArray);
		myArray.add(new JsonPrimitive(2));
		System.out.println(myArray);

		JsonArray newArray = new JsonArray();
		newArray.add(new JsonPrimitive(3));
		for (int i = 0; i < myArray.size(); i++) {
			newArray.add(myArray.get(i));
		}
		System.out.println(newArray);
	}

	/**
	 * 
	 * @param arr
	 *            java.lang.String[] .
	 * @return
	 */
	public JsonArray convert(final String[] arr) {
		JsonArray jsonArr = new JsonArray();
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				jsonArr.add(new JsonPrimitive(arr[i]));
			}
		}
		return jsonArr;
	}

	public boolean exists(final String value, JsonArray jsonArray) {
		for (int i = 0; i < jsonArray.size(); i++) {
			if (jsonArray.get(i).getAsString().equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	/**
	 * Add an element at specific index of a JsonArray.
	 * @param index - where the element will be added.
	 * @param val - value to be added.
	 * @param currentArray - existing array where it will be added.
	 * @return modified JsonArray with new entry.
	 */
	public static JsonArray insert(int index, JsonElement val, JsonArray currentArray) {
		if (index > currentArray.size()) {
			currentArray.add(val);
		}
	    JsonArray newArray = new JsonArray();
	    for (int i = 0; i < index; i++) {
	        newArray.add(currentArray.get(i));
	    }
	    newArray.add(val);

	    for (int i = index; i < currentArray.size(); i++) {
	        newArray.add(currentArray.get(i));
	    }
	    return newArray;
	}

}
