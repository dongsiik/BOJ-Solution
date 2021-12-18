
// 제목 : 최소비용 구하기 2
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/11779
// 메모리(kb) : 59728
// 실행시간(ms) : 620

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		//도시 수, 버스 수
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		//인접리스트로 표현한 그래프
		ArrayList<Edge>[] graph = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());	
			
			graph[from].add(new Edge(to, cost, 0, ""));
		}
		
		//시작점과 도착점
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		//다익스트라 알고리즘 - O((V+E)log E)
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0, 1, Integer.toString(start)));
		
		//연결된 최단거리
		int[] cost = new int[N+1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[start] = 0;
		
		while(!pq.isEmpty()) {
			//이제 연결하려는 간선
			Edge current = pq.poll();
			
			//이미 연결되었다면 생략
			if(current.cost>cost[current.dest]) continue;
			
			//도착지라면 결과 출력
			if(current.dest==end) {
				System.out.println(current.cost);
				System.out.println(current.count);
				System.out.println(current.path);
				return;
			}
			
			//이 간선과 연결된 다른 간선들에 대해 정보 갱신 및 추가
			for(Edge next: graph[current.dest]) {
				int nextCost = current.cost + next.cost;
				if(cost[next.dest]>nextCost) {
					cost[next.dest] = nextCost;
					int nextCount = current.count+1;
					String nextPath = current.path+" "+next.dest;
					pq.add(new Edge(next.dest, nextCost, nextCount, nextPath));
				}
			}
		}
	}
	
	//버스 노선
	static class Edge implements Comparable<Edge>{
		//도착지, 총 비용, 도시의 개수, 경로
		int dest;
		int cost;
		int count;
		String path;
		
		public Edge(int dest, int cost, int count, String path) {
			this.dest = dest;
			this.cost = cost;
			this.count = count;
			this.path = path;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}	
	}
}