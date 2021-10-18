// 제목 : N-Queen
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/9663
// 메모리(kb) : 14624
// 실행시간(ms) : 2576

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// 조건을 만족하는 경우의 수
	static int count;
	//퀸의 수
	static int n;
	//각각 인덱스의 세로줄, 우상향 대각선, 우하향 대각선에 퀸이 있는지를 나타내는 배열
	static boolean[] column, diag1, diag2;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		count = 0;
		n = Integer.parseInt(br.readLine());
		column = new boolean[n];
		diag1 = new boolean[2*n];
		diag2 = new boolean[2*n];
		
		//0번 행부터 퀸 놓기
		setQueen(0);
		
		System.out.println(count);
	}
	
	static void setQueen(int k) {
		//n개를 다 놓았으면 경우의 수를 하나 늘리고 종료
		if(k==n) {
			count++;
			return;
		}
		
		for(int j=0;j<n;j++) {
			//k행 j열에 놓을만하다면
			if(check(k, j)) {
				//놓고
				column[j] = true;
				diag1[k+j] = true;
				diag2[n+k-j] = true;
				//재귀를 보내고
				setQueen(k+1);
				//치움
				column[j] = false;
				diag1[k+j] = false;
				diag2[n+k-j] = false;
			}
		}
	}
	
	static boolean check(int k, int j) {
		//열, 대각선에 퀸이 있다면 false
		if(column[j] || diag1[k+j] || diag2[n+k-j]) return false;		
		//아니면 true
		return true;
	}
}