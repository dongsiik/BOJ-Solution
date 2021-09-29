// 제목 : 열쇠
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/9328
// 메모리(kb) : 26840
// 실행시간(ms) : 288

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main {

	// 빌딩의 세로, 가로 크기
	static int h, w;
	// 빌딩
	static char[][] map;
	// 출력할 때 쓸 StringBuilder
	static StringBuilder sb;

	public static void main(String args[]) throws IOException {
		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		// 테스트 케이스 수
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < TC; tc++) {
			// 입력 받기
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			// 모서리를 .으로 감싸서 받을 예정
			map = new char[h + 2][w + 2];

			// 빌딩 입력받기
			for (int i = 1; i <= h; i++) {
				String str = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j + 1] = str.charAt(j);
				}
			}

			// 테두리 포장
			for (int i = 0; i < h + 2; i++) {
				map[i][0] = '.';
				map[i][w + 1] = '.';
			}

			for (int j = 1; j < w + 1; j++) {
				map[0][j] = '.';
				map[h + 1][j] = '.';
			}

			// 비트마스킹으로 열쇠 목록 저장
			int keyList = 0;
			String keyListString = br.readLine();
			if (!keyListString.equals("0")) {
				for (int i = 0; i < keyListString.length(); i++) {
					char key = keyListString.charAt(i);
					keyList |= keyCharToInt(key);
				}
			}

			// BFS
			bfs(keyList);
		}

		System.out.println(sb);

	}

	// 열쇠를 비트로 표현하기
	static int keyCharToInt(char c) {
		return 1 << (c - 'a');
	}

	// 문을 비트로 표현하기
	static int lockCharToInt(char c) {
		return 1 << (c - 'A');
	}

	// BFS
	static void bfs(int keyList) {

		// 델타 탐색용 배열
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		// 훔친 문서 수
		int numOfDocuments = 0;

		outerLoop: while (true) {

			// 현재 열쇠 목록
			int oldKey = keyList;

			// BFS용 큐와 방문여부 배열
			ArrayDeque<Node> q = new ArrayDeque<>();
			boolean[][] visited = new boolean[h + 2][w + 2];

			// 0,0부터 시작(.으로 된 포장지 왼쪽 위 끝)
			q.add(new Node(0, 0));
			visited[0][0] = true;

			while (!q.isEmpty()) {
				Node current = q.poll();
				int cx = current.x;
				int cy = current.y;

				for (int d = 0; d < 4; d++) {
					int nx = cx + dx[d];
					int ny = cy + dy[d];
					// 접근가능하다면
					if (accessible(nx, ny, visited, keyList)) {
						// 평지로 만들고, 무슨 곳이었는지 판단
						char nextItem = map[nx][ny];
						map[nx][ny] = '.';

						// 문서였다면 문서 개수 하나 증가
						if (nextItem == '$')
							numOfDocuments++;
						// 열쇠였다면 획득
						else if (Character.isLowerCase(nextItem)) {
							keyList |= keyCharToInt(nextItem);
						}

						visited[nx][ny] = true;
						q.add(new Node(nx, ny));

					}
				}
			}

			// 새로운 열쇠를 얻었다면 다시 반복, 아니라면 종료
			if (keyList != oldKey)
				continue outerLoop;
			break;
		}

		// 출력문에 훔친 문서 수 저장
		sb.append(numOfDocuments).append('\n');

	}

	// 어떤 칸에 갈 수 있는지 판별
	static boolean accessible(int nx, int ny, boolean[][] visited, int keyList) {
		// 지도 안이고 방문하지 않았어야함
		if (nx >= 0 && nx < h + 2 && ny >= 0 && ny < w + 2 && !visited[nx][ny]) {
			// 벽이면 안 됨
			if (map[nx][ny] == '*')
				return false;
			// 열수 없는 문이면 안 됨
			if (Character.isUpperCase(map[nx][ny]) && ((lockCharToInt(map[nx][ny]) & keyList) == 0))
				return false;

			// 그 외에는 갈 수 있음
			return true;
		}

		return false;
	}

	// 좌표를 저장하는 Node 클래스
	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}