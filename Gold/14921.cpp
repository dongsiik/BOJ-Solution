// 제목 : 용액 합성하기
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/14921
// 메모리(kb) : 2916
// 실행시간(ms) : 12

#include<iostream>
#include<vector>

using namespace std;

//용액들
vector<int> solutions;
//현재까지 제일 0에 가까운 때 특성 값
int closest;

//용액 두 개를 섞어서 현재 0에 가장 가까운 조합과 비교 후 갱신
int judge(int left, int right) {

	int newSolution = solutions[left] + solutions[right];
	if (abs(newSolution) < abs(closest))
		closest = newSolution;

	return newSolution;
}

int main() {

	//빠른 입출력
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	//용액 정보 입력받기
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		int solution;
		cin >> solution;
		solutions.push_back(solution);
	}


	closest = 2e9 + 1;

	//두 포인터 알고리즘으로 용액 시험
	int left = 0;
	int	right = n - 1;

	while (true) {
		int newSolution = judge(left, right);

		if (left + 1 == right) break;
		if (newSolution == 0) break;

		if (newSolution > 0)
			right--;
		else
			left++;

	}

	//출력
	cout << closest;

	return 0;
}