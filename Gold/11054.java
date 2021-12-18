
// 제목 : 가장 긴 증가하는 바이토닉 부분 수열
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/11054
// 메모리(kb) : 14628
// 실행시간(ms) : 156

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//수열의 길이
		int N = Integer.parseInt(br.readLine());
		
		//증가수열, 감소수열의 편의를 위해 양 끝을 비워둠
		int[] S = new int[N+2];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}

		//LIS - O(N^2)
		int[] increasing = new int[N+2];
		
		for(int i=1;i<=N;i++) {
			for(int j=0;j<i;j++) {
				if(S[j]<S[i]) {
					increasing[i] = Math.max(increasing[i], increasing[j]+1);
				}
			}
		}
		
		//위와 비슷함
		int[] decreasing = new int[N+2];
		
		for(int i=N;i>=1;i--) {
			for(int j=N+1;j>i;j--) {
				if(S[j]<S[i]) {
					decreasing[i] = Math.max(decreasing[i], decreasing[j]+1);
				}
			}
		}
		
		//가장 긴 증가하는 바이토닉 부분 수열 길이 구하기
		int answer = 0;
		
		for(int i=1;i<=N;i++) {
			answer = Math.max(answer, increasing[i]+decreasing[i]-1);
		}
		
		System.out.println(answer);

	}

}