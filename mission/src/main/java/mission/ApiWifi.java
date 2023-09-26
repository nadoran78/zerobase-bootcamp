
package mission;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

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
	
	public ApiWifi() {
		getTotalCount();
		System.out.println(totalCount);
	}
	
	public boolean request(int n, ConnectionPool conn) {
		try {
			String key = "484a6e736a6e616437317570695046";
			String end = Integer.toString(n);
			
			StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
			urlBuilder.append("/" +  URLEncoder.encode(key,"UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
			urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
			urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
			urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
			urlBuilder.append("/" + URLEncoder.encode(end,"UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
			// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
			
			URL url = new URL(urlBuilder.toString());
			
			OkHttpClient client = new OkHttpClient().newBuilder().connectionPool(conn).build();
			
			Request.Builder builder = new Request.Builder().url(url).get();
			Request request = builder.build();
			
			Response response = client.newCall(request).execute();
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
	
	public void getTotalCount() {
		ConnectionPool conn = new ConnectionPool();
		request(1, conn);
		
		JsonElement element = JsonParser.parseString(json);
		JsonObject object = element.getAsJsonObject();
		
		JsonObject wifiInfoObject = object.get("TbPublicWifiInfo").getAsJsonObject();
		totalCount = wifiInfoObject.get("list_total_count").getAsInt();
		
	}
	
	public void getTotalInfo() {
		ConnectionPool conn = new ConnectionPool();
		request(totalCount, conn);
		
		JsonElement element = JsonParser.parseString(json);
		JsonObject object = element.getAsJsonObject();
		
		JsonObject wifiInfoObject = object.get("TbPublicWifiInfo").getAsJsonObject();
		JsonArray rows = wifiInfoObject.get("row").getAsJsonArray();
		
		for (int i = 0; i < rows.size(); i++) {
			JsonObject row = rows.get(i).getAsJsonObject();
			Gson gson = new Gson();
			EachWifi eachWifi = gson.fromJson(row, EachWifi.class);
			
		}
		
	}

}

class EachWifi{
	private String X_SWIFI_MGR_NO;
	private String X_SWIFI_WRDOFC;
	private String X_SWIFI_MAIN_NM;
	private String X_SWIFI_ADRES1;
	private String X_SWIFI_ADRES2;
	private String X_SWIFI_INSTL_FLOOR;
	private String X_SWIFI_INSTL_TY;
	private String X_SWIFI_INSTL_MBY;
	private String X_SWIFI_SVC_SE;
	private String X_SWIFI_CMCWR;
	private String X_SWIFI_CNSTC_YEAR;
	private String X_SWIFI_INOUT_DOOR;
	private String X_SWIFI_REMARS3;
	private String LAT;
	private String LNT;
	private String WORK_DTTM;
	
	EachWifi(String X_SWIFI_MGR_NO, String X_SWIFI_WRDOFC, String X_SWIFI_MAIN_NM, String X_SWIFI_ADRES1
			, String X_SWIFI_ADRES2, String X_SWIFI_INSTL_FLOOR, String X_SWIFI_INSTL_TY
			, String X_SWIFI_INSTL_MBY, String X_SWIFI_SVC_SE, String X_SWIFI_CMCWR, String X_SWIFI_CNSTC_YEAR
			, String X_SWIFI_INOUT_DOOR, String X_SWIFI_REMARS3, String LAT, String LNT, String WORK_DTTM) {
		this.X_SWIFI_MGR_NO = X_SWIFI_MGR_NO;
		this.X_SWIFI_WRDOFC = X_SWIFI_WRDOFC;
		this.X_SWIFI_MAIN_NM = X_SWIFI_MAIN_NM;
		this.X_SWIFI_ADRES1 = X_SWIFI_ADRES1;
		this.X_SWIFI_ADRES2 = X_SWIFI_ADRES2;
		this.X_SWIFI_INSTL_FLOOR = X_SWIFI_INSTL_FLOOR;
		this.X_SWIFI_INSTL_TY = X_SWIFI_INSTL_TY;
		this.X_SWIFI_INSTL_MBY = X_SWIFI_INSTL_MBY;
		this.X_SWIFI_SVC_SE = X_SWIFI_SVC_SE;
		this.X_SWIFI_CMCWR = X_SWIFI_CMCWR;
		this.X_SWIFI_CNSTC_YEAR = X_SWIFI_CNSTC_YEAR;
		this.X_SWIFI_INOUT_DOOR = X_SWIFI_INOUT_DOOR;
		this.X_SWIFI_REMARS3 = X_SWIFI_REMARS3;
		this.LAT = LAT;
		this.LNT = LNT;
		this.WORK_DTTM = WORK_DTTM;
		
	}
	
	
}
