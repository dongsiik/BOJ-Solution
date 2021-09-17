// 제목 : 피보나치 수의 합
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/2086
// 메모리(kb) : 14176
// 실행시간(ms) : 120

// 아이디어 : 피보나치 수열의 1항부터 n항까지 합은 F(n+2)-1과 같습니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 나머지를 구할 때 나누는 수
	static final int divisor = 1_000_000_000;

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		long answer = getFibo(b+2)-getFibo(a+1);
		if(answer<0) answer += divisor;
		
		// 출력
		System.out.println(answer);
	}

	static void matrixMultiply(long[][] A, long[][] B) {
		// 행렬 새로 만들기 싫어서 왼쪽 행렬에 덮어 씌웠습니다.
		long a00 = A[0][0] * B[0][0] + A[0][1] * B[1][0];
		long a01 = A[0][0] * B[0][1] + A[0][1] * B[1][1];
		long a10 = A[1][0] * B[0][0] + A[1][1] * B[1][0];
		long a11 = A[1][0] * B[0][1] + A[1][1] * B[1][1];
		A[0][0] = a00 % divisor;
		A[0][1] = a01 % divisor;
		A[1][0] = a10 % divisor;
		A[1][1] = a11 % divisor;
	}

	static long getFibo(long n) {
		// 행렬을 이용한 피보나치 계산
		long resMatrix[][] = { { 1, 0 }, { 0, 1 } };
		long A[][] = { { 1, 1 }, { 1, 0 } };

		// 분할 정복을 이용한 거듭제곱
		while (n > 0) {
			if (n % 2 == 0) {
				matrixMultiply(A, A);
				n /= 2;
			} else {
				matrixMultiply(resMatrix, A);
				n--;
			}
		}

		// F_n 출력
		return resMatrix[1][0];
	}
}