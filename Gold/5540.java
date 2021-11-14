
// 제목 : 夜店 (Night Market)
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/5540
// 메모리(kb) : 20620
// 실행시간(ms) : 264

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 상점 수, 축제가 끝나는 시간, 불꽃놀이가 열리는 시간
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		int[] dp = new int[T + 1];

		// 상점 정보 입력받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			// 불꽃놀이 시간을 전후로 끊어서 냅색
			for (int j = T; j >= S + B; j--) {
				dp[j] = Math.max(dp[j], dp[j - B] + A);
			}

			for (int j = S; j >= B; j--) {
				dp[j] = Math.max(dp[j], dp[j - B] + A);
			}

		}

		// dp 테이블에서 최댓값 구하기
		int answer = 0;
		for (int i = 0; i <= T; i++)
			answer = Math.max(answer, dp[i]);
		// 출력
		System.out.println(answer);
	}

}