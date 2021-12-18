
// 제목 : 문자열 폭발
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/9935
// 메모리(kb) : 25032
// 실행시간(ms) : 332

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 원본 문자열, 폭발 문자열
		char[] text = br.readLine().toCharArray();
		char[] bomb = br.readLine().toCharArray();

		// 폭발하지 않는 문자만 모아놓은 배열
		char[] answer = new char[text.length];
		int answerIndex = 0;

		// 글자를 읽으면서
		for (int i = 0; i < text.length; i++) {
			// 폭발 문자열의 끝과 같은 문자라면
			if (text[i] == bomb[bomb.length - 1] && answerIndex >= bomb.length - 1) {
				// 이전까지의 문자들과 이어져서 폭발 문자열이라면 폭발
				if (checkExplode(bomb, answer, answerIndex)) {
					answerIndex -= bomb.length - 1;
				}
				// 아니라면 폭발하지 않는 문자열에 추가
				else {
					answer[answerIndex++] = text[i];
				}
			}
			// 아니라면 폭발하지 않는 문자열에 추가
			else {
				answer[answerIndex++] = text[i];
			}
		}

		// 남은 문자열을 스트링으로 바꾼 후 출력
		String answerString = answerIndex == 0 ? "FRULA" : new String(answer, 0, answerIndex);
		System.out.println(answerString);
	}

	// 폭발문자열과 같은지 확인
	static boolean checkExplode(char[] bomb, char[] answer, int answerIndex) {

		for (int i = 0; i < bomb.length - 1; i++) {
			if (answer[i + answerIndex - bomb.length + 1] != bomb[i])
				return false;
		}

		return true;
	}
}