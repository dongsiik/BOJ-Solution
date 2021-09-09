// 제목 : 숨바꼭질 3
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/13549
// 메모리(kb) : 20760
// 실행시간(ms) : 176
// 0-1 BFS를 적용했습니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		// 좌표 최댓값, 거리를 무한대로 나타내기 위한 거리 최댓값
		final int MAX = 100000;
		final int INF = 1000000000;

		// 방문 여부, 지금까지 구한 최소 시간
		boolean[] visited = new boolean[MAX + 1];
		int[] time = new int[MAX + 1];
		Arrays.fill(time, INF);

		// 0-1 BFS를 위한 deque
		ArrayDeque<Integer[]> dq = new ArrayDeque<>(MAX);
		dq.offerFirst(new Integer[] { 0, n });

		while (!dq.isEmpty()) {
			// 가장 가까운 점 꺼내기
			Integer[] cur = dq.pollFirst();

			// 좌표와 걸린 시간
			int pos = cur[1];
			int cost = cur[0];

			// 이미 방문했다면 건너뜀
			if (visited[pos])
				continue;

			// 아니라면 시간 기록하고 방문 처리
			time[pos] = cost;
			visited[pos] = true;

			// 술래를 잡았다면 출력하고 종료
			if (pos == k) {
				System.out.println(cost);
				return;
			}

			// 순간이동 하는 경우, 시간을 비교해서 큐의 앞부분에 넣어줌
			int next = 2 * pos;
			if (next <= MAX && cost < time[next]) {
				time[next] = cost;
				dq.offerFirst(new Integer[] { cost, next });
			}

			// 그 외의 경우에도 시간을 비교해서 큐의 뒷부분에 넣어줌
			next = pos + 1;
			if (next <= MAX && cost + 1 < time[next]) {
				time[next] = cost + 1;
				dq.offerLast(new Integer[] { cost + 1, next });
			}
			next = pos - 1;
			if (next >= 0 && cost + 1 < time[next]) {
				time[next] = cost + 1;
				dq.offerLast(new Integer[] { cost + 1, next });
			}

		}

	}

}