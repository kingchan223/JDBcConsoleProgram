package src.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Program4Mysql {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String title = "TEST3333";
		String content = "ha3ha3ha3h";
		String files = "";
		int id = 3;
		
		Connection conn;
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String password = "1";
		String driverName = "com.mysql.cj.jdbc.Driver";
		String sql = "UPDATE newlecture.NOTICE "+
				"SET"+//set 앞에 띄어쓰기 주의!
				"    TITLE=?," +
				"    CONTENT=?," +
				"    FILES=?" +
				"WHERE ID=?";
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, user, password);
		
		PreparedStatement st = conn.prepareStatement(sql);//미리 쿼리문 준비
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id); 

		int result = st.executeUpdate();
		System.out.println(result);

		st.close();
		conn.close();

	}

}
