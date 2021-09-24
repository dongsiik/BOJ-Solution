// 제목 : 미세먼지 안녕!
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/17144
// 메모리(kb) : 26820
// 실행시간(ms) : 296

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 집의 행 크기, 열 크기, 관찰할 시간 t
	static int r, c, t;
	// 집
	static int[][] map;
	// 델타탐색용 배열
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	// 공기청정기 위쪽 행번호
	static int cleanerR = -1;

	public static void main(String[] args) throws IOException {
		// 입력도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 초기화
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		map = new int[r][c];
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1 && cleanerR < 0)
					cleanerR = i;
			}
		}

		// 원하는 시간까지 시뮬레이션
		for (int time = 0; time < t; time++) {
			spreadDust();
			cleanerWorks();
			// printMap();

		}

		// 결과 출력
		System.out.println(getDust());

	}

	// 먼지를 퍼뜨리는 함수
	public static void spreadDust() {
		// 먼지의 변화량을 담는 집 크기의 임시 배열
		int[][] temp = new int[r][c];

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				// 미세먼지가 있는 칸이라면
				if (map[i][j] > 0) {
					// 퍼지는 양
					int dustOverFive = map[i][j] / 5;
					// 퍼지는 방향 수
					int count = 0;
					for (int d = 0; d < 4; d++) {
						int ni = i + dx[d];
						int nj = j + dy[d];
						// 집 안이면서 공기청정기가 아니라면 퍼뜨리고 횟수를 하나 늘려줌
						if (ni >= 0 && ni < r && nj >= 0 && nj < c && map[ni][nj] != -1) {
							count++;
							temp[ni][nj] += dustOverFive;
						}
					}
					// 퍼져나간 만큼 원래 칸의 미세먼지 양을 줄여줌
					temp[i][j] -= dustOverFive * count;
				}
			}
		}

		// 미세먼지 변화량에 따라 미세먼지 상태 갱신
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				map[i][j] += temp[i][j];
			}
		}
	}

	public static void cleanerWorks() {
		// 위쪽 청정기, 시계방향으로 진행하며 빨아들임
		upperCleaner();
		// 아래쪽 청정기, 반시계 방향으로 진행하며 빨아들임
		lowerCleaner();
	}

	// 위쪽 공기청정기 부분의 공기 흐름
	public static void upperCleaner() {
		int direction = -1;
		int d = (4 + direction) % 4;
		// 처음에는 위로 가면서 빨아들임
		int cx = cleanerR + dx[d];
		int cy = dy[d];

		while (true) {
			// 다음 칸 좌표
			int nx = cx + dx[d];
			int ny = cy + dy[d];

			// 다음 칸이 범위를 벗어나면 방향 전환
			if (nx < 0 || nx > cleanerR || ny < 0 || ny >= c) {
				d = (d + 4 + direction) % 4;
				nx = cx + dx[d];
				ny = cy + dy[d];
			}

			// 다음 칸이 공기청정기라면 한 바퀴 돌았으므로 종료
			if (map[nx][ny] == -1) {
				map[cx][cy] = 0;
				break;
			}

			// 현재 칸의 상태를 다음 칸의 상태로 변경(빨아들였으므로)
			map[cx][cy] = map[nx][ny];
			cx = nx;
			cy = ny;

			// printMap();
		}
	}

	// 아래쪽 공기청정기 부분의 흐름. 위쪽과 비슷하므로 설명은 생략
	public static void lowerCleaner() {
		int direction = 1;
		int d = (4 + direction) % 4;
		int cx = cleanerR + 1 + dx[d];
		int cy = dy[d];

		while (true) {
			int nx = cx + dx[d];
			int ny = cy + dy[d];

			if (nx <= cleanerR || nx >= r || ny < 0 || ny >= c) {
				d = (d + 4 + direction) % 4;
				nx = cx + dx[d];
				ny = cy + dy[d];
			}

			if (map[nx][ny] == -1) {
				map[cx][cy] = 0;
				break;
			}

			map[cx][cy] = map[nx][ny];
			cx = nx;
			cy = ny;

			// printMap();
		}
	}

	// 집의 칸마다 먼지를 세서 총량을 구해주는 함수
	public static int getDust() {

		int amountOfDust = 0;

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] > 0)
					amountOfDust += map[i][j];
			}
		}

		return amountOfDust;
	}

	// 디버깅을 위해 집의 먼지 상태를 출력하는 함수
//	public static void printMap() {
//		System.out.println("-------------------맵 출력 시작----------------");
//		for (int i = 0; i < r; i++) {
//			for (int j = 0; j < c; j++) {
//				System.out.print(map[i][j] + "\t");
//			}
//			System.out.println();
//		}
//		System.out.println("-------------------맵 출력 종료----------------");
//		System.out.println();
//	}
}