// 제목 : 선분 교차 3
// 티어 : 플래티넘 4
// 링크 : https://www.acmicpc.net/problem/20149
// 메모리(kb) : 2020
// 실행시간(ms) : 0

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

//직선 p1p2, p3p4의 교차점 구하기(기울기가 다르다고 가정)
pair<double, double> getIntersection(pair<long long, long long>& p1, pair<long long, long long>& p2, pair<long long, long long>& p3, pair<long long, long long>& p4) {
	//직선 p1p2를 Ax+By = C, p3p4를 Dx+Ey = F로 표현 후, 역행렬을 곱해서 x,y를 구함
	double A = p2.second - p1.second;
	double B = p1.first - p2.first;
	double C = p2.second * p1.first - p2.first * p1.second;
	double D = p4.second - p3.second;
	double E = p3.first - p4.first;
	double F = p4.second * p3.first - p4.first * p3.second;

	double x = (E * C - B * F) / (A * E - B * D);
	double y = (-C * D + A * F) / (A * E - B * D);
	return make_pair(x, y);
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
			//한 점에서 만나는 경우 점의 좌표 출력
			if (A == D) cout << '\n' << A.first << ' ' << A.second;
			else if (B == C) cout << '\n' << B.first << ' ' << B.second;
			return 0;
		}
		else {
			cout << 0;
			return 0;
		}
	}

	//선분이 교차하는 경우 1 출력, 아니면 0 출력
	if (abc * abd <= 0 && cda * cdb <= 0) {
		cout << 1 << '\n';
		//교점의 좌표 출력
		//소수점 아래 10자리까지 출력하도록 설정
		cout.setf(ios::fixed);
		cout.precision(10);
		cout << getIntersection(A, B, C, D).first << ' ' << getIntersection(A, B, C, D).second;

	}
	else cout << 0;

	return 0;
}
