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



import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;



/**
 * @author debmalyajash
 *
 */
public class JSONUtilTest {

	/**
	 * Test method for {@link util.JSONUtil#convert(java.lang.String[])}.
	 */
	@Test
	public void testConvert() {
		JSONUtil util = new JSONUtil();
		JsonArray jsonArr = util.convert(null);
		Assert.assertEquals(0, jsonArr.size());
		
		jsonArr = util.convert(new String[] {"MO", "LOCAL", "NOROAMING", "On-Net"});
		Assert.assertEquals(4, jsonArr.size());
		Assert.assertEquals("MO",jsonArr.get(0).getAsString());
		JsonArray myArr = ( ( JsonElement ) jsonArr ).getAsJsonArray();
		
		Assert.assertTrue(util.exists("MO",myArr));
		Assert.assertFalse(util.exists("MO1",myArr));

	}
	
	@Test
	public void testInsert() {
		
		JsonArray oldArray = new JsonArray();
		oldArray.add(true);
		oldArray.add('A');
		oldArray.add(123);
		
		JsonObject arrayElement = new JsonObject();
		arrayElement.add("Comment", new JsonPrimitive ("Playing with JsonArray"));
		oldArray.add(arrayElement);
		Assert.assertEquals(4, oldArray.size());
		
		
		JsonArray newArray = JSONUtil.insert(0, new JsonPrimitive(0), oldArray);
		Assert.assertEquals(5, newArray.size());
		Assert.assertEquals(0, newArray.get(0).getAsInt());
		
		newArray = JSONUtil.insert(1, arrayElement, newArray);
		Assert.assertEquals(6, newArray.size());
		System.out.println(newArray);
		
		newArray = JSONUtil.insert(7, new JsonPrimitive("How are you JsonArray?"), newArray);
	}

}
