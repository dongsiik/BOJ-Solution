// 제목 : Kas
// 티어 : 플래티넘 4
// 링크 : https://www.acmicpc.net/problem/14211
// 메모리(kb) : 210580
// 실행시간(ms) : 464

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	// 조건상 나올 수 있는 지폐 합의 총합
	static final int MAX = 100_000;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 지폐 수
		int N = Integer.parseInt(br.readLine());

		// 지폐당 가치, 총합
		int[] value = new int[N];
		int sum = 0;

		for (int i = 0; i < N; i++) {
			value[i] = Integer.parseInt(br.readLine());
			sum += value[i];
		}

		// dp[banknoteNum][diff] = higher
		// banknoteNum번까지 지폐를 써서 두 사람이 나눠가진 돈의 차이가 diff일때
		// 더 많이 가진 사람의 액수가 higher라는 뜻
		int[][] dp = new int[N + 1][MAX + 1];

		// 처음에는 둘의 차이가 0이어야만 함
		for (int i = 1; i <= MAX; i++) {
			dp[0][i] = -1;
		}

		// 지폐를 하나씩 나눠가짐
		for (int i = 1; i <= N; i++) {

			for (int j = 0; j <= MAX; j++) {
				// 지금 지폐를 나눠갖지 않고 보류하는 경우
				dp[i][j] = dp[i - 1][j];

				// 더 많이 가진 사람에게 지폐를 주는 경우
				if (j - value[i - 1] >= 0 && dp[i - 1][j - value[i - 1]] != -1) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - value[i - 1]] + value[i - 1]);
				}
				// 더 적게 가진 사람에게 줬고, 두 사람 사이에 금액 역전이 일어난 경우
				if (value[i - 1] - j >= 0 && dp[i - 1][value[i - 1] - j] != -1) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][value[i - 1] - j] + j);
				}
				// 더 적게 가진 사람에게 줬고, 두 사람 사이에 금액 역전이 일어나지 않은 경우
				if (j + value[i - 1] <= MAX && dp[i - 1][j + value[i - 1]] != -1) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j + value[i - 1]]);
				}
			}

		}

		// dp[N][0]은 N번 지폐까지 봤을 때, 두 사람이 공평하게 나누어가질 수 있는 최대 액수
		// 남은 지폐는 sum-2*dp[N][0]만큼 있고, 카지노에서 이만큼 따서 나눠가지므로 둘이 공평하게 나눠가질 금액은
		// sum - dp[N][0]이 된다.
		System.out.println(sum - dp[N][0]);

	}

}