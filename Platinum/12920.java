// 제목 : 평범한 배낭 2
// 티어 : 플래티넘 4
// 링크 : https://www.acmicpc.net/problem/12920
// 메모리(kb) : 14820
// 실행시간(ms) : 220

// 참고한 링크 : https://blog.koderpark.dev/171

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//물건 종류 수, 가방의 최대 무게
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 물건의 묶음들의 무게와 가치
		ArrayList<Integer> weight = new ArrayList<>(N*15);
		ArrayList<Integer> value = new ArrayList<>(N*15);
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			//무게, 가치, 개수
			int V = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			//물건들을 2의 거듭제곱개의 묶음으로 묶은 후, 목록에 넣기
			for(int j=0;K>0;j++) {
				int pow = Math.min(1<<j, K);
				weight.add(pow*V);
				value.add(pow*C);
				K -= pow;
			}
		}
		
		//냅색 DP
		int size = weight.size();
		int[] dp = new int[M+1];
		for(int i=0;i<size;i++) {
			for(int j=M;j>0;j--) {
				if(j>=weight.get(i)) dp[j] = Math.max(dp[j], dp[j-weight.get(i)]+value.get(i));
			}
		}
	
		System.out.println(dp[M]);
	}

}