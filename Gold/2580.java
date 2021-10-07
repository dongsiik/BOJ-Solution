// 제목 : 스도쿠
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/2580
// 메모리(kb) : 18900
// 실행시간(ms) : 300

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] sudoku; // 스도쿠판
	static boolean[][] garo;// garo[rowNum][value]는 rowNum번 행에 value가 있으면 true, 아니면 false
	static boolean[][] sero;// sero[columnNum][value]는 columnNum번 열에 value가 있으면 true, 아니면 false
	static boolean[][] nemo;// nemo[nemoNum][value]는 nemoNum번 작은 사각형에 value가 있으면 true, 아니면 false

	public static void main(String[] args) throws IOException {

		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		sudoku = new int[9][9];
		garo = new boolean[9][10];
		sero = new boolean[9][10];
		nemo = new boolean[9][10];

		// 입력받기
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				int value = Integer.parseInt(st.nextToken());
				sudoku[i][j] = value;
				if (value != 0) {
					garo[i][value] = true;
					sero[j][value] = true;
					nemo[(i / 3) * 3 + (j / 3)][value] = true;
				}
			}
		}

		// 왼쪽 위부터 검사
		dfs(0);

	}

	// 재귀. 한 군데라도 성공하면 연쇄적으로 true가 반환되며 다른 경우를 시험하지 않음
	static boolean dfs(int level) {
		// 다 채웠다면 출력하고 true 반환
		if (level == 81) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(sudoku[i][j]).append(' ');
				}
				sb.append('\n');
			}

			System.out.println(sb);
			return true;
		}

		// 현재 보려는 행과 열의 번호
		int currentRow = level / 9;
		int currentColumn = level % 9;

		// 이미 표시된 칸이라면 생략
		if (sudoku[currentRow][currentColumn] != 0) {
			if (dfs(level + 1))
				return true;
		}
		// 빈 칸이라면
		else {
			for (int value = 1; value <= 9; value++) {
				// 1부터 9까지의 값 중 어떤 값을 놓을 수 있다면
				if (dupCheck(currentRow, currentColumn, value)) {
					// 놓고, 다음 단계를 시험해보고, 놓은 걸 지움
					setSudoku(currentRow, currentColumn, value);
					// printSudoku();
					if (dfs(level + 1))
						return true;

					unsetSudoku(currentRow, currentColumn, value);
				}
			}

		}

		return false;
	}

	// 행과 열, 사각형을 보며 숫자가 중복되지 않았다면 true, 아니라면 false
	static boolean dupCheck(int currentRow, int currentColumn, int value) {
		if (!garo[currentRow][value] && !sero[currentColumn][value]
				&& !nemo[(currentRow / 3) * 3 + currentColumn / 3][value])
			return true;

		return false;
	}

	// 스도쿠에 숫자 쓰기
	static void setSudoku(int currentRow, int currentColumn, int value) {
		garo[currentRow][value] = true;
		sero[currentColumn][value] = true;
		nemo[(currentRow / 3) * 3 + currentColumn / 3][value] = true;
		sudoku[currentRow][currentColumn] = value;
	}

	// 스도쿠에 숫자 지우기
	static void unsetSudoku(int currentRow, int currentColumn, int value) {
		garo[currentRow][value] = false;
		sero[currentColumn][value] = false;
		nemo[(currentRow / 3) * 3 + currentColumn / 3][value] = false;
		sudoku[currentRow][currentColumn] = 0;
	}

	// 디버깅용 스도쿠 출력 함수
	static void printSudoku() {
		System.out.println("----------------------------------");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(sudoku[i][j]);
			}
			System.out.println();
		}
		System.out.println("----------------------------------");
	}
}