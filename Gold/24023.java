
// 제목 : 아기 홍윤
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/24023
// 메모리(kb) : 38224
// 실행시간(ms) : 548

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	// 현재 범위에서 비트 자리별 출현수
	static int[] bitCount = new int[30];

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 배열 크기, 원하는 값
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 투 포인터 알고리즘 [start, end) 범위
		int start = 0;
		int end = 0;

		while (true) {

			// 현재 값
			int bitwiseOr = getBitwiseOr();

			if (bitwiseOr >= K) {
				remove(arr[start++]);
			} else if (end == N)
				break;
			else if (bitwiseOr < K) {
				add(arr[end++]);
			}

			// 배열 인덱스는 0부터 시작하므로 범위를 변경하여 출력
			if (getBitwiseOr() == K) {
				System.out.println(start + 1 + " " + end);
				return;
			}

		}

		System.out.println(-1);

	}

	// 출현한 비트로 숫자 만들기
	static int getBitwiseOr() {
		int ret = 0;

		for (int i = 0; i < 30; i++) {
			if (bitCount[i] > 0) {
				ret += (1 << i);
			}
		}

		return ret;
	}

	// 비트 자리별 출현 수 더하기
	static void add(int num) {

		for (int i = 0; i < 30; i++) {
			bitCount[i] += num % 2;
			num /= 2;
		}
	}

	// 비트 자리별 출현 수 빼기
	static void remove(int num) {
		for (int i = 0; i < 30; i++) {
			bitCount[i] -= num % 2;
			num /= 2;
		}
	}
}