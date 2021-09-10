// 제목 : 숨바꼭질
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/1697
// 메모리(kb) : 16732
// 실행시간(ms) : 160

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		//좌표 최댓값
		final int MAX = 100000;
		
		//입력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//입력
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		//방문여부 배열, 큐
		boolean[] visited = new boolean[MAX+1];
		ArrayDeque<Integer> q = new ArrayDeque<>(MAX);
		//시작점 넣기
		q.offer(n);
		visited[n] = true;
		
		int time=0;
		while(!q.isEmpty()) {
			//현재 시간에 있는 점들에만 대해서
			int size = q.size();
			for(int i=0;i<size;i++) {
				//현재 점
				int cur = q.poll();
				//술래를 잡았다면 시간을 출력하고 종료
				if(cur==k) {
					System.out.println(time);
					return;
				}
				
				//순간이동, 한칸 앞, 한칸 뒤 큐에 넣기
				int next = cur*2;
				if(next<=MAX && !visited[next]) {
					q.offer(next);
					visited[next] = true;
				}
				next = cur+1;
				if(next<=MAX && !visited[next]) {
					q.offer(next);
					visited[next] = true;
				}
				next = cur-1;
				if(next>=0 && !visited[next]) {
					q.offer(next);
					visited[next] = true;
				}
			}
			//시간 증가
			time++;
		}
	}

}