// 제목 : LCS
// 티어 : 골드5
// 링크 : https://www.acmicpc.net/problem/9251
// 메모리(kb) : 18424
// 실행시간(ms) : 152

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
  
class Main
{
	public static void main(String args[]) throws IOException
    {
		//빠른 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//문자열 두 개를 입력받고, 길이도 구해놓기
		String s1 = br.readLine();
		int len1 = s1.length();
		String s2 = br.readLine();
		int len2 = s2.length();
		
		//동적계획법(다이나믹 프로그래밍)을 이용해 LCS 구하기
		int[][] LCS = new int[len1+1][len2+1];
		
		for(int i=1;i<=len1;i++) {
			for(int j=1;j<=len2;j++) {
				if(s1.charAt(i-1)==s2.charAt(j-1)) LCS[i][j] = LCS[i-1][j-1]+1;
				else LCS[i][j] = Math.max(LCS[i-1][j], LCS[i][j-1]);
			}
		}
		
		//출력
		System.out.println(LCS[len1][len2]);
		
    }
	
}