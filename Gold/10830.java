// 제목 : 행렬 제곱
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/10830
// 메모리(kb) : 14224
// 실행시간(ms) : 124

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 원소를 나눠서 나머지를 구할 때 쓰는 값
	static int divisor = 1000;

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// n과 b 입력받기. b는 int 범위를 초과합니다.
		int n = Integer.parseInt(st.nextToken());
		long b = Long.parseLong(st.nextToken());

		// A 입력받기
		int[][] A = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// A^b 계산하고 출력
		power(A, b);

	}

	private static void power(int[][] a, long b) {
		// 배열 크기
		int n = a.length;

		// 분할제곱을 이용한 거듭제곱
		// 결과를 표시할 값. 시작은 단위행렬부터
		int[][] result = new int[n][n];
		for (int i = 0; i < n; i++)
			result[i][i] = 1;

		while (b > 0) {
			// A^b = (A^2)^(b/2)
			if (b % 2 == 0) {
				a = matrixMultiplication(a, a);
				b /= 2;
			}
			// A^b = A* A^(b-1)
			else {
				b--;
				result = matrixMultiplication(result, a);
			}
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(result[i][j]).append(' ');
			}
			sb.append('\n');
		}

		System.out.println(sb);

	}

	// 행렬의 곱셈
	static int[][] matrixMultiplication(int[][] a, int[][] b) {
		// n*m 행렬과 m*l 행렬을 곱하면 결과는 n*l 행렬
		int n = a.length;
		int m = b.length;
		int l = b[0].length;
		// 결과 행렬
		int[][] result = new int[n][l];

		// 계산
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < l; k++) {
				for (int j = 0; j < m; j++) {
					result[i][k] += a[i][j] * b[j][k];
				}
				result[i][k] %= divisor;
			}
		}

		// 리턴
		return result;
	}
}