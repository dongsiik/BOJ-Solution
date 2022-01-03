
// 제목 : 등차수열과 쿼리
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/23888
// 메모리(kb) : 317392
// 실행시간(ms) : 1240

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	// 초항, 공차
	static int a, d;

	public static void main(String args[]) throws IOException {

		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 초항, 공차, 쿼리 수
		a = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(br.readLine());

		// 쿼리마다
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int operation = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			if (operation == 1) {
				sb.append(getSum(l, r)).append('\n');
			} else {
				sb.append(getGcd(l, r)).append('\n');
			}
		}

		System.out.println(sb);

	}

	// 등차수열의 합 구하기
	static long getSum(int l, int r) {
		long res = ((long) (r - l + 1) * (2 * a + (long) d * (l + r - 2))) / 2;

		return res;
	}

	// 최대 공약수 구하기
	static long getGcd(int l, int r) {
		// Al의 절댓값
		long al = Math.abs((long) a + d * (long) (r - 1));

		// 항이 하나라면, 절댓값이 최대공약수
		if (l == r) {
			return al;
		}
		// 아니라면, 첫 항과 공차의 최대공약수를 구하면 됨
		else {
			long b = Math.abs(d);

			if (al < b) {
				long temp = al;
				al = b;
				b = temp;
			}

			while (b > 0) {
				long temp = al % b;
				al = b;
				b = temp;

			}

			return al;
		}
	}
}