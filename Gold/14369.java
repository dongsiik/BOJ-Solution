// 제목 : 전화번호 수수께끼 (Small)
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/14369
// 메모리(kb) : 14056
// 실행시간(ms) : 128

// 아이디어 : 백트래킹을 사용한 DFS
// 이 방법으로는 Large 버전을 못 푸니, Large 버전 풀이는 14370을 참고하시기 바랍니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	static StringBuilder sb;
	// 스트링에서 나올 알파벳 종류별 개수 세기
	static int[] S;
	// 숫자 0~9까지 각각 알파벳이 종류별로 몇 개씩 나오는지
	static int[][] numToIntArr;

	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 0부터 9까지 숫자를 알파벳 종류별 개수로 표현
		numToIntArr = new int[10][];
		numToIntArr[0] = stringToIntArr("ZERO");
		numToIntArr[1] = stringToIntArr("ONE");
		numToIntArr[2] = stringToIntArr("TWO");
		numToIntArr[3] = stringToIntArr("THREE");
		numToIntArr[4] = stringToIntArr("FOUR");
		numToIntArr[5] = stringToIntArr("FIVE");
		numToIntArr[6] = stringToIntArr("SIX");
		numToIntArr[7] = stringToIntArr("SEVEN");
		numToIntArr[8] = stringToIntArr("EIGHT");
		numToIntArr[9] = stringToIntArr("NINE");

		// 테스트 케이스 수
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			S = stringToIntArr(br.readLine());

			// DFS
			sb.append("Case #").append(tc).append(": ");
			dfs(0);
			sb.append('\n');
		}

		System.out.println(sb);

	}

	static boolean dfs(int startNum) {

		// 조건을 만족하면 true 리턴
		if (checkCompleted()) {
			return true;
		}

		for (int num = startNum; num < 10; num++) {
			// 다음 숫자로 num을 포함할 수 있다면 dfs를 다시 한 번 함
			if (setNumber(num)) {
				// 완성되었다면 true를 리턴하고 종료
				if (dfs(num)) {
					return true;
				}
				// 포함시켰던 num 빼기
				unsetNumber(num);
			}
		}

		// 여기까지 왔다면 실패했으므로 false 리턴
		return false;
	}

	// 스트링에 주어진 모든 알파벳을 남김없이 썼다면 true,아니라면 false
	static boolean checkCompleted() {
		for (int i = 0; i < 15; i++) {
			if (S[i] != 0) {
				return false;
			}
		}

		return true;
	}

	// String의 남은 알파벳들이 num을 포함할 수 있으면 num의 알파벳들을 빼고 true, 아니라면 false
	static boolean setNumber(int num) {
		for (int i = 0; i < 15; i++) {
			if (S[i] < numToIntArr[num][i]) {
				return false;
			}
		}

		for (int i = 0; i < 15; i++) {
			S[i] -= numToIntArr[num][i];
		}
		sb.append(num);

		return true;
	}

	// 숫자를 포함시키면서 뺐던 알파벳 복구시키기
	static void unsetNumber(int num) {
		for (int i = 0; i < 15; i++) {
			S[i] += numToIntArr[num][i];
		}
		sb.setLength(sb.length() - 1);
	}

	// 알파벳 26종류 대신 15개만 쓰이므로 압축을 위해 이런 과정을 거쳤음
	static int charToInt(char c) {
		switch (c) {
		case 'E':
			return 0;
		case 'F':
			return 1;
		case 'G':
			return 2;
		case 'H':
			return 3;
		case 'I':
			return 4;
		case 'N':
			return 5;
		case 'O':
			return 6;
		case 'R':
			return 7;
		case 'S':
			return 8;
		case 'T':
			return 9;
		case 'U':
			return 10;
		case 'V':
			return 11;
		case 'W':
			return 12;
		case 'X':
			return 13;
		case 'Z':
			return 14;

		}

		return -1;
	}

	// 스트링의 알파벳별 개수 세기
	static int[] stringToIntArr(String str) {
		int[] res = new int[15];

		for (int i = 0; i < str.length(); i++) {
			res[charToInt(str.charAt(i))]++;
		}

		return res;
	}

}