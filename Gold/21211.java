
// 제목 : Jogging
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/21211
// 메모리(kb) : 65476
// 실행시간(ms) : 624

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	// 정상적으로 나올 수 없는 큰 값
	static final int INF = 1_000_000;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 교차로 수, 도로 수, 코스 길이의 하한, 상한
		int I = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int U = Integer.parseInt(st.nextToken());

		// 인접리스트로 표현한 그래프
		ArrayList<Street>[] graph = new ArrayList[I];
		for (int i = 0; i < I; i++) {
			graph[i] = new ArrayList<>();
		}

		// 도로를 방문한 적이 있었는지를 나타내는 배열, 다른 교차로까지의 현재까지 최단거리
		boolean[] visitedStreet = new boolean[S];
		int[] dist = new int[I];
		Arrays.fill(dist, 1_000_000_000);

		// 그래프 정보 입력받기, 도로 번호도 임의로 지정해서 넣었음
		for (int i = 0; i < S; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());
			graph[from].add(new Street(i, to, length));
			graph[to].add(new Street(i, from, length));
		}

		// 코스의 수
		int answer = 0;

		// 우선순위 큐에 0번 집과 연결된 도로를 먼저 넣었음
		PriorityQueue<Street> pq = new PriorityQueue<>((s1, s2) -> (s1.length - s2.length));
		for (Street s : graph[0]) {
			answer++;
			visitedStreet[s.num] = true;
			pq.add(s);
			dist[s.to] = s.length;
		}

		// 개선된 다익스트라 알고리즘
		while (!pq.isEmpty()) {
			Street curStreet = pq.poll();

			// 다른 최단경로가 있었으면 생략
			if (curStreet.length > dist[curStreet.to])
				continue;
			// 상한선에 걸쳐서 다른 도로로 더 이상 못 간다면 생략
			if (curStreet.length * 2 >= U)
				continue;

			// 연결된 다른 도로
			for (Street nextStreet : graph[curStreet.to]) {
				// 방문했던 도로라면 생략
				if (visitedStreet[nextStreet.num])
					continue;

				// 새로운 도로이므로 정답을 하나 늘리고, 방문처리
				answer++;
				visitedStreet[nextStreet.num] = true;

				// 조건을 따져서 새로운 도로로 연결되는 교차점을 큐에 넣어줌
				if (dist[nextStreet.to] > curStreet.length + nextStreet.length) {
					dist[nextStreet.to] = curStreet.length + nextStreet.length;
					pq.add(new Street(nextStreet.num, nextStreet.to, dist[nextStreet.to]));
				}
			}
		}

		// 출력
		System.out.println(answer);
	}

	// 도로번호, 도착지, 길이를 저장하는 도로 클래스
	static class Street {
		int num, to, length;

		public Street(int num, int to, int length) {
			super();
			this.num = num;
			this.to = to;
			this.length = length;
		}

	}
}