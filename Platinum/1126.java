// 제목 : 같은 탑
// 티어 : 플래티넘 3
// 링크 : https://www.acmicpc.net/problem/1126
// 메모리(kb) : 120564
// 실행시간(ms) : 628

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int MAX;
	static int n;
	static int[] blocks;
	static int[][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력받기, MAX는 모든 블록들을 한 탑에 쌓았을 때 나오는 최대 높이
		n = Integer.parseInt(br.readLine());
		blocks = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			blocks[i] = Integer.parseInt(st.nextToken());
			MAX += blocks[i];
		}

		// dp 배열. -1은 아직 탐색하지 않았다는 것을 나타냄
		// dp[i][j]는 i번 블록, 높이차 j부터 시작해서 결국 높이차가 0이 되도록 쌓아나갈 수 있는 최대 높이
		dp = new int[n + 1][MAX + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= MAX; j++) {
				dp[i][j] = -1;
			}
		}

		// recursive(0,0)는 0번 블록, 높이차 0부터 시작해서 결국 높이차가 0이 되도록 쌓아나갈 수 있는 최대 높이
		int answer = recursive(0, 0);
		// 최대 높이가 0이라면 불가능할때이므로 -1 출력, 아니라면 높이 출력
		if (answer == 0)
			System.out.println(-1);
		else
			System.out.println(answer);

	}

	// 재귀로 DP 완성하기
	static int recursive(int now, int diff) {
		// 모든 블록을 사용했을 때
		if (now == n) {
			// 두 탑의 높이가 다르다면 정수 최솟값 반환
			if (diff != 0)
				return Integer.MIN_VALUE;
			// 같다면 0 반환
			else
				return 0;
		}
		
		// 한쪽 탑이 너무 커서 다른 조각을 놓아도 높이가 같아질 수 없는 상황이라면 정수 최솟값 반환
		if (diff > MAX / 2) 
			return Integer.MIN_VALUE;
		
		// 이미 처리한 상황이라면 처리한 값 반환
		if (dp[now][diff] != -1)
			return dp[now][diff];

		// 방문 처리
		dp[now][diff] = Integer.MIN_VALUE;
		// 지금 블록을 쓰지 않는 상황과 비교
		dp[now][diff] = Math.max(dp[now][diff], recursive(now + 1, diff));
		// 지금 블록을 높이가 높은 탑 쪽에 얹은 상황과 비교
		dp[now][diff] = Math.max(dp[now][diff], recursive(now + 1, diff + blocks[now]));

		// 지금 블록을 높이가 작은 탑 쪽에 얹은 상황
		// 탑의 높이가 역전되었을 때
		if (blocks[now] > diff) 
			dp[now][diff] = Math.max(dp[now][diff], recursive(now + 1, blocks[now] - diff) + diff);
		// 탑의 높이가 역전되지 않았을 때
		else 
			dp[now][diff] = Math.max(dp[now][diff], recursive(now + 1, diff - blocks[now]) + blocks[now]);
		
		// 리턴
		return dp[now][diff];
	}
}