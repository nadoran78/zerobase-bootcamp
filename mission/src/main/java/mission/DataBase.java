package mission;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataBase {
	
	public void dbInsert() {
		String filePath = "/Users/gwonjiyeong/dev/git/zerobase-bootcamp/mission/wifi.db";
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        String id = "1";
        String name = "이정창";
        
        try {
            Class.forName ("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + filePath);

            String sql = "INSERT INTO sample (id, name)\n" +
                    "values (?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            

            int affected = preparedStatement.executeUpdate();

            if (affected > 0){
                System.out.println("저장 성공");
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
