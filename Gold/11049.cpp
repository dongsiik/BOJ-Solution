// 제목 : 행렬 곱셈 순서
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/11049
// 메모리(kb) : 3076
// 실행시간(ms) : 48

#include<iostream>

using namespace std;

int main() {

	//빠른 입출력
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	//행렬의 개수
	int N;
	cin >> N;

	//dp[i][j] = value는 j-i번 행렬부터 j번행렬까지 곱했을 때 최소 연산 횟수가 value라는 뜻 (행렬 번호는 1부터 매겼음)
	int** dp = new int* [N];
	for (int i = 0; i < N; i++) {
		dp[i] = new int[N + 1];
		for (int j = 0; j <= N; j++) {
			dp[i][j] = 0;
		}
	}

	//행렬이 A*B가 정해지려면 A의 열수와 B의 행수가 같아야하므로 중복된 정보는 빼고 저장했습니다.
	int* matrices = new int[N + 1];

	int left, right;
	for (int j = 0; j < N; j++) {
		cin >> left >> right;
		matrices[j] = left;
	}
	matrices[N] = right;

	//dp 테이블 채우기
	for (int i = 1; i < N; i++) {
		for (int j = i+1; j <= N; j++) {
			dp[i][j] = 2147483647;
			//(j-i)번부터 j번까지 곱하는 경우는, (j-i)부터 (j-i+k)까지, (j-i+k+1)부터 j번까지 곱한 다음 곱한 경우들을 비교해보면 됨
			for (int k = 0; k <= i-1; k++) {
				dp[i][j] = min(dp[i][j], dp[k][j - i + k] + dp[i - k - 1][j] + matrices[j - i - 1] * matrices[j + k - i] * matrices[j]);
			}
		}
	}

	//출력
	cout << dp[N - 1][N];

	//동적할당 해제
	for (int i = 0; i < N; i++) {
		delete[] dp[i];
	}
	delete[] dp;

	return 0;
}