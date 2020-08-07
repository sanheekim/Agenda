package com.agenda.medilocker;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MediLockerRegist {

//element.getAsJsonObject().get("response").getAsJsonObject().get("body").getAsJsonObject().get("items")
//.getAsJsonObject().get("item").getAsJsonArray().get(0).getAsJsonObject().get("ITEM_NAME");	

	public void MediRegist(String strJson,String[] items) {

		JsonElement element = JsonParser.parseString(strJson);
		JsonObject isElement = element.getAsJsonObject().get("response").getAsJsonObject().get("body").getAsJsonObject().get("items")
				.getAsJsonObject();
		if(isElement.get("item").isJsonArray() == true) {
		JsonArray ArrayElement = isElement.get("item").getAsJsonArray();
		
		String toStr = ArrayElement.get(0).getAsJsonObject().get("ITEM_NAME").toString();
		System.out.println(items[0]);
		for(int i = 0; i < items.length; i++) {
			if(!items[i].equals(toStr)) {
				System.out.println("완전 동일!");
			}else {
				System.out.println("뭐라도 출력해주라...");
				break;
			}
			
		}
		
		}else if(isElement.get("item").isJsonObject() == true) {

			System.out.println(isElement.get("item").getAsJsonObject().get("ITEM_NAME"));
		}
	}		
}
