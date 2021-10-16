
// 제목 : 할 일 정하기 1
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/1311
// 메모리(kb) : 97180
// 실행시간(ms) : 436

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;	//사람 수
	static int[][] dp;	//dp[level][progression] level명의 사람이 progression 상태로 일을 끝냈을 때, 일을 다 끝낼따까지 추가로 필요한 최소비용
	static int[][] D;	//D[i][j]는 i번 사람이 j번 일을 할 때 비용
	static final int INF = 300_000;	//최악의 효율로 일을 해도 나올 수 없는 총 비용의 최댓값
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		//입력받기
		N = Integer.parseInt(br.readLine());
		dp = new int[N][1<<N];
		for(int i=0;i<N;i++)
			Arrays.fill(dp[i], INF);

		D = new int[N][N];
		for(int i=0;i<N;i++) {
			st =  new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				D[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(recursion(0,0));
		
	}
	
	static int recursion(int level, int progression) {
		//일을 다 했다면 추가로 비용이 안 들기 때문에 0 리턴
		if(level==N) return 0;
		
		//이미 처리했다면 처리한 값 리턴
		if(dp[level][progression] != INF) return dp[level][progression];
		
		//level번 사람이 할 일에 따라서
		for(int i=0;i<N;i++) {
			//이미 처리하지 않은 일을 처리해보면서 최댓값 계산
			if((progression & (1<<i))==0) {
				dp[level][progression] = Math.min(dp[level][progression], D[level][i]+recursion(level+1, progression | (1<<i)));
			}
		}
		
		//리턴
		return dp[level][progression];
	}
}