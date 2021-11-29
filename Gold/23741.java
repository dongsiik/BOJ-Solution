
// 제목 : 야바위 게임
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/23741
// 메모리(kb) : 53692
// 실행시간(ms) : 484

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 정점 수, 간선 수, 시작 시 공이 있던 정점 번호, 공이 든 컵이 움직힌 횟수
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken()) - 1;
		int Y = Integer.parseInt(st.nextToken());

		// 인접 리스트로 표현한 그래프
		ArrayList<Integer>[] graph = new ArrayList[N];
		for (int i = 0; i < N; i++)
			graph[i] = new ArrayList<>();

		// 간선 정보 입력받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken()) - 1;
			int right = Integer.parseInt(st.nextToken()) - 1;
			graph[left].add(right);
			graph[right].add(left);
		}

		// BFS
		Queue<Integer> q = new LinkedList<>();
		q.add(X);
		boolean[] visited = null;

		for (int i = 1; i <= Y; i++) {
			// 방문 횟수마다 큐 크기를 기록해두어서 매 횟수마다 정확히 그 크기만큼만 BFS를 반복해나갈 예정
			int qSize = q.size();
			visited = new boolean[N];

			// 매 횟수마다 방문여부를 초기화하면서 BFS
			for (int j = 0; j < qSize; j++) {
				int current = q.poll();

				for (int next : graph[current]) {
					if (!visited[next]) {
						visited[next] = true;
						q.add(next);
					}
				}
			}
		}

		// 공이 있을만한 정점들
		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				sb.append(i + 1).append(' ');
			}
		}

		// 공이 있을만한 곳이 있다면 정점 출력, 아니라면 -1 출력
		if (sb.length() != 0) {
			System.out.println(sb);
		} else {
			System.out.println(-1);
		}
	}
}