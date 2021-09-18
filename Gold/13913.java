// 제목 : 숨바꼭질 4
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/13913
// 메모리(kb) : 21536
// 실행시간(ms) : 244

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	// 좌표 최댓값
	static final int MAX = 100000;
	// 이 점에 도착하기 직전에 있던 점 좌표
	static int[] parent;
	
	public static void main(String[] args) throws NumberFormatException, IOException {


		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 수빈, 동생의 시작 위치
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		//bfs로 시간을 구하고
		int time = bfs(n,k);
		//그 시간동안 있었던 일 역추적
		trace(n,k,time);

		return;
	}
	
	static int bfs(int n, int k) {
		//방문여부 처리 겸 직전에 있었던 점 좌표
		parent = new int[MAX+1];
		for(int i=0;i<=MAX;i++) parent[i] = Integer.MAX_VALUE;
		
		//현재 시간
		int time=0;
		
		//큐, 현재 점을 방문처리하고, 큐에 넣고 시작
		ArrayDeque<Integer> q = new ArrayDeque<>();
		parent[n] = n;
		q.add(n);
		
		while(!q.isEmpty()) {
			
			//현재 시간대에 방문한 점들에 대해서
			int size=q.size();
			
			for(int i=0;i<size;i++) {
				//목적지라면 현재 시각 출력 및 반환 후 종료
				int current = q.poll();
				if(current==k) {
					System.out.println(time);
					return time;
				}
				
				//다음에 방문할 점이 범위 안이고 방문하지 않았다면, 방문처리하고 큐에 넣음
				int next = 2*current;
				if(next<=MAX && parent[next]==Integer.MAX_VALUE) {
					parent[next] = current;
					q.add(next);
				}
				next = current+1;
				if(next<=MAX && parent[next]==Integer.MAX_VALUE) {
					parent[next] = current;
					q.add(next);
				}
				next = current-1;
				if(next>=0 && parent[next]==Integer.MAX_VALUE) {
					parent[next] = current;
					q.add(next);
				}
			}
			//시간 증가
			time++;
		}
		//여기까지 올 일 없지만 이 줄이 없으면 컴파일이 안 됩니다.
		return -1;
	}
	
	//자취 역추적
	static void trace(int n, int k, int time) {
		//모아서 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		
		//0초부터 도착한 초까지 time+1칸이 필요
		int[] nodes = new int[time+1];
		
		//마지막 순간에는 k를 방문함
		nodes[time] = k;
		int recentNode = k;
		int past = time;
		//역추적
		while(past>0) {
			recentNode = parent[recentNode];
			nodes[--past] = recentNode;
		}
		
		//역추적한 결과 출력
		for(int i=0;i<=time;i++) {
			sb.append(nodes[i]).append(' ');
		}
		
		System.out.println(sb);
	}
	

}