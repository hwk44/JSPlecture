package edu.pnu;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class JDBCClientMySQL {

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
		ResultSet rs = st.executeQuery("select Name,Continent,Population,HeadOfState from country");

		// 질의 결과 Parsing 
		// cursor Processing
		// 데이터 베이스로 질의를 날리면, 데이터를 한번에 가져오는것이 아니라, 데이터 베이스 안에 질의 결과가 테이블 형태로 만들어져 있음
		// 그런데 rs 객체가 그 테이블을 가리킴 (heap을 가리키듯)
		// cursor Processing 에서는 처음에 빈 공간을 가리키지만
		// rs.next 함수를 호출하게 되면 첫번째 레코드 값을 가져와서 저장하는 것임 
		// 그리고 getString 으로 ~~~ ???
		
		// 순차적으로 next로 테이블 row를 순회 하면서 데이터를 가져옴
		// 마지막 열이 된다면 next 함수가 false를 반환하면서 while문 탈출
		
		while (rs.next()) { // 다음 결과 레코드로 이동
			for (int i = 1; i <= 4; i++) {
				if (i != 1)
					System.out.print(",");
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}
		// 생성된 객체 연결을 모두 해제
		
			rs.close();
			st.close();
			con.close(); // 자바 가비지 콜렉터가 수거해 가지만 가급적 닫아주어야 함
		
	}

}
