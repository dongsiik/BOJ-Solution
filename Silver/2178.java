
// 제목 : 미로 탐색
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/2178
// 메모리(kb) : 14776
// 실행시간(ms) : 136

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	// 델타탐색용 배열
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 정보 입력받기
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		// BFS
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 1));

		while (!q.isEmpty()) {
			Node curNode = q.poll();

			if (curNode.x == N - 1 && curNode.y == M - 1) {
				System.out.println(curNode.time);
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nx = curNode.x + dx[d];
				int ny = curNode.y + dy[d];

				if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 1) {
					map[nx][ny] = 0;
					q.add(new Node(nx, ny, curNode.time + 1));
				}
			}
		}

	}

	// 점의 좌표와 방문한 시각을 나타내는 Node 클래스
	static class Node {
		int x, y, time;

		public Node(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

	}
}