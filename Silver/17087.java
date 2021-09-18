// 제목 : 숨바꼭질 6
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/17087
// 메모리(kb) : 25980
// 실행시간(ms) : 376

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 동생 수, 수빈이의 위치
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());

		// 동생과 수빈이와의 위치 차이를 저장
		int[] brothers = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			brothers[i] = Math.abs(s - Integer.parseInt(st.nextToken()));
		}

		// 최대공약수 구하기
		System.out.println(getGCD(brothers));
		return;
	}

	// 최대 공약수 구하기
	static int getGCD(int[] brothers) {
		int gcd = brothers[0];

		for (int i = 1; i < brothers.length; i++) {
			gcd = getGCD(gcd, brothers[i]);
		}

		return gcd;

	}

	static int getGCD(int a, int b) {
		int temp;
		while (b > 0) {
			temp = a;
			a = b;
			b = temp % b;
		}

		return a;
	}

}