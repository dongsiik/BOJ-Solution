
// 제목 : 비숍
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/1799
// 메모리(kb) : 14812
// 실행시간(ms) : 160
// 아이디어 : 흰 칸의 비숍들, 검은 칸의 비숍들은 서로 무관하다. 완전 탐색을 할 때 섞어서 하지 말고 따로따로 한 후에 각각 최대로 놓을 수 있는 개수를 더한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] board;
	static boolean[] column;
	static int N, answerWithColor;

	public static void main(String[] args) throws NumberFormatException, IOException {

		init();
		printAnswer();
	}



	static void init() throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//체스판의 크기, 특정 색깔 칸 위에 비숍들을 놓을 수 있는 최대 개수
		N = Integer.parseInt(br.readLine());
		answerWithColor = 0;
		
		//보드판을 45도 돌리기
		board = new int[2*N-1][2*N-1];
		column = new boolean[2*N-1];
		
		//입력받기
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				board[i+j][N-1+i-j] = Integer.parseInt(st.nextToken());
			}
		}
		
	}

	//백트래킹을 섞어서 DFS로 완전 탐색 하기
	//매개변수는 각각 행 번호, 지금까지 놓은 비숍의 수
	static void dfs(int rowNumber, int count) {
		//탐색을 다 했다면 최댓값 갱신 후 종료
		if(rowNumber>=2*N-1) {
			answerWithColor = Math.max(answerWithColor, count);
			return;
		}
		
		//다음 행에 아무 것도 안 놓기
		dfs(rowNumber+2,count);
		
		//다음 행에 자리가 있다면 놓고, 재귀 함수를 호출하고, 치우기
		for(int columnNum=0;columnNum<2*N-1;columnNum++) {
			if(board[rowNumber][columnNum]==1 && !column[columnNum]) {
					column[columnNum] = true;
					dfs(rowNumber+2,count+1);					
					column[columnNum] = false;
			}
		}
		
	}
	
	//정답 출력
	private static void printAnswer() {
		//왼쪽 위의 색깔 비숍부터 탐색
		dfs(0,0);
		int answer = answerWithColor;
		
		// 보드판이 1보다 커서 색이 두 개라면, 다른 색도 탐색
		answerWithColor = 0;
		if(N>1) {
			dfs(1,0);
			answer += answerWithColor;
		}
		
		//출력
		System.out.println(answer);
		
	}
}