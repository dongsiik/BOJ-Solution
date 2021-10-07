// 제목 : 문제집
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/1766
// 메모리(kb) : 44968
// 실행시간(ms) : 564

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 문제 수, 조건의 정보 수
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// graph[i]에 j가 있다는 것은 i를 풀고 나서 j를 풀면 쉽다는 뜻
		ArrayList<Integer>[] graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			graph[i] = new ArrayList<>();

		// 선행하는 문제의 개수
		int[] numOfPrerequisite = new int[N + 1];

		// 정보 입력받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int prev = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			graph[prev].add(next);
			numOfPrerequisite[next]++;
		}

		// 선행조건이 더 이상 없는 문제들을 담아 문제번호가 작은 순서대로 꺼내는 우선순위 큐
		PriorityQueue<Integer> pq = new PriorityQueue<>((n1, n2) -> (n1 - n2));
		// 선행조건이 없는 문제들을 우선순위큐에 넣음
		for (int i = 1; i <= N; i++)
			if (numOfPrerequisite[i] == 0)
				pq.offer(i);

		// 큐가 빌 때까지
		while (!pq.isEmpty()) {
			// 문제 번호를 뽑아서 출력문에 저장
			int current = pq.poll();
			sb.append(current).append(' ');

			// 이 문제와 관련된 문제들의 선행조건 개수를 하나씩 줄이고, 더 이상 선행조건이 없다면 우선순위 큐에 넣음
			for (int i = 0; i < graph[current].size(); i++) {
				int next = graph[current].get(i);
				if (--numOfPrerequisite[next] == 0)
					pq.offer(next);

			}
		}

		// 출력
		System.out.println(sb);

	}

}