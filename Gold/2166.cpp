// 제목 : 다각형의 면적
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/2166
// 메모리(kb) : 2020
// 실행시간(ms) : 4

#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

double CCW(pair<double, double>& p1, pair<double, double>& p2, pair<double, double>& p3) {
	//외적으로 구한 삼각형 P1P2P3의 넓이
	return 0.5*((p2.first - p1.first) * (p3.second - p1.second) - (p2.second - p1.second) * (p3.first - p1.first));
}

int main() {

	//빠른 입출력
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	//점의 수
	int N;
	cin >> N;

	//처음 두 점 입력받기
	pair<double, double> fixedPoint, prevPoint, nextPoint;
	cin >> fixedPoint.first >> fixedPoint.second >> nextPoint.first >> nextPoint.second;

	//면적
	double sum = 0;

	//고정된 점과 인접한 두 점의 삼각형 넓이를 면적에 더하기
	for (int i = 0; i < N - 2; i++) {
		prevPoint.first = nextPoint.first;
		prevPoint.second = nextPoint.second;

		cin >> nextPoint.first >> nextPoint.second;
		sum += CCW(fixedPoint, prevPoint, nextPoint);
	}

	//면적이 음수라면 양수로 바꿔줌
	if (sum < 0) sum = -sum;

	//소수 둘째자리에서 반올림하여 출력
	cout << fixed;
	cout.precision(1);
	cout << sum;

	return 0;
}
