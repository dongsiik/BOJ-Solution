// 제목 : 맥주 마시면서 걸어가기
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/9205
// 메모리(kb) : 20000
// 실행시간(ms) : 248

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 수
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			// 편의점 수 입력받기
			int n = Integer.parseInt(br.readLine());
			// 시작점, 편의점, 도착점의 x,y 좌표를 저장할 배열
			int[][] points = new int[n + 2][2];
			for (int i = 0; i < n + 2; i++) {
				st = new StringTokenizer(br.readLine());
				points[i][0] = Integer.parseInt(st.nextToken());
				points[i][1] = Integer.parseInt(st.nextToken());
			}

			// 좌표 차이를 이용해서 거리 구하고 저장하기
			int[][] graph = new int[n + 2][n + 2];
			for (int i = 0; i < n + 2; i++) {
				for (int j = 0; j < n + 2; j++) {
					graph[i][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
					// 거리가 20병*50(m/병)=1000m 이하인 곳들은 맥주를 리필하면 되므로 거리를 0으로 둠
					if (graph[i][j] <= 1000)
						graph[i][j] = 0;
				}
			}

			// 플로이드 워샬
			for (int i = 0; i < n + 2; i++) {
				for (int j = 0; j < n + 2; j++) {
					for (int k = 0; k < n + 2; k++) {
						graph[j][k] = Math.min(graph[j][k], graph[j][i] + graph[i][k]);
					}
				}
			}

			// 출력문 저장
			if (graph[0][n + 1] == 0)
				sb.append("happy\n");
			else
				sb.append("sad\n");

		}

		// 출력
		System.out.println(sb);
	}
}