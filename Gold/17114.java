// 제목 : 하이퍼 토마토
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/17114
// 메모리(kb) : 606952
// 실행시간(ms) : 2416

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	// 델타 탐색용 배열
	static int[] dm = { -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	static int[] dn = { 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	static int[] doo = { 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	static int[] dp = { 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	static int[] dq = { 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	static int[] dr = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	static int[] ds = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0 };
	static int[] dt = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0 };
	static int[] du = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0 };
	static int[] dv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0 };
	static int[] dw = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1 };

	// 11차원 토마토 창고
	static int[][][][][][][][][][][] box;
	// 창고의 각 변의 크기
	static int M, N, O, P, Q, R, S, T, U, V, W;

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 창고 크기 입력받기
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		O = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		box = new int[M][N][O][P][Q][R][S][T][U][V][W];

		// 안 익은 토마토 수
		int numOfUnripeTomato = 0;
		// 날짜
		int day = 0;
		// BFS용 큐
		ArrayDeque<Tomato> queue = new ArrayDeque<>();

		for (int w = 0; w < W; w++) {
			for (int v = 0; v < V; v++) {
				for (int u = 0; u < U; u++) {
					for (int t = 0; t < T; t++) {
						for (int s = 0; s < S; s++) {
							for (int r = 0; r < R; r++) {
								for (int q = 0; q < Q; q++) {
									for (int p = 0; p < P; p++) {
										for (int o = 0; o < O; o++) {
											for (int n = 0; n < N; n++) {
												st = new StringTokenizer(br.readLine());
												for (int m = 0; m < M; m++) {
													box[m][n][o][p][q][r][s][t][u][v][w] = Integer
															.parseInt(st.nextToken());
													// 안 익은 토마토라면, 안 익은 토마토 개수를 하나 증가
													if (box[m][n][o][p][q][r][s][t][u][v][w] == 0)
														numOfUnripeTomato++;
													// 익은 토마토라면, 0초에 익었다며 큐에 넣어줌
													if (box[m][n][o][p][q][r][s][t][u][v][w] == 1)
														queue.add(new Tomato(m, n, o, p, q, r, s, t, u, v, w, 0));
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		// BFS
		while (!queue.isEmpty()) {
			// 현재 토마토
			Tomato t = queue.poll();

			// 현재 시각 갱신
			day = Math.max(day, t.dayOfRipening);

			// 인접한 22방향에 대해서
			for (int d = 0; d < 22; d++) {
				int mNext = t.m + dm[d];
				int nNext = t.n + dn[d];
				int oNext = t.o + doo[d];
				int pNext = t.p + dp[d];
				int qNext = t.q + dq[d];
				int rNext = t.r + dr[d];
				int sNext = t.s + ds[d];
				int tNext = t.t + dt[d];
				int uNext = t.u + du[d];
				int vNext = t.v + dv[d];
				int wNext = t.w + dw[d];

				// 안 이은 토마토라면, 안 익은 토마토 개수를 하나 줄이고, 익히고, 큐에 넣어줌
				if (isNextTomatoUnripe(mNext, nNext, oNext, pNext, qNext, rNext, sNext, tNext, uNext, vNext, wNext)) {
					numOfUnripeTomato--;
					box[mNext][nNext][oNext][pNext][qNext][rNext][sNext][tNext][uNext][vNext][wNext] = 1;
					queue.add(new Tomato(mNext, nNext, oNext, pNext, qNext, rNext, sNext, tNext, uNext, vNext, wNext,
							t.dayOfRipening + 1));
				}
			}
		}

		// 안 익은 토마토가 없다면 시간 출력, 아니라면 -1 출력
		if (numOfUnripeTomato == 0)
			System.out.println(day);
		else
			System.out.println(-1);
	}

	// 다음 칸이 창고 범위 안이면서 안 익은 토마토인지를 판별하는 함수
	// 사실 별 건 아닌데 조건이 하도 길어져서 보기 흉해서 분리했습니다.
	static boolean isNextTomatoUnripe(int mNext, int nNext, int oNext, int pNext, int qNext, int rNext, int sNext,
			int tNext, int uNext, int vNext, int wNext) {
		if (mNext >= 0 && mNext < M && nNext >= 0 && nNext < N && oNext >= 0 && oNext < O && pNext >= 0 && pNext < P
				&& qNext >= 0 && qNext < Q && rNext >= 0 && rNext < R && sNext >= 0 && sNext < S && tNext >= 0
				&& tNext < T && uNext >= 0 && uNext < U && vNext >= 0 && vNext < V && wNext >= 0 && wNext < W
				&& box[mNext][nNext][oNext][pNext][qNext][rNext][sNext][tNext][uNext][vNext][wNext] == 0) {
			return true;
		}

		else
			return false;
	}

	// 토마토 클래스. 좌표와 익은 날짜를 저장함
	static class Tomato {
		int m;
		int n;
		int o;
		int p;
		int q;
		int r;
		int s;
		int t;
		int u;
		int v;
		int w;
		int dayOfRipening;

		public Tomato(int m, int n, int o, int p, int q, int r, int s, int t, int u, int v, int w, int dayOfRipening) {
			super();
			this.m = m;
			this.n = n;
			this.o = o;
			this.p = p;
			this.q = q;
			this.r = r;
			this.s = s;
			this.t = t;
			this.u = u;
			this.v = v;
			this.w = w;
			this.dayOfRipening = dayOfRipening;
		}

	}
}