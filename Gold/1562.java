
// 제목 : 계단수
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/1562
// 메모리(kb) : 18360
// 실행시간(ms) : 168

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	// 나머지를 구할 수
	static final int MOD = 1_000_000_000;
	// 숫자의 길이
	static int N;
	// dp[level][lastNum][bit] = j는 길이가 level이고, 현재 마지막이 lastNum이고, 0~9를 포함하는지가 bit로
	// 표현된 숫자가
	// 조건을 만족하도록 계속 이어나갈 수 있는 경우의 수
	static int[][][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		dp = new int[N][10][1 << 10];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < 10; j++)
				for (int k = 0; k < (1 << 10); k++)
					dp[i][j][k] = -1;

		int answer = 0;
		// 첫 숫자는 0이 아니므로, 1부터 9까지 시작하는 숫자를 탐색
		for (int i = 1; i <= 9; i++) {
			answer = (answer + getDP(1, i, 1 << i)) % MOD;
		}

		System.out.println(answer);
	}

	static int getDP(int level, int lastNum, int bit) {
		// 길이가 N일 때
		if (level == N) {
			// 0~9를 다 가지고 있다면 1, 아니라면 0 리턴
			if (bit == (1 << 10) - 1)
				return 1;
			else
				return 0;
		}

		// 이미 탐색했다면 탐색한 값 반환
		if (dp[level][lastNum][bit] != -1)
			return dp[level][lastNum][bit];

		int res = 0;

		// 뒤에 작게 이어붙여보기
		if (lastNum > 0)
			res = (res + getDP(level + 1, lastNum - 1, bit | (1 << (lastNum - 1)))) % MOD;
		// 뒤에 크게 이어붙여보기
		if (lastNum < 9)
			res = (res + getDP(level + 1, lastNum + 1, bit | (1 << (lastNum + 1)))) % MOD;

		return dp[level][lastNum][bit] = res;
	}
}