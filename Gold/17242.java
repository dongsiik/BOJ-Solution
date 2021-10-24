
// 제목 : KaKa & Bebe
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/17242
// 메모리(kb) : 26968
// 실행시간(ms) : 320

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	// 정점 수, 간선 수
	static int N, M;
	// 인접리스트로 표현한 그래프
	static ArrayList<Node>[] graph;

	public static void main(String[] args) throws NumberFormatException, IOException {

		init();
		dijkstra();

	}

	static void init() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N];
		for (int i = 0; i < N; i++)
			graph[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int kaka = Integer.parseInt(st.nextToken());
			int bebe = Integer.parseInt(st.nextToken());
			graph[from].add(new Node(to, kaka, bebe));
			graph[to].add(new Node(from, kaka, bebe));
		}

	}

	static void dijkstra() {
		// dp[nodeNum][kaka] = bebe는 nodeNum번호 정점까지 가며 kaka가 kaka만큼 있을 때, bebe의 최솟값이
		// bebe라는 뜻
		int[][] dp = new int[N][1001];
		for (int i = 1; i < N; i++)
			for (int j = 0; j <= 1000; j++)
				dp[i][j] = 1001;

		// 스트레스가 적은 순서대로 우선순위 큐를 만들고, 0번 지점 방문 처리
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> (n1.kaka * n1.bebe - n2.kaka * n2.bebe));
		pq.add(new Node(0, 0, 0));

		// 다익스트라 알고리즘
		while (!pq.isEmpty()) {
			Node curNode = pq.poll();
			int curNodeNum = curNode.to;
			int curKaka = curNode.kaka;
			int curBebe = curNode.bebe;

			if (dp[curNodeNum][curKaka] < curBebe)
				continue;

			// 탈출구라면 스트레스를 출력하고 종료
			if (curNodeNum == N - 1) {
				System.out.println(curKaka * curBebe);
				return;
			}

			// 연결된 다른 정점에 대하여
			for (Node next : graph[curNodeNum]) {
				int nextNodeNum = next.to;
				int nextKaka = curKaka + next.kaka;
				int nextBebe = curBebe + next.bebe;
				Node nextAccumNode = new Node(nextNodeNum, nextKaka, nextBebe);
				// Kaka가 1000마리 이하이고, Bebe도 기존의 수와 적다면
				if (nextKaka <= 1000 && dp[nextNodeNum][nextKaka] > nextBebe) {
					// 방문처리
					pq.add(nextAccumNode);
					// DP 테이블 갱신
					for (int i = nextKaka; i <= 1000; i++) {
						if (dp[nextNodeNum][i] > nextBebe) {
							dp[nextNodeNum][i] = nextBebe;
						} else
							break;
					}
				}
			}

		}

		// 탈춯하지 못한 경우 -1 출력
		System.out.println(-1);
		return;
	}

	// 정점번호, 그 정점까지 경로의 kaka, bebe의 누적합을 저장하는 Node 클래스
	static class Node {
		int to, kaka, bebe;

		public Node(int to, int kaka, int bebe) {
			super();
			this.to = to;
			this.kaka = kaka;
			this.bebe = bebe;
		}
	}

}