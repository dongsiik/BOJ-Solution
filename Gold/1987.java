// 제목 : 알파벳
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/1987
// 메모리(kb) : 15344
// 실행시간(ms) : 976

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int answer;	//정답
	public static int R, C;		//보드의 세로, 가로 칸 수
	public static char[][] board;//보드
	public static boolean[] visited;	//지나는 경로의 알파벳 중복 여부
	public static int[] dx = {1,0,-1,0};	//델타탐색용 배열
	public static int[] dy = {0,1,0,-1};
	
	public static void main(String []args) throws IOException{
		//입력을 빠르게 받기 위한 BufferedReader와 StringTokenizer
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//보드의 세로, 가로 칸 수
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		//보드 칸마다 알파벳 정보 입력받기
		board = new char[R][C];
		for(int i=0;i<R;i++)
			board[i] = br.readLine().toCharArray();
		
		//지금 지나는 경로의 알파벳 중복 여부를 확인하기 위한 배열
		visited = new boolean[26];
		
		//좌측 상단부터 재귀적으로 진행
		recursiveMove(0,0,1);
	
		//출력
		System.out.println(answer);
	}
	
	//r,c는 현재 막 밟은 보드의 좌표, length는 지금 밟은 땅을 포함해서 밟아온 칸수
	public static void recursiveMove(int r, int c, int length) {
		//지금 칸의 알파벳을 기록하고, 길이의 최댓값을 갱신
		visited[board[r][c]-'A']=true;
		answer = Math.max(answer, length);
		
		//상하좌우 4방향 탐색
		for(int i=0;i<4;i++) {
			int nr = r +dx[i];
			int nc = c +dy[i];
			//상하좌우로 보드 안이면서, 중복되지 않은 알파벳이면  재귀적으로 진행
			if(nr>=0 && nr<R && nc>=0 && nc<C && !visited[board[nr][nc]-'A'])
				recursiveMove(nr, nc, length+1);
		}
		
		//다른 경로에서 쓸 수 있도록 지금 방문한 칸의 기록을 지움
		visited[board[r][c]-'A']=false;
		
	}

}