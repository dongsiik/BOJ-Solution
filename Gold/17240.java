
// 제목 : Team Selection
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/17240
// 메모리(kb) : 30900
// 실행시간(ms) : 400

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	//인원 수
	static int N;
	//인원별, 역할별 실력
	static int[][] stats;
	//dp[level][state]는 level명까지 state 상태로 조를 짠 상태에서, 앞으로 빈자리를 채워서 더 얻을 수 있는 실력의 최대치
	static int[][] dp;
	//정상적으로 나올 수 있는 수를 더해도 음수가 될만한 적당히 큰 음수
	static final int INF = -10000;

	public static void main(String[] args) throws NumberFormatException, IOException {

		//입력받기
		init();
		//재귀 & DP로 답 구하기
		System.out.println(getDP(0, 0));

	}

	static void init() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		stats = new int[N][5];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				stats[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//dp 테이블 초기화
		dp = new int[N][1 << 5];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < (1 << 5); j++) {
				dp[i][j] = INF;
			}
		}
	}

	static int getDP(int level, int state) {
		//모든 사람을 다 봤을 때, 역할이 다 채워졌으면 0, 아니면 INF 리턴
		if (level == N) {
			if (state == (1 << 5) - 1)
				return 0;
			else
				return INF;
		}
		
		//이미 처리했다면 처리한 값 반환
		if (dp[level][state] != INF)
			return dp[level][state];

		//현재 사람을 아무 역할에도 안 넣었을 때
		int ret = getDP(level + 1, state);

		//현재 사람을 빈 역할에 넣어봤을 때
		for (int i = 0; i < 5; i++) {
			if ((state & (1 << i)) == 0)
				ret = Math.max(ret, stats[level][i] + getDP(level + 1, state | (1 << i)));
		}

		//dp 테이블 채우기
		return dp[level][state] = ret;
	}

}
