
// 제목 : 절댓값 힙
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/11286
// 메모리(kb) : 25544
// 실행시간(ms) : 348

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		PriorityQueue<Integer> pq = new PriorityQueue<>(new myComparator());

		// 연산 수
		int N = Integer.parseInt(br.readLine());

		while (N-- > 0) {
			// 연산
			int comm = Integer.parseInt(br.readLine());
			// 빼기
			if (comm == 0) {
				if (pq.isEmpty()) {
					sb.append(0).append('\n');
				} else {
					sb.append(pq.poll()).append('\n');
				}
			}
			// 넣기
			else {
				pq.offer(comm);
			}
		}

		// 출력
		System.out.println(sb);
	}

	// 문제에서 하라는 대로 우선순위 만들기
	static class myComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			if (Math.abs(o1) != Math.abs(o2)) {
				return Integer.compare(Math.abs(o1), Math.abs(o2));
			} else {
				// o1-o2하면 overflow가 생길 수도 있음!
				return Integer.compare(o1, o2);
			}
		}

	}
}