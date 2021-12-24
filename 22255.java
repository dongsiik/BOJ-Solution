
// 제목 : 호석사우루스
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/22255
// 메모리(kb) : 16968
// 실행시간(ms) : 212

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	// 미궁의 크기와 미궁
	static int N, M;
	static int[][] map;

	// 이동할 수 있는 방향
	static int[][] dr = { { -1, 1 }, { 0, 0 }, { -1, 1, 0, 0 } };
	static int[][] dc = { { 0, 0 }, { -1, 1 }, { 0, 0, -1, 1 } };

	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		// 시작점과 도착점
		st = new StringTokenizer(br.readLine());
		int Sr = Integer.parseInt(st.nextToken()) - 1;
		int Sc = Integer.parseInt(st.nextToken()) - 1;
		int Er = Integer.parseInt(st.nextToken()) - 1;
		int Ec = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 다익스트라 알고리즘 O((V+E) log E)
		// 좌표와 이동방식 별로 총 비용을 저장함
		int[][][] cost = new int[N][M][3];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < 3; k++) {
					cost[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(Sr, Sc, 0, 0));

		cost[Sr][Sc][0] = 0;

		while (!pq.isEmpty()) {
			Edge currentEdge = pq.poll();

			// 이미 이 지점에 같은 방식으로 방문했다면 생략
			if (cost[currentEdge.r][currentEdge.c][currentEdge.mode] < currentEdge.cost)
				continue;

			// 도착점이라면 비용 출력 후 종료
			if (currentEdge.r == Er && currentEdge.c == Ec) {
				System.out.println(currentEdge.cost);
				return;
			}

			// 이동할 수 있는 방향에 대해서
			for (int d = 0; d < dr[currentEdge.mode].length; d++) {
				// 다음 방향과, 다음에 이동할 방법
				int nr = currentEdge.r + dr[currentEdge.mode][d];
				int nc = currentEdge.c + dc[currentEdge.mode][d];
				int nMode = (currentEdge.mode + 1) % 3;
				// 미궁 밖이나 벽이 아니라면
				if (canGo(nr, nc)) {
					// 비용을 계산해서 방문할 필요가 있는지 따짐
					int nCost = currentEdge.cost + map[nr][nc];
					if (cost[nr][nc][nMode] > nCost) {
						cost[nr][nc][nMode] = nCost;
						pq.offer(new Edge(nr, nc, nMode, nCost));
					}

				}
			}
		}

		// 여기까지 왔으면 실패이므로 -1 출력
		System.out.println(-1);

	}

	// 미궁 밖이나 벽인지 판정해주는 함수
	static boolean canGo(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != -1)
			return true;
		else
			return false;
	}

	// 좌표, 다음 이동방식, 지금까지의 비용을 저장하고, 우선순위 큐를 위해 Comparable을 구현한 점 클래스
	static class Edge implements Comparable<Edge> {
		int r, c;
		int mode;
		int cost;

		public Edge(int r, int c, int mode, int cost) {
			this.r = r;
			this.c = c;
			this.mode = mode;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

}