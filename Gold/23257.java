
// 제목 : 비트코인은 신이고 나는 무적이다
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/23257
// 메모리(kb) : 15112
// 실행시간(ms) : 168

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	//이전 N개의 달, 중복을 허용해 고를 수 있는 가짓수
	static int N, M;
	//N개의 달별로 주어진 월봉
	static int[] arr;
	//dp[level][status]는 level개 골라서 status(비트마스킹으로 표현됨)일 때, 앞으로 잘 골라서 얻을 수 있는 최댓값
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {

		init();
		System.out.println(getDP(0,0));
	}

	//입력받고 초기화
	static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Math.abs(Integer.parseInt(st.nextToken()));
		}
		
		dp = new int[M][1<<10];
	}
	
	//재귀를 이용한 DP테이블 채우기
	static int getDP(int level, int status) {
		//M개 골랐다면, 지금 값 반환
		if(level==M) return status;
		
		//이미 계산했었다면, 계산한 값 반환
		if(dp[level][status]!=0) return dp[level][status];
		
		int ret = 0;
		
		//임의의 달을 두 번 고르기 = 안 고르고 횟수만 두 개 늘리기
		if(level<=M-2) ret = Math.max(ret, getDP(level+2,status));
		
		//달 하나를 골라 xor한 값과 비교 후 갱신
		for(int i=0;i<N;i++) {
			ret = Math.max(ret, getDP(level+1, status ^ arr[i]));
		}
		
		return dp[level][status] = ret;
	}
	
}