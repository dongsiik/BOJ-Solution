// 제목 : 텀 프로젝트
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/9466
// 메모리(kb) : 305360
// 실행시간(ms) : 1296

// 참고한 링크 : https://bcp0109.tistory.com/32

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	//팀을 이룬 학생 수, 전체 학생 수
	static int numOfStudentsInTeam;
	static int N;
	//각 학생별로 희망하는 다른 학생
	static int[] students;
	//DFS에서 탐사한 적이 있었는지, 팀을 이루는지까지 조사되었는지
	static boolean[] visited;
	static boolean[] finished;
	
	public static void main(String[] args) throws IOException {

		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=TC;tc++) {
			//초기하
			numOfStudentsInTeam = 0;
			N = Integer.parseInt(br.readLine());
			
			students = new int[N];
			visited = new boolean[N];
			finished = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) 
				students[i] = Integer.parseInt(st.nextToken())-1;
			
			
			//dfs
			for(int i=0;i<N;i++) 
				dfs(i);
			
			//출력문 저장
			sb.append(N-numOfStudentsInTeam).append('\n');
		}
		
		//출력
		System.out.println(sb);
	}
	
	static void dfs(int current) {
		//이미 방문했다면 종료
		if(visited[current]) return;
		
		//방문처리
		visited[current] = true;
		int next = students[current];
		
		//다음 지점을 방문한 적이 없다면 방문
		if(!visited[next]) {
			dfs(next);
		}
		//방문했지만 팀 구성은 안 했다면, 다음 점부터 지금 점까지 싸이클이 생기며 팀이 될 예정
		else if(!finished[next]) {
			numOfStudentsInTeam++;
			for(int i=next;i!=current;i=students[i]) {
				numOfStudentsInTeam++;
			}
		}
		
		//팀 구성 판정 완료 처리
		finished[current] = true;
	}

}