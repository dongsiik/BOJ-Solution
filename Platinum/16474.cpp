// 제목 : 이상한 전깃줄
// 티어 : 플래티넘 5
// 링크 : https://www.acmicpc.net/problem/16474
// 메모리(kb) : 4396
// 실행시간(ms) : 56

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
	
	//빠른 입출력
	cin.tie(0);
	ios::sync_with_stdio(0);

	//왼쪽, 오른쪽 전봇대의 개수
	int n, m;
	cin >> n >> m;

	//NumtoIndex[i]=j는 i번 전봇댄는 j번째 위치에 있다는 뜻
	int* leftNumToIndex = new int[n + 1];
	int* rightNumToIndex = new int[m + 1];

	//왼쪽, 오른쪽 전봇대의 번호별 위치 입력받기
	for (int i = 1; i <= n; i++) {
		int num;
		cin >> num;
		leftNumToIndex[num] = i;
	}

	for (int i = 1; i <= m; i++) {
		int num;
		cin >> num;
		rightNumToIndex[num] = i;
	}

	//전봇대의 연결 상태를 저장할 벡터와, 전깃줄 수
	vector<vector<int>> lines(n + 1);
	int k;
	cin >> k;

	//vector[i]는 왼쪽 i번째 위치에 있는 전봇대에 연결된 오른쪽 전봇대의 위치들을 저장함
	for (int i = 0; i < k; i++) {
		int leftNum, rightNum;
		cin >> leftNum >> rightNum;
		lines[leftNumToIndex[leftNum]].push_back(rightNumToIndex[rightNum]);
	}

	//왼쪽 전봇대에 연결된 오른쪽 전봇대 위치들을 내림차순으로 정렬.
	//오른쪽 전봇대 위치가 큰 순서대로 비교해야 같은 왼쪽에 연결된 다른 전깃줄의 방해 없이 LIS 알고리즘이 가능함
	for (int i = 1; i <= n; i++) {
		sort(lines[i].begin(), lines[i].end(), greater<int>());
	}

	//n log n의 시간복잡도인 LIS 알고리즘
	int* LIS = new int[k + 1];
	LIS[0] = 0;
	int lengthOfLIS = 0;

	for (int i = 1; i <= n; i++) {
		int size = lines[i].size();
		for (int j = 0; j < size; j++) {
			int pos = lower_bound(LIS, LIS + lengthOfLIS + 1, lines[i][j]) - LIS;
			LIS[pos] = lines[i][j];
			if (pos == lengthOfLIS + 1) lengthOfLIS++;
		}
	}

	//전체 전깃줄의 개수 - 남길 수 있는 최대 전깃줄의 개수
	cout << k - lengthOfLIS;
}