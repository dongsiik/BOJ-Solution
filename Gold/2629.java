
// 제목 : 양팔저울
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/2629
// 메모리(kb) : 14912
// 실행시간(ms) : 144

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 주어진 무게가 측정가능한지를 나타내는 배열
	static boolean[] measurable;
	// 구슬 무게의 최댓값
	static final int upper_bound = 40000;

	public static void main(String[] args) throws IOException {

		measurable = new boolean[upper_bound + 1];
		measurable[0] = true;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 추 정보 입력받기
		while (st.hasMoreTokens()) {
			// 지금 추의 무게
			int weight = Integer.parseInt(st.nextToken());
			for (int i = upper_bound - weight; i >= 0; i--) {
				// 다른 조합과 같은 편 저울에 올려서 잴 수 있는 무게
				if (measurable[i]) {
					measurable[i + weight] = true;
				}
			}
			for (int i = 0; i <= upper_bound; i++) {
				// 다른 조합과 다른 편 저울에 올려서 잴 수 있는 무게
				if (measurable[i]) {
					measurable[Math.abs(i - weight)] = true;
				}
			}
		}

		br.readLine();
		st = new StringTokenizer(br.readLine());

		StringBuilder sb = new StringBuilder();

		// 구슬 무게를 잴 수 있는지 보기
		while (st.hasMoreTokens()) {
			int marble = Integer.parseInt(st.nextToken());
			if (measurable[marble])
				sb.append("Y ");
			else
				sb.append("N ");
		}

		System.out.println(sb);

	}

}