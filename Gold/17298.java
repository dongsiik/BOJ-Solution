
// 제목 : 오큰수
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/17298
// 메모리(kb) : 142176
// 실행시간(ms) : 1088

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	// 수열의 길이, 수열, 오큰수
	static int N;
	static int[] arr;
	static int[] NGE;

	public static void main(String[] args) throws IOException {

		// 초기화
		init();
		// 오큰수 구하기
		getNGE();
		// 출력
		printAnswer();

	}

	static void init() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

	}

	private static void getNGE() {
		NGE = new int[N];

		// 스택을 이용하여 오른쪽 수부터 오큰수를 구하고, 스택에 넣음
		Stack<Integer> stack = new Stack<>();

		for (int i = N - 1; i >= 0; i--) {
			while (!stack.isEmpty() && stack.peek() <= arr[i]) {
				stack.pop();
			}

			// 오른쪽에 큰 수가 없으면 -1, 아니면 큰 수의 값
			if (stack.isEmpty())
				NGE[i] = -1;
			else
				NGE[i] = stack.peek();

			stack.push(arr[i]);
		}
	}

	// 정답 출력하기
	private static void printAnswer() {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++)
			sb.append(NGE[i]).append(' ');

		System.out.println(sb);
	}

}