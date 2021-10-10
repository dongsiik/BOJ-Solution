// 제목 : 파일 합치기
// 티어 : 골드3
// 링크 : https://www.acmicpc.net/problem/11066
// 메모리(kb) : 30608
// 실행시간(ms) : 492

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {

		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		//테스트 케이스 수
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=TC;tc++) {
			//파일 수 입력받기
			int K = Integer.parseInt(br.readLine());
			
			//dp[i][j]는 i번 파일부터 j번파일까지 합했을 때 최소비용
			int[][] dp = new int[K+1][K+1];
			//partialSum[i]는 1번부터 i번파일까지 합했을 때 파일의 크기
			int[] partialSumOfChapters = new int[K+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<K;i++) {
				int input = Integer.parseInt(st.nextToken());
				partialSumOfChapters[i+1] =partialSumOfChapters[i]+input;
			}
			
			//DP
			for(int length=2;length<=K;length++) {
				for(int startChap = 1; startChap<=K-length+1;startChap++) {
					int endChap = startChap+length-1;
					dp[startChap][endChap] = Integer.MAX_VALUE;
					for(int midChap=startChap;midChap<endChap;midChap++) {
						dp[startChap][endChap] = Math.min(dp[startChap][endChap], partialSumOfChapters[endChap]-partialSumOfChapters[startChap-1]+dp[startChap][midChap]+dp[midChap+1][endChap]);
					}
				}
			}

			//출력문 저장			
			sb.append(dp[1][K]).append('\n');
		}
		
		//출력
		System.out.println(sb);
	}
	

}