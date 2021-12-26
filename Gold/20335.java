
// 제목 : Generators
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/20335
// 메모리(kb) : 60524
// 실행시간(ms) : 840

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	//도시 수, 발전소를 지울 수 있는 도시 수
	static int N, M;
	//모든 도시에 전력을 공급할 수 있는 최소 비용
	static long answer;
	//크루스칼 알고리즘을 위한 부모 배열
	static int[] parents;
	//전선, 발전소 정보
	static ArrayList<Edge> edges;
	
	
	public static void main(String args[]) throws IOException {

		init();
		
		kruskal();
		
		System.out.println(answer);
	}
	
	static void init() throws IOException {
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = 0;
		
		parents = new int[N+1];
		for(int i=1;i<=N;i++) {
			parents[i] = i;
		}
		
		//발전소는 0번 도시와 연결된 셈 친다.
		edges = new ArrayList<>(N+M);
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges.add(new Edge(0, to, cost));
		}
		
		//도시 연결 정보
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			int cost = Integer.parseInt(st.nextToken());
			edges.add(new Edge(i, i%N+1, cost));
		}
		
		//비용 오름차순을로 정렬
		Collections.sort(edges);
	}
	
	//크루스칼 알고리즘
	static void kruskal() {
		int count = 0;
		
		for(Edge e : edges) {
			if(union(e.from, e.to)) {
				count++;
				answer += e.cost;
			}
			
			if(count==N) break;
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
		
		
	}
	
	static int find(int city) {
		if(parents[city]==city) return city;
		else return parents[city] = find(parents[city]);
	}
	
	static boolean union(int cityA, int cityB) {
		int parentA = find(cityA);
		int parentB = find(cityB);
		
		if(parentA==parentB) return false;
		
		parents[Math.max(parentA, parentB)] = Math.min(parentA, parentB);
		
		return true;
	}
}