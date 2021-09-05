// 제목 : LCS 2
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/9252
// 메모리(kb) : 18296
// 실행시간(ms) : 180

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	//문자열 두 개
	static String src1, src2;

	public static void main(String args[]) throws IOException {
		// 빠른 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//문자열 두 개 입력받기
		src1 = br.readLine();
		int len1 = src1.length();
		src2 = br.readLine();
		int len2 = src2.length();

		//LCS 길이를 저장할 배열
		int[][] LCSLength = new int[len1 + 1][len2 + 1];

		//두 문자열에서 한 글자씩 비교하다가 공통 문자가 있으면 길이를 하나 늘려줌
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (src1.charAt(i - 1) == src2.charAt(j - 1)) {
					LCSLength[i][j] = LCSLength[i - 1][j - 1] + 1;
				} else {
					LCSLength[i][j] = Math.max(LCSLength[i - 1][j], LCSLength[i][j - 1]);
				}
			}
		}

		//길이를 바탕으로 문자열 구하기
		String LCS = getLCS(LCSLength);

		//출력
		System.out.println(LCSLength[len1][len2]);
		System.out.println(LCS);
	}

	public static String getLCS(int[][] LCSLength) {
		StringBuilder LCS = new StringBuilder();

		//str1, str2에서 읽고 있는 문자열을 가리키는 인덱스
		int i = LCSLength.length - 1;
		int j = LCSLength[0].length - 1;

		//문자열이 길이가 증가하는 순간에 넣은 문자열을 찾아줌
		while (i != 0 && j != 0) {
			if (LCSLength[i][j] == LCSLength[i - 1][j])
				i--;
			else if (LCSLength[i][j] == LCSLength[i][j - 1])
				j--;
			else {
				LCS.append(src1.charAt(i - 1));
				i--;
				j--;
			}
		}

		//끝부터 찾았으므로 뒤집어서 return
		return LCS.reverse().toString();
	}

}