// 제목 : 치즈
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/2638
// 메모리(kb) : 15300
// 실행시간(ms) : 204
// isExposed 함수를 만들지 않고 인라인으로 처리하면 15196kb, 172ms가 나옵니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	// 델타 탐색용 변수들과 치즈판
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] board;
	static int n, m;

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 빠른 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 보드 크기 입력받고 보드 초기화
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		board = new int[n][m];

		// 정보 입력받기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 최근에 치즈가 녹아서 사라진 시간
		// 원래 0부터 하는 게 맞겠지만, 어떤 점에 공기가 통하기 시작한 시각을 -time으로 찍어주기 위해 1부터 했습니다.
		int time = 1;

		// BFS를 위한 dq
		ArrayDeque<Point> dq = new ArrayDeque<>();
		// 치즈가 없이 공기중에 노출되어있을 0,0부터 시작
		board[0][0] = -1;
		dq.add(new Point(0, 0, 1));

		while (!dq.isEmpty()) {
			// 점 뽑아오기
			Point curPoint = dq.poll();
			int cx = curPoint.x;
			int cy = curPoint.y;
			int cTime = curPoint.destTime;

			// 주변 4방향에 대해서
			for (int i = 0; i < 4; i++) {
				// 주변 점의 좌표
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
					// 공기라면, 같은 시간으로 큐의 앞부분에 넣어줌
					if (board[nx][ny] == 0) {
						dq.addFirst(new Point(nx, ny, cTime));
						board[nx][ny] = -cTime;
					}
					// 치즈라면
					else if (board[nx][ny] == 1) {
						// 이 시점에서 사방에 공기가 통하는 곳이 두 곳 이상인지 확인
						if (isExposed(nx, ny, cTime)) {
							// 녹는데 한 시간이 걸리므로 한 시간을 더해서 큐의 뒷부분에 넣어줌
							dq.addLast(new Point(nx, ny, cTime + 1));
							// 최근에 치즈가 녹아서 사라진 시간 갱신
							time = Math.max(time, cTime + 1);
							board[nx][ny] = -(cTime + 1);
						}
					}
				}
			}

		}

		// 출력, 0이 아니라 1부터 시간을 셌으므로 1을 빼고 출력
		System.out.println(time - 1);

	}

	// 점마다 좌표와 이 점에 도착한 시간을 저장하는 클래스
	static class Point {
		int x;
		int y;
		int destTime;

		public Point(int x, int y, int destTime) {
			super();
			this.x = x;
			this.y = y;
			this.destTime = destTime;
		}

	}

	// 주변 4방향에 공기가 노출된 방향 수를 세어서 2 이상이면 true를 반환
	static boolean isExposed(int x, int y, int time) {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			// 배열 범위 안이면서, 공기가 통하고, 통한 시각이 현재 시각 이전이어야함
			if (nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] < 0 && time >= -board[nx][ny])
				count++;
		}

		if (count >= 2)
			return true;
		else
			return false;
	}

}