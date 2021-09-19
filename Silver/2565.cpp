// 제목 : 전깃줄
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/2565
// 메모리(kb) : 2020
// 실행시간(ms) : 0

#include <iostream>

using namespace std;

int main() {
	//arr[i]=j는 A번 전봇대 i번 위치와 B번 전봇대 j번 위치가 연결되었다는 뜻
	int arr[501];
	
	fill(arr, arr + 501, 0);

	//전깃줄의 개수
	int numOfLines;
	cin >> numOfLines;

	//입력받기
	for (int i = 0; i < numOfLines; i++) {
		int from, to;
		cin >> from >> to;
		arr[from] = to;
	}

	//평범한 n logn LIS 알고리즘
	int* LIS = new int[numOfLines + 1];
	for (int i = 0; i <= numOfLines; i++) LIS[i] = 0;
	int lengthOfLIS = 0;

	for (int i = 1; i <= 500; i++) {
		if (arr[i] == 0) continue;

		int pos = lower_bound(LIS, LIS + lengthOfLIS + 1, arr[i]) - LIS;
		LIS[pos] = arr[i];
		if (pos == lengthOfLIS+1) lengthOfLIS++;
	}

	//없애야할 전깃줄의 최소 개수 = 전체 전깃줄 - 꼬이지 않은 전깃줄 최대 개수
	cout << numOfLines - lengthOfLIS;
}