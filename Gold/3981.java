
// 제목 : 큐브러버
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/3981
// 메모리(kb) : 29876
// 실행시간(ms) : 388

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	// x1,...,x4로 x = A*a모양으로 연립방정식을 만들 때, A의 역행렬에 6을 곱해서 값을 모두 정수로 만든 행렬
	static long[][] inverseOfMatrix = { { -1, 3, -3, 1 }, { 9, -24, 21, -6 }, { -26, 57, -42, 11 },
			{ 24, -36, 24, -6 } };

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 수
		int TC = Integer.parseInt(br.readLine());

		TCloop: while (TC-- > 0) {

			st = new StringTokenizer(br.readLine());

			// 수열의 길이
			int N = Integer.parseInt(st.nextToken());

			// 길이가 4 이상이면 항상 가능함
			if (N <= 4) {
				sb.append("YES\n");
				continue;
			}

			// 처음 네 수 받기
			long[] initial4 = new long[4];
			for (int i = 0; i < 4; i++) {
				initial4[i] = Integer.parseInt(st.nextToken());
			}

			// 네 수로 a,b,c,d값 구하기
			long[] ABCD = getABCD(initial4);

			// 이후 값들이 저렇게 구한 a,b,c,d와 잘 맞는지 판정하기
			for (int i = 5; i <= N; i++) {
				long x = 6 * Integer.parseInt(st.nextToken());

				if (!isEqual(x, i, ABCD)) {
					sb.append("NO\n");
					continue TCloop;
				}

			}

			sb.append("YES\n");
		}

		// 출력
		System.out.println(sb);
	}

	// 역행렬을 미리 구한 다음 곱해서 a,b,c,d 구하기
	static long[] getABCD(long[] initial4) {
		long[] ABCD = new long[4];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				ABCD[i] += inverseOfMatrix[i][j] * initial4[j];
			}
		}

		return ABCD;
	}

	// 5번째 수부터 구한 a,b,c,d와 잘 맞는지 판별
	static boolean isEqual(long x, int i, long[] ABCD) {

		long result = 0;
		for (int j = 0; j < 4; j++) {
			result += ABCD[j] * Math.pow(i, 3 - j);
		}

		return result == x;
	}
}