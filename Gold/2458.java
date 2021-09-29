// 제목 : 키 순서
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/2458
// 메모리(kb) : 42340
// 실행시간(ms) : 768

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	static final int INF = 1000;

	public static void main(String args[]) throws IOException {
		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 학생 수, 비교한 횟수
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// 플로이드 워셜을 위한 그래프 초기화
		int[][] graph = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++)
			for (int j = 0; j <= n; j++)
				graph[i][j] = INF;

		for (int i = 0; i <= n; i++)
			graph[i][i] = 0;

		// 그래프 입력받기
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a][b] = 0;
		}

		// 폴로이드 워셜 알고리즘
		for (int j = 1; j <= n; j++)
			for (int i = 1; i <= n; i++)
				for (int k = 1; k <= n; k++)
					graph[i][k] = Math.min(graph[i][k], graph[i][j] + graph[j][k]);

		// 자신의 키 순위를 아는 것과, 임의의 다른 학생들과 키 비교가 가능한가는 동치이므로 키 비교가 안 되는 학생들 수를 셈
		int notClear = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (graph[i][j] != 0 && graph[j][i] != 0) {
					notClear++;
					break;
				}
			}
		}

		System.out.println(n - notClear);
	}
}