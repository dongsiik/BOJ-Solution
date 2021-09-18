// 제목 : 숨바꼭질 5
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/17071
// 메모리(kb) : 44696
// 실행시간(ms) : 280
// 아이디어 : 어떤 칸을 처음 방문했을 때, 한 칸 왼쪽, 한 칸 오른쪽으로 움직이면 2초 후에도 그 칸에 있을 수 있다.
// 동생이 t초에 k(t)칸을 방문했을 때, 수빈이 t초, t-2초, ...에 k(t)칸을 방문했는지 따져보면 된다.
// 수빈이 t초에 n(t)칸을 방문했다면, t를 2로 나눈 나머지 시간에 n(t)칸을 방문했다고 기록한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 좌표 최댓값
		final int MAX = 500000;

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 수빈, 동생의 시작 위치
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		// visited[k][i]는 2로 나눈 나머지가 i인 시간대에 k를 방문한 적이 있었는지를 나타냄
		boolean[][] visited = new boolean[MAX + 1][2];

		// 현재 시간
		int time = 0;

		// BFS에 시작점을 넣음
		ArrayDeque<Integer> q = new ArrayDeque<>();
		visited[n][0] = true;
		q.offer(n);

		// BFS
		while (!q.isEmpty()) {
			k += time;
			// 동생이 맵을 뚫고 나간 경우 -1 출력 후 종료
			if (k > MAX) {
				System.out.println(-1);
				return;
			}

			// 현재 시간대에 대해서
			int size = q.size();
			for (int i = 0; i < size; i++) {
				// 현재 지점
				int cur = q.poll();

				// 다음 지점
				int next = cur * 2;
				// 맵 범위 안이면서, 그 칸을 이전에 홀/짝 시간대에 방문한 적이 없다면 방문처리하고 큐에 넣어줌
				if (next <= MAX && !visited[next][(time + 1) % 2]) {
					q.offer(next);
					visited[next][(time + 1) % 2] = true;
				}
				next = cur + 1;
				if (next <= MAX && !visited[next][(time + 1) % 2]) {
					q.offer(next);
					visited[next][(time + 1) % 2] = true;
				}
				next = cur - 1;
				if (next >= 0 && !visited[next][(time + 1) % 2]) {
					q.offer(next);
					visited[next][(time + 1) % 2] = true;
				}
			}

			// 동생의 현재 위치를 홀짝이 맞는 전 시간대에 방문한 적이 있다면
			if (visited[k][time % 2]) {
				// 지금 시간 출력 후 종료
				System.out.println(time);
				return;
			}
			// 현재 시간 1 증가
			time++;
		}

		// 여기까지 왔다면 못 잡았으므로 -1 출력
		System.out.println(-1);
		return;
	}

}