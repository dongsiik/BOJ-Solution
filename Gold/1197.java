// 제목 : 최소 스패닝 트리
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/1197
// 메모리(kb) : 56600
// 실행시간(ms) : 632
// PRIM 알고리즘을 사용하였으며, SWEA 3124번과 사실상 같습니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	public static void main(String args[]) throws IOException {

		// 빠른 입출력을 위한 도구들
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 정점 수, 간선 수
		st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		// 방문 여부 배열
		boolean[] visited = new boolean[v + 1];
		// 그래프
		ArrayList<Edge>[] graph = new ArrayList[(int) v + 1];
		for (int i = 0; i <= v; i++)
			graph[i] = new ArrayList<>();

		// 그래프 정보 입력받기
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[start].add(new Edge(end, cost));
			graph[end].add(new Edge(start, cost));
		}

		// 가중치
		long totalCost = 0;
		// 프림 알고리즘을 위한 우선순위 큐
		PriorityQueue<Edge> q = new PriorityQueue<>();
		// 1번 점부터 방문한다고 치고 넣어줌
		q.add(new Edge(1, 0));

		while (!q.isEmpty()) {
			// 점을 하나 꺼냄
			Edge curEdge = q.poll();

			// 점의 번호와 비용 구하기
			int current = curEdge.dest;
			long cost = curEdge.cost;
			// 방문했다면 건너뜀
			if (visited[current])
				continue;

			// 아니라면 방문처리하고, 총 가중치에 더함
			visited[current] = true;
			totalCost += cost;

			// 이 점과 연결된 다른 점들에 대해서
			for (int i = 0; i < graph[current].size(); i++) {
				Edge nextEdge = graph[current].get(i);
				int next = nextEdge.dest;
				long nextCost = nextEdge.cost;
				// 방문하지 않았다면 큐에 넣어줌
				if (!visited[next]) {
					q.add(new Edge(next, nextCost));
				}
			}
		}

		// 출력
		System.out.print(totalCost);
	}

	// 우선순위 큐에 넣기 위해 Comparable을 implements함
	static class Edge implements Comparable<Edge> {
		int dest;
		long cost;

		public Edge(int dest, long cost) {
			super();
			this.dest = dest;
			this.cost = cost;
		}

		// 가까운 정점이 먼저 나오도록 조정
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Long.compare(this.cost, o.cost);
		}

	}
}