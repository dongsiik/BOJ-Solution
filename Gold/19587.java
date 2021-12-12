
// 제목 : 객실 배치
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/19587
// 메모리(kb) : 14232
// 실행시간(ms) : 124

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	//나머지를 구할 때 나눌 값
	static final int MOD = 1_000_000_007;

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long N = Long.parseLong(br.readLine());

		System.out.println(getAnswer(N-1));
	}
	
	//점화식을 세워 행렬로 표현한 후, 분할 정복을 이용한 거듭제곱으로 계산함
	static long getAnswer(long N) {
		
		long resMatrix[][] = {{1,0,0},{0,1,0},{0,0,1}};
		long A[][] = {{1,1,1},{1,0,1},{1,1,0}};
		
		while(N>0) {
			if(N%2==1) {
				resMatrix = matrixMultiply(resMatrix, A);				
			}
			
	            A = matrixMultiply(A, A);
	            N /= 2;
	        
		}
		
		long answer = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                answer = (answer + resMatrix[i][j]) % MOD;
            }
        }
		
        return answer;
	}
	
	//행렬 곱셈
	static long[][] matrixMultiply(long[][] A, long[][] B) {
		int a = A.length;
		int b = B.length;
		int c = B[0].length;
		
		long[][] result = new long[a][c];
		
		for(int i=0;i<a;i++) {
			for(int k=0;k<c;k++) {
				for(int j=0;j<b;j++) {
					result[i][k] = (result[i][k] + A[i][j]*B[j][k])%MOD;
				}
			}
		}
	    
	    return result;
	}
}