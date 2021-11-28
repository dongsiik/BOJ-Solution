
// 제목 : 전화번호 수수께끼 (Large)
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/14370
// 메모리(kb) : 15972
// 실행시간(ms) : 156

// 아이디어 : G는 EIGHT에만 나오니까 8은 G가 나온 만큼 있다. 8을 빼면 H는 THREE에만 나오므로 비슷하게 계산한다. 비슷한 방법으로 반복 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	// 스트링에서 나올 알파벳 갯수 세기
	static int[] S;
	// 숫자 0~9까지 각각 알파벳이 종류별로 몇 개씩 나오는지
	static int[][] numToIntArr;
	// 스트링에서 나온 숫자 개수 세기
	static int[] numberCount;

	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

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
			numberCount = new int[10];

			// 숫자와 대응되는 알파벳을 찾아서 순서대로 빼기
			setNumber(8, S[charToInt('G')]);
			setNumber(3, S[charToInt('H')]);
			setNumber(2, S[charToInt('T')]);
			setNumber(4, S[charToInt('U')]);
			setNumber(6, S[charToInt('X')]);
			setNumber(0, S[charToInt('Z')]);
			setNumber(7, S[charToInt('S')]);
			setNumber(1, S[charToInt('O')]);
			setNumber(5, S[charToInt('V')]);
			setNumber(9, S[charToInt('E')]);

			// 출력문 저장
			sb.append("Case #").append(tc).append(": ");
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < numberCount[i]; j++) {
					sb.append(i);
				}
			}
			sb.append('\n');
		}

		System.out.println(sb);

	}

	// String S에서 숫자 num을 count번 빼기
	static boolean setNumber(int num, int count) {
		numberCount[num] = count;

		for (int i = 0; i < 15; i++) {
			S[i] -= count * numToIntArr[num][i];
		}

		return true;
	}

	// 알파벳을 숫자로 바꾸기. 알파벳 26개 전부가 아니라 15개만 쓰이므로 효율성을 위해 이렇게 사용함
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