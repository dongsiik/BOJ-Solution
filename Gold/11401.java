// 제목 : 이항 계수 3
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/11401
// 메모리(kb) : 44980
// 실행시간(ms) : 204

// nCr = nC(r-1) * (n-i+1)/i를 사용해서 nC1부터 nCr을 구하면 44976kb, 1040ms가 나옵니다.
// 나머지가 거듭제곱을 엄청 많이하고 곱하는 거라서 그런가봐요.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	// 나머지를 구할 때 나눌 수
	static int divisor = 1_000_000_007;
	// factorial[i] = i!
	static long[] factorial;

	public static void main(String args[]) throws IOException {
		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// n과 k 입력받기
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		// n!까지 구하기
		getFactorial(n);

		// nCk = n! / (n-k)!*k!
		System.out.println(getCombination(n, k));

	}

	// 팩토리얼 구하는 함수
	static void getFactorial(int n) {
		factorial = new long[(int) n + 1];
		factorial[0] = 1;
		for (int i = 1; i <= n; i++) {
			factorial[i] = factorial[i - 1] * i % divisor;
		}
	}

	// 분할정복을 이용한 거듭제곱
	static long getPower(long exp, long pow) {
		long res = 1;
		while (pow > 0) {
			if (pow % 2 == 1) {
				res = res * exp % divisor;
				pow--;
			}
			pow /= 2;
			exp = exp * exp % divisor;
		}

		return res;
	}

	// nCk = n! / (n-k)!*k! = n! * (n-k)! ^ (divisor-2) * (k!) ^ (divisor-2)
	static long getCombination(int n, int k) {
		long l1 = factorial[n];
		long l2 = factorial[n - k] * factorial[k] % divisor;

		return l1 * getPower(l2, divisor - 2) % divisor;

	}
}