// 제목 : 최솟값
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/10868
// 메모리(kb) : 49472
// 실행시간(ms) : 620

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
		sTree.init(arr, 1, 0, n - 1);

		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			sb.append(sTree.findMin(1, 0, n-1, a, b)).append('\n');
		}

		// 출력
		System.out.println(sb);
	}

	// 세그먼트 트리
	static class SegmentTree {
		// 트리와 트리 크기
		int[] tree;
		int treeSize;

		// 생성자
		public SegmentTree(int arrSize) {
			int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
			this.treeSize = (int) Math.pow(2, h + 1);
			tree = new int[treeSize];
		}

		// 초기화
		public int init(int[] arr, int node_idx, int start, int end) {
			if (start == end) {
				return tree[node_idx] = arr[start];
			}

			int middle = (start + end) / 2;
			return tree[node_idx] = Math.min(init(arr, node_idx * 2, start, middle), init(arr, node_idx * 2 + 1, middle + 1, end));
		}

		public int findMin(int node_idx, int start, int end, int left, int right) {
			if(end<left || start>right) return Integer.MAX_VALUE;
			if(left<=start && end<=right) return tree[node_idx];
			
			int middle = (start+end)/2;
			return Math.min(findMin(node_idx*2, start, middle, left, right), findMin(node_idx*2+1, middle+1, end, left, right));
		}
	}

}