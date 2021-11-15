
// 제목 : 최단최단경로
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/23087
// 메모리(kb) : 139828
// 실행시간(ms) : 1036

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	// 나머지를 구할 때 나누는 값
	static final int MOD = 1_000_000_009;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 지하철 역 수, 노선 수, 시작점, 도착점
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());

		// 인접리스트로 표현한 그래프
		ArrayList<Edge>[] graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u].add(new Edge(v, 1, w));
		}

		// 시작점부터의 최단거리 배열
		long[] dist = new long[N + 1];
		Arrays.fill(dist, Long.MAX_VALUE);
		// 현재까지 최단거리로 도착할 때 최소 환승 횟수
		int[] transfer = new int[N + 1];
		// 최단최단거리로 도착하는 경로 수
		long[] ways = new long[N + 1];
		ways[x] = 1;
		ways[y] = -1;

		// 시작점 큐에 넣기
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(x, 0, 0));
		dist[x] = 0;

		// 다익스트라 알고리즘
		while (!pq.isEmpty()) {

			Edge curEdge = pq.poll();

			int curNodeNum = curEdge.to;
			int curTransfer = curEdge.transfer;
			long curDistance = curEdge.distance;

			// 더 이상 처리할 필요가 없는 경로들
			if (curDistance > dist[curNodeNum]
					|| (curDistance == dist[curNodeNum] && curTransfer > transfer[curNodeNum])) {
				// 도착점이라면 반복문 종료, 아니라면 그냥 생략
				if (curNodeNum == y) {
					break;
				} else {
					continue;
				}
			}

			// 연결된 다른 노선에 대해서
			for (Edge nextEdge : graph[curNodeNum]) {
				int nextNodeNum = nextEdge.to;
				int nextTransfer = curTransfer + 1;
				long nextDistance = (curDistance + nextEdge.distance);

				// 거리가 짧아지는 경우
				if (dist[nextNodeNum] > nextDistance) {
					transfer[nextNodeNum] = nextTransfer;
					dist[nextNodeNum] = nextDistance;
					ways[nextNodeNum] = ways[curNodeNum];
					pq.add(new Edge(nextNodeNum, nextTransfer, nextDistance));
				}
				// 거리가 같고, 환승횟수가 줄어드는 경우
				else if (dist[nextNodeNum] == nextDistance && transfer[nextNodeNum] > nextTransfer) {
					transfer[nextNodeNum] = nextTransfer;
					ways[nextNodeNum] = ways[curNodeNum];
					pq.add(new Edge(nextNodeNum, nextTransfer, nextDistance));

				}
				// 거리도 같고, 환승횟수도 같으면 경로의 수를 늘려줌
				else if (dist[nextNodeNum] == nextDistance && transfer[nextNodeNum] == nextTransfer) {
					ways[nextNodeNum] = (ways[nextNodeNum] + ways[curNodeNum]) % MOD;
				}
			}
		}

		// 도착지까지 갔다면, 거리, 환승횟수, 경로의 수 출력
		if (ways[y] != -1) {
			System.out.println(dist[y]);
			System.out.println(transfer[y]);
			System.out.println(ways[y]);
		}
		// 못 갔다면, -1 출력
		else {
			System.out.println(-1);

		}

	}

	// 노선 클래스
	static class Edge implements Comparable<Edge> {
		// 노선의 도착지, 환승 횟수, 시작점부터의 거리
		int to;
		int transfer;
		long distance;

		public Edge(int to, int transfer, long distance) {
			super();
			this.to = to;
			this.transfer = transfer;
			this.distance = distance;
		}

		// 거리가 다르다면 거리가 짧은 순, 같다면 환승횟수가 적은 순서대로 정렬
		@Override
		public int compareTo(Edge o) {
			if (this.distance != o.distance) {
				return Long.compare(this.distance, o.distance);
			} else {
				return Integer.compare(this.transfer, o.transfer);
			}
		}

	}

}