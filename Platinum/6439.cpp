// 제목 : 교차
// 티어 : 플래티넘 5
// 링크 : https://www.acmicpc.net/problem/6439
// 메모리(kb) : 2020
// 실행시간(ms) : 0

#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

//선분 네 개가 담길 직사각형 벡터
vector<pair<pair<int, int>, pair<int, int>>> rectangle;

int CCW(pair<int, int>& p1, pair<int, int>& p2, pair<int, int>& p3) {
	//AB와 AC의 외적값의 z좌표
	int crossProduct = (p2.first - p1.first) * (p3.second - p1.second) - (p2.second - p1.second) * (p3.first - p1.first);

	//AB의 반시계방향쪽에 AC가 있는 경우
	if (crossProduct > 0) return 1;
	//AB와 AC가 나란할 경우
	else if (crossProduct == 0) return 0;
	//AB의 시계방향쪽에 AC가 있는 경우
	else return -1;
}

//두 선분이 교차하는지 판정
bool intersectionCheck(pair<pair<int, int>, pair<int, int>> line1, pair<pair<int, int>, pair<int, int>> line2) {
	auto p1 = line1.first;
	auto p2 = line1.second;
	auto p3 = line2.first;
	auto p4 = line2.second;
	
	int p123 = CCW(p1, p2, p3);
	int p124 = CCW(p1, p2, p4);
	int p341 = CCW(p3, p4, p1);
	int p342 = CCW(p3, p4, p2);
	//두 선분이 한 직선 위에 있을 때
	if (p123 == 0 && p124 == 0 && p341 == 0 && p342 == 0) {
		if (p1 <= p4 && p2 >= p3) return true;
		else return false;
	}
	//아니면서 교차할 때
	else if (p123 * p124 <= 0 && p341 * p342 <= 0) return true;
	//교차하지 않을 때
	else return false;
}

//선분과 직사각형의 네 변이 교차하는지 판정
bool intersectionCheck(pair<pair<int, int>, pair<int, int>> line) {
	for (int i = 0; i < rectangle.size(); i++) {
		if (intersectionCheck(rectangle[i], line)) return true;
	}

	return false;
}

int main() {

	//빠른 입출력
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	//테스트 케이스 수
	int TC;
	cin >> TC;

	//테스트 케이스 마다
	for (int tc = 0; tc < TC; tc++) {
		//선분의 두 점 입력받아 선분 만들기
		pair<int, int> point1, point2;
		cin >> point1.first >> point1.second >> point2.first >> point2.second;
		if (point1 > point2) swap(point1, point2);
		pair<pair<int, int>, pair<int, int>> lineSegment = make_pair(point1, point2);

		//직사각형의 네 꼭짓점 입력받아 직사각형 만들기
		rectangle.clear();
		int xLeft, yTop, xRight, yBottom;
		cin >> xLeft >> yTop >> xRight >> yBottom;
		if (xLeft > xRight) swap(xLeft, xRight);
		if (yBottom > yTop) swap(yBottom, yTop);

		rectangle.push_back({ {xLeft,yBottom},{xLeft,yTop} });
		rectangle.push_back({ {xRight,yBottom},{xRight,yTop} });
		rectangle.push_back({ {xLeft,yBottom},{xRight,yBottom} });
		rectangle.push_back({ {xLeft,yTop},{xRight,yTop} });

		//선분이 직사각형 내부인지 판정
		if (point1.first >= xLeft && point1.first <= xRight && point1.second >= yBottom && point1.second <= yTop) cout << "T\n";
		else if (point2.first >= xLeft && point2.first <= xRight && point2.second >= yBottom && point2.second <= yTop) cout << "T\n";
		//선분이 직사각형의 네 변과 교차하는지 판정
		else if (intersectionCheck(lineSegment)) cout << "T\n";
		//아니라면 F
		else cout << "F\n";
		
	}
	return 0;
}
