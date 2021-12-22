
// 제목 : 우주신과의 교감 
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/1774
// 메모리(kb) : 23928
// 실행시간(ms) : 268

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 노드의 수, 이미 연결된 간선의 수
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 좌표 기록
		int[][] coordinates = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			coordinates[i][0] = Integer.parseInt(st.nextToken());
			coordinates[i][1] = Integer.parseInt(st.nextToken());
		}

		// 좌표를 거리로 변환
		double[][] distance = new double[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				distance[i][j] = distance[j][i] = getDistance(coordinates, i, j);
			}
		}

		// 연결된 간선을 입력받아 둘 사이의 거리를 0으로 만들기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
			distance[A][B] = distance[B][A] = 0;
		}

		// 프림 알고리즘 - O(N^2)
		double[] connectedDistance = new double[N];
		for (int i = 1; i < N; i++) {
			connectedDistance[i] = Double.MAX_VALUE;
		}

		boolean[] visited = new boolean[N];

		double answer = 0;

		for (int i = 0; i < N; i++) {
			int minIndex = -1;
			double minDistance = Double.MAX_VALUE;

			for (int j = 0; j < N; j++) {
				if (!visited[j] && connectedDistance[j] < minDistance) {
					minIndex = j;
					minDistance = connectedDistance[j];
				}
			}

			visited[minIndex] = true;
			answer += minDistance;

			for (int j = 0; j < N; j++) {
				connectedDistance[j] = Math.min(connectedDistance[j], distance[minIndex][j]);
			}
		}

		// 출력
		System.out.printf("%.2f", answer);

	}

	static double getDistance(int[][] distance, int i, int j) {
		return Math.sqrt(Math.pow(distance[i][0] - distance[j][0], 2) + Math.pow(distance[i][1] - distance[j][1], 2));
	}

}