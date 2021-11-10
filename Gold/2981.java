// 제목 : 검문
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/2981
// 메모리(kb) : 14228
// 실행시간(ms) : 128

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 숫자의 개수
		int N = Integer.parseInt(br.readLine());

		// 숫자들의 차이
		int[] diff = new int[N];

		// 숫자들의 차이 채우기
		int prev = 0;
		int curr = Integer.parseInt(br.readLine());
		int first = curr;

		for (int i = 0; i < N - 1; i++) {
			prev = curr;
			curr = Integer.parseInt(br.readLine());
			diff[i] = Math.abs(curr - prev);
		}

		diff[N - 1] = Math.abs(curr - first);

		// 차이들의 최대공약수 구하기
		int gcd = getGcd(diff);

		// O(sqrt(N))으로 약수 구하기
		StringBuilder sb = new StringBuilder();
		int sqrt = (int) Math.sqrt(gcd);

		for (int i = 2; i < sqrt; i++) {
			if (gcd % i == 0)
				sb.append(i).append(' ');
		}

		if (sqrt > 1 && sqrt * sqrt != gcd && gcd % sqrt == 0)
			sb.append(sqrt).append(' ');

		for (int i = sqrt; i >= 1; i--) {
			if (gcd % i == 0)
				sb.append(gcd / i).append(' ');
		}

		System.out.println(sb);
	}

	static int getGcd(int a, int b) {
		if (a < b) {
			int temp = a;
			a = b;
			b = temp;
		}

		while (b > 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}

		return a;
	}

	static int getGcd(int[] a) {

		int size = a.length;
		if (size == 1)
			return a[0];

		int res = getGcd(a[0], a[1]);

		for (int i = 2; i < size; i++) {
			res = getGcd(res, a[i]);
		}

		return res;
	}

}