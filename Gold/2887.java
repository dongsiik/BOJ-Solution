
// 제목 : 행성 터널
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/2887
// 메모리(kb) : 71620
// 실행시간(ms) : 1604

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	//행성 수
	static int N;
	//행성 번호, 좌표
	static int[][] coordinates;
	//가까운 행성들을 이은 터널 후보
	static ArrayList<Edge> edges;
	//크루스칼 알고리즘에 필요한 분리 집합 알고리즘에 필요한 부모 배열
	static int[] parents;
	
	public static void main(String[] args) throws IOException{
		
		init();
		makeEdges();
		kruskal();
	}
	
	//입력받기
	static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		coordinates = new int[N][4];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			coordinates[i][0] = i;
			coordinates[i][1] = Integer.parseInt(st.nextToken());
			coordinates[i][2] = Integer.parseInt(st.nextToken());
			coordinates[i][3] = Integer.parseInt(st.nextToken());
		}
		
	}
	
	//x좌표가 가까운 행성끼리, y좌표가 가까운 행성끼리, z좌표가 가까운 행성끼리 터널 후보 만들기
	static void makeEdges() {
		edges = new ArrayList<Main.Edge>(3*N-3);
		
		Arrays.sort(coordinates, (int[] a,int[] b)->(a[1]-b[1]));
		for(int i=0;i<N-1;i++) {
			edges.add(new Edge(coordinates[i][0], coordinates[i+1][0], getDistance(coordinates[i], coordinates[i+1])));
		}
		
		Arrays.sort(coordinates, (int[] a,int[] b)->(a[2]-b[2]));
		for(int i=0;i<N-1;i++) {
			edges.add(new Edge(coordinates[i][0], coordinates[i+1][0], getDistance(coordinates[i], coordinates[i+1])));
		}
		
		Arrays.sort(coordinates, (int[] a,int[] b)->(a[3]-b[3]));
		for(int i=0;i<N-1;i++) {
			edges.add(new Edge(coordinates[i][0], coordinates[i+1][0], getDistance(coordinates[i], coordinates[i+1])));
		}
		
		
	}
	
	//크루스칼 알고리즘
	static void kruskal() {
	
		//거리가 가까운 순서대로 정렬
		edges.sort((e1,e2)->Integer.compare(e1.cost, e2.cost));
		
		parents = new int[N];
		for(int i=0;i<N;i++) parents[i] = i;
		
		long answer = 0;
		int count = 0;
		
		for(Edge edge : edges) {
			int nodeA = edge.from;
			int nodeB = edge.to;
			int cost = edge.cost;
			
			if(union(nodeA, nodeB)) {
				count++;
				answer += cost;
			}
			
			if(count==N-1) break;
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
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	
	//두 행성 사이의 거리 구하기
	static int getDistance(int[] a, int[] b) {
		return Math.min(Math.min(Math.abs(a[1]-b[1]), Math.abs(a[2]-b[2])), Math.abs(a[3]-b[3]));
	}
}
