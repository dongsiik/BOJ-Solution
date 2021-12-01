
// 제목 : 리그 오브 레전설 (Small)
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/17271
// 메모리(kb) : 16696
// 실행시간(ms) : 268

// 아이디어 : 점화식을 행렬 꼴로 바꾼 다음, 분할정복을 이용한 거듭제곱

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	// 나머지를 구할 때 나누는 값
	static final int MOD = 1_000_000_007;

	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long N = Long.parseLong(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		System.out.println(getAnswer(N, M));
	}

	static long getAnswer(long N, int M) {
		if (N < M)
			return 1;

		// Identity Matrix
		long[][] res = new long[M][M];
		for (int i = 0; i < M; i++)
			res[i][i] = 1;

		// 점화식을 행렬로 표현
		long[][] A = new long[M][M];
		A[0][0] = 1;
		A[0][M - 1] = 1;
		for (int i = 1; i < M; i++) {
			A[i][i - 1] = 1;
		}

		// 분할 정복을 이용한 거듭제곱
		while (N > 0) {
			if (N % 2 == 1) {
				res = matrixMultiplication(res, A);
			}

			N /= 2;
			A = matrixMultiplication(A, A);

		}

		// 정답 구하기
		long answer = 0;
		for (int i = 0; i < M; i++) {
			answer = (answer + res[M - 1][i]) % MOD;
		}

		return answer;
	}

	// 행렬 곱셈
	static long[][] matrixMultiplication(long[][] A, long[][] B) {
		int n = A.length;
		int m = B.length;
		int l = B[0].length;

		long[][] res = new long[n][l];

		for (int i = 0; i < n; i++) {
			for (int k = 0; k < l; k++) {
				for (int j = 0; j < m; j++) {
					res[i][k] = (res[i][k] + A[i][j] * B[j][k]) % MOD;
					;
				}
			}
		}

		return res;
	}
}