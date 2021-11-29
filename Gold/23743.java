
// 제목 : 방탈출
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/23743
// 메모리(kb) : 90024
// 실행시간(ms) : 1076

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	// 방 수
	static int N;
	// 분리집합 알고리즘에 쓰일 부모 배열
	static int[] parent;
	// 워프와 탈출구를 포함한 모든 간선
	static ArrayList<Edge> edges;

	public static void main(String args[]) throws IOException {

		// 입력받기
		init();
		// 크루스칼 알고리즘 써서 정답 출력하기
		System.out.println(kruskal());

	}

	static void init() throws IOException {
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 방 수, 워프 수
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 출구를 0번으로 가정
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++)
			parent[i] = i;

		// 워프를 간선에 추가하기
		edges = new ArrayList<>(N + M);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges.add(new Edge(from, to, cost));
		}

		// 비상탈출구를 간선에 추가하기
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			edges.add(new Edge(0, i, Integer.parseInt(st.nextToken())));
		}

	}

	// 크루스칼 알고리즘
	static long kruskal() {
		long answer = 0;

		// 비용 내림차순 정렬
		edges.sort((e1, e2) -> (e1.cost - e2.cost));
		int count = 0;

		// 출구까지 N+1개의 점을 N개의 간선으로 잇고, 비용 더하기
		for (Edge curEdge : edges) {
			if (union(curEdge.from, curEdge.to)) {
				answer += curEdge.cost;
				count++;
			}

			if (count == N)
				break;
		}

		return answer;
	}

	// 분리집합 알고리즘
	static int find(int nodeNum) {
		if (nodeNum == parent[nodeNum])
			return parent[nodeNum];
		else
			return parent[nodeNum] = find(parent[nodeNum]);
	}

	static boolean union(int nodeA, int nodeB) {
		int parentA = find(nodeA);
		int parentB = find(nodeB);

		if (parentA == parentB)
			return false;

		else
			parent[Math.max(parentA, parentB)] = Math.min(parentA, parentB);

		return true;
	}

	// 간선 클래스
	static class Edge {
		int from;
		int to;
		int cost;

		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

	}
}