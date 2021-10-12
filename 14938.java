
// 제목 : 서강그라운드
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/14938
// 메모리(kb) : 14268
// 실행시간(ms) : 148

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 지역 수, 수색범위
	static int N, M;
	//지역별 아이템 수
	static int[] items;
	//그래프
	static int[][] graph;
	
	public static void main(String[] args) throws IOException {

		// 초기화
		init();
		//플로이드 워셜
		FW();
		//출발점별 최대 아이템 수 구하기
		getAnswer();

	}



	static void init() throws IOException {
		//FW 알고리즘을 위해 고른, 정상적으로 나올 수 없는 두 점 사이 경로의 최댓값
		final int INF = 20000;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//지역 수, 수색 범위, 길의 개수
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		//지역당 아이템 개수
		items = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) items[i] = Integer.parseInt(st.nextToken());
		
		//인접행렬로 표현한 그래프, FW를 위해 자기 자신을 제외한 다른 점과의 거리를 무한대로 초기화
		graph = new int[N][N];
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++) 
				graph[i][j] = INF;
		
		for(int i=0;i<N;i++) graph[i][i] = 0;
		
		//그래프 정보 입력받기
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			graph[from][to] = cost;
			graph[to][from] = cost;
			
		}

	}
	
	private static void FW() {
		//각각의 점을 경유지로 해서 거리를 갱신
		for(int j=0;j<N;j++) 
			for(int i=0;i<N;i++)
				for(int k=0;k<N;k++)
					graph[i][k] = Math.min(graph[i][k], graph[i][j]+graph[j][k]);
		
	}
	
	//정답 출력하기
	private static void getAnswer() {
		//출발점별 아이템 수의 최댓값
		int answer = 0;
		
		for(int i=0;i<N;i++) {
			//현재 아이템 수
			int currentNumOfItem = 0;
			//거리가 M 이하인 다른 지역의 아이템 수를 더함
			for(int j=0;j<N;j++) {
				if(graph[i][j]<=M)
					currentNumOfItem += items[j];
			}
			
			//최댓값과 비교 후 갱신
			answer = Math.max(answer, currentNumOfItem);
		}
		
		//출력
		System.out.println(answer);
	}



}