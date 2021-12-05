
// 제목 : 파티
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/1238
// 메모리(kb) : 19960
// 실행시간(ms) : 228

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	// 학생 수, 도로 수, 주최자 번호
	static int N, M, X;

	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		// 인접 리스트로 그래프 받기
		ArrayList<Edge>[] graphToParty = new ArrayList[N + 1];
		ArrayList<Edge>[] graphFromParty = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			graphToParty[i] = new ArrayList<>();
			graphFromParty[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			graphFromParty[from].add(new Edge(to, dist));
			graphToParty[to].add(new Edge(from, dist));
		}

		int[] distFromParty = dijkstra(graphFromParty);
		int[] distToParty = dijkstra(graphToParty);

		// 가장 긴 거리 구하기
		int answer = -1;
		for (int i = 1; i <= N; i++) {
			if (distFromParty[i] + distToParty[i] > answer) {
				answer = distFromParty[i] + distToParty[i];
			}
		}

		System.out.println(answer);
	}

	// 우선순위 큐 다익스트라
	static int[] dijkstra(ArrayList<Edge>[] graph) {

		int[] dist = new int[graph.length];
		Arrays.fill(dist, 1_000_001);

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(X, 0));
		dist[X] = 0;

		// 최단 거리를 구한 학생 수
		int count = 0;

		while (!pq.isEmpty()) {
			if (count == N)
				break;

			Edge curEdge = pq.poll();

			if (curEdge.dist > dist[curEdge.dest]) {
				continue;
			}

			count++;
			for (Edge next : graph[curEdge.dest]) {
				int nextNodeNum = next.dest;
				int nextDist = curEdge.dist + next.dist;
				if (dist[nextNodeNum] > nextDist) {
					dist[nextNodeNum] = nextDist;
					pq.add(new Edge(nextNodeNum, nextDist));
				}
			}
		}

		return dist;
	}

	// 도로 클래스
	static class Edge implements Comparable<Edge> {
		int dest, dist;

		public Edge(int dest, int dist) {
			this.dest = dest;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return this.dist - o.dist;
		}

	}

}