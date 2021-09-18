// 제목 : 토마토
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/7576
// 메모리(kb) : 103260
// 실행시간(ms) : 668

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	// 델타 탐색용 배열
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 상자 크기 입력받아서 상자 만들기
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[][] box = new int[n][m];

		// 안 익은 토마토 수
		int numOfUnripeTomato = 0;
		// 최근에 토마토가 새롭게 익은 날짜
		int day = 0;
		// BFS용 큐
		ArrayDeque<Tomato> q = new ArrayDeque<>();

		// 상자에 토마토 넣기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				// 안 익은 토마토라면 개수를 하나 증가
				if (box[i][j] == 0)
					numOfUnripeTomato++;
				// 익은 토마토라면 BFS용 큐에 넣어줌
				if (box[i][j] == 1) {
					q.add(new Tomato(i, j, 0));
				}
			}
		}

		// BFS
		while (!q.isEmpty()) {
			Tomato t = q.poll();

			// 토마토를 꺼낸 뒤, 최근에 익은 날짜 갱신
			int cx = t.x;
			int cy = t.y;
			int cday = t.dayOfRipening;
			day = Math.max(day, cday);

			// 4방향 델타탐색
			for (int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				// 상자 안이면서 안 익었으면
				if (nx >= 0 && nx < n && ny >= 0 && ny < m && box[nx][ny] == 0) {
					// 안 익은 토마토 개수 하나 감소, 익었다고 표시, 큐에 넣기
					numOfUnripeTomato--;
					box[nx][ny] = 1;
					q.add(new Tomato(nx, ny, cday + 1));
				}
			}
		}

		// 출력
		if (numOfUnripeTomato == 0)
			System.out.println(day);
		else
			System.out.println(-1);
	}

	static class Tomato {
		// x,y좌표, 익은 날짜
		int x;
		int y;
		int dayOfRipening;

		public Tomato(int x, int y, int dayOfRipening) {
			super();
			this.x = x;
			this.y = y;
			this.dayOfRipening = dayOfRipening;
		}

	}
}