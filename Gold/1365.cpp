// 제목 : 꼬인 전깃줄
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/1365
// 메모리(kb) : 2804
// 실행시간(ms) : 12

#include <iostream>

using namespace std;

int main() {

	//빠른 입출력	
	cin.tie(0);
	ios::sync_with_stdio(0);

	//전깃줄의 개수
	int numOfLines;
	cin >> numOfLines;

	int* arr = new int[numOfLines];
	//입력받기
	for (int i = 0; i < numOfLines; i++) {
		cin >> arr[i];
	}

	//평범한 n logn LIS 알고리즘
	int* LIS = new int[numOfLines + 1];
	LIS[0] = 0;
	int lengthOfLIS = 0;

	for (int i = 0; i <numOfLines; i++) {

		int pos = lower_bound(LIS, LIS + lengthOfLIS + 1, arr[i]) - LIS;
		LIS[pos] = arr[i];
		if (pos == lengthOfLIS+1) lengthOfLIS++;
	}

	//없애야할 전깃줄의 최소 개수 = 전체 전깃줄 - 꼬이지 않은 전깃줄 최대 개수
	cout << numOfLines - lengthOfLIS;
}