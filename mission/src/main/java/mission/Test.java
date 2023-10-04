package mission;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Test {
	public void test() {
		String json = "{\"name\": \"Messi\", \"email\" : \"test@test.com\", \"age\" : 20}";

	    //1. JsonObject사용
		
//	    JsonElement element = JsonParser.parseString(json);
//	   
//	    JsonObject object = element.getAsJsonObject();
//	   
//	    System.out.println(object.get("name").getAsString());//Messi 출력
//	    System.out.println(object.get("age").getAsInt());//20 출력
	    
	    //2. gson사용
		
		Gson gson = new Gson();
	    
		Map<String, Object> map = gson.fromJson(json, Map.class);
		
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " :::" + entry.getValue());
		}
	    
		Member member = gson.fromJson(json, Member.class);
		
		System.out.println(member.name);
		System.out.println(member.email);
		System.out.println(member.age);
		
		
	}
	
	

}

class Member{
	String name;
	String email;
	int age;
}
