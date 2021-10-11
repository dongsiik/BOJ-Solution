// 제목 : LCS 4
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/13711
// 메모리(kb) : 35252
// 실행시간(ms) : 432

// 아이디어 : 두 수열에서 1~N이 한 번씩 등장하므로, 두 번째 수열에서 각각의 항이 첫 번째 수열에서 몇 번째 항으로 등장하는지를 본 다음, LIS로 푼다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 수열의 길이
	static int N;
	// LIS로 풀 수 있도록 변형한 두 번째 수열
	static int[] arr;

	public static void main(String[] args) throws IOException {

		input();
		LIS();

	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 수열의 수
		N = Integer.parseInt(br.readLine());

		// 각각의 숫자가 첫 번째 수열에서 몇 번째 위치에 등장하는지 저장
		st = new StringTokenizer(br.readLine());
		int[] numToIndex = new int[N + 1];
		for (int i = 1; i <= N; i++)
			numToIndex[Integer.parseInt(st.nextToken())] = i;

		// 두 번째 수열에서, 숫자 대신 그 숫자가 첫 번째 수열에서 등장하는 위치로 저장
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = numToIndex[Integer.parseInt(st.nextToken())];

	}

	// 이분탐색을 이용한 시간복잡도 n log n의 LIS 알고리즘
	static void LIS() {

		int size = 0;

		// LIS[i] = j는 길이가 i인 여러 LIS 중 가장 짧은 꼬리가 j라는 뜻
		int[] LIS_tail = new int[N + 1];

		for (int i = 0; i < N; i++) {

			// arr[i]를 붙일 위치를 찾아서 붙이기
			int pos = lower_bound(LIS_tail, 0, size + 1, arr[i]);
			if (pos == size + 1)
				size++;

			LIS_tail[pos] = arr[i];
		}

		// 크기 출력
		System.out.println(size);
	}

	// 이진 탐색
	static int lower_bound(int[] arr, int start, int end, int target) {

		while (start < end) {
			int mid = (start + end) / 2;
			if (arr[mid] >= target)
				end = mid;
			else
				start = mid + 1;

		}

		return end;
	}
}