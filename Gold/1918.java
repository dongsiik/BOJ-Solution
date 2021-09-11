// 제목 : 후위 표기식
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/1918
// 메모리(kb) : 14036
// 실행시간(ms) : 128
// 참고 링크 : https://dundung.tistory.com/133

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 입력받기
		String input = br.readLine();

		// 연산자들을 넣어줄 스택
		ArrayDeque<Character> stack = new ArrayDeque<>(50);

		// 문자열을 한 글자씩 읽어들이면서
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			// 알파벳이면 바로 후위표기법에 넣어줌
			if (c >= 'A' && c <= 'Z')
				sb.append(c);

			// 여는 괄호면 연산자 스택에 넣음
			else if (c == '(')
				stack.push(c);

			// 닫는 괄호면 연산자 스택에서 여는 괄호가 나올 때까지 털어서 후위 표기식에 넣어줌
			else if (c == ')') {
				while (!stack.isEmpty()) {
					char fromStack = stack.pop();
					if (fromStack == '(')
						break;
					sb.append(fromStack);
				}
			}

			// 사칙연산자라면
			else {
				// 자신보다 우선순위가 높은 애들을 꺼내서 후위 표기식에 넣어주고, 자신을 연산자 스택에 넣음
				while (!stack.isEmpty() && getPrioirity(stack.peek()) >= getPrioirity(c)) {
					sb.append(stack.pop());
				}
				stack.push(c);
			}
		}
		// 남은 연산자가 있다면 꺼내서 후위 표기식에 넣어줌
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		// 출력
		System.out.print(sb.toString());

	}

	// 우선 순위 구하는 함수.
	static int getPrioirity(char c) {
		if (c == '(')
			return 0;
		if (c == '+' || c == '-')
			return 1;
		else
			return 2;
	}

}