
// 제목 : 구슬 탈출 2
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/13460
// 메모리(kb) : 14276
// 실행시간(ms) : 128

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 보드의 크기와 보드
	static int N, M;
	static char[][] board;

	// 빨간 공, 파란 공을 굴린 이후, 각각의 좌표에 이전에 있었는지 확인하는 배열
	static boolean[][][][] visited;

	// 델타 탐색용 배열
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 초기화
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new char[N][M];
		Balls initialBalls = new Balls();
		initialBalls.attempt = 0;

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				char ch = str.charAt(j);
				// 빨간 공과 파란 공은 보드에 표시하는 대신, 좌표를 따로 기록해둠
				switch (ch) {
				case 'R':
					initialBalls.red = new int[] { i, j };
					board[i][j] = '.';
					break;
				case 'B':
					initialBalls.blue = new int[] { i, j };
					board[i][j] = '.';
					break;
				default:
					board[i][j] = ch;
				}
			}

		}

		// 처음 상태를 방문처리하고, 큐에 집어넣음
		visited = new boolean[N][M][N][M];
		initialBalls.visit();
		Queue<Balls> q = new LinkedList<>();
		q.add(initialBalls);

		while (!q.isEmpty()) {
			Balls oldBalls = q.poll();
			// 10번 넘게 굴렸다면 종료
			if (oldBalls.attempt > 10)
				break;

			// 빨간 공만 구멍에 들어갔다면 굴린 횟수 출력 후 종료
			if (oldBalls.goalIn() == 1) {
				System.out.println(oldBalls.attempt);
				return;
			}
			// 파란 공이 들어간 경우는 건너뜀
			if (oldBalls.goalIn() == -1)
				continue;

			// 4방향으로 공을 굴려보고, 전에 처리한 적이 없다면 방문처리하고 큐에 넣음
			for (int d = 0; d < 4; d++) {
				Balls newBalls = new Balls(oldBalls);
				newBalls.roll(d);

				if (newBalls.checkVisited())
					continue;

				newBalls.visit();
				q.add(newBalls);

			}

		}

		// 여기까지 왔다면 공을 넣는 데 실패했음
		System.out.println(-1);

	}

	static class Balls {
		// 굴린 횟수, 빨간 공의 좌표, 파란 공의 좌표
		int attempt;
		int[] red;
		int[] blue;

		// 생성자
		public Balls() {
			super();
		}

		// 다른 공으로 부터 만든 생성자
		public Balls(Balls oldBalls) {
			this.attempt = oldBalls.attempt + 1;
			this.red = Arrays.copyOf(oldBalls.red, 2);
			this.blue = Arrays.copyOf(oldBalls.blue, 2);
		}

		// 구슬 굴리기
		public void roll(int direction) {
			roll(direction, red, blue);
			roll(direction, blue, red);
			roll(direction, red, blue);
		}

		// 구슬 하나 굴리기
		public void roll(int direction, int[] targetBall, int[] anotherBall) {

			while (true) {
				// 공이 구멍에 빠졌다면 종료
				if (board[targetBall[0]][targetBall[1]] == 'O')
					break;

				// 한 칸 옆 위치
				int nx = targetBall[0] + dx[direction];
				int ny = targetBall[1] + dy[direction];

				// 보드 범위 안이고, 다른 공과 부딪힐 일이 없다면 한 칸 이동
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && board[nx][ny] != '#'
						&& (board[nx][ny] == 'O' || !(nx == anotherBall[0] && ny == anotherBall[1]))) {
					targetBall[0] = nx;
					targetBall[1] = ny;
				} else
					break;
			}
		}

		// 파란 공이 들어갔다면 -1, 그렇지 않고 빨간 공이 들어갔다면 1, 둘 다 아니라면 0
		public int goalIn() {
			if (board[blue[0]][blue[1]] == 'O')
				return -1;
			else if (board[red[0]][red[1]] == 'O')
				return 1;
			else
				return 0;

		}

		// 방문 처리
		public void visit() {
			visited[red[0]][red[1]][blue[0]][blue[1]] = true;
		}

		// 방문했는지를 리턴
		public boolean checkVisited() {
			return visited[red[0]][red[1]][blue[0]][blue[1]];
		}

	}
}