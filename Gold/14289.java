
// 제목 : 본대 산책 3
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/14289
// 메모리(kb) : 16144
// 실행시간(ms) : 204

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 인접행렬로 표현한 캠퍼스 지도
	static long[][] A;
	// 나머지를 구할 때 쓰는 수
	static final int MOD = 1_000_000_007;
	// 산책할 시간
	static int D;

	public static void main(String[] args) throws NumberFormatException, IOException {

		init();
		printAnswer();
	}

	static void init() throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//건물 수, 도로 수
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//캠퍼스 지도 입력받기
		A = new long[N][N];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			A[a][b] = 1;
			A[b][a] = 1;
		}

		//산책할 시간 입력받기
		D = Integer.parseInt(br.readLine());

	}

	// 행렬 곱셈
	static long[][] matrixMultiply(long[][] left, long[][] right) {
		int N = left.length;
		int M = right.length;
		int L = right[0].length;

		long[][] res = new long[N][L];

		for (int i = 0; i < N; i++) {
			for (int k = 0; k < L; k++) {
				for (int j = 0; j < M; j++)
					res[i][k] = (res[i][k] + left[i][j] * right[j][k]) % MOD;
			}
		}

		return res;
	}

	// 분할정복을 이용한 행렬의 거듭제곱
	static long[][] matrixPower(long[][] arr, long pow) {
		int length = arr.length;
		long[][] result = new long[length][length];
		for (int i = 0; i < length; i++)
			result[i][i] = 1L;

		while (pow > 0) {
			if (pow % 2 == 1) {
				result = matrixMultiply(result, arr);
			}
			pow /= 2;
			arr = matrixMultiply(arr, arr);
		}

		return result;
	}

	// 출력
	static void printAnswer() {
		System.out.println(matrixPower(A, D)[0][0]);
	}

}