// 제목 : 달이 차오른다, 가자.
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/1194
// 메모리(kb) : 16300
// 실행시간(ms) : 144

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	// 미로의 세로, 가로 크기
	static int N, M;
	// 미로
	static char[][] map;
	// 델타 탐색용 배열
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String args[]) throws IOException {
		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력 받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][];
		for (int i = 0; i < N; i++)
			map[i] = br.readLine().toCharArray();

		// 시작점 찾기
		Node s = new Node();
		findStartPoint(s);
		// 시작점부터 BFS
		bfs(s);

	}

	// 미로에서 0을 찾아 위치를 정하고 종료
	static void findStartPoint(Node s) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '0') {
					s.x = i;
					s.y = j;
					map[i][j] = '.';
					return;
				}
			}
		}
	}

	// BFS
	static void bfs(Node s) {
		// visited[i][j][keyList] = true는 i,j좌표에 비트마스킹으로 표현한 keyList를 가지고 방문한 적이 있다는 뜻
		boolean[][][] visited = new boolean[N][M][1 << 6];

		// 큐에 시작점을 넣고 방문처리
		Queue<Node> q = new LinkedList<>();
		q.add(s);
		visited[s.x][s.y][0] = true;

		// 큐가 빌 때까지
		while (!q.isEmpty()) {
			// 현재 상태
			Node curNode = q.poll();
			int cx = curNode.x;
			int cy = curNode.y;
			int cTime = curNode.time;
			int cKeyList = curNode.keyList;

			// 4방향 델타탐색
			for (int d = 0; d < 4; d++) {
				// 다음 지점 좌표와 방문하는 시간
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				int nTime = cTime + 1;

				// 배열 범위 안이고, 현재 열쇠 목록을 가지고 방문한 적이 없다면
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny][cKeyList]) {
					// 벽이면 생략
					if (map[nx][ny] == '#')
						continue;
					// 빈 곳이면 현재 열쇠 목록과 함께 큐에 넣고 방문처리
					else if (map[nx][ny] == '.') {
						q.add(new Node(nx, ny, nTime, cKeyList));
						visited[nx][ny][cKeyList] = true;
					}
					// 열쇠라면, 현재 열쇠 목록에 열쇠를 더해 큐에 넣고 방문처리
					else if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
						int key = 1 << (map[nx][ny] - 'a');
						int nKeyList = cKeyList | key;
						if (!visited[nx][ny][nKeyList]) {
							q.add(new Node(nx, ny, nTime, nKeyList));
							visited[nx][ny][nKeyList] = true;
						}
					}
					// 문이라면 맞는 열쇠가 있을 때만 현재 열쇠 목록과 함께 큐에 넣고 방문 처리
					else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
						int lock = 1 << (map[nx][ny] - 'A');
						if ((cKeyList & lock) != 0) {
							q.add(new Node(nx, ny, nTime, cKeyList));
							visited[nx][ny][cKeyList] = true;
						}
					}
					// 탈출구라면 방문 시각 출력 후 종료
					else if (map[nx][ny] == '1') {
						System.out.println(nTime);
						return;
					}
				}

			}
		}

		// 여기까지 왔으면 탈출 못 했으므로 -1 출력
		System.out.println(-1);
	}

	// 상태를 표시하는 Node 클래스
	static class Node {
		int x;
		int y;
		int time;
		int keyList;

		public Node() {
		};

		public Node(int x, int y, int time, int keyList) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.keyList = keyList;
		}

	}
}