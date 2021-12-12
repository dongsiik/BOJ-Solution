
// 제목 : 동방 프로젝트 (Large)
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/17235
// 메모리(kb) : 22616
// 실행시간(ms) : 228

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	// 동아리방이 오른쪽으로 몇 번째 방에 연결되어있는지를 나타내는 배열
	static int[] rightWall;

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 방 수, 부수는 횟수
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		rightWall = new int[N + 1];

		// 방 부수는 정보 입력받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			rightWall[x] = Math.max(rightWall[x], y);
		}

		// 방 수
		int answer = 0;

		// 탐색하는 방 번호
		int room = 1;

		while (room <= N) {
			// 벽이 부서지지 않았다면 다음 방으로 건너뜀
			if (rightWall[room] == 0 || rightWall[room] == room) {
				room++;
			}
			// 벽이 부서졌다면
			else {
				// 연결된 오른쪽 끝 번호
				int end = rightWall[room];
				// 연결되어서 탐색할 방 번호
				int rightRoom = room;
				// 부서진 부분까지
				while (rightRoom <= end) {
					// 더 오른쪽으로 이어지지는 않았는지 확인
					end = Math.max(end, rightWall[rightRoom]);
					rightRoom++;
				}
				room = end + 1;
			}
			// 연결된 방 수 하나 늘리기
			answer++;
		}

		// 출력
		System.out.println(answer);

	}

}