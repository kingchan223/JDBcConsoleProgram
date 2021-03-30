package src.com.newlecture.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import src.com.newlecture.app.entity.Notice;

public class NoticeService {
	private String url = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String password = "1";
	private String driverName = "com.mysql.cj.jdbc.Driver";
	
	public List<Notice> getList(int page, String feild, String query) throws ClassNotFoundException, SQLException{
		
		int start =  (page-1)*10;     // 0,  10, 20,
		int end =     start+10;       // 10, 20, 30, 

		Connection conn;
		
		String sql = "select * from newlecture.NOTICE_VIEW WHERE "+feild+" LIKE ? LIMIT ?, ?";
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, user, password);
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, "%"+query+"%");
		st.setInt(2, start);
		st.setInt(3, end);
		ResultSet rs = st.executeQuery();         

		List<Notice> list = new ArrayList<Notice>();

		while(rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content = rs.getString("CONTENT");
			int hit = rs.getInt("HIT");
			String files = rs.getString("FILES");
			Notice notice = new Notice(
								id,
								title,
								writerId,
								regDate,
								content,
								hit,
								files
					);
			list.add(notice);
		}
		rs.close();
		st.close();
		conn.close();
		
		return list;
	}
	
	public int getCount() throws ClassNotFoundException, SQLException {
		
		int count = 0;
		
		Connection conn;
		
		String sql = "select count(ID) COUNT from newlecture.NOTICE;";
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, user, password);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);         

		List<Notice> list = new ArrayList<Notice>();

		if(rs.next())
			count = rs.getInt("COUNT");

		rs.close();
		st.close();
		conn.close();

		return count;
	}
	
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		Connection conn;
		String title = notice.getTitle();
		String writerId = notice.getWriterId();
		String content = notice.getContent();
		String files = notice.getFiles();
		
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
		
		st.close();
		conn.close();
		return result;
	}
	
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		String title = notice.getTitle();
		String content = notice.getContent();
		String files = notice.getFiles();
		int id = notice.getId();
		
		Connection conn;

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
		st.close();
		conn.close();
		return result;
	}
	
	public int delete(int id) throws ClassNotFoundException, SQLException {
		
		Connection conn;

		String sql = "DELETE FROM newlecture.NOTICE WHERE ID=?";
		
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, user,password);
		PreparedStatement st = conn.prepareStatement(sql);//미리 쿼리문 준비
		st.setInt(1, id);

		
		int result = st.executeUpdate();
		System.out.println(result);
		
		st.close();
		conn.close();
		return result;
	}
	
	public Notice getPost(int postId) throws ClassNotFoundException, SQLException{

		Connection conn;
		
		
		String sql = "select * from newlecture.NOTICE_VIEW WHERE ID=?";
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, user, password);
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, postId);
		ResultSet rs = st.executeQuery();         

		Notice post = new Notice();

		while(rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content = rs.getString("CONTENT");
			int hit = rs.getInt("HIT");
			String files = rs.getString("FILES");
			post = new Notice(
								id,
								title,
								writerId,
								regDate,
								content,
								hit,
								files
					);
		}
		rs.close();
		st.close();
		conn.close();
		
		return post;
	}
	public void upHit(int postId, int hit) throws ClassNotFoundException, SQLException {
		int id = 3;
		
		Connection conn;
		String sql = "UPDATE newlecture.NOTICE "+
				"SET NOTICE.HIT=? "+
				"WHERE ID=?";
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, user, password);
		
		PreparedStatement st = conn.prepareStatement(sql);//미리 쿼리문 준비
		st.setInt(1, hit+1);
		st.setInt(2, postId);
	
		int result = st.executeUpdate();
		System.out.println(result);
	
		st.close();
		conn.close();
	}
}
