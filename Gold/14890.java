// 제목 : 경사로
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/14890
// 메모리(kb) : 15040
// 실행시간(ms) : 152

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

	static int N, X;
	static int[][] map;
	static boolean[][] slope;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String args[]) throws IOException {

		input();

		System.out.println(getAnswer());

	}

	// 입력받기
	static void input() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		slope = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	// 경사로 설치하기
	static void setSlope(int cx, int cy, int direction) {

		int nx = cx, ny = cy;

		for (int i = 0; i < X; i++) {
			nx = nx + dx[direction];
			ny = ny + dy[direction];
			slope[nx][ny] = true;
		}
	}

	// 경사로 치우기
	static void clearSlope() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				slope[i][j] = false;
		}
	}

	// 경사로 설치할 수 있는지 확인하기
	static boolean canSetSlope(int cx, int cy, int direction) {

		int nx = cx, ny = cy;

		for (int i = 0; i < X; i++) {
			nx = nx + dx[direction];
			ny = ny + dy[direction];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] != map[cx][cy] - 1 || slope[nx][ny])
				return false;
		}

		return true;
	}

	// 답 구하기
	static int getAnswer() {
		int answer = 0;

		// 행 검사
		rowLoop: for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - 1; j++) {
				if (map[i][j] == map[i][j + 1])
					continue;
				if (Math.abs(map[i][j] - map[i][j + 1]) > 1)
					continue rowLoop;

				if (map[i][j] < map[i][j + 1]) {
					if (canSetSlope(i, j + 1, 2)) {
						setSlope(i, j + 1, 2);
						continue;
					} else {
						continue rowLoop;
					}
				}
				if (map[i][j] > map[i][j + 1]) {
					if (canSetSlope(i, j, 3)) {
						setSlope(i, j, 3);
						continue;
					} else {
						continue rowLoop;
					}
				}

			}
			answer++;
		}

		clearSlope();

		// 열 검사
		columnLoop: for (int j = 0; j < N; j++) {
			for (int i = 0; i < N - 1; i++) {
				if (map[i][j] == map[i + 1][j])
					continue;
				if (Math.abs(map[i][j] - map[i + 1][j]) > 1)
					continue columnLoop;

				if (map[i][j] < map[i + 1][j]) {
					if (canSetSlope(i + 1, j, 0)) {
						setSlope(i + 1, j, 0);
						continue;
					} else {
						continue columnLoop;
					}
				}

				if (map[i][j] > map[i + 1][j]) {
					if (canSetSlope(i, j, 1)) {
						setSlope(i, j, 1);
						continue;
					} else {
						continue columnLoop;
					}
				}

			}
			answer++;
		}

		return answer;
	}

}