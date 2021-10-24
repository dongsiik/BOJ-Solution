
// 제목 : KCM Travl
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/10217
// 메모리(kb) : 307552
// 실행시간(ms) : 2016

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	// 공항 수, 총 비용
	static int N, M;
	// 인접리스트로 표현한 노선 정보
	static ArrayList<Node>[] graph;
	// 입력 도구
	static BufferedReader br;

	public static void main(String[] args) throws NumberFormatException, IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		// 테스트 케이스마다 초기화 후 다익스트라 알고리즘 적용
		for (int tc = 1; tc <= TC; tc++) {
			init();
			dijkstra();
		}
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			graph[i] = new ArrayList<>();

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			graph[from].add(new Node(to, cost, time));
		}

	}

	static void dijkstra() {
		// dp[airport][cost] = time은 airpot번 공항에 cost 비용으로 도착할 수 있는 가장 빠른 시간
		int[][] dp = new int[N + 1][M + 1];
		for (int i = 2; i <= N; i++)
			for (int j = 0; j <= M; j++)
				dp[i][j] = 1_000_000;

		// 방문 시간이 빠른 순서대로 우선순위 큐를 만들고, 1번 지점 방문 처리
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> (n1.time - n2.time));
		pq.add(new Node(1, 0, 0));

		// 다익스트라 알고리즘
		while (!pq.isEmpty()) {
			Node curNode = pq.poll();
			int curNodeNum = curNode.to;
			int curCost = curNode.cost;
			int curTime = curNode.time;

			if (dp[curNodeNum][curCost] < curTime)
				continue;

			// LA라면 시간을 출력하고 종료
			if (curNodeNum == N) {
				System.out.println(curTime);
				return;
			}

			// 연결된 다른 공항에 대하여
			for (Node next : graph[curNodeNum]) {
				int nextNodeNum = next.to;
				int nextCost = curCost + next.cost;
				int nextTime = curTime + next.time;
				Node nextAccumNode = new Node(nextNodeNum, nextCost, nextTime);
				// 총 비용이 M이하이고, 이 공항을 거친 비용과 시간이 기존값보다 더 작다면
				if (nextCost <= M && dp[nextNodeNum][nextCost] > nextTime) {
					// 방문처리
					pq.add(nextAccumNode);
					// DP 테이블 갱신
					for (int i = nextCost; i <= M; i++) {
						if (dp[nextNodeNum][i] > nextTime) {
							dp[nextNodeNum][i] = nextTime;
						} else
							break;
					}
				}
			}

		}

		// LA까지 못 간 경우 Poor KCM 출력
		System.out.println("Poor KCM");
		return;
	}

	// 공항 번호, 도착 비용, 시간을 저장할 Node 클래스
	static class Node {
		int to, cost, time;

		public Node(int to, int cost, int time) {
			super();
			this.to = to;
			this.cost = cost;
			this.time = time;
		}
	}

}