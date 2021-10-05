// 제목 : 팰린드롬?
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/10942
// 메모리(kb) : 229584
// 실행시간(ms) : 796

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 숫자들 입력받기
		int N = Integer.parseInt(br.readLine());
		int[] letters = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			letters[i] = Integer.parseInt(st.nextToken());

		// dp[start][end]는 start부터 end까지가 팰린드롬이면 2, 아니면 1, 아직 모르면 0
		// 예외로 start와 end가 1 차이이고, 가리키는 숫자가 같을 때는 재귀 호출의 편의를 위해 dp[start][start-1] = 2로
		// 두었음
		int[][] dp = new int[N + 1][N + 1];

		// 쿼리 입력받아 판단하기
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			sb.append(checkPalindrome(start, end, dp, letters) - 1).append('\n');
		}

		// 출력
		System.out.println(sb);
	}

	static int checkPalindrome(int start, int end, int[][] dp, int[] letters) {
		// dp(start,start)에서 호출하거나, dp(start,start+1)에서 호출하고 start와 start+1의 숫자가 같은 경우
		if (start >= end)
			return 2;

		// 이미 검사한 적 있다면 검사한 값 돌려줌
		if (dp[start][end] != 0)
			return dp[start][end];

		// 양 끝이 다르면 회문이 안 되므로 1 반환
		if (letters[start] != letters[end])
			return dp[start][end] = 1;

		// 양쪽 끝을 자른 게 회문인지 확인
		return dp[start][end] = checkPalindrome(start + 1, end - 1, dp, letters);
	}
}