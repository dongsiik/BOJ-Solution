// 제목 : N-Queen
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/9663
// 메모리(kb) : 14552
// 실행시간(ms) : 4680
// 실행시간이 왜 이렇게 오래 걸리나 해서 N=15일 때를 직접 돌려보니, 실제로도 한참 있다가 나왔습니다.
// 퀸이 이전에 놓인 퀸들에게 잡히는지는 반복문을 돌며 이전 퀸 각각에 대해 따지는 대신 잡히는 열, 왼쪽 아래 대각선, 오른쪽 아래 대각선을 봄으로써 더 빨리 볼 수 있습니다.
// 위에서 말한 코드는 9663.cpp에 있습니다.


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	//정답, 체스판의 크기
	static int count;
	static int n;
	//board[i]=j는 i행 j열에 퀸이 있다는 뜻
	static int[] board;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//초기화
		count = 0;
		n = Integer.parseInt(br.readLine());
		board = new int[n];
		
		//첫 행부터 퀸 놓기
		setQueen(0);
		
		//출력
		System.out.println(count);
	}
	
	static void setQueen(int k) {
		//n개를 무사히 다 놓았다면 가능한 경우의 수 하나 증가
		if(k==n) {
			count++;
			return;
		}
		
		//지금 행 0열부터 n-1열까지 퀸을 놓고, 이전 퀸에게 잡히는지 확인
		for(int j=0;j<n;j++) {
			board[k] = j;
			if(check(k)) setQueen(k+1);
		}
	}
	
	//r행에 놓은 퀸이 이전 퀸들에게 잡히는지 확인하는 함수
	static boolean check(int r) {
		for(int i=0;i<r;i++) {
			//이전 퀸과 같은 행에 있거나, 같은 대각선에 있으면 잡히므로 false
			if(board[i]==board[r] ||( (r - i) == Math.abs(board[i]-board[r]))) return false;
		}
		
		//안 잡혔다면 true
		return true;
	}
}
