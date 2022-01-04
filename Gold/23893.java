// 제목 : 알프스의 힘
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/23893
// 메모리(kb) : 32300
// 실행시간(ms) : 452

// 공식 풀이 참고
// https://upload.acmicpc.net/2e037db3-396a-4b24-a6bc-808f187335ac/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	// 정수의 개수, 나누는 값, 나머지
	static int N, P, K;

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		long[] arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = f(Long.parseLong(st.nextToken()));
		}

		// 크기 순서대로 정렬
		Arrays.sort(arr);
		int start = 0;
		long answer = 0;

		while (start < N - 1) {
			// 크기가 같은 수는 [start, end)
			if (arr[start] == arr[start + 1]) {
				int end = start + 1;

				while (end < N && arr[start] == arr[end]) {
					end++;
				}

				// 쌍의 수
				answer += (long) (end - start) * (end - start - 1) / 2;
				start = end;
			} else {
				start++;
			}
		}

		System.out.println(answer);

	}

	// a^3 - k*a (mod P)
	static long f(long a) {
		return (((a * a) % P * a) % P - (K * a) % P + P) % P;
	}
}