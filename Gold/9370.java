
// 제목 : 미확인 도착지
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/9370
// 메모리(kb) : 72160
// 실행시간(ms) : 684

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			//교차로 수, 도로 수, 목적지 후부의 수
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());

			//인접 리스트로 그래프 표현
			ArrayList<Node>[] graph = new ArrayList[N];
			for (int i = 0; i < N; i++)
				graph[i] = new ArrayList<Node>();

			//방문 여부, 목적지 후보인지 여부
			boolean[] visited = new boolean[N];
			boolean[] candidates = new boolean[N];

			//조건을 만족하는 목적지 후보들
			ArrayList<Integer> possibleCandidates = new ArrayList<>();

			//시작점, 경유한 도로의 두 교차로 번호
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken()) - 1;
			int G = Integer.parseInt(st.nextToken()) - 1;
			int H = Integer.parseInt(st.nextToken()) - 1;

			//그래프 입력받기. 번호가 1부터이므로 1을 빼서 저장함
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken()) - 1;
				int B = Integer.parseInt(st.nextToken()) - 1;
				int D = Integer.parseInt(st.nextToken());
				if ((A == G && B == H) || (A == H && B == G)) {
					graph[A].add(new Node(B, D, true));
					graph[B].add(new Node(A, D, true));
				} else {
					graph[A].add(new Node(B, D, false));
					graph[B].add(new Node(A, D, false));
				}
			}

			//목적지 후보들을 입력받기
			for (int i = 0; i < T; i++) {
				int candidate = Integer.parseInt(br.readLine()) - 1;
				candidates[candidate] = true;
			}

			//우선순위 큐를 이용한 다익스트라. 방문 여부를 거리 배열을 만들어 비교하는 대신, GH를 경유하는지를 따지기 위해 visited 배열로 처리
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(S, 0, false));

			while (!pq.isEmpty()) {
				//현재 지점 번호와, 시작지점부터의 거리, GH를 통과했는지 
				Node current = pq.poll();
				int currentNodeNum = current.to;
				int currentDistance = current.dist;
				boolean currentPassCheck = current.passGH;

				//이미 방문했다면 생략
				if (visited[currentNodeNum])
					continue;

				//방문 처리
				visited[currentNodeNum] = true;

				//후보이면서 GH를 지났으면 조건을 만족하는 후보에 넣어줌
				if (candidates[currentNodeNum] && currentPassCheck) 
					possibleCandidates.add(currentNodeNum);
			
				//이 점과 연결된 다른 점들에 대해서 큐에 넣어줌
				for (Node next : graph[currentNodeNum]) {
					int nextNodeNum = next.to;
					int nextNodeDistance = currentDistance + next.dist;
					boolean nextPassCheck = currentPassCheck | next.passGH;

					if (!visited[nextNodeNum]) 
						pq.offer(new Node(nextNodeNum, nextNodeDistance, nextPassCheck));		
				}

			}

			//조건을 만족하는 후보들 정렬 후 출력문에 저장
			possibleCandidates.sort(null);
			for (Integer i : possibleCandidates) 
				sb.append(i + 1).append(' ');
			sb.append('\n');
		}

		//출력
		System.out.println(sb);
	}

	//Node 클래스. 우선순위큐에서 거리와 방문 여부로 비교할 수 있도록 compareTo를 overrride하였음.
	static class Node implements Comparable<Node> {
		int to;
		int dist;
		boolean passGH;

		Node() {
		}

		public Node(int to, int dist, boolean passGH) {
			super();
			this.to = to;
			this.dist = dist;
			this.passGH = passGH;
		}

		@Override
		public int compareTo(Node o) {
			if (this.dist != o.dist)
				return this.dist - o.dist;
			else
				return (this.passGH ? -1 : 0) - (o.passGH ? -1 : 0);
		};

	}
}