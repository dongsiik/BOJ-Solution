// 제목 : 아기 상어
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/16236
// 메모리(kb) : 14740
// 실행시간(ms) : 136

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	// 공간 한 변의 길이
	static int n;
	// 공간
	static int[][] board;
	// 아기 상어의 좌표와 크기, 지금 크기에서 먹은 물고기 수
	static int x, y, level, exp;

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입력받기
		input();

		// 총 걸린 시간
		int totalTime = 0;
		while (true) {
			// 다음 물고기까지 시간 구하기
			int currentTime = findFish();
			// 다음 물고기가 없으면 종료
			if (currentTime == -1)
				break;
			// 다음 물고기가 있으면, 그때까지 걸린 시간만큼 증가
			else
				totalTime += currentTime;
		}

		// 총 시간 출력
		System.out.println(totalTime);
	}

	static void input() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 공간 크기 입력받아 공간 만들기
		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		// 상어의 상태 초기화
		level = 2;
		exp = 0;

		// 점마다 정보 입력받기
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int num = Integer.parseInt(st.nextToken());
				// 상어라면 공간에는 표시 안 하고 좌표만 따로 기록
				if (num == 9) {
					x = i;
					y = j;
				} else
					board[i][j] = num;
			}
		}
	}

	static int findFish() {
		// 델타탐색용 배열
		int[] dx = { -1, 0, 0, 1 };
		int[] dy = { 0, -1, 1, 0 };

		// 방문여부
		boolean[][] visited = new boolean[n][n];
		// 상어의 까다로운 먹잇감 위치 선정을 위한 Comparator 객체와 우선순위 큐
		MyComparator myComparator = new MyComparator();
		PriorityQueue<int[]> q = new PriorityQueue<>(myComparator);
		// 시작점을 방문처리하고 큐에 넣음
		visited[x][y] = true;
		q.add(new int[] { x, y, 0 });

		while (!q.isEmpty()) {
			// 현재 점 정보
			int[] current = q.poll();
			int cx = current[0];
			int cy = current[1];
			int cTime = current[2];

			// 현재 점에 자신보다 작은 물고기가 있다면 그때까지 걸린 시간을 반환하고 종료
			if (eatFish(cx, cy)) {
				return cTime;
			}

			for (int i = 0; i < 4; i++) {
				// 4방향 둘러보기
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				// 공간 안이고, 자신보다 쎈 물고기가 아니라서 지나갈 수 있고, 아직 지나간 적이 없다면 방문처리하고 큐에 넣기
				if (nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] <= level && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new int[] { nx, ny, cTime + 1 });
				}
			}
		}

		// 여기까지 왔으면 물고기를 못 찾았다는 뜻으로 -1 리턴
		return -1;
	}

	// 매개변수로 받은 점에서 잡아먹을 물고기가 있는지 판단
	static boolean eatFish(int nx, int ny) {
		// 빈 공간이 아니면서 자신보다 레벨이 낮은 물고기라면
		if (board[nx][ny] > 0 && board[nx][ny] < level) {
			// 잡아먹어서 지도에서 치우고, 그 자리로 이동하고, 잡아먹은 횟수를 늘림
			board[nx][ny] = 0;
			x = nx;
			y = ny;
			exp++;
			// 조건을 만족하면 크기가 커짐
			if (exp == level) {
				level++;
				exp = 0;
			}

			return true;
		}

		return false;
	}

	// 먹잇감 위치 취향을 존중하기 위한 Comparator
	static class MyComparator implements Comparator<int[]> {

		@Override
		public int compare(int[] o1, int[] o2) {
			// 총 거리를 우선 비교해보고
			if (o1[2] != o2[2])
				return o1[2] - o2[2];
			// x좌표(세로 위치)를 비교해보고
			else if (o1[0] != o2[0])
				return o1[0] - o2[0];
			// y좌표(가로 위치)를 비교해봄
			else
				return o1[1] - o2[1];
		}

	}
}