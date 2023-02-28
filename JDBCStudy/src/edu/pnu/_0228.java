package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class _0228 {

	public static void main(String[] args) throws Exception {

		// MySQL 접속을 위한 JDBC 드라이버 로드
		// Class.forName Class클래스의 static 매소드인 for Name
		Class.forName("com.mysql.cj.jdbc.Driver");

		// MySQL Database 연결 객체 생성
		// DriverManager 클래스의 getConnection static 메소드
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "musthave", "tiger");

		// 질의를 위한 객체 생성
		Statement st = con.createStatement();

		// SQL 질의
		// 질의 객체를 만들고 executeQuery로 질의를 날려서 질의를 받는게 resultSet 객체
//		ResultSet rs = st.executeQuery("select count(*),countrylanguage.Language"
//										+" from country,countrylanguage"
//										+" where country.Code = countrylanguage.CountryCode and countrylanguage.Language=\'English\'"
//										+" group by countrylanguage.Language");


		
		ResultSet rs = st.executeQuery("select Language "
				+ "from countrylanguage "
				+ "where CountryCode = 'KOR'");
		
		//-- 대한민국이 사용하는 언어
		System.out.println("------------ 대한민국이 사용하는 언어 ------------");
		while (rs.next()) { // 다음 결과 레코드로 이동
			for (int i = 1; i <= 1; i++) {
				if (i != 1)
					System.out.print(",");
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}

		
		rs = st.executeQuery("select count(*), countrylanguage.Language\r\n"
				+ "from country, countrylanguage\r\n"
				+ "where country.Code = countrylanguage.CountryCode and countrylanguage.Language=\"English\"\r\n"
				+ "group by countrylanguage.Language");
		
		//-- English 를 사용하는 나라의 수
		System.out.println("------------ English 를 사용하는 나라의 수 ------------");

		while (rs.next()) { // 다음 결과 레코드로 이동
			for (int i = 1; i <= 2; i++) {
				if (i != 1)
					System.out.print(",");
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}
		
		
		rs = st.executeQuery("select country.Name, country.Continent\r\n"
				+ "from country, countrylanguage\r\n"
				+ "where country.Code = countrylanguage.CountryCode and countrylanguage.Language=\"English\" and countrylanguage.IsOfficial='T'");
		
		System.out.println("------------ English 를 공식언어로 사용하는 나라의 대륙과 이름 ------------");

		while (rs.next()) { // 다음 결과 레코드로 이동
			for (int i = 1; i <= 2; i++) {
				if (i != 1)
					System.out.print(",");
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}
		
		
		rs = st.executeQuery("select count(*), country.Continent\r\n"
				+ "from country, countrylanguage\r\n"
				+ "where country.Code = countrylanguage.CountryCode and countrylanguage.Language=\"English\"\r\n"
				+ "group by country.Continent");
		
		System.out.println("------------ English 를 사용하는 나라의 수를 대륙별로 출력 ------------");

		while (rs.next()) { // 다음 결과 레코드로 이동
			for (int i = 1; i <= 2; i++) {
				if (i != 1)
					System.out.print(",");
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}
		
		
			rs.close();
			st.close();
			con.close(); // 자바 가비지 콜렉터가 수거해 가지만 가급적 닫아주어야 함
		
	}
	
}
