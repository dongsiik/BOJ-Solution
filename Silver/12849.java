
// 제목 : 본대 산책
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/12849
// 메모리(kb) : 14220
// 실행시간(ms) : 128

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
		// D 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		D = Integer.parseInt(br.readLine());

		// 캠퍼스 지도를 그래프로 표현하기
		A = new long[8][];
		A[0] = new long[] { 0, 1, 0, 1, 0, 0, 0, 0 };
		A[1] = new long[] { 1, 0, 1, 1, 0, 0, 0, 0 };
		A[2] = new long[] { 0, 1, 0, 1, 1, 1, 0, 0 };
		A[3] = new long[] { 1, 1, 1, 0, 0, 1, 0, 0 };
		A[4] = new long[] { 0, 0, 1, 0, 0, 1, 1, 0 };
		A[5] = new long[] { 0, 0, 1, 1, 1, 0, 0, 1 };
		A[6] = new long[] { 0, 0, 0, 0, 1, 0, 0, 1 };
		A[7] = new long[] { 0, 0, 0, 0, 0, 1, 1, 0 };

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