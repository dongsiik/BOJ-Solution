// 제목 : LCS 3
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/1958
// 메모리(kb) : 20376
// 실행시간(ms) : 192

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//문장과 길이 저장
		String first = br.readLine();
		int firstLength  = first.length();
		String second = br.readLine();
		int secondLength = second.length();
		String third = br.readLine();
		int thirdLength = third.length();
		
		//차원이 하나 늘어난 것을 빼면 LCS 1과 똑같습니다.
		int[][][] LCS = new int[firstLength+1][secondLength+1][thirdLength+1];
		
		for(int i=1;i<=firstLength;i++) {
			for(int j=1;j<=secondLength;j++) {
				for(int k=1;k<=thirdLength;k++) {
					if(first.charAt(i-1)==second.charAt(j-1) && second.charAt(j-1)==third.charAt(k-1)) 
						LCS[i][j][k] = LCS[i-1][j-1][k-1]+1;		
					else 
						LCS[i][j][k] = Math.max(LCS[i-1][j][k], Math.max(LCS[i][j-1][k], LCS[i][j][k-1]));
					
				}
			}
		}
		
		System.out.println(LCS[firstLength][secondLength][thirdLength]);

	}
}