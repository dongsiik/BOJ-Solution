// 제목 : 톱니바퀴
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/14891
// 메모리(kb) : 14320
// 실행시간(ms) : 204

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {
		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 자석들
		Magnet[] chain = new Magnet[4];


		// 자석들 정보 입력받기
		for (int i = 0; i < 4; i++) {
			int[] blades = new int[8];
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				blades[j] = str.charAt(j)-'0';
			}
			chain[i] = new Magnet(blades);
		}

		// 회전 수
		int K = Integer.parseInt(br.readLine());

		// 회전 시키기
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			// 회전 시킬 자석 번호와 방향
			int magnetNum = Integer.parseInt(st.nextToken()) - 1;
			int direction = Integer.parseInt(st.nextToken());

			// 연결되었는지 확인하면서 회전할 방향 설정
			setDirections(chain, magnetNum, direction);
			// 회전
			rotateAll(chain);
		}

		// 점수 계산
		int answer = getAnswer(chain);

		System.out.println(answer);
	}

	/**
	 * 
	 * @param chain            : 자석 목록
	 * @param magnetNum        : 회전하기 시작하는 번호
	 * @param initialDirection : 처음 방향
	 */
	static void setDirections(Magnet[] chain, int magnetNum, int initialDirection) {
		// BFS로 방향 설정했습니다.

		int[] dx = { -1, 1 };

		// 방문 여부 배열과 큐
		boolean[] visited = new boolean[4];
		Queue<int[]> q = new LinkedList<>();

		// 첫 지점의 방향을 설정하고, 방문 처리하고, 큐에 넣어줌
		chain[magnetNum].direction = initialDirection;
		visited[magnetNum] = true;
		q.add(new int[] { magnetNum, initialDirection });

		while (!q.isEmpty()) {
			// 현재 자석
			int[] current = q.poll();
			int currentMagnetNum = current[0];
			int currentDirecton = current[1];

			// 양 옆 자석
			for (int d = 0; d < 2; d++) {
				int nextMagnetNum = currentMagnetNum + dx[d];
				int nextDirection = currentDirecton * (-1);
				// 배열 범위 안이면서, 방문하지 않았고, 지금 자석과 연결되어있다면
				if (nextMagnetNum >= 0 && nextMagnetNum < 4 && !visited[nextMagnetNum]
						&& isConnected(chain, currentMagnetNum, nextMagnetNum)) {
					// 방향 설정, 방문처리, 큐에 넣기
					chain[nextMagnetNum].direction = nextDirection;
					visited[nextMagnetNum] = true;
					q.add(new int[] { nextMagnetNum, nextDirection });
				}
			}
		}
	}

	/**
	 * 
	 * @param chain 자석 배열
	 * @param i1    자석 번호 1
	 * @param i2    자석 번호 2
	 * @return 두 자석이 연결되어있는지
	 */
	static boolean isConnected(Magnet[] chain, int i1, int i2) {
		Magnet m1 = chain[i1];
		Magnet m2 = chain[i2];
		if (i1 < i2) {
			if (m1.getRight() != m2.getLeft())
				return true;
			return false;
		} else {
			if (m1.getLeft() != m2.getRight())
				return true;
			return false;
		}
	}

	// 모든 자석 회전
	static void rotateAll(Magnet[] chain) {
		for (Magnet m : chain)
			m.rotate();
	}

	/**
	 * @param chain 자석 목록
	 * @return 점수
	 */
	static int getAnswer(Magnet[] chain) {
		int answer = 0;

		for (int i = 0; i < 4; i++) {
			if (chain[i].blades[chain[i].top] == 1)
				answer += (int) Math.pow(2, i);
		}

		return answer;
	}

	// 자석 클래스
	static class Magnet {
		// 배열로 구현한 유사 큐, top이 제일 상단부에 위치한 자석을 가리킴
		int[] blades;
		int top;
		// 회전할 방향
		int direction;

		public Magnet(int[] blades) {
			super();
			this.blades = blades;
			this.top = 0;
			this.direction = 0;
		}

		// 상단부를 맞춰주고, 회전방향을 0으로 맞춤
		void rotate() {
			top = (top - direction + 8) % 8;
			direction = 0;
		}

		// 왼쪽 오른쪽 자석 반환
		int getLeft() {
			return blades[(top + 6) % 8];
		}

		int getRight() {
			return blades[(top + 2) % 8];
		}
	}
}