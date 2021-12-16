
// 제목 : 물대기
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/1368
// 메모리(kb) : 23700
// 실행시간(ms) : 268

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 논 수
		int N = Integer.parseInt(br.readLine());

		// 비용 배열
		int[][] cost = new int[N + 1][N + 1];

		// 지하수맥을 0번 논으로 간주
		for (int i = 1; i <= N; i++) {
			int c = Integer.parseInt(br.readLine());
			cost[0][i] = cost[i][0] = c;
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 프림 알고리즘 - O(N^2)
		int[] connectedCost = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			connectedCost[i] = Integer.MAX_VALUE;
		}

		int answer = 0;

		boolean[] connected = new boolean[N + 1];

		for (int i = 0; i <= N; i++) {
			int minCost = Integer.MAX_VALUE;
			int minIndex = -1;

			for (int j = 0; j <= N; j++) {
				if (!connected[j] && connectedCost[j] < minCost) {
					minCost = connectedCost[j];
					minIndex = j;
				}
			}

			connected[minIndex] = true;
			answer += minCost;

			for (int j = 0; j <= N; j++) {
				connectedCost[j] = Math.min(connectedCost[j], cost[minIndex][j]);
			}
		}

		System.out.println(answer);
	}

}