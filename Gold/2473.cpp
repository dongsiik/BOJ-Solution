// 제목 : 세 용액
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/2473
// 메모리(kb) : 2164
// 실행시간(ms) : 8

#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

//세 용액을 현재까지 구한 최선과 비교 후갱신
long long judge(int left, int middle, int right, vector<int>& solutions, long long& closest, vector<int>& answer) {
	long long newSolution = (long long)solutions[left] + solutions[middle] + solutions[right];

	if (closest > abs(newSolution)) {
		closest = abs(newSolution);
		answer[0] = solutions[left];
		answer[1] = solutions[middle];
		answer[2] = solutions[right];
	}

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
	vector<int> solutions(n);

	for (int i = 0; i < n; i++)
		cin >> solutions[i];

	//오름차순 정렬
	sort(solutions.begin(), solutions.end());

	//현재까지 0과의 차이가 가장 작은 값과 그 때 세 용액의 번호
	long long closest = 3e9 + 1;
	vector<int> answer(3,0);


	//1번 용액을 0번부터 시험해보고, 그에 맞춰 2~3번 용액을 두 포인터 알고리즘으로 찾는다
	for (int left = 0; left < n - 2; left++) {
		//2번 용액은 1번 용액 바로 다음으로 시작
		int middle = left + 1;
		//1,2번 용액을 합쳤을 때 0보다 크다면, 이보다 큰 1,2번 조합은 시험해볼 필요가 없음
		if (solutions[left] + solutions[middle] >= 0) {
			//연이은 세 용액으로 최선과 비교 후 종료
			judge(left, middle, middle + 1, solutions, closest, answer);
			break;
		}
		//두 포인터 알고리즘 사용하기
		else {
			//이분탐색으로 찾은 3번 용액 특성값의 상한선
			int right = lower_bound(solutions.begin() + middle, solutions.begin() + n, -(solutions[left] + solutions[middle])) - solutions.begin();
			if (right == n) right--;

			//두 포인터 알고리즘
			while (true) {
				long long newSolution= judge(left, middle, right, solutions, closest, answer);

				if (middle + 1 == right) break;
				if (newSolution == 0) {
					cout << answer[0] << ' ' << answer[1] << ' ' << answer[2];
					return 0;
				}
				else if (newSolution > 0)
					right--;
				else
					middle++;
			}
		}
	}


	//출력
	cout << answer[0] << ' ' << answer[1] << ' ' << answer[2];

	return 0;
}