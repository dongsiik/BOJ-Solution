// 제목 : 찾기
// 티어 : 플래티넘 5
// 링크 : https://www.acmicpc.net/problem/1786
// 메모리(kb) : 39852
// 실행시간(ms) : 516

// kmp 알고리즘을 사용하였습니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 텍스트와 패턴 입력받기
		char[] text = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();

		// 부분일치 배열
		int[] pi = new int[pattern.length];

		// 부분일치 배열 만들기
		for (int i = 1, j = 0; i < pattern.length; i++) {
			while (j > 0 && pattern[i] != pattern[j]) {
				j = pi[j - 1];
			}
			if (pattern[i] == pattern[j])
				pi[i] = ++j;
		}

		// 탐색
		int count = 0;
		for (int i = 0, j = 0; i < text.length; i++) {
			while (j > 0 && text[i] != pattern[j]) {
				j = pi[j - 1];
			}

			if (text[i] == pattern[j]) {
				// 패턴이 모두 포함된 경우
				if (j == pattern.length - 1) {
					// 개수를 하나 늘리고, 위치를 저장함
					count++;
					sb.append(i + 2 - pattern.length);
					j = pi[j];
				} else {
					j++;
				}
			}
		}

		// 출력
		System.out.println(count);
		System.out.println(sb);

	}
}