
// 제목 : 독특한 계산기
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/19591
// 메모리(kb) : 72800
// 실행시간(ms) : 532

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "+-*/", true);

		//문자열 입력받아 숫자와 연산자 분리하기
		int N = st.countTokens();

		String[] deque = new String[N];
		for (int i = 0; i < N; i++) {
			deque[i] = st.nextToken();
		}

		//처음 +a, -a꼴 연산하기
		int head = 0;
		long headValue = 0;

		if (deque[head].equals("+")) {
			headValue = Long.parseLong(deque[++head]);
		} else if (deque[head].equals("-")) {
			headValue = -Long.parseLong(deque[++head]);
		} else {
			headValue = Long.parseLong(deque[head]);
		}

		int tail = N - 1;
		long tailValue = Long.parseLong(deque[tail]);

		//서로 겹치기 전까지 우선순위를 따져서 연산함
		while (head < tail-2) {
			if (getPriority(deque, head, headValue, tail, tailValue)>0) {
				headValue = operation(deque, head, headValue, false);
				head += 2;
			}
			else {
				tailValue = operation(deque, tail, tailValue, true);
				tail -= 2;
			}
		}
		
		//마지막 연산자가 하나만 남았을 때
		if(head +2 == tail) {
			switch(deque[head+1]) {
			case "+":
				headValue = headValue + tailValue;
				break;
			case "-":
				headValue = headValue - tailValue;
				break;
			case "*":
				headValue = headValue * tailValue;
				break;
			default:
				headValue = headValue / tailValue;
				break;
			}
		}

		System.out.println(headValue);

	}

	//연산 우선순위 구하기
	static int getPriority(String[] deque, int head, long headValue, int tail, long tailValue) {
		//곱셈 나눗셈 vs 덧셈 뺄셈
		if(getOperationType(deque[head+1])!=getOperationType(deque[tail-1])) {
			return getOperationType(deque[head+1])-getOperationType(deque[tail-1]);
		}
		//결과가 다른지
		else if(operation(deque, head, headValue, false)!=operation(deque, tail, tailValue, true)) {
			return Long.compare(operation(deque, head, headValue, false), operation(deque, tail, tailValue, true));
		}
		//결과도 같다면 앞부터
		else return 1;
	}
	
	static int getOperationType(String operation) {
		if (operation.equals("*") || operation.equals("/"))
			return 2;
		return 1;
	}

	//계산하기
	static long operation(String[] deque, int index, long value, boolean tail) {
		if (!tail) {
			switch (deque[index + 1]) {
			case "+":
				return value + Long.parseLong(deque[index + 2]);
			case "-":
				return value - Long.parseLong(deque[index + 2]);
			case "*":
				return value * Long.parseLong(deque[index + 2]);
			default:
				return value / Long.parseLong(deque[index + 2]);
			}
		} else {
			switch (deque[index - 1]) {
			case "+":
				return Long.parseLong(deque[index - 2]) + value;
			case "-":
				return Long.parseLong(deque[index - 2]) - value;
			case "*":
				return Long.parseLong(deque[index - 2]) * value;
			default:
				return Long.parseLong(deque[index - 2]) / value;
			}
		}
	}
}