
// 제목 : 도시 분할 계획
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/1647
// 메모리(kb) : 323052
// 실행시간(ms) : 1584

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	//집 수
	static int N;
	//도로
	static ArrayList<Edge> edges;
	//크루스칼 알고리즘에 필요한 분리 집합 알고리즘에 필요한 부모 배열
	static int[] parents;
	
	public static void main(String[] args) throws IOException{
		
		init();
		kruskal();
	}
	
	//입력받기
	static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		edges = new ArrayList<>(M);
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken())-1;
			int B = Integer.parseInt(st.nextToken())-1;
			int C = Integer.parseInt(st.nextToken());
			edges.add(new Edge(A, B, C));
		}
	}
		
	//크루스칼 알고리즘
	static void kruskal() {
	
		//거리가 가까운 순서대로 정렬
		edges.sort((e1,e2)->e1.cost-e2.cost);
		
		parents = new int[N];
		for(int i=0;i<N;i++) parents[i] = i;
		
		int answer = 0;
		int count = 0;
		
		for(Edge edge : edges) {
			int nodeA = edge.from;
			int nodeB = edge.to;
			int cost = edge.cost;
			
			if(union(nodeA, nodeB)) {
				count++;
				answer += cost;
			}
		
			//두 개의 마을로 만들 것이므로, N-1이 아니라 N-2일때 중단
			if(count==N-2) break;
		}
		
		System.out.println(answer);
	}

	//부모 노드 찾기(경로 압축)
	static int getParent(int nodeNum) {
		if(parents[nodeNum]==nodeNum) return nodeNum;
		
		return parents[nodeNum] = getParent(parents[nodeNum]);
	}
	
	//부모가 다른 경우 합치기
	static boolean union(int nodeNumA, int nodeNumB) {
		
		int parentA = getParent(nodeNumA);
		int parentB = getParent(nodeNumB);
		
		if(parentA==parentB) return false;
		
		if(parentA<parentB) parents[parentB] = parentA;
		else parents[parentA] = parentB;
		
		return true;
	}
	
	//터널 후보 클래스
	static class Edge {
		int from, to;
		int cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
}
