
// 제목 : Parity Constraint Shortest Path
// 티어 : 골드 2	
// 링크 : https://www.acmicpc.net/problem/20128
// 메모리(kb) : 150040
// 실행시간(ms) : 1520

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 정점 수 간선 수
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 인접 리스트로 그래프 표현
		ArrayList<Edge>[] graph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}

		// 인덱스를 0부터 하기 위해 1을 빼서 입력받음
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			graph[from].add(new Edge(to, cost));
			graph[to].add(new Edge(from, cost));
		}

		// 짝수, 홀수 경로로 N까지 갈 수 있는 최단거리
		long[][] dist = new long[N][2];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], Long.MAX_VALUE);
		}

		// 시작점은 0의 비용으로 갈 수 있음
		dist[0][0] = 0;

		// 우선순위 큐를 쓰는 다익스트라 알고리즘
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0, 0));

		while (!pq.isEmpty()) {
			Edge curEdge = pq.poll();

			int parity = (int) (curEdge.cost % 2);

			if (dist[curEdge.to][parity] < curEdge.cost) {
				continue;
			}

			for (Edge nextEdge : graph[curEdge.to]) {
				int nextNodeNum = nextEdge.to;
				long nextCost = curEdge.cost + nextEdge.cost;
				int nextParity = (int) (nextCost % 2);
				if (dist[nextNodeNum][nextParity] > nextCost) {
					dist[nextNodeNum][nextParity] = nextCost;
					pq.add(new Edge(nextNodeNum, nextCost));
				}
			}

		}

		// 출력
		for (int i = 0; i < N; i++) {
			for (int j = 1; j >= 0; j--) {
				sb.append((dist[i][j] == Long.MAX_VALUE ? -1 : dist[i][j])).append(' ');
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

	// Comparable을 구현한 Edge 클래스
	static class Edge implements Comparable<Edge> {
		int to;
		long cost;

		public Edge(int to, long cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Long.compare(this.cost, o.cost);
		}

	}
}