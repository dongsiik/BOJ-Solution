// 제목 : 다이어트
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/1484
// 메모리(kb) : 14180
// 실행시간(ms) : 128

//아이디어 : 현재 몸무게를 c, 이전 몸무게를 p라고 했을 때, g = c^2 - p^2 = (c+p)(c-p)이므로, 가능한 c-p를 찾아서 계산해본다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
  
class Main
{
	public static void main(String args[]) throws IOException
    {
		//빠른 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		//문제의 입력값 g
		int g = Integer.parseInt(br.readLine());
		
		//c+p>c-p이므로 c-p<=sqrt(g)이고, c가 작은 순서대로 찾아야하므로 c-p가 큰 순서대로 반복문을 돌았음
		for(int cMinusP=(int)Math.sqrt(g);cMinusP>0;cMinusP--) {
			if(g%cMinusP!=0) continue;
			
			//g = (c+p)(c-p)로 표현되는 경우
			int cPlusP = g/cMinusP;
			
			//c와 p를 구함
			int c = (cPlusP + cMinusP)/2;
			int p = (cPlusP - cMinusP)/2;
			
			//c와 p가 양의 정수인지 확인하고, 그렇다면 결과값에 저장
			if(c>0 && p>0 && (g==c*c-p*p)) sb.append(c).append('\n');
		}
		
		//가능한 경우가 없다면 -1 출력, 있다면 가능한 경우 출력
		if(sb.length()==0) System.out.println(-1);
		else System.out.print(sb.toString());
    }
		
}