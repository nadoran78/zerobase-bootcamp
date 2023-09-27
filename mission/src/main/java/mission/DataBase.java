package mission;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class DataBase {
	
	
	
	public void dbInsert(EachWifi eachWifi) {
		String filePath = "/Users/gwonjiyeong/dev/git/zerobase-bootcamp/mission/wifi.db";
		
			
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
       
        
        try {
            Class.forName ("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        
        
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + filePath);
            
            String sql = "INSERT INTO PUBLIC_WIFI "  + 
					"(num, gu, name, address, detail_address, install_floor, install_type, " +
					"install_by, service_type, net_type, install_year, in_outdoor, contact_env, " +
					"lat, lnt, work_datetime) " +
					"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, eachWifi.getX_SWIFI_MGR_NO());
            preparedStatement.setString(2, eachWifi.getX_SWIFI_WRDOFC());
            preparedStatement.setString(3, eachWifi.getX_SWIFI_MAIN_NM());
            preparedStatement.setString(4, eachWifi.getX_SWIFI_ADRES1());
            preparedStatement.setString(5, eachWifi.getX_SWIFI_ADRES2());
            preparedStatement.setString(6, eachWifi.getX_SWIFI_INSTL_FLOOR());
            preparedStatement.setString(7, eachWifi.getX_SWIFI_INSTL_TY());
            preparedStatement.setString(8, eachWifi.getX_SWIFI_INSTL_MBY());
            preparedStatement.setString(9, eachWifi.getX_SWIFI_SVC_SE());
            preparedStatement.setString(10, eachWifi.getX_SWIFI_CMCWR());
            preparedStatement.setString(11, eachWifi.getX_SWIFI_CNSTC_YEAR());
            preparedStatement.setString(12, eachWifi.getX_SWIFI_INOUT_DOOR());
            preparedStatement.setString(13, eachWifi.getX_SWIFI_REMARS3());
            preparedStatement.setDouble(14, eachWifi.getLAT());
            preparedStatement.setDouble(15, eachWifi.getLNT());
            preparedStatement.setString(16, eachWifi.getWORK_DTTM());
            

            int affected = preparedStatement.executeUpdate();

            if (affected > 0){
                //System.out.println("저장 성공");
            } else {
                System.out.println("저장 실패");
            }




        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (Exception e) {
            	e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (connection!= null && !connection.isClosed()){
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
}
