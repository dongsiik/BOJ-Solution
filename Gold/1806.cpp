// 제목 : 부분합
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/1806
// 메모리(kb) : 2412
// 실행시간(ms) : 8

#include<iostream>

using namespace std;

int main() {

	//빠른 입출력
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	//입력받기
	int n, s;
	cin >> n >> s;
	int* arr = new int[n];
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}

	//두 포인터 알고리즘
	int left = 0;
	int right = 0;
	int sum = 0;

	//수열의 최대 길이는 n의 상한값인 100000이므로, 100001을 초기값으로 놓고 점점 줄여나갈 예정
	int answer = 100001;

	while (true) {
		//구하려는 값보다 크거나 같으면 왼쪽 한 칸을 뺌
		if (sum >= s) {
			sum -= arr[left++];
		}
		//구하려는 값보다 작은데 오른쪽 칸이 다 찼다면 종료
		else if (right == n) break;
		//구하려는 값보다 작으며 오른쪽에 더 칸이 있다면 오른쪽 한 칸을 더함
		else sum += arr[right++];

		//현재 합이 구하려는 값 이상이라면 수열의 최소 길이 갱신
		if (sum >= s) {
			answer = min(answer, right - left);
		}
	}

	//길이가 100001이라면 조건을 만족하는 게 불가능했으므로 0 출력, 아니라면 최소 길이 출력
	if (answer == 100001) answer = 0;
	cout << answer;

	return 0;
}