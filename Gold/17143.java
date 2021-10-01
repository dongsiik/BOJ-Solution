// 제목 : 낚시왕
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/17143
// 메모리(kb) : 25928
// 실행시간(ms) : 360

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 지도 크기, 상어 수, 낚은 상어 크기 총합
	static int R, C, M, answer;

	// 델타 탐색용 배열
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 1, -1 };

	// 지도
	static Shark[][] map;

	public static void main(String[] args) throws IOException {

		// 입력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력받기
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new Shark[R][C];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1; // 1부터 시작하므로 1을 뺐음
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			Shark shark = new Shark(r, c, s, d, z);
			map[r][c] = shark;
		}

		answer = 0;

		// 낚시왕이 열을 옮겨다니며 낚시하고, 상어들이 이동함
		for (int c = 0; c < C && M > 0; c++) {
			fishing(c);
			moveAll();
		}

		// 출력
		System.out.println(answer);

	}

	// c열에서 낚시하기
	private static void fishing(int c) {
		for (int r = 0; r < R; r++) {
			// 상어가 보이면
			if (map[r][c] != null) {
				// 낚은 상어 크기 총합을 늘리고, 맵에서 제거하고, 상어 수를 하나 줄이고 종료
				Shark target = map[r][c];
				answer += target.z;
				map[r][c] = null;
				M--;
				return;
			}
		}
	}

	// 상어 이동 시키기
	private static void moveAll() {
		// 상어가 없다면 아무 것도 안 함
		if (M == 0)
			return;

		// 상어가 이동을 마친 후에 잡아먹기 시작하므로 새로운 맵을 만들어서 하나하나 옮김
		Shark[][] newMap = new Shark[R][C];

		// 이전 맵을 돌아보며 상어가 있다면 새로운 맵으로 옮겨줄 예정
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {

				if (map[i][j] == null)
					continue;

				// 이전 맵에 있는 상어의 새로운 위치를 계산
				Shark s = map[i][j];
				move(s);

				// 다른 상어가 있다면
				if (newMap[s.r][s.c] != null) {
					// 일단 둘 중 하나는 잡아먹히니 상어 수 감소
					M--;

					// 전임자보다 레벨이 높으면 자리를 차지하고, 아니면 말음
					Shark previousOne = newMap[s.r][s.c];
					if (previousOne.z < s.z) {
						newMap[s.r][s.c] = s;
					}
				}
				// 다른 상어가 없다면 자리 차지
				else {
					newMap[s.r][s.c] = s;
				}
			}
		}

		// 맵 갱신
		map = newMap;

	}

	// 상어 움직이기. 10158번 개미 문제 참고
	private static void move(Shark s) {
		int nr = (s.r + s.s * dr[s.d]) % (2 * R - 2);
		if (nr < 0)
			nr += 2 * R - 2;
		if (nr >= R) {
			nr = 2 * R - 2 - nr;
			s.d = 3 - s.d;
		}

		int nc = (s.c + s.s * dc[s.d]) % (2 * C - 2);
		if (nc < 0)
			nc += 2 * C - 2;
		if (nc >= C) {
			nc = 2 * C - 2 - nc;
			s.d = 7 - s.d;
		}

		s.r = nr;
		s.c = nc;

	}

	// 상어 클래스
	static class Shark {
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

	}
}