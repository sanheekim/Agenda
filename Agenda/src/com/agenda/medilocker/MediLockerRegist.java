package com.agenda.medilocker;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MediLockerRegist {
//element.getAsJsonObject().get("response").getAsJsonObject().get("body").getAsJsonObject().get("items")
//.getAsJsonObject().get("item").getAsJsonArray().get(0).getAsJsonObject().get("ITEM_NAME");	
	private String[] strArr = null ;
	private ArrayList<String> dupArr = null ;
	
	public void MediRegist(String strJson,String str, String str_1, String member_id) {
		MediLockerDao dao = new MediLockerDao();
		MediLockerDto dto = new MediLockerDto();
		dto.setMember_id(member_id);
		strArr = str.split(",");
		dupArr = removeDuplicate(strArr);
		//strArr = replaceStr(str);
		//dupArr = removeDuplicate(strArr);
		
		//String[] dupstrArr = new String[dupArr.size()]; //dupArr의 길이만큼 선언
		//for(int i = 0; i < dupArr.size(); i++) {
		//	dupstrArr[i] = dupArr.get(i);
		//}
		System.out.println(str_1);
		JsonElement element = JsonParser.parseString(strJson);
		JsonObject isElement = element.getAsJsonObject().get("response").getAsJsonObject().get("body").getAsJsonObject();
		
		if(!isElement.get("items").isJsonNull()) {
			if(isElement.get("items").getAsJsonObject().get("item").isJsonArray()) {
				JsonArray ArrayElement = isElement.get("items").getAsJsonObject().get("item").getAsJsonArray();
				
				for(int i = 0; i < ArrayElement.size(); i ++) {
					String item_name = ArrayElement.get(i).getAsJsonObject().get("ITEM_NAME").getAsString();
					String subStr = item_name.substring(i, str_1.length());
					
					if(str_1.equals(item_name)) { // 추출된 문자와 조회된 item_name이 완벽하게 일치할 때
						System.out.println("toStr : " + item_name );
						
						if(strArr[0].equals(item_name)) {
							dto.setPres_mediname(item_name);
							dao.insert(dto);
						}else {
							int idx = dupArr.indexOf(item_name);
							String mediadd = "";
							for(int j = 0 ; j < idx; j++) {
								mediadd += (j == 0)? dupArr.get(0): "," + dupArr.get(j) ;
							}
							System.out.println("mediadd : " + mediadd);
							dto.setPres_mediname(mediadd);
							dto.setPres_medinameadd(","+item_name);
							dao.update(dto);
						}
					}else if(str_1.equals(subStr)) { // 추출된 문자와 추출된 문자의 길이만큼 잘라낸 item_name이 일치할 때
						System.out.println(item_name);
						System.out.println("추출된 문자 : " + str_1);
					
						if(strArr[0].equals(subStr)) {
							dto.setPres_mediname(subStr);
							dao.insert(dto);
						}else {
							int idx = dupArr.indexOf(subStr);
							String mediadd = "";
							for(int j = 0 ; j < idx; j++) {
								mediadd += (j == 0)? dupArr.get(0): "," + dupArr.get(j) ;
							}
							System.out.println("mediadd : " + mediadd);
							dto.setPres_mediname(mediadd);
							dto.setPres_medinameadd(","+subStr);
							dao.update(dto);
							//update
						}
					}
					
				}
				
				
			}else if(isElement.get("items").getAsJsonObject().get("item").isJsonObject()) {
				String item_name = isElement.get("items").getAsJsonObject().
											get("item").getAsJsonObject().get("ITEM_NAME").getAsString();
				
					String subStr = item_name.substring(0, str_1.length());
					if(str_1.equals(item_name)) { // 추출된 문자와 조회된 item_name이 완벽하게 일치할 때
						System.out.println("toStr : " + item_name );
						
						if(strArr[0].equals(item_name)) {
							dto.setPres_mediname(item_name);
							dao.insert(dto);
						}else {
							int idx = dupArr.indexOf(item_name);
							String mediadd = "";
							for(int i = 0 ; i < idx; i++) {
								mediadd += (i == 0)? dupArr.get(0): "," + dupArr.get(i) ;
							}
							System.out.println("mediadd : " + mediadd);
							dto.setPres_mediname(mediadd);
							dto.setPres_medinameadd(","+item_name);
							dao.update(dto);
						}
					}else if(str_1.equals(subStr)) { // 추출된 문자와 추출된 문자의 길이만큼 잘라낸 item_name이 일치할 때
						System.out.println(item_name);
						System.out.println("추출된 문자 : " + str_1);
					
						if(strArr[0].equals(subStr)) {
							dto.setPres_mediname(subStr);
							dao.insert(dto);
						}else {
							int idx = dupArr.indexOf(subStr);
							String mediadd = "";
							for(int i = 0 ; i < idx; i++) {
								mediadd += (i == 0)? dupArr.get(0): "," + dupArr.get(i) ;
							}
							System.out.println("mediadd : " + mediadd);
							dto.setPres_mediname(mediadd);
							dto.setPres_medinameadd(","+subStr);
							dao.update(dto);
							//update
						}
					}
				}
			}
		}			
	
	public String[] replaceStr(String str) {
		String[] strArr = str.split(",");
		for(int i = 0; i < strArr.length; i++) {
			strArr[i] = strArr[i].replace("젠점","젠정");
            strArr[i] = strArr[i].replace("점안역","점안액");
            strArr[i] = strArr[i].replace("장안액","점안액");
            strArr[i] = strArr[i].replace("060",""); //이번 테스트에 한해서만 추가
            strArr[i] = strArr[i].replace("징","정"); //이번 테스트에 한해서만 추가
            strArr[i] = strArr[i].replace("접","럽"); //이번 테스트에 한해서만 추가
            
		}
		return strArr;
	}
	
	public ArrayList<String> removeDuplicate(String[] strArr){
		ArrayList<String> dupArr = new ArrayList<String>(); 
		for(String str : strArr) {
			if(!dupArr.contains(str)) {
				dupArr.add(str);
			}
		}
		return dupArr;
	}
	
}
