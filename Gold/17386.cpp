// 제목 : 선분 교차 1
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/17386
// 메모리(kb) : 2020
// 실행시간(ms) : 0

//17387.cpp와 완전히 같은 코드입니다. 문제의 조건상 모두 한 직선 상에 있을 리 없으니 관련된 코드를 삭제해도 무방합니다.

#include<iostream>

using namespace std;

#include<iostream>
#include<algorithm>

using namespace std;

int CCW(pair<long long, long long>& p1, pair<long long, long long>& p2, pair<long long, long long>& p3) {
	//AB와 AC의 외적값의 z좌표
	long long crossProduct = (p2.first - p1.first) * (p3.second - p1.second) - (p2.second - p1.second) * (p3.first - p1.first);

	//AB의 반시계방향쪽에 AC가 있는 경우
	if (crossProduct > 0) return 1;
	//AB와 AC가 나란할 경우
	else if (crossProduct == 0) return 0;
	//AB의 시계방향쪽에 AC가 있는 경우
	else return -1;
}

int main() {

	//빠른 입출력
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	//점 입력받기
	pair<long long, long long> A, B, C, D;

	cin >> A.first >> A.second >> B.first >> B.second;
	cin >> C.first >> C.second >> D.first >> D.second;

	int abc = CCW(A, B, C);
	int abd = CCW(A, B, D);
	
	int cda = CCW(C, D, A);
	int cdb = CCW(C, D, B);

	//모두 한 직선일 때
	if (abc == 0 && abd == 0 && cda == 0 && cdb == 0) {
		//선분 AB에서 왼쪽이나 아래에 있는 점을 A라고 함. CD도 동일
		if (A > B) swap(A, B);
		if (C > D) swap(C, D);
		
		//선분이 겹치면 1 출력, 아니면 0 출력
		if (A <= D && B >= C) {
			cout << 1;
			return 0;
		}
		else {
			cout << 0;
			return 0;
		}
	}

	//선분이 교차하는 경우 1 출력, 아니면 0 출력
	if (abc * abd <= 0 && cda * cdb <= 0) cout << 1;
	else cout << 0;

	return 0;
}
