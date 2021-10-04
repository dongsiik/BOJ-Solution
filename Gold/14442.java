// 제목 : 벽 부수고 이동하기 2
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/14442
// 메모리(kb) : 312324
// 실행시간(ms) : 1128

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 델타 탐색
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		char[][] graph = new char[n][];
		for (int i = 0; i < n; i++)
			graph[i] = br.readLine().toCharArray();

		// 방문여부 배열, 방문하지 않았다면 -1, 방문했다면 벽 부수기 기회를 몇 번 남기고 방문했는지를 나타냅니다.
		int[][] visited = new int[n][m];
		for (int i = 0; i < n; i++) {
			Arrays.fill(visited[i], -1);
		}

		// BFS를 위한 큐
		Queue<int[]> q = new LinkedList<int[]>();

		// 1초에 0,0을 k번 벽 부술 기회를 가지고 방문처리 하고 시작
		int time = 1;
		visited[0][0] = k;
		q.offer(new int[] { 0, 0, k });

		while (!q.isEmpty()) {
			// 현재 시간에 방문하는 큐들을 같이 처리하기 위해 2중 반복문을 돌았습니다.
			int size = q.size();

			for (int i = 0; i < size; i++) {

				// 현재 점의 정보, z는 여기까지 오면서 벽을 뚫은 적이 있었는지를 나타냄
				int[] cur = q.poll();
				int x = cur[0];
				int y = cur[1];
				int z = cur[2];

				// 도착시 시간 출력 후 종료
				if (x == n - 1 && y == m - 1) {
					System.out.println(time);
					return;
				}

				// 델타 탐색
				for (int d = 0; d < 4; d++) {
					// 다음 지점
					int nx = x + dx[d];
					int ny = y + dy[d];

					// 벽 범위 안이고
					if (nx >= 0 && nx < n && ny >= 0 && ny < m) {

						// 평지이고, 지금보다 벽을 적게 뚫고 방문한 적이 없다면
						if (graph[nx][ny] == '0' && visited[nx][ny] < z) {
							q.offer(new int[] { nx, ny, z });
							visited[nx][ny] = z;
						}
						// 벽인데 벽을 뚫을 기회가 남았고, 남은 기회가 지금보다 많게 방문한 적이 없다면
						else if (graph[nx][ny] == '1' && z > 0 && visited[nx][ny] < z - 1) {
							q.offer(new int[] { nx, ny, z - 1 });
							visited[nx][ny] = z - 1;
							;
						}
					}
				}
			}

			// 시간 증가
			time++;

		}

		System.out.println(-1);

	}

}