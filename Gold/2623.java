// 제목 : 음악프로그램
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/2623
// 메모리(kb) : 14536
// 실행시간(ms) : 148

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {
		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 가수 수, 보조PD 수
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// 인접리스트로 그래프 만들고 초기화
		ArrayList<Integer>[] graph = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++)
			graph[i] = new ArrayList<>();
		// 위상정렬을 위해 선행 노드의 수를 담은 배열
		int[] prior = new int[n + 1];

		// 보조 PD별로 입력받기
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			// 담당한 가수의 수
			int num = Integer.parseInt(st.nextToken());
			// 두 가수의 순서
			int prev;
			int next = Integer.parseInt(st.nextToken());
			while (st.hasMoreTokens()) {
				// 선행 가수 리스트에 후행 가수를 저장하고, 후행 가수에 선행 노드를 하나 증가시킴
				prev = next;
				next = Integer.parseInt(st.nextToken());
				graph[prev].add(next);
				prior[next]++;
			}
		}

		// 순서가 정해진 가수의 수
		int count = 0;

		// 큐
		ArrayDeque<Integer> q = new ArrayDeque<>();
		// 선행 가수가 없는 가수들부터 큐에 넣어줌
		for (int i = 1; i <= n; i++) {
			if (prior[i] == 0)
				q.add(i);
		}

		while (!q.isEmpty()) {
			// 현재 가수를 명단에 넣고, 순서가 정해진 가수의 수를 하나 증가시킴
			int current = q.poll();
			count++;
			sb.append(current).append('\n');

			// 이 가수 다음에 나와야 할 가수들에 대해서
			for (int i = 0; i < graph[current].size(); i++) {
				int next = graph[current].get(i);
				// 선행 가수 수를 하나 줄이고, 선행 가수가 이제 없다면 큐에 넣어줌
				if (prior[next] > 0 && --prior[next] == 0) {
					q.add(next);
				}
			}
		}

		// 출력
		if (count == n)
			System.out.println(sb);
		else
			System.out.println(0);
	}
}