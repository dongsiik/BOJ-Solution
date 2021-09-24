// 제목 : 외판원 순회
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/2098
// 메모리(kb) : 19948
// 실행시간(ms) : 208

// 참고한 링크 : https://velog.io/@jini_eun/%EB%B0%B1%EC%A4%80-2098%EB%B2%88-%EC%99%B8%ED%8C%90%EC%9B%90-%EC%88%9C%ED%9A%8C-Java-Python

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	// 회사를 포함한 장소 수, 최소 비용
	static int n;
	// 그래프
	static int[][] graph;
	// dp 배열
	static int[][] dp;
	// 16개의 도시를 최대비용으로 여행했을 때 나오는 총 비용의 최댓값
	static final int INF = 16_000_000;

	public static void main(String[] args) throws IOException {

		// 입력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 회사를 포함한 장소의 수
		n = Integer.parseInt(br.readLine());

		// 그래프 입력받기
		graph = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// dp 배열 초기화
		// dp[i][j]는 i번째 도시를 마지막으로 방문했고, j번 도시들을 방문한 상태(비트마스킹)일 때
		// 순회를 마치기 위해 앞으로 필요한 비용의 최솟값
		dp = new int[n][(1 << n) - 1];
		for (int i = 0; i < n; i++)
			Arrays.fill(dp[i], INF);

		// 출력
		System.out.println(dfs(0, 1));
	}

	// 매개변수는 각각 최근에 방문한 도시 번호, 방문한 도시 목록임
	static int dfs(int recentlyVisited, int visited) {

		// 모든 도시들을 방문했을 때
		if (visited == (1 << n) - 1) {
			// 마지막으로 방문한 도시에서 시작점인 0번까지 갈 수 없다면 INF return, 아니라면 0번까지의 거리 return
			if (graph[recentlyVisited][0] == 0)
				return INF;
			else
				return graph[recentlyVisited][0];
		}

		// 이미 처리한 적이 있다면 그 값을 반환
		if (dp[recentlyVisited][visited] != INF)
			return dp[recentlyVisited][visited];

		// 다른 도시들에 대해
		for (int i = 1; i < n; i++) {
			// 방문한 적 없고, 최근에 방문한 도시와 연결되어 있다면
			if ((visited & 1 << i) == 0 && graph[recentlyVisited][i] != 0) {
				// 다음 도시를 그 도시로 택하는 경우와 비교 후 갱신
				dp[recentlyVisited][visited] = Math.min(dp[recentlyVisited][visited],
						dfs(i, visited | (1 << i)) + graph[recentlyVisited][i]);
			}
		}

		return dp[recentlyVisited][visited];
	}

}