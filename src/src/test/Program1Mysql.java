package src.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program1Mysql {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection connection;
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String password = "1";
		String driverName = "com.mysql.cj.jdbc.Driver";
		String sql = "select * from newlecture.Notice";//newlecture.Notice필수!!!
		Class.forName(driverName);
		connection = DriverManager.getConnection(url, user, password);
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while(rs.next()) {
			int id = rs.getInt("ID");
			String content = rs.getString("CONTENT");
			String title = rs.getString("TITLE");
			System.out.println("content:"+content);
			System.out.println("title:"+title);
		}
		rs.close();
		st.close();
		connection.close();
	}
}

//class DBConnection {
//	Connection connection;
//	public void connect() throws ClassNotFoundException, SQLException {
//		String url = "jdbc:mysql://localhost:3306/";
//		String user = "root";
//		String password = "dlcksdud12~";
//		String driverName = "com.mysql.cj.jdbc.Driver";
//		String sql = "select * from newlecture.Comment";//newlecture.Comment필수!!!
//		
//			// ① 로드(카카오 택시에 `com.mysql.cj.jdbc.Driver` 라는 실제 택시 드라이버를 등록)
//			// 하지만 개발자는 실제로 `com.mysql.cj.jdbc.Driver`를 다룰 일은 없다.
//			// 내부적으로 JDBC가 알아서 다 해주기 때문에 우리는 JDBC의 DriverManager 를 통해서 DB와의 연결을 얻으면 된다.
//			Class.forName(driverName);
//			// ② 연결
//			connection = DriverManager.getConnection(url, user, password);
//			Statement st = connection.createStatement();
//			ResultSet rs = st.executeQuery(sql);
//			while(rs.next()) {
//				int id = rs.getInt("ID");
//				String content = rs.getString("CONTENT");
//				System.out.println("content:"+content);
//			}
//			rs.close();
//			st.close();
//			connection.close();
//			
//
//	}
//}