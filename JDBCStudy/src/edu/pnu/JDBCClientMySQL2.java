package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class JDBCClientMySQL2 {

//	public int getColumnCount(ResultSet rs) throws Exception {
//		return rs.getMetaData().getColumnCount();
//	}

	public int printColumnName(ResultSet rs) throws Exception {
		ResultSetMetaData meta = rs.getMetaData();
		int count = meta.getColumnCount();
		
		StringBuilder sb = new StringBuilder("#");
		for (int i = 1 ; i <= count ; i++) {
			sb.append("," + meta.getColumnName(i));
		}
		System.out.println(sb);
		System.out.println("-".repeat(sb.length()));
		
		return count;
	}
	
	public void StudyStatement(Connection con) throws Exception {

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from country limit 10");

//		printColumnName(rs);
		
		int colCount = printColumnName(rs);
		int rowCount = 1;
		while(rs.next()) {
			for(int i = 1 ; i <= colCount ; i++) {
				if (i == 1)	System.out.print((rowCount++) + ",");
				else		System.out.print(",");
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}
		rs.close();
		st.close();
	}

	public void StudyPrepareStatement(Connection con) throws Exception {
		// prepareStatement where 절이 있을 경우 거의 대부분 사용
		PreparedStatement st = con.prepareStatement("select * from country where code=?");

		st.setString(1, "KOR"); // setString 메소드가 KOR에 ""를 붙여줌
		ResultSet rs = st.executeQuery();

//		printColumnName(rs);
		
		int colCount = printColumnName(rs);
		int rowCount = 1;
		while(rs.next()) {
			for(int i = 1 ; i <= colCount ; i++) {
				if (i == 1)	System.out.print((rowCount++) + ",");
				else		System.out.print(",");
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}
		rs.close();
		st.close();
	}	
	
	public static void main(String[] args) throws Exception  { // 프로그램 진입점
		
		// 클래스 JDBCClientMySQL2 의 인스턴스를 생성
		JDBCClientMySQL2 cli = new JDBCClientMySQL2();

		// driver load
		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "musthave", "tiger");) {
			System.out.println("<=== StudyStatement ===>");
			cli.StudyStatement(con); // StudyStatement 메서드를 찾음
			System.out.println();

			System.out.println("<=== StudyPrepareStatement ===>");
			cli.StudyPrepareStatement(con);
		}		
	}
}
