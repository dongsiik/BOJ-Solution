
// 제목 : High Card Low Card (Gold)
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/11963
// 메모리(kb) : 23268
// 실행시간(ms) : 276

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

class Main {

	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// N
		int N = Integer.parseInt(br.readLine());

		// 상대가 순서대로 낼 카드
		int[] ElsieCards = new int[N];
		// 상대가 그 카드를 가지고 있는지 여부
		boolean[] usedCards = new boolean[2 * N];

		// 배열 인덱스는 0부터 시작하므로, 맞추기 위해 1을 빼고 입력받았음
		for (int i = 0; i < N; i++) {
			ElsieCards[i] = Integer.parseInt(br.readLine()) - 1;
			usedCards[ElsieCards[i]] = true;
		}

		// 남은 카드를 deque에 오름차순으로 넣기
		ArrayDeque<Integer> deque = new ArrayDeque<>(N);
		for (int i = 0; i < 2 * N; i++) {
			if (!usedCards[i])
				deque.add(i);
		}

		// 정답
		int answer = 0;
		// 전반전에서 지는 라운드의 수
		int dummy = 0;

		// 전반전에서 상대가 내는 카드 내림차순 정렬
		Arrays.sort(ElsieCards, 0, N / 2);

		// 상대가 내는 큰 카드부터, 그보다 큰 카드가 있으면 내고, 아니면 나중에 남는 카드 중에 가장 큰 카드를 낼 예쩡
		for (int i = N / 2 - 1; i >= 0; i--) {
			int peek = deque.peekLast();
			if (peek > ElsieCards[i]) {
				answer++;
				deque.pollLast();
			} else {
				dummy++;
			}
		}

		// 지는 라운드에서 버리는 카드
		for (int i = 0; i < dummy; i++) {
			deque.pollLast();
		}

		// 후반전에 상대가 내는 카드 내림차순 정렬
		Arrays.sort(ElsieCards, N / 2, N);

		// 상대가 내는 작은 카드부터 비슷한 방식으로 처리
		for (int i = N / 2; i < N; i++) {
			int peek = deque.peekFirst();
			if (peek < ElsieCards[i]) {
				answer++;
				deque.pollFirst();
			}
		}

		System.out.println(answer);
	}

}