// 제목 : 부분 문자열
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/16916
// 메모리(kb) : 30816
// 실행시간(ms) : 368

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
		for (int i = 0, j = 0; i < text.length; i++) {
			while (j > 0 && text[i] != pattern[j]) {
				j = pi[j - 1];
			}

			if (text[i] == pattern[j]) {
				// 패턴이 모두 포함된 경우
				if (j == pattern.length - 1) {
					// 1을 출력하고 종료
					System.out.println(1);
					return;
				} else {
					j++;
				}
			}
		}

		// 여기까지 왔다면 못 찾았으므로 0 출력
		System.out.println(0);
	}
}