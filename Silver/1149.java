// 제목 : 1149
// 티어 : 실버1
// 링크 : https://www.acmicpc.net/problem/1149
// 메모리(kb) : 14532
// 실행시간(ms) : 136

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
  
class Main
{
	public static void main(String args[]) throws IOException
    {
		//빠른 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//집의 수
		int n = Integer.parseInt(br.readLine());
		int[][] houses = new int[n][3];
		
		//색깔 정보 입력받기
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++)
				houses[i][j] = Integer.parseInt(st.nextToken());
		}
		
		//동적계획법
		for(int i=1;i<n;i++) {
			for(int j=0;j<3;j++) {
				int min = Integer.MAX_VALUE;
				for(int k=0;k<3;k++) {
					if(j!=k) min = Math.min(min, houses[i-1][k]);
				}
				houses[i][j] += min;
			}
		}
		
		//마지막값 중 최솟값
		int min = Integer.MAX_VALUE;
		for(int j=0;j<3;j++) {
			min = Math.min(min, houses[n-1][j]);
		}
		
		//출력
		System.out.println(min);
		
    }
	
}
