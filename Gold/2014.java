// 제목 : 소수의 곱
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/2014
// 메모리(kb) : 35880
// 실행시간(ms) : 284

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입출력 도구들
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 소수의 수, 구하고자하는 수의 순서 n
		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		// 숫자들을 비교하기 위한 우선순위 큐, 소수 목록, 현재 보고 있는 i번째로 보고 있는 수 val
		PriorityQueue<Long> pq = new PriorityQueue<>();
		long[] primes = new long[k + 1];
		long val = 0;

		// 소수 입력받으며 큐에 넣기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			primes[i] = Integer.parseInt(st.nextToken());
			pq.add(primes[i]);
		}

		// i번째로 작은 소수를 보면서
		for (int i = 0; i < n; i++) {
			val = pq.poll();

			// 다른 소수를 곱해서 큐에 넣기
			for (int j = 0; j < k; j++) {
				pq.add(val * primes[j]);
				// primes가 오름차순이므로, 큐에 합성수가 들어갈 때는 웬만하면 작은 소인수들의 곱부터 들어가도록 하여 중복과 메모리 초과를 방지
				if (val % primes[j] == 0)
					break;
			}

		}

		// 출력
		System.out.println(val);
	}
}
