// 제목 : 조합
// 티어 : 실버 2
// 링크 : https://www.acmicpc.net/problem/2407
// 메모리(kb) : 17768
// 실행시간(ms) : 192

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
		
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		//100C50은 long으로도 안 담아져서 BigInteger를 사용했습니다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//nCm = nCm-n이므로 오른쪽이 작은 쪽으로 구했습니다.
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		if(2*m>n) m = n-m;
		
		//nCr = n-1 C r-1 + n-1 C r
		BigInteger[][] combi = new BigInteger[n+1][m+1];
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=m;j++) {
				if(j>i) continue;
				else if(j==0 || j==i) combi[i][j] = new BigInteger(new Integer(1).toString());
				else combi[i][j] = new BigInteger(combi[i-1][j].add(combi[i-1][j-1]).toString());
			}
		}
		
		System.out.println(combi[n][m]);
		
	}
}