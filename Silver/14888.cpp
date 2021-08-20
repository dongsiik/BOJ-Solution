// 제목 : 연산자 끼워넣기 
// 티어 : 실버1
// 링크 : https://www.acmicpc.net/problem/14888
// 메모리(kb) : 2020
// 실행시간(ms) : 0

#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main() {
	int n;		//숫자의 수
	int An[11];	//수열
	int maxV = -1e9;	//연산의 최댓값
	int minV = 1e9;		//연산의 최댓값
	vector<int> ope;	//연산자들

	//숫자 입력받기
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> An[i];
	}
	//연산자 4종류에 대하여
	for (int i = 1; i <= 4; i++) {
		//각각 연산자의 개수
		int x;
		cin >> x;
		//그 연산자의 개수만큼 ope에 집어넣기
		for (int j = 0; j < x; j++) ope.push_back(i);
	}

	//가능한 모든 연산자 배치 경우의 수 시험
	do {
		int now = An[0];
		for (int i = 0; i < n - 1; i++) {
			//연산자에 따라 전값과 다음값 연산
			switch (ope[i]) {
			case 1:
				now += An[i + 1];
				break;
			case 2:
				now -= An[i + 1];
				break;
			case 3:
				now *= An[i + 1];
				break;
			case 4:
				now /= An[i + 1];
				break;
			}
		}
		//연산 후 결과 비교
		minV = min(minV, now);
		maxV = max(maxV, now);
	} while (next_permutation(ope.begin(), ope.end()));

	//출력
	cout << maxV << endl;
	cout << minV << endl;
}