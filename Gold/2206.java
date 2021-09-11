// 제목 : 벽 부수고 이동하기
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/2206
// 메모리(kb) : 113752
// 실행시간(ms) : 656

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
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

		char[][] graph = new char[n][];
		for (int i = 0; i < n; i++)
			graph[i] = br.readLine().toCharArray();

		// 방문여부 배열, 마지막 인덱스는 벽을 뚫은 적이 있는지 없는지를 나타냅니다.
		boolean[][][] visited = new boolean[n][m][2];
		// BFS를 위한 큐
		ArrayDeque<int[]> q = new ArrayDeque<>();

		// 1초에 0,0을 방문처리 하고 시작
		int time = 1;
		visited[0][0][0] = true;
		q.push(new int[] { 0, 0, 0 });

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

					if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
						// 평지라면 바로 넣어줌
						if (graph[nx][ny] == '0' && !visited[nx][ny][z]) {
							q.offer(new int[] { nx, ny, z });
							visited[nx][ny][z] = true;
						}
						// 벽인데 벽을 뚫은 적이 없다면 벽을 뚫고 넣어줌
						else if (graph[nx][ny] == '1' && !visited[nx][ny][z] && z == 0) {
							q.offer(new int[] { nx, ny, 1 });
							visited[nx][ny][1] = true;
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