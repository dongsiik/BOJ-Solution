
// 제목 : 리모컨
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/1107
// 메모리(kb) : 14344
// 실행시간(ms) : 164

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 이동하려는 채널, 부서진 버튼 수
	static int N, M;
	// 숫자 버튼이 부서졌는지
	static boolean[] broken;

	public static void main(String[] args) throws NumberFormatException, IOException {

		init();
		getAnswer();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		M = Integer.parseInt(br.readLine());
		broken = new boolean[10];

		if (M != 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++)
				broken[Integer.parseInt(st.nextToken())] = true;
		}
	}

	static void getAnswer() {
		// 100부터 +-버튼으로 이동하는 경우가 가능한 출력의 최댓값
		int answer = Math.abs(N - 100);

		// 반복문을 돌면서, 그 수를 리모컨으로 누르고 +-하는 것과 비교 후 갱신
		for (int i = 0; i <= Math.max(100, 2 * N); i++) {
			answer = Math.min(answer, getDistance(i));
		}
		System.out.println(answer);
	}

	static int getDistance(int num) {
		// 숫자 버튼이 부서져서 못 누르는 수라면 int 최댓값 반환
		if (containsBroken(num))
			return Integer.MAX_VALUE;

		// 아니라면 그 숫자를 누르는 데 필요한 숫자버튼, +-버튼의 합을 구해서 반환
		int result = getDigit(num);

		result += Math.abs(N - num);

		return result;
	}

	// 숫자를 부서진 버튼을 피해서 누를 수 있는지 확인하는 함수
	static boolean containsBroken(int num) {

		if (num == 0 && broken[0])
			return true;

		while (num > 0) {
			if (broken[num % 10])
				return true;
			num /= 10;
		}

		return false;
	}

	// 자릿수 구하기. 보통은 Math.log를 써도 되지만, 이 경우는 0이 포함되고 이게 더 빨라서 새로 만들었습니다..
	static int getDigit(int num) {
		if (num == 0)
			return 1;

		int count = 0;

		while (num > 0) {
			count++;
			num /= 10;
		}

		return count;
	}

}