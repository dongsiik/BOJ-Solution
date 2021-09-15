// 제목 : 치즈
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/2636
// 메모리(kb) : 14480
// 실행시간(ms) : 136

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

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 빠른 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 보드 크기 입력받고 보드 초기화
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		board = new int[n][m];

		// 정보 입력받기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 최근에 치즈가 녹아서 사라진 시간
		int time = 0;
		// 최근에 녹아서 사라진 치즈 수
		int CheeseInTime = 0;

		// BFS를 위한 dq
		ArrayDeque<Point> dq = new ArrayDeque<>();
		// 치즈가 없이 공기중에 노출되어있을 0,0부터 시작
		dq.add(new Point(0, 0, 0));

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
						board[nx][ny] = -1;
					}
					// 치즈라면
					else if (board[nx][ny] == 1) {
						// 녹는데 한 시간이 걸리므로 한 시간을 더해서 큐의 뒷부분에 넣어줌
						dq.addLast(new Point(nx, ny, cTime + 1));
						// 지금보다 더 나중에 녹은 치즈라면, 최근에 녹은 치즈 수를 1로 변경
						if (time < cTime + 1) {
							time = cTime + 1;
							CheeseInTime = 1;
						}
						// 아니라면 최근에 녹은 치즈 수 하나 추가
						else if (time == cTime + 1) {
							CheeseInTime++;
						}
						board[nx][ny] = -1;
					}
				}
			}

		}

		// 출력
		System.out.println(time);
		System.out.println(CheeseInTime);

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

}