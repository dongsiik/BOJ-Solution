// 제목 : 그룹 나누기 (Subset)
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/16132
// 메모리(kb) : 15876
// 실행시간(ms) : 144

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	
	//학생수, 두 부분집합의 원소의 합이 같을 때 그 값
	static int N, upperBound;
	//dp[level][sum] = num은 level번 학생 전까지 고려해서 한 쪽 부분집합 원소의 합이 sum일 때
	//지금부터 두 집합의 합이 같도록 만들어나갈 수 있는 경우의 수가 num이라는 뜻
	static long[][] dp;

	public static void main(String args[]) throws IOException {
		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//학생 수
		N = Integer.parseInt(br.readLine());

		// 산술적으로 불가능한 상황이라면 0 리턴 후 종료
		if (N * (N + 1) % 4 != 0) {
			System.out.println(0);
			return;
		}

		// 두 부분집합의 원소의 합이 같을 때 그 값
		upperBound = N * (N + 1) / 4;
		//dp 테이블 만들고 초기화
		dp = new long[N + 1][upperBound + 1];
		for (int i = 0; i <= N; i++)
			Arrays.fill(dp[i], -1);

		// 출력
		// 두 집합을 나누었을 때, 집합 간의 순서는 중요하지 않으므로 2로 나눔
		long answer = recursion(0, 0) / 2;
		if (answer > 0)
			System.out.println(answer);
		else 
			System.out.println(0);

	}

	//재귀로 dp 테이블 채우기
	static long recursion(int level, int sum) {
		//이미 한 쪽을 더한 값이 너무 크면 불가능하다고 반환
		if (sum > upperBound)
			return Integer.MIN_VALUE;

		//모든 학생들을 고려했을 때
		if (level == N) {
			//잘 됐으면 1 반환, 아니면 불가능하다고 반환
			if (sum == upperBound)
				return 1;
			else
				return Integer.MIN_VALUE;
		}

		//이미 처리했으면 처리한 값
		if (dp[level][sum] != -1)
			return dp[level][sum];

		//방문처리
		dp[level][sum] = Integer.MIN_VALUE;

		//이 원소를 넣었거나넣지 않았을 때 학생들을 잘 나눌 수 있다면
		if (recursion(level + 1, sum) > 0 || recursion(level + 1, sum + level + 1) > 0) {
			//지금 상황에서 경우의 수를 늘려줌
			dp[level][sum] = Math.max(0, recursion(level + 1, sum))
					+ Math.max(0, recursion(level + 1, sum + level + 1));
		}

		return dp[level][sum];
	}
}