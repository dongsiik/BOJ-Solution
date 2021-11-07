
// 제목 : 노트북의 주인을 찾아서
// 티어 : 플래티넘 5
// 링크 : https://www.acmicpc.net/problem/1298
// 메모리(kb) : 15456
// 실행시간(ms) : 160

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	//노트북과 사람의 수, 관계의 수
	static int N, M;
	//관계
	static ArrayList<Integer>[] graph;
	//i번 노트북의 주인이 j라는 뜻
	static int[] ownerOfLaptop;
	//dfs에서 방문 여부 처리
	static boolean[] checked;

	public static void main(String[] args) throws IOException {

		init();
		bipartiteMatching();
	}

	// 입력받기
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N+1];
		for (int i = 1; i <= N; i++)
			graph[i] = new ArrayList<>();

		ownerOfLaptop = new int[N+1];
		checked = new boolean[N+1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
		}
	}

	static void bipartiteMatching() {

		//매칭된 사람과 노트북 수
		int answer = 0;
		
		for (int i = 1; i <= N; i++) {
			//dfs 초기화
			Arrays.fill(checked, false);
			//노트북을 가질 수 있다면 수 하나 증가
			if (dfs(i)) answer++;
			
		}

//		for(int i=1;i<=N;i++) {
//			if(ownerOfLaptop[i]!=0) {
//				System.out.println(ownerOfLaptop[i]+" has "+i);
//			}
//		}
		
		System.out.println(answer);
	}

	//person번 사람이 자신의 노트북 후보 중 하나를 가질 수 있다면 true, 아니라면 false
	static boolean dfs(int person) {

		//그 사람이 예상하는 자신의 노트북 후보들
		for (int laptop : graph[person]) {

			//이미 처리한 노트북이라면 생략
			if (checked[laptop]) continue;
			
			checked[laptop] = true;

			//노트북 주인이 없거나, 전 주인이 다른 노트북을 가질 수 있다면, 이 노트북을 선점하고 true 리턴
			if (ownerOfLaptop[laptop] == 0 || dfs(ownerOfLaptop[laptop])) {
				ownerOfLaptop[laptop] = person;
				return true;
			}
		}

		//아니라면 false
		return false;
	}

}
