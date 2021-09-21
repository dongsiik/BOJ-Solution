// 제목 : 구간 곱 구하기
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/11505
// 메모리(kb) : 91100
// 실행시간(ms) : 572

// 참고한 링크 목록
// https://m.blog.naver.com/ndb796/221282210534
// https://www.acmicpc.net/blog/view/9    (이상 이론 및 C++ 구현)
// https://codingnojam.tistory.com/49?category=1015196
// https://hongjw1938.tistory.com/20        (이상 Java 구현)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int divisor = 1_000_000_007;

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 숫자의 수 n, 변경이 일어나는 횟수 m, 구간의 곱을 구하는 횟수 k
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		// 배열 입력받기
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		// 세그먼트 트리 만들기
		SegmentTree sTree = new SegmentTree(n);
		sTree.init(arr, 1, 0, n - 1);

		// 입력받은 대로 연산
		for (int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			// 변경
			if (a == 1) {
				b--;
				arr[b] = c;
				sTree.update(1, 0, n - 1, b, c);
			}
			// 구간 곱 구하기
			else {
				sb.append(sTree.multiplication(1, 0, n - 1, b - 1, c - 1)).append('\n');
			}
		}

		// 출력
		System.out.println(sb);
	}

	// 세그먼트 트리
	static class SegmentTree {
		// 트리와 트리 크기
		long[] tree;
		int treeSize;

		// 생성자
		public SegmentTree(int arrSize) {
			int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
			this.treeSize = (int) Math.pow(2, h + 1);
			tree = new long[treeSize];
		}

		// 초기화
		public long init(int[] arr, int node_idx, int start, int end) {
			if (start == end) {
				return tree[node_idx] = arr[start];
			}

			int middle = (start + end) / 2;
			return tree[node_idx] = (init(arr, node_idx * 2, start, middle)
					* init(arr, node_idx * 2 + 1, middle + 1, end)) % divisor;
		}

		// 값 변경
		public long update(int node_idx, int start, int end, int idx, long changedValue) {
			if (idx < start || idx > end)
				return tree[node_idx];

			if (start == idx && end == idx) {
				return tree[node_idx] = changedValue;
			}

			int middle = (start + end) / 2;
			return tree[node_idx] = (update(node_idx * 2, start, middle, idx, changedValue)
					* update(node_idx * 2 + 1, middle + 1, end, idx, changedValue)) % divisor;

		}

		// 곱 구하기
		public long multiplication(int node_idx, int start, int end, int left, int right) {
			if (left > end || right < start)
				return 1;

			if (left <= start && end <= right)
				return tree[node_idx];

			int middle = (start + end) / 2;
			return (multiplication(node_idx * 2, start, middle, left, right)
					* multiplication(node_idx * 2 + 1, middle + 1, end, left, right)) % divisor;
		}
	}

}