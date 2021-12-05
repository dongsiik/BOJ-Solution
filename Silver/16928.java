
// 제목 : 뱀과 사다리 게임
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/16928
// 메모리(kb) : 14272
// 실행시간(ms) : 124

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	
	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//사다리와 뱀의 수
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//jump[i]=[j]는 i칸에 사다리나 뱀이 있다면 j로 간다는 뜻
		int[] jump = new int[101];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			jump[u] = v;
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			jump[u] = v;
		}
		
		//BFS
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		boolean[] visited = new boolean[101];
		visited[1] = true;
		
		int time = 0;
		
		while(!q.isEmpty()) {
			int qSize = q.size();
			
			while(qSize-->0) {
				int current = q.poll();
				
				if(current==100) {
					System.out.println(time);
					return;
				}
				
				for(int die=1;die<=6;die++) {
					int next = current + die;
					if(next<=100 && !visited[next]) {
						visited[next] = true;
						//사다리나 뱀이 없을 때
						if(jump[next]==0) {
							q.add(next);
						}
						//사다리나 뱀이 있을 때
						else {
							int nnext = jump[next];
							if(!visited[nnext]) {
								visited[nnext] = true;
								q.add(nnext);
							}
						}
					}
				}
			}
			
			time++;
		}
		
	}

}