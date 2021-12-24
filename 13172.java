
// 제목 : Σ
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/13172
// 메모리(kb) : 20036
// 실행시간(ms) : 224

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	static final int MOD = 1_000_000_007;
	
	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//주사위 수
		int M = Integer.parseInt(br.readLine());
		long answer = 0;
		
		//각각의 주사위마다 기댓값을 구하고 더하기
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			long N = Long.parseLong(st.nextToken());
			long S = Long.parseLong(st.nextToken());
			answer = (answer + getExp(N,S))%MOD;
		}
		
		System.out.println(answer);
	}
	
	//기댓값 구하기
	static long getExp(long N, long S) {
		return (getInverse(N)*S)%MOD;
	}
	
	//곱셈 역원 구하기
	static long getInverse(long N) {
		int pow = MOD - 2;
		long res = 1;
		
		while(pow>0) {
			if(pow%2==1) {
				res = (res * N)%MOD;
			}
			
			N = (N*N)%MOD;
			pow/=2;
		}
		
		return res;
	}
}