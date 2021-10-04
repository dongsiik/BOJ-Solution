// 제목 : 웜홀
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/1865
// 메모리(kb) : 23008
// 실행시간(ms) : 588

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	//정점 수, 도로 수, 웜홀 수
	static int N,M,W;
	//그래프
	static ArrayList<Node>[] graph;
	
	public static void main(String args[]) throws IOException {
		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		//테스트 케이스 수
		int TC = Integer.parseInt(br.readLine());
		
		//테스트 케이스 마다
		for(int tc=1;tc<=TC;tc++) {
			//입력받기
			st = new StringTokenizer(br.readLine());
			N =Integer.parseInt(st.nextToken());
			M =Integer.parseInt(st.nextToken());
			W =Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[N+1];
			for(int i=1;i<=N;i++) graph[i] = new ArrayList<>();
			
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				graph[S].add(new Node(E,T));
				graph[E].add(new Node(S,T));
			}
			
			for(int i=0;i<W;i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				graph[S].add(new Node(E,-T));
			}
			
			//벨만 포드 알고리즘
			BellmanFord(sb);
		}
		
		//출력
		System.out.println(sb);
	}
	
	static void BellmanFord(StringBuilder sb) {
		
		int[] dist = new int[N+1];
		
		for(int i=1;i<N;i++) {
			for(int j=1;j<=N;j++) {
				for(int k=0;k<graph[j].size();k++) {
					int next = graph[j].get(k).to;
					int nextDist = dist[j] + graph[j].get(k).dist;
					
					if(dist[next]>nextDist) {
						dist[next] = nextDist;
					}
				}
			}
		}
		
		for(int j=1;j<=N;j++) {
			
			for(int k=0;k<graph[j].size();k++) {
				int next = graph[j].get(k).to;
				int nextDist = dist[j] + graph[j].get(k).dist;
				
				if(dist[next]>nextDist) {
					sb.append("YES\n");
					return;
				}
			}
		}
		
		sb.append("NO\n");
		return;
	}
	
	static class Node{
		int to;
		int dist;
		
		public Node(int to, int dist) {
			this.to = to;
			this.dist = dist;
		}
		
	}
}