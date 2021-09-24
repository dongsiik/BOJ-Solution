// 제목 : RGB 거리 2
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/17404
// 메모리(kb) : 14484
// 실행시간(ms) : 140

// 참고한 링크 : https://velog.io/@jini_eun/%EB%B0%B1%EC%A4%80-17404%EB%B2%88-RGB%EA%B1%B0%EB%A6%AC-2-Java-Python

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String args[]) throws IOException {
		// 빠른 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 집의 수
		int n = Integer.parseInt(br.readLine());
		int[][] houses = new int[n][3];
		// dp 테이블
		int[][] dp = new int[n][3];

		// 1000개의 집을 최대 비용으로 칠했을 때 나오는 총 비용의 최댓값
		int INF = 1000000;

		// 색깔 정보 입력받기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++)
				houses[i][j] = Integer.parseInt(st.nextToken());
		}

		// 출력할 정답
		int min = INF;

		// 첫 집의 색깔마다
		for (int color = 0; color < 3; color++) {

			// 첫 집의 비용
			for (int j = 0; j < 3; j++) {
				if (j == color)
					dp[0][j] = houses[0][j];
				else
					dp[0][j] = INF;
			}

			// 그 다음 집들까지의 총 비용
			for (int i = 1; i < n; i++) {
				for (int j = 0; j < 3; j++) {
					int previousMin = INF;
					for (int k = 0; k < 3; k++) {
						if (j != k)
							previousMin = Math.min(previousMin, dp[i - 1][k]);
					}
					dp[i][j] = previousMin + houses[i][j];
				}
			}

			// 마지막 집을 첫 집과 색이 다르게 했을 때 총 비용을 따져서 갱신
			for (int j = 0; j < 3; j++) {
				if (j != color)
					min = Math.min(min, dp[n - 1][j]);
			}
		}

		// 출력
		System.out.println(min);

	}

}