
// 제목 : Kingdom Rush (Large)
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/12397
// 메모리(kb) : 33176
// 실행시간(ms) : 648

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 수
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			// 레벨 수, 시도한 횟수, 현재까지 모은 별 수
			int N = Integer.parseInt(br.readLine());
			int trial = 0;
			int stars = 0;

			// level별로 a, b 입력받기
			int[][] levels = new int[2][N];
			// 현재 레벨에서 받은 별 수
			int[] checked = new int[N];

			// level 입력받기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				levels[0][i] = a;
				levels[1][i] = b;
			}

			// 시도하기
			trialLoop: while (stars < 2 * N) {

				// 완전히 클리어할 수 있는 스테이지가 있다면, 클리어하기
				for (int j = 0; j < N; j++) {
					if (levels[1][j] <= stars && checked[j] != 2) {
						trial++;
						stars += (2 - checked[j]);

						checked[j] = 2;

						continue trialLoop;
					}
				}

				// 처음 별 하나를 얻을 수 있는 스테이지 중에서, 두 번째 별 레이팅이 가장 높은 별 고르기
				int levelToTry = -1;
				int nextRating = -1;
				for (int i = 0; i < N; i++) {
					if (levels[0][i] <= stars && levels[1][i] > nextRating && checked[i] == 0) {
						levelToTry = i;
						nextRating = levels[1][i];
					}
				}

				// 그런 레벨이 없다면 종료
				if (levelToTry == -1)
					break;

				// 그런 레벨이 있다면 시도해서 별 하나를 얻기
				trial++;
				stars++;

				checked[levelToTry] = 1;

			}

			// 출력
			if (stars < 2 * N) {
				sb.append("Case #").append(tc).append(": Too Bad\n");
			} else {
				sb.append("Case #").append(tc).append(": ").append(trial).append('\n');
			}
		}

		System.out.println(sb);
	}

}