package org.vision.common;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// JDBC 1,2,4 단계를 전담하는 클래스 
public class ConnectionFactory {
	private static Connection conn;
	private static String url;
	private static String user;
	private static String pwd;
	private static DataSource dataFactory;
	// 초기화 블럭 
	static{
		// getConnection() 용 
		url = "jdbc:oracle:thin:@localhost:1521:xe";
		user = "leyla";
		pwd ="leyla";
		// getConnection2() 용 
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 위 초기화 블럭에서 지정한 디폴트한 값 이외의 정보로 세팅시키고자 할때에 사용함 
	// getConnection()용 
	public static void setInfo(String url,String user,String pwd) {
		ConnectionFactory.url =url;
		ConnectionFactory.user =user;
		ConnectionFactory.pwd =pwd;
	}

	public static Connection getConnection() throws SQLException,ClassNotFoundException{
		// throws 와 throw의 차이점을 정리하세요.
		System.out.println("getConnection()");
		try {
			// 1단계 
			Class.forName("oracle.jdbc.driver.OracleDriver"); // ClassNotFoundException throws
			// 2단계 
			conn = DriverManager.getConnection(url,user,pwd);// SQLException throws 
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("오라클 드라이버가 없습니다. ㅠㅠ");
		} catch (SQLException e) {
			throw new SQLException("url,user,pwd 잘못으로 Connection객체를 만들지 못했습니다!ㅠㅠ");
		}
		
		return conn;
	}
	
	
	/* 
	 * 이 메소드를 사용하려면 아파치서버의 context.xml에 아래 내용과 같은 정보가
	 * 들어 있어야 한다.
	 * <Resource
		name="jdbc/oracle"
		auth="Container"
		type="javax.sql.DataSource"
		driverClassName="oracle.jdbc.OracleDriver"
		url="jdbc:oracle:thin:@localhost:1521:XE"
		username="madang"
		password="madang2"
		maxActive="50"
		maxWait="-1"/>  
	 * */
	public static Connection getConnection2() {
		Connection conn = null;
		try {
			conn =  dataFactory.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static void close() {
		try {
			if(conn!=null && !conn.isClosed()) conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
