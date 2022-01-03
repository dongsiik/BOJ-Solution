// 제목 : 타이어 끌기
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/23891
// 메모리(kb) : 58640
// 실행시간(ms) : 328

// 아이디어 : 2반이 있던 자리에 사람을 더 많이 보낼 경우, 2반의 점수를 빼는 대신 1반이 2배의 점수를 가져간다고 계산함
// 카카오 2021년 하반기 블라인드 채용에 비슷한 문제가 있었던 것으로 기억합니다. 그 문제는 추가로 어느 타이어에 몇 명을 보내야하는지도 출력해야했습니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//타이어 수, 학생 수
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 2반의 점수 총합
		int sumB = 0;
		
		//타이어의 점수, 2반이 배정한 학생 수
		int[] S = new int[N+1];
		int[] P = new int[N+1];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
			if(P[i]>0) {
				sumB += S[i];
			}
		}
		
		//dp[i][j] = i번 타이어까지 총 j명의 인원을 보냈을 때 1반의 점수 최댓값
		int[][] dp = new int[N+1][M+1];
		for(int i=1;i<=N;i++) {
			//2반 사람이 있는 경우, 안 보내기 vs P[i]명 보내기 vs P[i]+1명 보내기
			if(P[i]>0) {
				
				//안 보내기
				for(int j=0;j<=M;j++) {
					dp[i][j] = dp[i-1][j];
				}
				
				// vs P[i]명 보내기 
				for(int j=P[i];j<=M;j++) {
					dp[i][j] = Math.max(dp[i-1][j-P[i]]+S[i], dp[i-1][j]);
				}
				// vs P[i]+1명 보내기
				for(int j=P[i]+1;j<=M;j++) {
					dp[i][j] = Math.max(dp[i-1][j-P[i]-1]+2*S[i], dp[i][j]);
				}
			}
			//2반 사람이 없는 경우, 안 보내기 vs 1명 보내기
			else {
				for(int j=1;j<=M;j++) {
					dp[i][j] = Math.max(dp[i-1][j-1]+S[i], dp[i-1][j]);
				}
			}
		}
		
		
		if(dp[N][M]>sumB) {
			System.out.println("W");
		}
		else if(dp[N][M]==sumB) {
			System.out.println("D");
		}
		else {
			System.out.println("L");
		}
	}
}