
// 제목 : 사과나무
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/20002
// 메모리(kb) : 24044
// 실행시간(ms) : 420

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 과수원 크기
		int N = Integer.parseInt(br.readLine());

		// 과수원 입력받기
		int[][] farm = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				farm[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 누적합 구하기
		int[][] accumulation = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				accumulation[i][j] = accumulation[i][j - 1] + accumulation[i - 1][j] - accumulation[i - 1][j - 1]
						+ farm[i - 1][j - 1];
			}
		}

		// 정사각형별로 최댓값 구하기
		int answer = Integer.MIN_VALUE;
		for (int down = 1; down <= N; down++) {
			for (int right = 1; right <= N; right++) {
				int bound = Math.min(down, right);
				for (int k = 1; k <= bound; k++) {
					int current = accumulation[down][right] + accumulation[down - k][right - k]
							- accumulation[down - k][right] - accumulation[down][right - k];
					answer = Math.max(current, answer);
				}
			}
		}

		// 정답 출력
		System.out.println(answer);

	}

}