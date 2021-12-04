// 제목 : 용이 산다
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/3430
// 메모리(kb) : 23960
// 실행시간(ms) : 1648

#include <iostream>
#include <set>
using namespace std;

//n, m의 최댓값
const int MAX = 1000000;
//호수 수, 일기 예보 수, 일기예보, 호수에 최근 비가 내린 날짜
int N, M, forecast[MAX], lowerBound[MAX + 1];

bool getAnswer() {
	//set을 이용하여, 최근에 비가 내린 이후이면서 물을 마실 수 있는 가장 빠른 날짜를 구할 예정
	set<int> s;

	for (int i = 0; i < M; i++) {
		//일기예보 입력받기
		cin >> forecast[i];

		//비가 안 온다면, 물을 마실 수 있으므로 set에 넣음
		if (forecast[i] == 0) {
			s.insert(i);
		}
		//비가 온다면
		else {
			//직전에 비가 내린 이후이면서 물을 마실 수 있는 가장 빠른 날
			auto it = s.lower_bound(lowerBound[forecast[i]]);
			//그런 날이 없다면
			if (it == s.end()) {
				//나머지 입력 처리 후 false 반환
				for (int j = i + 1; j < M; j++) {
					cin >> forecast[i];
				}
				return false;
			}
			//최근에 비가 내린 날짜를 갱신하고, 그 날짜에 물을 마신 호수 위치를 기록하고, 물을 마심 
			else {
				lowerBound[forecast[i]] = i;
				forecast[*it] = -forecast[i];
				s.erase(it);
			}
		}
	}

	return true;
}
int main()
{
	//빠른 입출력
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	//테스트 케이스 수
	int TC;
	cin >> TC;

	for (int tc = 1; tc <= TC; tc++) {
		//초기화
		cin >> N >> M;
		for (int i = 0; i <= N; i++) {
			lowerBound[i] = 0;
		}

		//출력
		if (getAnswer()) {
			cout << "YES\n";
			for (int j = 0; j < M; j++) {
				if (forecast[j] <= 0) {
					cout << -forecast[j] << ' ';
				}
			}
			cout << '\n';
		}
		else {
			cout << "NO\n";
		}
	}

	return 0;
}