// 제목 : 개업
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/13910
// 메모리(kb) : 22528
// 실행시간(ms) : 228

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	static final int INF = 1_000_000;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		//주문받은 짜장면 수, 웍 수
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 한 번에 요리할 수 있는 짜장면의 수
		ArrayList<Integer> cook = new ArrayList<>(N);
		boolean[] check = new boolean[N + 1];

		//웍 정보 입력받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int wok = Integer.parseInt(st.nextToken());

			cook.add(wok);
			check[wok] = true;
		}

		//두 웍으로 요리할 수 있는 경우의 수 따지기
		for (int i = 0; i < M; i++) {
			int leftWok = cook.get(i);
			for (int j = 0; j < i; j++) {
				int rightWok = cook.get(j);
				int wokSet = leftWok + rightWok;
				//두 웍을 합쳐서 N 그릇을 넘거나, 기존에 이만큼 처리할 수 있었다면 생략
				if (wokSet > N || check[wokSet])
					continue;

				//아니라면 한 번에 요리할 수 있는 짜장면 수에 넣어주기
				cook.add(wokSet);
				check[wokSet] = true;
			}
		}

		//dp[i] = j는 i그릇을 요리할 때 최소 요리 횟수가 j라는 뜻
		int[] dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			dp[i] = INF;
		}

		//dp
		for (int i = 0; i < cook.size(); i++) {
			int wokSet = cook.get(i);

			for (int j = wokSet; j <= N; j++) {
				dp[j] = Math.min(dp[j], dp[j - wokSet] + 1);
			}
		}

		System.out.println(dp[N] == INF ? -1 : dp[N]);
	}
}