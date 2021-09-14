// 제목 : 최소비용 구하기
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/1916
// 메모리(kb) : 49216
// 실행시간(ms) : 452

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		// 빠른 입출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 도시의 수 n, 버스의 수 m
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		// 방문여부 배열, 최단거리 배열
		int[] distance = new int[n + 1];
		for (int i = 0; i <= n; i++)
			distance[i] = 1000_000_001;

		// 그래프 입력받기
		ArrayList<Node>[] graph = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++)
			graph[i] = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[from].add(new Node(to, cost));
		}

		// 시작도시, 도착도시 입력받기
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		// 다익스트라 알고리즘을 위한 우선순위 큐
		PriorityQueue<Node> pq = new PriorityQueue<>();
		// 시작도시 방문처리, 거리 0, 시작도시와 연결된 도시들 큐에 넣기
		distance[start] = 0;
		for (Node node : graph[start]) {
			pq.add(node);
		}

		// 다익스트라 알고리즘
		while (!pq.isEmpty()) {
			//가장 가까이에 있는 도시 뽑아오기
			Node node = pq.poll();

			int current = node.dest;
			int dist = node.cost;

			// 방문된 도시라면 생략
			if (distance[current] < dist)
				continue;

			// 아니라면 시작도시에서 이 도시까지의 최소비용과 직전 도시를 기록함
			distance[current] = dist;
			if (current == end)
				break;

			// 연결된 도시들을 비교 후 큐에 추가
			for (Node nextNode : graph[current]) {
				int next = nextNode.dest;
				int cost = dist + nextNode.cost;
				if (distance[next] > cost) {
					distance[next] = cost;
					pq.add(new Node(next, cost));
				}
			}

		}

		// 출력
		System.out.println(distance[end]);
	}

	// 노드를 저장하는 배열
	static class Node implements Comparable<Node> {
		int dest;
		int cost;

		public Node(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
	}
}