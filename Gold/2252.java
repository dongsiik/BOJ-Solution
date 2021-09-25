// 제목 : 줄 세우기
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/2252
// 메모리(kb) : 42860
// 실행시간(ms) : 464

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 정점의 수, 간선의 수
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// 자기보다 키가 작은 학생 수
		int[] preconditions = new int[n + 1];
		// 그래프. graph[i]에 j가 있다면 키가 i<j라는 뜻
		ArrayList<Integer>[] graph = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++)
			graph[i] = new ArrayList<>();

		// 입력받기
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// a<b
			graph[a].add(b);
			preconditions[b]++;
		}

		// 자기보다 키가 작은 학생이 없는 학생부터 큐에 넣어주고 출력문에도 저장
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= n; i++) {
			if (preconditions[i] == 0) {
				q.add(i);
				sb.append(i).append(' ');
			}
		}

		while (!q.isEmpty()) {
			// 현재 학생
			int current = q.poll();

			// 이 학생보다 큰 다른 학생들에 대해서, 작은 학생을 하나씩 줄이고, 작은 학생이 없다면 큐에 넣고 출력문에도 저장
			for (int i = 0; i < graph[current].size(); i++) {
				int next = graph[current].get(i);
				if (--preconditions[next] == 0) {
					q.add(next);
					sb.append(next).append(' ');
				}
			}
		}

		// 출력
		System.out.println(sb);
	}

}
