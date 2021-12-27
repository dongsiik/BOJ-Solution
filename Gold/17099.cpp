
// 제목 : Contest
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/17099
// 메모리(kb) : 6712
// 실행시간(ms) : 164

// 회의실 배정 4 (19623) 문제의 junseo님 코드(26492986)를 참고하였습니다.

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

//최대 대회 수
const int maxN = 3 * 1e5 + 30;

//대회 수, 현재 대회까지 최대 상금
int N, dp[maxN];
//대회 정보
pair<pair<int, int>, int> contests[maxN];


int main()
{
	//빠른 입출력
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N;
	
	//종료시간, 시작시간, 상금 순으로 입력하기
	for (int i = 1; i <= N; i++) {
		cin >> contests[i].first.second >> contests[i].first.first >> contests[i].second;
	}

	//종료시간 오름차순 정렬
	sort(contests + 1, contests + 1 + N);

	for (int i = 1; i <= N; i++) {
		//현재 대회 직전에 끝나는 대회 찾기
		int j = lower_bound(contests + 1, contests + 1 + N, pair<pair<int, int>, int>(pair<int,int> (contests[i].first.second, -1), -1)) - contests - 1;
		//끝나는 시간이 겹칠 때
		if (contests[j].first.first == contests[i].first.second) {
			dp[i] = max(dp[i - 1], dp[j-1] + contests[i].second);
		}
		//아닐 때
		else {
			dp[i] = max(dp[i - 1], dp[j] + contests[i].second);
		}
	}

	cout << dp[N];
	
	return 0;
}