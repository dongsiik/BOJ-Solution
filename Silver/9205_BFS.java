// 제목 : 맥주 마시면서 걸어가기
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/9205
// 메모리(kb) : 14920
// 실행시간(ms) : 160

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	//편의점 수
	static int n;
	//지점들의 x,y 좌표
	static int[][] points;
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		//테스트 케이스 수
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=0;tc<T;tc++) {
			//입력 받기
			n = Integer.parseInt(br.readLine());
			points = new int[n+2][2];
			for(int i=0;i<n+2;i++) {
				st = new StringTokenizer(br.readLine());
				points[i][0] = Integer.parseInt(st.nextToken());
				points[i][1] = Integer.parseInt(st.nextToken());
			}
			
			//bfs
			bfs();
			
		}
		//출력
		System.out.println(sb);
	}
	
	static void bfs() {
		
		//bfs용 큐
		ArrayDeque<Integer> q = new ArrayDeque<>();
		//방문 여부
		boolean[] visited = new boolean[n+2];
		
		//집을 방문처리 하고 큐에 넣음
		visited[0] = true;
		q.offer(0);
		
		while(!q.isEmpty()) {
			//현재 지점
			int current = q.poll();
			
			//모든 지점에 대해서
			for(int i=0;i<n+2;i++) {
				//거리를 구함
				int distance = Math.abs(points[i][0]-points[current][0])+Math.abs(points[i][1]-points[current][1]);
				//거리가 1000m(20병 * 50m/병)이하이면서 방문하지 않은 지점에 대해서
				if(distance<=1000 && !visited[i]) {
					//도착지라면 happy 출력문에 저장 후 종료
					if(i==n+1) {
						sb.append("happy\n");
						return;
					}
					//아니라면 방문처리하고 큐에 넣음
					visited[i] = true;
					q.offer(i);
				}
			}
		}
		
		//여기까지 왔다면 도착지까지 맥주 마시며 못 갔으므로 sad를 출력문에 저장
		sb.append("sad\n");
	}
}