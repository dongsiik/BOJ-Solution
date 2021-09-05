// 제목 : LCS 2
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/9252
// 메모리(kb) : 440648
// 실행시간(ms) : 680

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
  
class Main
{
	public static void main(String args[]) throws IOException
    {
		//빠른 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//문자열 두 개 입력받기
		String src1 = br.readLine();
		int len1 = src1.length();
		String src2 = br.readLine();
		int len2 = src2.length();
		
		//길이가 아니라 진짜 수열인 LCS를 구하기
		StringBuilder[][] LCS = new StringBuilder[len1+1][len2+1];
		for(int i=0;i<=len1;i++) LCS[i][0] = new StringBuilder();
		for(int j=1;j<=len2;j++) LCS[0][j] = new StringBuilder();
		
		for(int i=1;i<=len1;i++) {
			for(int j=1;j<=len2;j++) {
				if(src1.charAt(i-1)==src2.charAt(j-1)) {
					LCS[i][j] = new StringBuilder(LCS[i-1][j-1].toString()).append(src1.charAt(i-1));
				}
				else {
					LCS[i][j] = new StringBuilder(takeLonger(LCS[i-1][j], LCS[i][j-1]).toString());
				}
			}
		}
		
		//출력
		System.out.println(LCS[len1][len2].length());
		System.out.print(LCS[len1][len2].toString());
    }
	
	//두 문자열 중 더 긴 문자열을 반환
	public static StringBuilder takeLonger(StringBuilder sb1, StringBuilder sb2) {
		if(sb1.length()>=sb2.length()) return sb1;
		else return sb2;
	}
	
}