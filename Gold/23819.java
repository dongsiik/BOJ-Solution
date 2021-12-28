
// 제목 : 묻고 더블로 마셔
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/23819
// 메모리(kb) : 19000
// 실행시간(ms) : 1216

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	//나머지를 구할 때 나눌 수
	static int MOD;
	
	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//사람 수, 첫 k명
		long N = Long.parseLong(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//처음 k명이 마시는 양
		st = new StringTokenizer(br.readLine());
		long[][] a = new long[K][1];
		for(int i=K-1;i>=0;i--) {
			a[i][0] = Long.parseLong(st.nextToken());
		}
		
		//점화식을 행렬로 표현하기
		long[][] A = new long[K][K];
		for(int i=0;i<K;i++) {
			A[0][i] = 1;
		}
		for(int i=0;i<K-1;i++) {
			A[i+1][i] = 1;
		}
		
		//나누는 수 입력받기
		MOD = Integer.parseInt(br.readLine());
		
		//점화식 계산하여 답 구하기
		System.out.println(matrixMultiply(pow(A, N-K), a)[0][0]);
	}
	
	//행렬 곱셈
	static long[][] matrixMultiply(long[][] A, long[][] B){
		int n = A.length;
		int m = B.length;
		int l = B[0].length;
		
		long[][] res = new long[n][l];
		
		for(int i=0;i<n;i++) {
			for(int k=0;k<l;k++) {
				for(int j=0;j<m;j++) {
					res[i][k] = (res[i][k]+(A[i][j]*B[j][k])%MOD)%MOD;
				}
			}
		}
		
		
		return res;
	}
	
	//분할 정복을 이용한 거듭제곱
	static long[][]  pow(long[][] A, long exp) {
		
		long[][] res = new long[A.length][A[0].length];
		for(int i=0;i<A.length;i++) {
			res[i][i] = 1;
		}
		
		while(exp>0) {
			if(exp%2==1) {
				res = matrixMultiply(res, A);
			}
			exp/=2;
			A = matrixMultiply(A, A);
		}
		
		return res;
	}
}