// 제목 : 행운쿠키 제작소
// 티어 : 플래티넘 5
// 링크 : https://www.acmicpc.net/problem/10982
// 메모리(kb) : 19960
// 실행시간(ms) : 1972

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	//조건상 나올 수 있는 정답의 최댓값
	static final int MAX = 100_000;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());

		//테스트 케이스 마다
		for (int tc = 0; tc < TC; tc++) {
			//작업 수
			int N = Integer.parseInt(br.readLine());

			//기계별 작엽당 소요시간
			int[] A = new int[N];
			int[] B = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				A[i] = Integer.parseInt(st.nextToken());
				B[i] = Integer.parseInt(st.nextToken());
			}

			// dp[i] = j는 현재 단계에서 A 기계에서 쓴 시간이 총 i일  때, B 기계에서 쓴 총 시간의 최솟값이 j라는 뜻
			int[] dp = new int[MAX + 1];

			//처음에는 A, B에서 걸린 시간이 모두 0이고, 나머지는 모두 무한대값을 넣어야함
			for (int i = 1; i <= MAX; i++) {
				dp[i] = MAX;
			}

			
			//N개의 작업 동안
			for (int i = 1; i <= N; i++) {

				for (int j = MAX; j >= 0; j--) {
					//B기계 작동시키기
					dp[j] = dp[j] + B[i - 1];
					//이전 단계에서 A기계 작동시킨 것과 비교
					if (j >= A[i - 1])
						dp[j] = Math.min(dp[j], dp[j - A[i - 1]]);

				}

			}

			//마지막 단계에서 A, B의 작동시간 중 최댓값 중 최솟값과 비교
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