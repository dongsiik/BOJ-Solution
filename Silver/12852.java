// 제목 : 1로 만들기 2
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/12852
// 메모리(kb) : 18052
// 실행시간(ms) : 152

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		// 입출력도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// n 입력받기
		int n = Integer.parseInt(br.readLine());

		// dp 배열 만들고 채우기
		int[] dp = new int[n + 1];

		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + 1;
			if (i % 2 == 0)
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			if (i % 3 == 0)
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);
		}

		// 경우의 수 출력
		sb.append(dp[n]).append('\n');

		// dp 배열 역추적하면서 과정 기록하기
		while (n > 0) {
			sb.append(n).append(' ');
			if (n % 3 == 0 && dp[n / 3] + 1 == dp[n])
				n /= 3;
			else if (n % 2 == 0 && dp[n / 2] + 1 == dp[n])
				n /= 2;
			else
				n--;
		}

		// 출력
		System.out.println(sb);
	}
}