// 제목 : 최소 힙
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/1927
// 메모리(kb) : 26376
// 실행시간(ms) : 360

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		// 빠른 입출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// n 입력받고, 최소 큐로 사용할 우선순위 큐를 선언함
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		// 연산마다
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			// 출력 및 제거
			if (num == 0) {
				if (pq.isEmpty())
					sb.append(0).append('\n');
				else
					sb.append(pq.poll()).append('\n');
			}
			// 삽입
			else {
				pq.offer(num);
			}
		}
		// 출력
		System.out.print(sb.toString());
	}

}