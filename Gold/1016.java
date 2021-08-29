// 제목 : 제곱 ㄴㄴ수
// 티어 : 골드1
// 링크 : https://www.acmicpc.net/problem/1016
// 메모리(kb) : 17912
// 실행시간(ms) : 192

//아이디어 : 에라토스테네스에서 소수의 배수들을 쳐내고 남은 값을 소수로 건진 것처럼, 제곱수를 쳐내고 남은 값을 제곱ㄴㄴ수로 건진다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
  
class Main
{
	
	
	public static void main(String args[]) throws IOException
    {
		//빠른 입출력 도구들
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
				
		//문제의 입력값
		long min = Long.parseLong(st.nextToken());
		long max = Long.parseLong(st.nextToken());
        
		long sqrtM = (long)Math.sqrt(max);
		
		//에라토스테네스의 체와 비슷한 방식으로 제곱수 거르기
		boolean[] nonSquare = new boolean[(int)(max-min+1)];
		for(long i=2;i<=sqrtM;i++) {
			long square = i*i;
			
			//빠른 반복문을 위한 시작점 조정
			long start;
			if(square>=min) start = square;
			else if(min%square==0) start = min;
			else start = ((min/square)+1)*square;
			
			//제곱수마다 표시
			for(long j = start; j<=max;j+=square) {
				nonSquare[(int)(j-min)] = true;
			}
		}
		
		//표시되지 않은 수, 제곱ㄴㄴ수 개수 세기
		int nonSquareCount = 0;
		for(boolean b:nonSquare) {
			if(!b) ++nonSquareCount;
		}
		
		//출력
		System.out.println(nonSquareCount);
    }
	
}