
// 제목 : 스타트링크
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/5014
// 메모리(kb) : 57420
// 실행시간(ms) : 212

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		//전체 층수, 시작층수, 도착층수, 버튼별로 오르거나 내려가는 층수
		int F = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		int U = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

		//visited[i] = j는 i층을 갈 때, j-1번 버튼을 눌러서 갈 수 있다는 뜻
		int[] visited = new int[F + 1];
		visited[S] = 1;

		//BFS
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(S);

		while (!q.isEmpty()) {
			int curFloor = q.poll();

			//도착
			if (curFloor == G) {
				System.out.println(visited[curFloor] - 1);
				return;
			}

			//위, 아래를 누르기
			if (curFloor + U <= F && visited[curFloor + U] == 0) {
				visited[curFloor + U] = visited[curFloor] + 1;
				q.offer(curFloor + U);
			}
			if (curFloor - D >= 1 && visited[curFloor - D] == 0) {
				visited[curFloor - D] = visited[curFloor] + 1;
				q.offer(curFloor - D);
			}
		}
		
		//여기까지 왔다면 못 갔으므로 계단을 이용하라는 메시지 출력
		System.out.println("use the stairs");
	}
}