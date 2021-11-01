
// 제목 : 사이클 게임
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/20040
// 메모리(kb) : 125668
// 실행시간(ms) : 700

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] parents;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//점의 수, 차례의 수
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//분리집합 알고리즘을 위한 부모 배열
		parents = new int[N];
		for(int i=0;i<N;i++) parents[i] = i;
		
		//차례 진행
		for(int i=1;i<=M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			//사이클이 생기면, 차례의 번호를 출력하고 종료
			if(union(a,b)) {
				System.out.println(i);
				return;
			}
		}
		
		//사이클이 안 생겼다면 0 출력 후 종료
		System.out.println(0);
	}
	
	//전형적인 분리 집합
	static int get(int node) {
		if(parents[node]==node) return node;
		return parents[node] = get(parents[node]);
	}
	
	static boolean union(int nodeA, int nodeB) {
		int parentA = get(nodeA);
		int parentB = get(nodeB);
		
		if(parentA==parentB) return true;
		
		if(parentA<parentB) parents[parentB] = parentA;
		else parents[parentA] = parents[parentB];
		
		return false;
	}
}