// 제목 : 앱
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/7579
// 메모리(kb) : 2180
// 실행시간(ms) : 0

#include<iostream>

using namespace std;

int main() {

	//빠른 입출력
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	//앱 수, 확보하고 싶은 메모리
	int n, m;
	cin >> n >> m;

	//앱 별로 메모리와 비용을 입력받음
	int* memory = new int[n];
	int* cost = new int[n];
	int costSum = 0;

	for (int i = 0; i < n; i++)
		cin >> memory[i];

	for (int i = 0; i < n; i++) {
		cin >> cost[i];
		costSum += cost[i];
	}

	//dp 테이블 만들기. dp[i] = j는 i 비용으로 확보할 수 있는 최대 메모리 용량이 j라는 뜻
	int* dp = new int[costSum + 1];
	fill(dp, dp + costSum + 1, 0);

	//dp
	for (int i = 0; i < n; i++) {
		for (int j = costSum; j >= cost[i]; j--) {
			dp[j] = max(dp[j], dp[j - cost[i]] + memory[i]);
		}
	}

	//동적할당 해제
	delete[] cost;
	delete[] memory;

	//목표치를 달성한 최소 비용 출력 후 종료
	for (int i = 0; i <= costSum; i++) {
		if (dp[i] >= m) {
			cout << i;
			return 0;
		}
	}


	return 0;
}