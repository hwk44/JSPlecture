package 테스트;

import 테스트.genericStack.EmptyGenericStackException;

public class _8queens {

	public static void main(String[] args) throws EmptyGenericStackException {
		int[][] d = new int[8][8];
		solvequeens(d);
	}
	
	

	private static void solvequeens(int[][] a) throws EmptyGenericStackException {
		int x = 0, y = 0;

		int num = 1;
		genericStack st = new genericStack(50); // 포인트를 담는 스택 생성
		Point p = new Point(x, y); // 포인트 생성

		while (p.getY() <= 8) {

			while (x < a.length && y < a.length) {

				// 다음 행 확인
				if (checkrow(a, x)) { // 같은행에 퀸이 없으면 true .같은 행에서 1이 있다면 false

				} else {// 있으면 다음 열
					if (x < a.length) { 
						x++;
						y = 0;
					}
				}
				if (checkcol(a, y) && checkdiagSE(a, x, y) && checkdiagSW(a, x, y)) { // 같은 열에 퀸이 없으면 true
					a[x][y] = 1; // 3조건 만족. 퀸을 넣음 스택 푸시
					st.push(new Point(x, y));
//				st.dump();
				} else { // 대각이나 열에 퀸이 있으면 다음 좌표

					if (y < a.length - 1)
						y++;
					else {
						x = st.peek().getX();
						y = st.peek().getY();
						a[x][y] = 0; // 이전 값에 0 을 넣고 스택 팝
						st.pop();
//					st.dump();
						if (y < a.length - 1)
							y++;
						else {
//						x++; y = 0;
							x = st.peek().getX();
							y = st.peek().getY();
							a[x][y++] = 0; // 이전 값에 0 을 넣고 스택 팝
							st.pop();
//						st.dump();
						}
					}

				}

			}
			if(st.top == 8) {
			System.out.println(num++);
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++) {
					System.out.print(a[i][j] + "   ");
				}
				System.out.println();
			}
			}

			int sum = 0;
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++) {
					sum += a[i][j];
				}
			}
			if (sum != 8)
				System.out.println(sum + "개. 8이 아님--------------------------------------");

			st.dump();

			x = st.peek().getX();
			y = st.peek().getY();
			a[x][y++] = 0; // 이전 값에 0 을 넣고 스택 팝
			st.pop();


		} // while 문 마지막

	} // solveQueen 메서드 마지막

	private static boolean checkrow(int[][] a, int x) {
		// 같은 행에서 1이 있다면 false
		for (int i = 0; i < a.length; i++) {
			if (a[x][i] == 1)
				return false;
		}
		return true; // 없으면 true
	}

	private static boolean checkcol(int[][] a, int y) {
		// 같은 열에서 1이 있다면 false
		for (int i = 0; i < a.length; i++) {
			if (a[i][y] == 1)
				return false;
		}
		return true; // 없으면 true
	}

	private static boolean checkdiagSW(int[][] a, int x, int y) {
		// 대각 성분에 1이 있다면 false
		int row = a.length, col = a.length;
		int ix = x, iy = y;
		while (0 <= ix && ix < row && 0 <= iy && iy < col) { // x 증가 y 감소

			if (a[ix][iy] == 1)
				return false;
			ix++;
			iy--;
		}
		ix = x;
		iy = y;
		while (0 <= ix && ix < row && 0 <= iy && iy < col) {
			if (a[ix][iy] == 1)
				return false;
			ix--;
			iy++;
		}
		return true; // 없으면 true
	}

	private static boolean checkdiagSE(int[][] a, int x, int y) {
		int row = a.length, col = a.length;
		int ix = x, iy = y;
		while (0 <= ix && ix < row && 0 <= iy && iy < col) {
			if (a[ix][iy] == 1)
				return false;
			ix--;
			iy--;
		}
		ix = x;
		iy = y;
		while (0 <= ix && ix < row && 0 <= iy && iy < col) {

			if (a[x][y] == 1)
				return false;
			ix++;
			iy++;
		}
		return true;
	}


}
