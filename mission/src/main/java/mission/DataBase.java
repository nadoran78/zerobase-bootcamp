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
	
	public int dbDeleteZero() {
		int deleteNum = 0;
		
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
            
            String sql = "DELETE FROM PUBLIC_WIFI \n"
            		+ "	WHERE lat=0 and lnt=0;";

            preparedStatement = connection.prepareStatement(sql);
                       

            int affected = preparedStatement.executeUpdate();
        	deleteNum = affected;
            
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
        return deleteNum;
	}
	
	public String dbSelect20(double lat1, double lnt1) {
		String filePath = "/Users/gwonjiyeong/dev/git/zerobase-bootcamp/mission/wifi.db";
		StringBuilder sb = new StringBuilder();

			
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
            
            String sql = "SELECT 6371 * (2* ATAN2(\n"
            		+ "	SQRT(SIN(RADIANS((? - lat)/2))*SIN(RADIANS((? - lat)/2)) \n"
            		+ "	+ COS(RADIANS(?)) * COS(RADIANS(lat)) * SIN(RADIANS((? - lnt)/2))* SIN(RADIANS((? - lnt)/2))), \n"
            		+ "	SQRT(1 - (SIN(RADIANS((? - lat)/2))*SIN(RADIANS((? - lat)/2)) \n"
            		+ "	+ COS(RADIANS(?)) * COS(RADIANS(lat)) * SIN(RADIANS((? - lnt)/2))* SIN(RADIANS((? - lnt)/2))))))\n"
            		+ "	as distance, \n"
            		+ "	num, name, gu, address, detail_address, install_floor, install_type, install_by,"
            		+ " service_type, net_type, install_year, in_outdoor, contact_env, lat, lnt, work_datetime"
            		+ " FROM PUBLIC_WIFI pw \n"
            		+ "	Order by distance \n"
            		+ "	limit 20;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, lat1);
            preparedStatement.setDouble(2, lat1);
            preparedStatement.setDouble(3, lat1);
            preparedStatement.setDouble(4, lnt1);
            preparedStatement.setDouble(5, lnt1);
            preparedStatement.setDouble(6, lat1);
            preparedStatement.setDouble(7, lat1);
            preparedStatement.setDouble(8, lat1);
            preparedStatement.setDouble(9, lnt1);
            preparedStatement.setDouble(10, lnt1);
            
            rs = preparedStatement.executeQuery();
            
            
            while (rs.next()) {
            	Double distance = rs.getDouble("distance");
            	String num = rs.getString("num");
            	String name = rs.getString("name");
            	String gu = rs.getString("gu");
            	String address = rs.getString("address");
            	String detailAddress = rs.getString("detail_address");
            	String installFloor = rs.getString("install_floor");
            	String installType = rs.getString("install_type");
            	String installBy = rs.getString("install_by");
            	String serviceType = rs.getString("service_type");
            	String netType = rs.getString("net_type");
            	String installYear = rs.getString("install_year");
            	String contactEnv = rs.getString("contact_env");
            	String inOutdoor = rs.getString("in_outdoor");
            	String lat = rs.getString("lat");
            	String lnt = rs.getString("lnt");
            	String workDatetime = rs.getString("work_datetime");
            	
            	String html = 
            			"<tr>\n"
            			+ "	<td>" + String.format("%.4f", distance) + "</td>\n"
            			+ "	<td>" + num + "</td>\n"
            			+ "	<td><a href=\"detail.jsp?num="+num+"\">" + name + "</a></td>\n"
            			+ "	<td>" + gu + "</td>\n"
            			+ "	<td>" + address + "</td>\n"
            			+ "	<td>" + detailAddress + "</td>\n"
            			+ "	<td>" + installFloor + "</td>\n"
            			+ "	<td>" + installType + "</td>\n"
            			+ "	<td>" + installBy + "</td>\n"
            			+ "	<td>" + serviceType + "</td>\n"
            			+ "	<td>" + netType + "</td>\n"
            			+ "	<td>" + installYear + "</td>\n"
            			+ "	<td>" + contactEnv + "</td>\n"
            			+ "	<td>" + inOutdoor + "</td>\n"
            			+ "	<td>" + lat + "</td>\n"
            			+ "	<td>" + lnt + "</td>\n"
            			+ "	<td>" + workDatetime + "</td>\n"
            			+ "</tr>\n";
            	
            	
            	sb.append(html);
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
        
        return sb.toString();
	}
	
	public void dbInserHistory(double lat, double lnt) {
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
            
            String sql = "INSERT INTO HISTORY "  + 
					"(lnt, lat, work_datetime) " +
					"values (?, ?, datetime('now','localtime'));";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, lnt);
            preparedStatement.setDouble(2, lat);
            
            

            int affected = preparedStatement.executeUpdate();

            if (affected > 0){
                System.out.println("history 저장 성공");
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
	
	public String dbSelectHistory() {
		String filePath = "/Users/gwonjiyeong/dev/git/zerobase-bootcamp/mission/wifi.db";
		StringBuilder sb = new StringBuilder();

			
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
            
            String sql = "SELECT id, lnt, lat, work_datetime"
            		+ " FROM HISTORY \n"
            		+ "	Order by id DESC ;";
            		

            preparedStatement = connection.prepareStatement(sql);
            
            
            rs = preparedStatement.executeQuery();
            
            
            while (rs.next()) {
            	String id = rs.getString("id");
            	String lnt = rs.getString("lnt");
            	String lat = rs.getString("lat");
            	String workDatetime = rs.getString("work_datetime");
                   	
            	String html = 
            			"<tr>\n"
		     			+ "	<td>" + id + "</td>\n"
            			+ "	<td>" + lnt + "</td>\n"
            			+ "	<td>" + lat + "</td>\n"
            			+ "	<td>" + workDatetime + "</td>\n"
            			+ "	<td style=\"text-align:center\"><button type =\"button\" onclick=\"location.href='history.jsp?id="+id+"'\">삭제</button></td>\n"
            			+ "</tr>\n";
            	
            	
            	sb.append(html);
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
        
        return sb.toString();
	}
	
	public void dbDeleteHistory(String id) {
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
            
            String sql = "DELETE FROM HISTORY\n"
            		+ "WHERE id=?;";
            		
        	int idNum = Integer.parseInt(id);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idNum);
            
            
            int affected = preparedStatement.executeUpdate();
            
            
            if (affected > 0){
                System.out.println("history 삭제 성공");
            } else {
                System.out.println("삭제 실패");
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
	
	public String dbSelectDetail(String num) {
		String filePath = "/Users/gwonjiyeong/dev/git/zerobase-bootcamp/mission/wifi.db";
		StringBuilder sb = new StringBuilder();

			
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
            
            String sql = "SELECT \"0.0000\" as distance,\n"
            		+ "	num, gu, name, address, detail_address , install_floor , install_type , \n"
            		+ "	install_by , service_type , net_type , install_year , in_outdoor , contact_env , \n"
            		+ "	lat, lnt, work_datetime \n"
            		+ "	FROM PUBLIC_WIFI\n"
            		+ "	WHERE num=? ;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, num);
            
            
            rs = preparedStatement.executeQuery();
            
            
            while (rs.next()) {
            	String distance = rs.getString("distance");
            	String manageNum = rs.getString("num");
            	String name = rs.getString("name");
            	String gu = rs.getString("gu");
            	String address = rs.getString("address");
            	String detailAddress = rs.getString("detail_address");
            	String installFloor = rs.getString("install_floor");
            	String installType = rs.getString("install_type");
            	String installBy = rs.getString("install_by");
            	String serviceType = rs.getString("service_type");
            	String netType = rs.getString("net_type");
            	String installYear = rs.getString("install_year");
            	String contactEnv = rs.getString("contact_env");
            	String inOutdoor = rs.getString("in_outdoor");
            	String lat = rs.getString("lat");
            	String lnt = rs.getString("lnt");
            	String workDatetime = rs.getString("work_datetime");
            	            	
            	String html = 
            			"<tr>\n"
            			+ "	<td>" + distance + "</td>\n"
            			+ "	<td>" + manageNum + "</td>\n"
            			+ "	<td>" + gu + "</td>\n"
            			+ "	<td><a href=\"detail.jsp?num="+num+"\">" + name + "</a></td>\n"
            			+ "	<td>" + address + "</td>\n"
            			+ "	<td>" + detailAddress + "</td>\n"
            			+ "	<td>" + installFloor + "</td>\n"
            			+ "	<td>" + installType + "</td>\n"
            			+ "	<td>" + installBy + "</td>\n"
            			+ "	<td>" + serviceType + "</td>\n"
            			+ "	<td>" + netType + "</td>\n"
            			+ "	<td>" + installYear + "</td>\n"
            			+ "	<td>" + contactEnv + "</td>\n"
            			+ "	<td>" + inOutdoor + "</td>\n"
            			+ "	<td>" + lat + "</td>\n"
            			+ "	<td>" + lnt + "</td>\n"
            			+ "	<td>" + workDatetime + "</td>\n"
            			+ "</tr>\n";
            	
            	
            	sb.append(html);
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
        
        return sb.toString();
	}

	public String dbSelectBookMarkGroup() {
		String filePath = "/Users/gwonjiyeong/dev/git/zerobase-bootcamp/mission/wifi.db";
		StringBuilder sb = new StringBuilder();
		String html = 
    			"<tr>"
    			+ "	<td style=\"text-align:center\" colspan=\"6\">정보가 존재하지 않습니다.</td>"
				+ "</tr>";

			
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
            
            String sql = "SELECT id, name, num, register_datetime, "
            		+ "IFNULL(update_datetime, \"\") as update_datetime"
            		+ " FROM BOOKMARK_GROUP \n"
            		+ "	Order by num ;";
            		

            preparedStatement = connection.prepareStatement(sql);
            
            
            rs = preparedStatement.executeQuery();
                                
            
            while (rs.next()) {
            	String id = rs.getString("id");
            	String name = rs.getString("name");
            	String num = rs.getString("num");
            	String registerDatetime = rs.getString("register_datetime");
            	String updateDatetime = rs.getString("update_datetime");
            	
            	String result = 
            			"<tr>\n"
		     			+ "	<td>" + id + "</td>\n"
            			+ "	<td>" + name + "</td>\n"
            			+ "	<td>" + num + "</td>\n"
            			+ "	<td>" + registerDatetime + "</td>\n"
            			+ "	<td>" + updateDatetime + "</td>\n"
            			+ "	<td style=\"text-align:center\">"
            			+ "		<a href=\"bookmark-group-update.jsp?id="+id+"\">수정</a> "
            			+ "		<a href=\"bookmark-group-delete.jsp?id="+id+"\">삭제</a>"
    					+ "	</td>\n"
            			+ "</tr>\n";
            	
            	
            	sb.append(result);
            }
        	
            if (sb.length() != 0) {
            	html = sb.toString();	
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
        
        return html;
	}

	public void dbInsertBookMarkGroup(String name, String num) {
		String filePath = "/Users/gwonjiyeong/dev/git/zerobase-bootcamp/mission/wifi.db";
		int number = Integer.parseInt(num);
			
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
            
            String sql = "INSERT INTO BOOKMARK_GROUP "  + 
					"(name, num, register_datetime) " +
					"values (?, ?, datetime('now','localtime'));";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, number);
            
            

            int affected = preparedStatement.executeUpdate();

            if (affected > 0){
                System.out.println("북마크 그룹 저장 성공");
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

	public void dbUpdateBookMarkGroup(String id, String name, String num) {
		String filePath = "/Users/gwonjiyeong/dev/git/zerobase-bootcamp/mission/wifi.db";
		int idNum = Integer.parseInt(id);
		int number = Integer.parseInt(num);
			
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
            
            String sql = "UPDATE BOOKMARK_GROUP\n"
            		+ "     SET name = ?,\n"
            		+ "         num = ?,\n"
            		+ "         update_datetime = datetime('now','localtime')\n"
            		+ "     WHERE id = ?;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, number);
            preparedStatement.setInt(3, idNum);
            
            

            int affected = preparedStatement.executeUpdate();

            if (affected > 0){
                System.out.println("북마크 그룹 수정 성공");
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
	
	public String[] dbSelectOneBookMarkGroup(String id) {
		String filePath = "/Users/gwonjiyeong/dev/git/zerobase-bootcamp/mission/wifi.db";
		String[] result = new String[2];
		int idNum = Integer.parseInt(id);
					
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
            
            String sql = "SELECT name, num"
            		+ " FROM BOOKMARK_GROUP \n"
            		+ "	WHERE id=? ;";
            		

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idNum);
            
            
            rs = preparedStatement.executeQuery();
                                
            
            while (rs.next()) {
            	
            	String name = rs.getString("name");
            	String num = rs.getString("num");
            	
            	result[0] = name;
            	result[1] = num;
                      
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
        
        return result;
	}
	
	public void dbDeleteBookMarkGroup(String id) {
		String filePath = "/Users/gwonjiyeong/dev/git/zerobase-bootcamp/mission/wifi.db";
		int idNum = Integer.parseInt(id);
				
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
            
            String sql = "DELETE FROM BOOKMARK_GROUP\n"
            		+ "	WHERE id = ?;";

            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, idNum);
            
            

            int affected = preparedStatement.executeUpdate();

            if (affected > 0){
                System.out.println("북마크 그룹 삭제 성공");
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
	
	public String dbBookMarkGroupName() {
		String filePath = "/Users/gwonjiyeong/dev/git/zerobase-bootcamp/mission/wifi.db";
		StringBuilder sb = new StringBuilder();
		
			
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
            
            String sql = "SELECT name"
                    + " FROM BOOKMARK_GROUP \n"
            		+ "	Order by num ;";
            		

            preparedStatement = connection.prepareStatement(sql);
            
            
            rs = preparedStatement.executeQuery();
                                
            
            while (rs.next()) {
                String name = rs.getString("name");
            	
            	String result = 
            			"<option>" + name + "</option>";
            	
            	
            	sb.append(result);
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
        
        return sb.toString();
	}
	
	public void dbInsertBookMark(String group, String num) {
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
            
            String sql = "INSERT INTO BOOKMARK \n"
            		+ "	(group_name, name, register_datetime, wifi_num, group_id)\n"
            		+ "	values ( ?, \n"
            		+ "		(SELECT name FROM PUBLIC_WIFI WHERE num = ?), \n"
            		+ "		datetime('now','localtime'),"
            		+ "		? ,"
            		+ "		(SELECT id FROM BOOKMARK_GROUP WHERE name = ?));";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, group);
            preparedStatement.setString(2, num);
            preparedStatement.setString(3, num);
            preparedStatement.setString(4, group);
            

            int affected = preparedStatement.executeUpdate();

            if (affected > 0){
                System.out.println("북마크 저장 성공");
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

	public void dbUpdateBookMark(String id, String name) {
		String filePath = "/Users/gwonjiyeong/dev/git/zerobase-bootcamp/mission/wifi.db";
		int idNum = Integer.parseInt(id);
			
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
            
            String sql = "UPDATE BOOKMARK \n"
            		+ "	SET group_name = ? \n"
            		+ "	WHERE group_name = \n"
            		+ "	(SELECT name From BOOKMARK_GROUP \n"
            		+ "	WHERE id=?);";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, idNum);
            
            

            int affected = preparedStatement.executeUpdate();

            if (affected > 0){
                System.out.println("북마크 수정 성공");
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
	
	public String dbSelectBookMark() {
		String filePath = "/Users/gwonjiyeong/dev/git/zerobase-bootcamp/mission/wifi.db";
		StringBuilder sb = new StringBuilder();
		String html = 
    			"<tr>"
    			+ "	<td style=\"text-align:center\" colspan=\"6\">정보가 존재하지 않습니다.</td>"
				+ "</tr>";

			
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
            
            String sql = "SELECT id, group_name, name, register_datetime, wifi_num "
            		+ " FROM BOOKMARK \n"
            		+ "	Order by id ;";
            		

            preparedStatement = connection.prepareStatement(sql);
            
            
            rs = preparedStatement.executeQuery();
                                
            
            while (rs.next()) {
            	String id = rs.getString("id");
            	String groupName = rs.getString("group_name");
            	String name = rs.getString("name");
            	String num = rs.getString("wifi_num");
            	String registerDatetime = rs.getString("register_datetime");
            	            	
            	String result = 
            			"<tr>\n"
		     			+ "	<td>" + id + "</td>\n"
            			+ "	<td>" + groupName + "</td>\n"
            			+ "	<td><a href=\"detail.jsp?num="+num+"\">" + name + "</a></td>\n"
            			+ "	<td>" + registerDatetime + "</td>\n"
            			+ "	<td style=\"text-align:center\">"
            			+ "		<a href=\"bookmark-delete.jsp?id="+id+"\">삭제</a>"
    					+ "	</td>\n"
            			+ "</tr>\n";
            	
            	
            	sb.append(result);
            }
        	
            if (sb.length() != 0) {
            	html = sb.toString();	
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
        
        return html;
	}
	
	public String[] dbSelectOneBookMark(String id) {
		String filePath = "/Users/gwonjiyeong/dev/git/zerobase-bootcamp/mission/wifi.db";
		String[] result = new String[3];
		int idNum = Integer.parseInt(id);
					
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
            
            String sql = "SELECT group_name, name, register_datetime"
            		+ " FROM BOOKMARK \n"
            		+ "	WHERE id=? ;";
            		

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idNum);
            
            
            rs = preparedStatement.executeQuery();
                                
            
            while (rs.next()) {
            	String groupName = rs.getString("group_name");
            	String name = rs.getString("name");
            	String registerDatetime = rs.getString("register_datetime");
            	
            	result[0] = groupName;
            	result[1] = name;
            	result[2] = registerDatetime;
                      
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
        
        return result;
	}
	
	public void dbDeleteBookMark(String id) {
		String filePath = "/Users/gwonjiyeong/dev/git/zerobase-bootcamp/mission/wifi.db";
		int idNum = Integer.parseInt(id);
				
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
            
            String sql = "DELETE FROM BOOKMARK\n"
            		+ "	WHERE id = ?;";

            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, idNum);
            
            

            int affected = preparedStatement.executeUpdate();

            if (affected > 0){
                System.out.println("북마크 삭제 성공");
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
	
	public void dbDeleteBookMark2(String name) {
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
            
            String sql = "DELETE FROM BOOKMARK\n"
            		+ "	WHERE group_name = ?;";

            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, name);
            
            

            int affected = preparedStatement.executeUpdate();

            if (affected > 0){
                System.out.println("북마크 삭제 성공");
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
