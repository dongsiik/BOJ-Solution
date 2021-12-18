
// 제목 : 이진 검색 트리
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/5639
// 메모리(kb) : 28456
// 실행시간(ms) : 708

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	// 전위 순회한 결과, 노드 수
	static int[] preorder;
	static int N;

	public static void main(String args[]) throws IOException {

		// 일단 노드 수의 최대 크기로 잡음
		preorder = new int[10000];

		// 입력...
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;

		while (true) {
			input = br.readLine();
			if (input == null || input.equals(""))
				break;
			preorder[N++] = Integer.parseInt(input);
		}

		// 재귀
		preToPost(0, N);

	}

	static void preToPost(int from, int to) {
		//루트 노드
		int root = preorder[from];
		
		//자신보다 크면서 제일 앞에 있는 오른쪽 자식 노드 찾기
		int rightChild = to;
		for (int i = from + 1; i < to; i++) {
			if (preorder[i] > root) {
				rightChild = i;
				break;
			}
		}

		//왼쪽 자식이 있다면 왼쪽 탐색
		if (from + 1 < rightChild) {
			preToPost(from + 1, rightChild);
		}
		//오른쪽 자식이 있다면 오른쪽 탐색
		if (rightChild < to) {
			preToPost(rightChild, to);
		}

		//후위 순회이므로 루트 노드를 마지막으로 출력
		System.out.println(root);
	}

}