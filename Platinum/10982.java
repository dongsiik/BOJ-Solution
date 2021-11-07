// 제목 : 행운쿠키 제작소
// 티어 : 플래티넘 5
// 링크 : https://www.acmicpc.net/problem/10982
// 메모리(kb) : 22148
// 실행시간(ms) : 2000

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	// 조건상 나올 수 있는 정답의 최댓값
	static final int MAX = 100_000;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());

		//테스트 케이스 마다
		for (int tc = 0; tc < TC; tc++) {
			//반죽 수
			int N = Integer.parseInt(br.readLine());

			//작업별 소요시간
			int[] A = new int[N];
			int[] B = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				A[i] = Integer.parseInt(st.nextToken());
				B[i] = Integer.parseInt(st.nextToken());
			}

			//dp[i] = j 현재 단계에서 A쪽에서 걸린 시간이 i일 때, B쪽에서 걸린 최소 시간
			//dp를 2차원 배열로 저장하면 메모리가 부족해져서, 이전과 현재를 1차원 배열로 저장하면서 쓸 예정
			int[] prevDp = new int[MAX + 1];
			int[] dp = new int[MAX + 1];
			int[] temp;

			//처음에는 A쪽, B쪽에서 걸린 시간이 모두 0이고, 나머지는 모두 무한대값을 넣어야함
			for (int i = 1; i <= MAX; i++) {
				dp[i] = MAX;
			}

			//N개의 작업 동안
			for (int i = 1; i <= N; i++) {
				//배열 swap
				temp = prevDp;
				prevDp = dp;
				dp = temp;

				for (int j = 0; j <= MAX; j++) {
					//이전 단계에서 B 기계 작동시키기
					dp[j] = prevDp[j] + B[i - 1];
					//이전 단계에서 A 기계 작동시킨 것과 비교
					if (j >= A[i - 1])
						dp[j] = Math.min(dp[j], prevDp[j - A[i - 1]]);
				}
			}

			//마지막 단계에서, A와 B 작동시간 중 최댓값 중 최솟값 비교
			int answer = MAX;
			for (int i = 0; i <= MAX; i++) {
				answer = Math.min(answer, Math.max(dp[i], i));
			}

			//출력문에 저장
			sb.append(answer).append('\n');

		}

		//출력
		System.out.println(sb);
	}

}