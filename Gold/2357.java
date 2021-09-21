// 제목 : 최솟값과 최댓값
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/2357
// 메모리(kb) : 59024
// 실행시간(ms) : 760

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

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 숫자의 수 n, 변경이 일어나는 횟수 m, 구간의 곱을 구하는 횟수 k
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// 배열 입력받기
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		// 세그먼트 트리 만들기
		SegmentTree sTree = new SegmentTree(n);
		sTree.minInit(arr, 1, 0, n - 1);
		sTree.maxInit(arr, 1, 0, n - 1);

		// 구간의 최솟값, 최댓값 구하기
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			sb.append(sTree.findMin(1, 0, n - 1, a, b)).append(' ').append(sTree.findMax(1, 0, n - 1, a, b))
					.append('\n');
		}

		// 출력
		System.out.println(sb);
	}

	// 세그먼트 트리
	static class SegmentTree {
		// 최소 트리, 최대 트리, 트리 크기
		int[] minTree;
		int[] maxTree;
		int treeSize;

		// 생성자
		public SegmentTree(int arrSize) {
			int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
			this.treeSize = (int) Math.pow(2, h + 1);
			minTree = new int[treeSize];
			maxTree = new int[treeSize];
		}

		// 최소 트리 초기화, 노드들은 후손들의 노드 중 최솟값을 가짐
		public int minInit(int[] arr, int node_idx, int start, int end) {
			if (start == end) {
				return minTree[node_idx] = arr[start];
			}

			int middle = (start + end) / 2;
			return minTree[node_idx] = Math.min(minInit(arr, node_idx * 2, start, middle),
					minInit(arr, node_idx * 2 + 1, middle + 1, end));
		}

		// 최대 트리 초기화, 노드들은 후손들의 노드 중 최댓값을 가짐
		public int maxInit(int[] arr, int node_idx, int start, int end) {
			if (start == end) {
				return maxTree[node_idx] = arr[start];
			}

			int middle = (start + end) / 2;
			return maxTree[node_idx] = Math.max(maxInit(arr, node_idx * 2, start, middle),
					maxInit(arr, node_idx * 2 + 1, middle + 1, end));
		}

		// 최솟값 찾기
		public int findMin(int node_idx, int start, int end, int left, int right) {
			if (end < left || start > right)
				return Integer.MAX_VALUE;
			if (left <= start && end <= right)
				return minTree[node_idx];

			int middle = (start + end) / 2;
			return Math.min(findMin(node_idx * 2, start, middle, left, right),
					findMin(node_idx * 2 + 1, middle + 1, end, left, right));
		}

		// 최댓값 찾기
		public int findMax(int node_idx, int start, int end, int left, int right) {
			if (end < left || start > right)
				return Integer.MIN_VALUE;
			if (left <= start && end <= right)
				return maxTree[node_idx];

			int middle = (start + end) / 2;
			return Math.max(findMax(node_idx * 2, start, middle, left, right),
					findMax(node_idx * 2 + 1, middle + 1, end, left, right));
		}
	}

}