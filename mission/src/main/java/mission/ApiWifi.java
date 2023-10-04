
package mission;

import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.Request;
import okhttp3.Response;

public class ApiWifi {
	 
	private int totalCount;
	private String json;
	private Response response;
	
	public ApiWifi() {
		setTotalCount();
		int maxRequest = 1000;
		int start = 1;
		int end = 0;
		for (int i = 0; i < totalCount/maxRequest + 1; i++) {
			end = start + maxRequest - 1;
			getTotalInfo(start, Math.min(end, totalCount ));
			System.out.printf("%d ~ %d 저장성공 \n", start, Math.min(end, totalCount ));
			start += maxRequest;
		}
		response.close();
		
		//읽어온 데이터 중 위도 및 경도가 0인 데이터 삭
		DataBase db = new DataBase();
		totalCount -= db.dbDeleteZero();
		
		
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public boolean request(int start, int end, ConnectionPool conn) {
		try {
			String key = "484a6e736a6e616437317570695046";
			String startStr = Integer.toString(start);
			String endStr = Integer.toString(end);
			
			StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
			urlBuilder.append("/" +  URLEncoder.encode(key,"UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
			urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
			urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
			urlBuilder.append("/" + URLEncoder.encode(startStr,"UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
			urlBuilder.append("/" + URLEncoder.encode(endStr,"UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
			// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
			
			URL url = new URL(urlBuilder.toString());
			
			OkHttpClient client = new OkHttpClient().newBuilder().connectionPool(conn).build();
			
			Request.Builder builder = new Request.Builder().url(url).get();
			Request request = builder.build();
			
			response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				ResponseBody body = response.body();
				if (body != null) {
					json = body.string();
					System.out.println("Response : " + json);
				}
			} else {
				System.out.println("Error Occured");
				
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public void setTotalCount() {
		ConnectionPool conn = new ConnectionPool();
		request(1, 1, conn);
		
		JsonElement element = JsonParser.parseString(json);
		JsonObject object = element.getAsJsonObject();
		
		JsonObject wifiInfoObject = object.get("TbPublicWifiInfo").getAsJsonObject();
		totalCount = wifiInfoObject.get("list_total_count").getAsInt();
		
	}
	
	public void getTotalInfo(int start, int end) {
		ConnectionPool conn = new ConnectionPool();
		request(start, end, conn);
		
		JsonElement element = JsonParser.parseString(json);
		JsonObject object = element.getAsJsonObject();
		
		JsonObject wifiInfoObject = object.get("TbPublicWifiInfo").getAsJsonObject();
		JsonArray rows = wifiInfoObject.get("row").getAsJsonArray();
		
		Gson gson = new Gson();
		DataBase db = new DataBase();
		for (int i = 0; i < rows.size(); i++) {
			JsonObject row = rows.get(i).getAsJsonObject();
			EachWifi eachWifi = gson.fromJson(row, EachWifi.class);
			db.dbInsert(eachWifi);
			
		}
		
	}

}


