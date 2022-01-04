// 제목 : 바코드 찢기
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/23892
// 메모리(kb) : 17600
// 실행시간(ms) : 236

// 공식 풀이 참고
// https://upload.acmicpc.net/2e037db3-396a-4b24-a6bc-808f187335ac/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 음료수 수, 음료수 가격
		long N = Long.parseLong(st.nextToken());
		long K = Long.parseLong(st.nextToken());

		// 가지고 있는 바코드의 가치
		st = new StringTokenizer(br.readLine());
		long given = parse(st.nextToken(), Long.parseLong(st.nextToken()));

		// 음료수에 붙어있는 바코드의 가치
		st = new StringTokenizer(br.readLine());
		long barcodeNum = parse(st.nextToken(), Long.parseLong(st.nextToken()));

		// 하나도 못 사는 경우
		if (given < K) {
			System.out.println(0);
			return;
		}

		// 계속 살 수 있는 경우
		if (barcodeNum >= K) {
			System.out.println(N);
			return;
		}

		// 그 외
		long Tmin = (given - K) / (K - barcodeNum) + 1;
		System.out.println(Math.min(Tmin, N));

	}

	// P번 반복되는 바코드의 가치
	static long parse(String barcode, long P) {

		int length = barcode.length();

		long res = parse(barcode) * P;

		// 바코드가 반복되며 나오는 가치
		if (barcode.charAt(length - 1) == '(' && barcode.charAt(0) == ')') {
			res += P - 1;
		}

		return res;

	}

	// 바코드 하나의 가치
	static long parse(String barcode) {
		int length = barcode.length();

		long res = 0;

		for (int i = 0; i < length - 1; i++) {
			if (barcode.charAt(i) == '(' && barcode.charAt(i + 1) == ')') {
				res++;
			}
		}

		return res;
	}
}