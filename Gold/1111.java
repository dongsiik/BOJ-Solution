
// 제목 : IQ Test
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/1111
// 메모리(kb) : 14220
// 실행시간(ms) : 124

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// caseA : 가능한 a,b쌍이 무수히 많은 경우, caseB : 가능한 a,b쌍이 하나도 없는 경우
		boolean caseA = false;
		boolean caseB = false;

		long a = 0;
		long b = 0;

		// N이 1인 경우, 다음 수가 여러 개임
		if (N == 1) {
			System.out.println('A');
			return;
		}
		// N이 2인 경우, 두 수가 다르다면 다음 수는 여러 개, 같다면 다음 수는 같은 수
		if (N == 2) {
			if (arr[0] == arr[1]) {
				System.out.println(arr[0]);
			} else {
				System.out.println('A');
			}
			return;
		}

		/*
		 * 숫자들을 x1, x2, ... 라고 할 때, 문제에서 제시된 규칙이 성립된다면 (x3-x2) = a(x2-x1)이 됨
		 * 
		 * x2-x1이 0이 아니면 양변을 나누어 a를 구하고, b를 구한 다음, 다시 검산해보면서 정수 a,b가 나오는지 확인 x2-x1이
		 * 0이라면, x3-x2가 0일 경우 부정(caseA), 아니라면 불능(caseB)이 됨
		 */
		if (arr[1] - arr[0] == 0) {
			if (arr[2] - arr[1] == 0) {
				caseA = true;
			} else {
				caseB = true;
			}
		} else {
			a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
			b = arr[1] - a * arr[0];
			if (arr[1] != a * arr[0] + b || arr[2] != a * arr[1] + b) {
				caseB = true;
			}
		}

		// 세 항으로 구한 규칙이 아래에서도 성립하는지 검증
		for (int i = 2; i < N - 1 && !caseB; i++) {
			if (caseA) {
				// 부정이라면 여전히 부정인지 확인
				if (arr[i + 1] == arr[i]) {
					continue;
				} else {
					caseA = false;
					caseB = true;
				}
			}

			// a,b가 한 쌍 존재한다면 이후 쌍에도 적용되는지 확인
			else if (arr[i + 1] != a * arr[i] + b) {
				caseB = true;
			}

		}

		// 경우에 따라 맞추어 출력
		if (caseB) {
			System.out.println('B');
		} else if (caseA) {
			System.out.println(arr[0]);
		} else {
			System.out.println(a * arr[N - 1] + b);
		}

	}
}