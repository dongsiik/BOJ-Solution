// 제목 : 두 용액
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/2470
// 메모리(kb) : 2920
// 실행시간(ms) : 16

#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

//용액들
vector<int> solutions;
//현재까지 0에 가장 가까운 용액 조합과 그 때 특성값
int answerLeft, answerRight, closest;

//입력받은 두 용액을 현재 0에 가장 가까운 조합과 비교 후 갱신
int judge(int left, int right) {
	int newSolution = solutions[left] + solutions[right];
	if (abs(newSolution) < closest) {
		answerLeft = left;
		answerRight = right;
		closest = abs(newSolution);
	}

	return newSolution;
}

int main() {

	//빠른 입출력
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	//용액 입력받기
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		int solution;
		cin >> solution;
		solutions.push_back(solution);
	}

	//오름차순 정렬
	sort(solutions.begin(), solutions.end());


	closest = 2e9 + 1;
	answerLeft = 0;
	answerRight = n - 1;


	//두 포인터 알고리즘
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
	cout << solutions[answerLeft] << ' ' << solutions[answerRight];

	return 0;
}