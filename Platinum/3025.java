// 제목 : 돌 던지기
// 티어 : 플래티넘 5
// 링크 : https://www.acmicpc.net/problem/3025
// 메모리(kb) : 73124
// 실행시간(ms) : 620

// 경로를 저장할 때 Stack보다 ArrayDeque가 빠르고, ArrayDeque보다는 row에 따른 column 위치를 담은 배열이 더 빠릅니다.
// 다만 고치기 귀찮아서 ArrayDeque로 작성한 코드를 올립니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	// 보드의 크기와 보드
	static int R, C;
	static char[][] map;
	// 시작하는 열마다 최근 경로를 저장해서 빠르게 경로를 탐색하기 위한 스택
	static ArrayDeque<int[]>[] paths;

	public static void main(String[] args) throws IOException {
		// 입력도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 초기화
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][];
		paths = new ArrayDeque[C];
		for (int i = 0; i < C; i++)
			paths[i] = new ArrayDeque<>(R);

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		// 돌 놓는 열 입력받아서 던지기
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int columnNum = Integer.parseInt(br.readLine()) - 1;
			start(columnNum);
		}

		// 보드 출력
		printMap();
	}

	// 돌을 던지는 열 번호
	static void start(int columnNum) {
		// 그 번호 경로가 비어있다면, 0행 columnNum열부터 돌을 던지기 시작
		if (paths[columnNum].isEmpty()) {
			putStone(0, columnNum, columnNum);
		}
		// 이미 한번 놓아본 곳이라면
		else {
			// 최근 경로를 먼 곳부터 보면서 장애물이나 돌이 있는 곳을 빼고, 빈 공간부터 시작
			while (map[paths[columnNum].peekFirst()[0]][paths[columnNum].peekFirst()[1]] != '.')
				paths[columnNum].pop();

			int[] currentPosition = paths[columnNum].pop();
			putStone(currentPosition[0], currentPosition[1], columnNum);
		}
	}

	// columnNum에서 떨어뜨린 돌이 지금 row행 column열에 있을 때
	private static void putStone(int row, int column, int startColumnNum) {
		// 경로에 저장
		paths[startColumnNum].addFirst(new int[] { row, column });

		// 아래칸이 벽이거나 장애물이면 돌을 놓음
		if (row + 1 == R || map[row + 1][column] == 'X') {
			map[row][column] = 'O';
		}
		// 아래칸이 비어있다면 아래로 이동하며 재귀함수 호출
		else if (map[row + 1][column] == '.') {
			putStone(row + 1, column, startColumnNum);
		}
		// 왼쪽 아래, 왼쪽옆이 비어있다면 왼쪽 아래로 돌을 보냄
		else if (column > 0 && map[row][column - 1] == '.' && map[row + 1][column - 1] == '.') {
			putStone(row + 1, column - 1, startColumnNum);
		}
		// 오른쪽 아래, 오른쪽 옆이 비어있다면 오른쪽 아래로 돌을 보냄
		else if (column < C - 1 && map[row][column + 1] == '.' && map[row + 1][column + 1] == '.') {
			putStone(row + 1, column + 1, startColumnNum);
		}
		// 그 밖에는 돌을 놓음
		else {
			map[row][column] = 'O';
		}

	}

	// 보드 출력
	static void printMap() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb);

	}
}