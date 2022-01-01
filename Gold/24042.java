
// 제목 : 횡단보도
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/24042
// 메모리(kb) : 278420
// 실행시간(ms) : 1408

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
		
	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		//지역 수, 횡단보도의 주기
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//인접리스트로 그래프 표현
		ArrayList<Edge>[] graph = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(new Edge(0, i, b));
			graph[b].add(new Edge(0, i, a));
		}
		
		//다익스트라 알고리즘을 위한 현재까지 최단거리 배열 초기화
		Edge[] dist = new Edge[N+1];
		for(int i=2;i<=N;i++) {
			dist[i] = new Edge(Long.MAX_VALUE, Integer.MAX_VALUE, 0);
		}
		dist[1] = new Edge(0,0,0);
		
		//총 걸린 시간이 짧은 시간으로 정렬한 우선순위 큐에 시작점을 넣음
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(0,0,1));
		
		while(!pq.isEmpty()) {
			Edge current = pq.poll();
			
			//도착지라면 결과 출력
			if(current.to==N) {
				System.out.println(M*(current.period)+current.phase);
				return;
			}
			
			//이미 더 짧은 시간에 지나갔다면 생략
			if(current.compareTo(dist[current.to])>0) continue;
			
			//현재 지역과 연결된 다른 지역에 대하여
			for(Edge next : graph[current.to]) {
				//다음 횡단보도가 켜지는 주기와, 주기 내에서 위상
				long nextPeriod = current.period;
				int nextPhase = next.phase +1;
				
				//다음 주기를 기다려야할 때
				if(nextPhase<current.phase) {
					nextPeriod++;
				}

				//더 빠른 시간에 그 지역으로 갈 수 있다면 갱신
				Edge nextEdge = new Edge(nextPeriod, nextPhase, next.to);
				if(nextEdge.compareTo(dist[next.to])<0) {
					dist[next.to].period = nextPeriod;
					dist[next.to].phase = nextPhase;
					pq.offer(nextEdge);
				}
			}
		}
	}
	
	//횡단보도 클래스
	static class Edge implements Comparable<Edge>{
		
		//몇 번째 주기인지, 주기 내에서 위치는 어떤지, 어디로 가는지
		long period;
		int phase;
		int to;
		
		public Edge(long period, int phase, int to) {
			super();
			this.period = period;
			this.phase = phase;
			this.to = to;
		}
		
		@Override
		public int compareTo(Edge o) {
			if(this.period==o.period) {
				return this.phase-o.phase;
			}
			else {
				return Long.compare(this.period, o.period);
			}
		}
	}
}