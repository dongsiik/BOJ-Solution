// 제목 : 구간 합 구하기 5
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/11660
// 메모리(kb) : 118528
// 실행시간(ms) : 872

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
		
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		//배열의 크기, 합을 구해야 하는 횟수
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		//배열을 만들고, 값을 입력받으며 누적합을 저장
		int[][] arr = new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				int value = Integer.parseInt(st.nextToken());
				arr[i][j] = value+arr[i-1][j] + arr[i][j-1] - arr[i-1][j-1];
			}
		}
		
		//합을 구할 때마다
		for(int i=0;i<m;i++) {
			//입력받아 부분합을 구해 출력문에 저장
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			sb.append(arr[x2][y2]+arr[x1-1][y1-1]-arr[x2][y1-1]-arr[x1-1][y2]).append('\n');
		}
		
		//출력
		System.out.printf(sb.toString());
	}
}