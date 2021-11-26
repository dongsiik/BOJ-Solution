
// 제목 : 경로 찾기
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/11403
// 메모리(kb) : 16236
// 실행시간(ms) : 188

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 정점 수
		int N = Integer.parseInt(br.readLine());

		// 인접행렬로 포현한 그래프
		int[][] map = new int[N][N];

		// 그래프 입력받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 플로이드 워셜 알고리즘
		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N; i++) {
				for (int k = 0; k < N; k++) {
					if (map[i][j] == 1 && map[j][k] == 1)
						map[i][k] = 1;
				}
			}
		}

		// 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j]).append(' ');
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}
}