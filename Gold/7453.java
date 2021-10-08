// 제목 : 합이 0인 네 정수
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/7453
// 메모리(kb) : 167364
// 실행시간(ms) : 4268

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 배열의 크기 N과 그 제곱값 NN
		int N = Integer.parseInt(br.readLine());
		int NN = N * N;
		// 배열들
		int[] A = new int[N];
		int[] B = new int[N];
		int[] C = new int[N];
		int[] D = new int[N];

		// 배열 입력받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}

		// A,B의 중간합, C,D의 중간합
		int[] leftSum = new int[NN];
		int[] rightSum = new int[NN];

		// 중간합 구하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				leftSum[N * i + j] = A[i] + B[j];
				rightSum[N * i + j] = C[i] + D[j];
			}
		}

		// 오름차순 정렬
		Arrays.sort(leftSum);
		Arrays.sort(rightSum);

		// 정답. long으로 받아야 합니다!
		long answer = 0L;

		// 두 포인터 알고리즘을 위한 포인터
		int leftIndex = 0;
		int rightIndex = NN - 1;

		// 두 포인터 알고리즘
		while (leftIndex < N * N && rightIndex >= 0) {
			long sum = leftSum[leftIndex] + rightSum[rightIndex];
			if (sum < 0)
				leftIndex++;
			else if (sum > 0)
				rightIndex--;
			else {
				// leftSum, rightSum에 같은 값이 여럿 있을 수 있으므로 처리
				// long으로 leftCount, rightCount를 받았습니다.
				// int로 받았다가 answer+=leftCount*rightCount에서 형변환 안해주면 overflow가 나오면서 틀렸습니다가 나와요.
				long leftCount = 1;
				while (leftIndex < NN - 1 && leftSum[leftIndex] == leftSum[leftIndex + 1]) {
					leftIndex++;
					leftCount++;
				}
				long rightCount = 1;
				while (rightIndex > 0 && rightSum[rightIndex] == rightSum[rightIndex - 1]) {
					rightIndex--;
					rightCount++;
				}
				answer += leftCount * rightCount;
				leftIndex++;
				rightIndex--;
			}
		}

		// 출력
		System.out.println(answer);

	}

}