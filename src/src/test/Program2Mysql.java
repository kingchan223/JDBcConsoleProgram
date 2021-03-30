package src.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Program2Mysql {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Connection conn;
		String title = "무하마드 알리";
		String writerId = "익명10";
		String content = "세꼐최고의 공격수";
		String files = "";
		
		String url = "jdbc:mysql://localhost:3306/";
		String driverName = "com.mysql.cj.jdbc.Driver";
		String user = "root";
		String password = "1";
		String sql = "INSERT INTO newlecture.NOTICE(	"+
				"    title," +
				"    writer_id," +
				"    content," +
				"    files" +
				
				") VALUES (?,?,?,?)";
		
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, user,password);
		PreparedStatement st = conn.prepareStatement(sql);//미리 쿼리문 준비
		st.setString(1, title);
		st.setString(2, writerId);
		st.setString(3, content);
		st.setString(4, files); 
		
		int result = st.executeUpdate();
		System.out.println(result);
		
		st.close();
		conn.close();
	}
}
