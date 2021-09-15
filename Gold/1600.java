// 제목 : 말이 되고픈 원숭이
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/1600
// 메모리(kb) : 59004
// 실행시간(ms) : 540

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	// 말처럼 움직일 수 있는 횟수, 격자판의 가로, 세로 길이
	static int k, w, h;
	// 격자판
	static int[][] board;

	public static void main(String[] args) throws NumberFormatException, IOException {

		input();
		bfs();

	}

	static void input() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		board = new int[h][w];
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void bfs() {
		// 0~3번까지는 사방으로 한 칸, 4~는 말처럼 움직이기
		int[] dx = { -1, 1, 0, 0, -2, -2, 2, 2, -1, -1, 1, 1 };
		int[] dy = { 0, 0, -1, 1, -1, 1, -1, 1, -2, 2, -2, 2 };
		// h행 w열에 말처럼 k번 움직여서 방문한 적이 있었는가
		boolean[][][] visited = new boolean[h][w][k + 1];

		// BFS용 큐
		ArrayDeque<Point> q = new ArrayDeque<>();
		visited[0][0][0] = true;
		q.add(new Point(0, 0, 0, 0));

		while (!q.isEmpty()) {
			// 현재 지점 정보
			Point currentPoint = q.poll();
			int cx = currentPoint.x;
			int cy = currentPoint.y;
			int ctime = currentPoint.time;
			int cHorseMove = currentPoint.horseMove;
			// 도착지라면 시간을 출력하고 종료
			if (cx == h - 1 && cy == w - 1) {
				System.out.println(ctime);
				return;
			}

			for (int i = 0; i < 12; i++) {
				// 다음 지점과 말처럼 움직인 횟수
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				int nHorseMove = (i >= 4) ? cHorseMove + 1 : cHorseMove;
				// 배열 범위 안이고, 말처럼 움직이는 횟수 제한 이내이고, 도착지가 평지이며, 아직 이 상태로 방문하지 않았다면
				if (nx >= 0 && nx < h && ny >= 0 && ny < w && nHorseMove <= k && board[nx][ny] == 0
						&& !visited[nx][ny][nHorseMove]) {
					// 방문처리하고 큐에 넣어줌
					visited[nx][ny][nHorseMove] = true;
					q.add(new Point(nx, ny, ctime + 1, nHorseMove));
				}
			}
		}

		// 여기까지 왔다면 목적지까지 도착 못 한 것이므로 -1 출력
		System.out.println(-1);
		return;
	}

	// 현재 점의 좌표와, 몇 번 움직였고, 그 중 말처럼은 몇 번 움직였는지 나타내는 배열
	static class Point {
		int x;
		int y;
		int time;
		int horseMove;

		public Point(int x, int y, int time, int horseMove) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
			this.horseMove = horseMove;
		}

	}
}