package src.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Program3Mysql {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		int id = 3;
		
		Connection conn;

		String url = "jdbc:mysql://localhost:3306/";
		String driverName = "com.mysql.cj.jdbc.Driver";
		String user = "root";
		String password = "1";
		String sql = "DELETE FROM newlecture.NOTICE WHERE ID=?";
		
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, user,password);
		PreparedStatement st = conn.prepareStatement(sql);//미리 쿼리문 준비
		st.setInt(1, id);

		
		int result = st.executeUpdate();
		System.out.println(result);
		
		st.close();
		conn.close();
	}
}
