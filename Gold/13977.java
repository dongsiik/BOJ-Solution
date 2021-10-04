// 제목 : 이항 계수와 쿼리
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/13977
// 메모리(kb) : 73360
// 실행시간(ms) : 596

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 나머지로 구할 때 나눌 수
	static final int MOD = 1_000_000_007;
	// 4백만 이하의 n에 대해 n!을 미리 다 구해놓고 시작할 예정
	static final int maxN = 4_000_000;
	static long[] factorial;

	public static void main(String[] args) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int M = Integer.parseInt(br.readLine());

		// 팩토리얼 계산
		setFactorial();

		// 쿼리 입력받으며 출력문에 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			sb.append(getCombination(N, K)).append('\n');
		}

		System.out.println(sb);
	}

	// 팩토리얼 구하기. 곱하고 나머지를 구하기 전 int 범위를 초과할 수 있으므로 long으로 배열을 만듦
	static void setFactorial() {
		factorial = new long[maxN + 1];
		factorial[0] = 1;

		for (int i = 1; i <= maxN; i++)
			factorial[i] = factorial[i - 1] * i % MOD;
	}

	// 조합 계산하기
	static long getCombination(int N, int K) {
		long numerator = factorial[N];
		long denominator = factorial[K] * factorial[N - K] % MOD;

		return divide(numerator, denominator);
	}

	// 페르마의 소정리를 이용해 곱셈의 역원 구하기
	static long divide(long numerator, long denominator) {

		return numerator * pow(denominator, MOD - 2) % MOD;
	}

	// 분할 정복을 이용한 거듭제곱
	static long pow(long base, long exp) {
		long res = 1;

		while (exp > 0) {
			if (exp % 2 == 1) {
				res = res * base % MOD;
				exp--;
			}

			base = base * base % MOD;
			exp /= 2;
		}
		return res;
	}

}