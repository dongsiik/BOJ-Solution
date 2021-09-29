// 제목 : 녹색 옷 입은 애가 젤다지?
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/4485
// 메모리(kb) : 19512
// 실행시간(ms) : 228


//아이디어 : 도둑루피를 최소로 획득하면서 0,0에서 N-1,N-1까지 가는 문제이므로, 한 점에서 다른 점들까지 최소 비용 경로를 찾는 다익스트라 알고리즘을 적용한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	// 도둑루피를 9개씩 125*125칸을 먹어도 나올 수 없는 큰 값을 무한대로 했음
	static final int INF = 200000;
	// 델타 탐색용 배열
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String args[]) throws IOException {
		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 번호
		int tcNum = 1;

		while (true) {
			// 동굴의 크기 입력받기
			int N = Integer.parseInt(br.readLine());

			// 0이면 반복문 종료
			if (N == 0)
				break;

			// 동굴 정보 입력받기
			int[][] cave = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 다익스트라 알고리즘에서 현재까지 구한 최소 비용과 같은 역할을 할 도둑루피의 누적합 배열
			int[][] sumOfBlackRupee = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sumOfBlackRupee[i][j] = INF;
				}
			}

			// 다익스트라 알고리즘 적용을 위한 우선순위 큐
			PriorityQueue<Point> pq = new PriorityQueue<>();
			// 0,0에서 cave[0][0]만큼의 도둑루피를 획득한 상태로 시작
			pq.add(new Point(0, 0, cave[0][0]));

			while (!pq.isEmpty()) {
				// 현재 지점의 x,y,도둑루피 누적 합
				Point current = pq.poll();
				int cx = current.x;
				int cy = current.y;
				int cRupee = current.sumOfBlackRupee;

				// 이미 처리한 곳이라면 생략
				if (sumOfBlackRupee[cx][cy] < cRupee)
					continue;

				// 도착지면 다익스트라 알고리즘 종료
				if (cx == N - 1 && cy == N - 1)
					break;

				// 4방향 델타 탐색
				for (int d = 0; d < 4; d++) {
					// 인접한 방향
					int nx = cx + dx[d];
					int ny = cy + dy[d];

					// 다음 지점이 동굴 범위 안이면서
					if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
						int nRupee = cRupee + cave[nx][ny];
						// 현재 점을 지나가는 게 더 도둑루피를 덜 획득하는 경우, 도둑루피 누적값을 갱신하고 큐에 넣어줌
						if (sumOfBlackRupee[nx][ny] > nRupee) {
							sumOfBlackRupee[nx][ny] = nRupee;
							pq.add(new Point(nx, ny, nRupee));
						}
					}
				}

			}

			// 출력문 저장
			sb.append("Problem ").append(tcNum++).append(": ").append(sumOfBlackRupee[N - 1][N - 1]).append('\n');

		}

		// 출력
		System.out.println(sb);
	}

	// x,y,현재까지 도둑루피 누적합을 저장하는 Point 객체
	static class Point implements Comparable<Point> {
		int x;
		int y;
		int sumOfBlackRupee;

		public Point(int x, int y, int sumOfBlackRupee) {
			this.x = x;
			this.y = y;
			this.sumOfBlackRupee = sumOfBlackRupee;
		}

		// 우선순위 큐에 넣기 위해 compareTo를 구현함
		@Override
		public int compareTo(Point o) {
			return this.sumOfBlackRupee - o.sumOfBlackRupee;
		}

	}
}