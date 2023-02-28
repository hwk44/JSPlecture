package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCClientH2 {

	public static void main(String[] args) throws Exception {

		// H2에 나와있는 드라이버 주소
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musthave", "musthave", "tiger");

		// 질의를 위한 객체 생성
		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery("select * from board");

		
		//-- 보드 테이블
		System.out.println("------------ 보드 테이블 ------------");
		while (rs.next()) { // 다음 결과 레코드로 이동
			for (int i = 1; i <= 6; i++) {
				if (i != 1)
					System.out.print(", ");
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}
		
		rs = st.executeQuery("select * from member");
		
		//-- member 테이블
		System.out.println("------------ member 테이블 ------------");

		while (rs.next()) { 
			for (int i = 1; i <= 4; i++) {
				if (i != 1)
					System.out.print(", ");
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}
		
			rs.close();
			st.close();
			con.close(); // 자바 가비지 콜렉터가 수거해 가지만 가급적 닫아주어야 함
		
	}
	
}
