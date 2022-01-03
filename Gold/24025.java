
// 제목 : 돌의 정령 줄세우기
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/24025
// 메모리(kb) : 25576
// 실행시간(ms) : 368

// 공식 풀이를 참고하였습니다.
// https://upload.acmicpc.net/8294438f-1337-4d1d-9105-4c8d0f4f45fb/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 정령 무리 수
		int N = Integer.parseInt(br.readLine());

		// 조건 입력받기
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 불가능한 경우
		if (arr[N - 1] < 0) {
			System.out.println("-1");
			return;
		}

		// 항상 오른쪽 시야가 트여있도록 만든 다음, 음수인 돌 정령 주위만 뒤집을 예정
		int[] answer = new int[N];
		for (int i = 0; i < N; i++) {
			answer[i] = N - i;
		}

		int start = 0;
		while (start < N) {
			// 시야제한이 양수라면 생략
			if (arr[start] > 0) {
				start++;
				continue;
			}

			// 시야제한이 음수라면
			if (arr[start] < 0) {
				int end = start;
				// 연속으로 음수인 구간이 끝나는 위치 찾기
				while (end < N) {
					if (arr[end] < 0) {
						end++;
					} else {
						break;
					}
				}

				// 뒤집기
				swap_range(answer, start, end);

				start = end;
			}
		}

		// 출력
		for (int i = 0; i < N; i++) {
			sb.append(answer[i]).append(' ');
		}

		System.out.println(sb);
	}

	static void swap_range(int[] answer, int start, int end) {

		while (start < end) {
			int temp = answer[start];
			answer[start] = answer[end];
			answer[end] = temp;
			start++;
			end--;
		}
	}
}