
// 제목 : 케빈 베이컨의 6단계 법칙
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/1389
// 메모리(kb) : 14120
// 실행시간(ms) : 136

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 정상적으로 나올 수 없는 두 사람 사이의 거리
	static final int INF = 10000;
	// 사람 수
	static int N;
	// 인접행렬로 표현한 그래프
	static int[][] graph;

	public static void main(String[] args) throws NumberFormatException, IOException {

		init();
		FW();
		getAnswer();
	}

	// 초기화
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 플로이드 워셜 알고리즘을 위해, 무한대로 초기화한 다음 관계 있는 사람들만 1로 설정할 예정
		graph = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++)
				graph[i][j] = INF;

		for (int i = 1; i <= N; i++)
			graph[i][i] = 0;

		// 입력받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph[A][B] = graph[B][A] = 1;
		}

	}

	// 플로이드 워셜 알고리즘
	private static void FW() {
		for (int j = 1; j <= N; j++) {
			for (int i = 1; i <= N; i++) {
				for (int k = 1; k <= N; k++) {
					graph[i][k] = Math.min(graph[i][k], graph[i][j] + graph[j][k]);
				}
			}
		}

	}

	// 사람별로 케빈 베이컨 단계 수 합을 구한 다음, 최솟값과 비교 후 갱신
	private static void getAnswer() {
		int minValue = Integer.MAX_VALUE;
		int minIndex = 0;

		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				sum += graph[i][j];
			}
			if (sum < minValue) {
				minValue = sum;
				minIndex = i;
			}
		}

		System.out.println(minIndex);

	}

}