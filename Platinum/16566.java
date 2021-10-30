
// 제목 : 카드 게임
// 티어 : 플래티넘 5
// 링크 : https://www.acmicpc.net/problem/16566
// 메모리(kb) : 356560
// 실행시간(ms) : 1904

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	// 전체 카드 수, 파란색 카드 수, 카드를 내는 횟수
	static int N, M, K;
	// 파란 카드 목록, 분리집합 알고리즘을 위한 parents 배열
	static int[] blueCards, parents;

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		blueCards = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++)
			blueCards[i] = Integer.parseInt(st.nextToken());

		// 오름차순 정렬
		Arrays.sort(blueCards);

		// 분리집합 알고리즘을 위한 parents 배열 초기화
		parents = new int[M + 1];
		for (int i = 0; i <= M; i++)
			parents[i] = i;

		// 카드 내기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			// 철수가 낼 카드
			int redCard = Integer.parseInt(st.nextToken());

			// 철수가 낼 카드보다 크면서 가장 작은 카드의 위치
			int index = upper_bound(redCard);

			// 이기는 카드가 없는 경우(불필요할수도 있는데 문제의 조건이 애매해서 넣었음)
			if (index == M || find(index) == M) {
				// 가장 작은 카드 내기
				int parentZero = find(0);
				sb.append(blueCards[parentZero]).append('\n');
				// 가장 작은 카드 제거하기
				union(parentZero, parentZero + 1);
			}
			// 이기는 카드가 있는 경우
			else {
				// 이기는 카드를 내기
				int parentIndex = find(index);
				sb.append(blueCards[parentIndex]).append('\n');
				// 낸 카드 제거하기
				union(parentIndex, parentIndex + 1);
			}
		}

		// 출력
		System.out.println(sb);

	}

	// 이분탐색을 이용하여 target보다 큰 카드 중, 가장 작은 카드의 위치를 리턴하는 upper_bound 함수
	static int upper_bound(int target) {
		int start = 0;
		int end = M;

		while (start < end) {
			int mid = (start + end) / 2;
			if (blueCards[mid] <= target) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}

		return end;
	}

	// 분리집합 알고리즘 중 find와 union 함수
	static int find(int node) {
		if (parents[node] == node)
			return node;
		return parents[node] = find(parents[node]);
	}

	static void union(int nodeA, int nodeB) {
		int parentA = find(nodeA);
		int parentB = find(nodeB);
		if (parentA < parentB)
			parents[parentA] = parentB;
		else
			parents[parentB] = parentA;
	}
}