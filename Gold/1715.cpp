// 제목 : 카드 정렬하기
// 티어 : 골드4
// 링크 : https://www.acmicpc.net/problem/1715
// 메모리(kb) : 2800
// 실행시간(ms) : 36

//363쪽 Q26 : 카드 정렬하기

#include <iostream>
#include <queue>

using namespace std;


int main() {
	//최소 힙으로 쓸 우선순위 큐, 값을 음수로 넣어서 사용할 예정
	priority_queue<int> q;
	int n;
	int result = 0;

	//카드 개수를 입력받고, 그만큼 카드를 넣음
	cin >> n;
	for (int i = 0; i < n; i++) {
		int x;
		cin >> x;
		q.push(-x);
	}

	//카드가 한 장 남을 때까지
	while (q.size() != 1) {
		//두 카드를 빼서 합한 다음 집어넣음
		int one = -q.top();
		q.pop();
		int two = -q.top();
		q.pop();
		int sum = one + two;
		result += sum;
		q.push(-sum);
	}

	//출력
	cout << result;

}