// 제목 : Money Sharing
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/18611
// 메모리(kb) : 4472
// 실행시간(ms) : 60

#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int main()
{
	//빠른 입출력
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	//대출 수, 예금 수
	int N, M;
	cin >> N >> M;
	int total = N + M;

	//액수가 큰 예금을 거를 때 쓸 우선순위 큐
	priority_queue<pair<int, int>> pq;

	//은행 잔고
	long long balance = 0;

	//-1이면 거절, 0이면 입금, 1이면 대출 승인으로 출력문 저장
	vector<int> answer(total);

	//입력받기
	for (int i = 0; i < total; i++) {
		int x;
		cin >> x;

		//예금일 때
		if (x > 0) {
			//이전 잔고로는 실행할 수 없는 큰 대출 신청 거절하기
			while (!pq.empty() && balance< 0) {
				balance += pq.top().first;
				answer[pq.top().second] = -1;
				pq.pop();
			}

			//잔고에 추가
			balance += x;
			answer[i] = 0;
		}
		//대출일 때 우선순위 큐에 넣기
		else {
			balance += x;
			pq.push(make_pair(-x, i));
		}

	}

	//잔고로 실행할 수 없는 큰 대출 신청 거절하기
	while (!pq.empty() && balance < 0) {
		balance += pq.top().first;
		answer[pq.top().second] = -1;
		pq.pop();
	}

	//남은 대출 승인하기
	while (!pq.empty()) {
		answer[pq.top().second] = 1;
		pq.pop();
	}

	//출력
	for (int i = 0; i < total; i++) {
		switch (answer[i]) {
		case -1:
			cout << "declined\n";
			break;
		case 0:
			cout << "resupplied\n";
			break;
		case 1:
			cout << "approved\n";
			break;
		}

	}

	return 0;
}