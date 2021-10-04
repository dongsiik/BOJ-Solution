// 제목 : 곱셈
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/1629
// 메모리(kb) : 14208
// 실행시간(ms) : 136

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {
		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		//곱한 다음 나머지를 구하기 직전에 int 범위를 넘어설 수 있어서 속편하게 long으로 받았습니다.
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		long C = Long.parseLong(st.nextToken());

		System.out.println(pow(A,B,C));
	}

	//분할 정복을 이용한 거듭 제곱
	static long pow(long base, long exp, long mod) {
		long result = 1;
		
		while(exp>0) {
			if(exp%2==1) {
				result  = (result * base) %mod;
				exp--;
			}
			exp/=2;
			base = (base * base)%mod;
		}
		
		return result;
	}
}