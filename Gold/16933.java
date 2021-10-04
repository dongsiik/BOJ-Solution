// 제목 : 벽 부수고 이동하기 3
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/16933
// 메모리(kb) : 312944
// 실행시간(ms) : 1404

// 밤에 벽을 만났을 때, 큐로 BFS를 돌면서 제자리에서 하루 기다리는 경우입니다.
// 우선순위 큐로 BFS를 돌면서 2일 시간이 걸리는 상황을 넣고 우선순위 큐에서 정렬하는 코드(1868ms)보다 빠릅니다.

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
		Queue<Node> q = new LinkedList<>();

		// 1초에 0,0을 k번 벽 부술 기회를 가지고 방문처리 하고 시작
		visited[0][0] = k;
		q.offer(new Node(0, 0, k, 1));

		while (!q.isEmpty()) {

			// 현재 점의 정보, z는 여기까지 오면서 벽을 뚫은 적이 있었는지를 나타냄
			Node cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int chance = cur.chance;
			int curDist = cur.distance;

			// 도착시 시간 출력 후 종료
			if (x == n - 1 && y == m - 1) {
				System.out.println(curDist);
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
					if (graph[nx][ny] == '0' && visited[nx][ny] < chance) {
						q.offer(new Node(nx, ny, chance, curDist + 1));
						visited[nx][ny] = chance;
					}
					// 벽인데 벽을 뚫을 기회가 남았고, 남은 기회가 지금보다 많게 방문한 적이 없다면
					else if (graph[nx][ny] == '1' && chance > 0 && visited[nx][ny] < chance - 1) {
						//낮이면 부수고 이동
						if(curDist%2==1) {
							q.offer(new Node(nx,ny,chance-1,curDist+1));
							visited[nx][ny] = chance -1;
						}
						//밤이면 제자리에서 하루 기다림
						else {
							q.offer(new Node(x,y,chance,curDist+1));
						}
					}
				}
			}

		}

		System.out.println(-1);

	}

	//x,y 좌표, 벽을 부술 기회가 몇번 남았는지, 시작점에서부터 여기까지 걸린 거리
	static class Node {
		int x, y, chance, distance;

		public Node(int x, int y, int chance, int distance) {
			super();
			this.x = x;
			this.y = y;
			this.chance = chance;
			this.distance = distance;
		}

	}

}