
// 제목 : Player-based Team Distribution
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/23742
// 메모리(kb) : 27872
// 실행시간(ms) : 432

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 플레이어 수, 플레이어별 계수
		int N = Integer.parseInt(br.readLine());
		int[] points = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			points[i] = Integer.parseInt(st.nextToken());
		}

		// 오름차순 정렬
		Arrays.sort(points);

		// 계수가 큰 사람부터 한 팀에 묶고, 더 이상 팀에 영입하면 팀의 점수가 떨어지는 사람이 나오면 중단

		// 한 사람이 들어갔을 때 팀의 점수 변동
		long positiveMarginal = 0L;

		// 팀의 인원
		long depth = 0;

		for (int i = N - 1; i >= 0; i--) {
			if (positiveMarginal + depth * points[i] >= 0) {
				depth++;
				positiveMarginal += points[i];
			} else {
				break;
			}
		}

		// 점수 총합
		long answer = 0L;
		answer = positiveMarginal * depth;

		// 나머지 사람들은 1인 1팀으로 침
		for (int i = 0; i < N - depth; i++) {
			answer += points[i];
		}

		// 출력
		System.out.println(answer);

	}

}