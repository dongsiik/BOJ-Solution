
// 제목 : 밤편지
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/23258
// 메모리(kb) : 266648
// 실행시간(ms) : 1060

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 300개 마을을 170324 거리씩 걸려서 방문해도 나올 수 없는 큰 값을 무한대로 함
	static final int INF = 1_000_000_000;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 마을 수, 질문 수
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		// dp[C][s][e] = distance는 2^C 미만의 이슬을 마시며 s에서 e로 가는 최단 거리
		int[][][] dp = new int[N + 2][N + 1][N + 1];

		// 입력받기
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int value = Integer.parseInt(st.nextToken());
				if (value == 0) {
					if (i == j)
						continue;
					// 연결되지 않았다면 무한대로 침
					dp[1][i][j] = INF;
				} else
					dp[1][i][j] = value;

			}
		}

		// j번 마을을 거치려면, 반딧불이가 2^(j+1)이상의 이슬을 마시고도 잠들지 않아야함
		// 플로이드 워셜 알고리즘을 적용하지만, 중간 결과를 따로 DP 테이블로 만들어서 저장
		for (int j = 1; j <= N; j++)
			for (int i = 1; i <= N; i++)
				for (int k = 1; k <= N; k++)
					dp[j + 1][i][k] = Math.min(dp[j][i][k], dp[j][i][j] + dp[j][j][k]);

		// 질문 입력받기
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());

			int C = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			// 거리가 무한대라면 -1, 아니라면 그 값 출력문 저장
			if (dp[C][s][e] == INF)
				sb.append(-1).append('\n');

			else
				sb.append(dp[C][s][e]).append('\n');
		}

		System.out.println(sb);
	}

}