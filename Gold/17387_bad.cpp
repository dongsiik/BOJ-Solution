// 제목 : 선분 교차 2
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/17387
// 메모리(kb) : 2020
// 실행시간(ms) : 0

#include<iostream>

using namespace std;

//직선은 선분의 양끝을 무한히 늘인 것입니다. 주석을 읽을 때 혼동하지 마세요.
//벡터 대신 부족함이 많은 포인트 클래스를 사용하여 코드가 불필요하게 길어졌습니다. 17387.cpp를 읽어주세요.

//포인트 클래스
class Point {
public:
	long long x, y;
	Point(long long x, long long y) {
		this->x = x;
		this->y = y;
	}
};

//line1, line2를 포함하는 직선에서 p의 상대적인 위치 구하기
long long getSign(Point& line1, Point& line2, Point& p) {
	return (line2.y - line1.y) * (p.x - line1.x) - (line2.x - line1.x) * (p.y - line1.y);
}

//line1, line2를 포함하는 직선에서 p1, p2의 상대적인 위치 구하기
int acrossLine(Point& line1, Point& line2, Point& p1, Point& p2) {

	long long sign1 = getSign(line1, line2, p1);
	long long sign2 = getSign(line1, line2, p2);

	//line 1, line 2, p1, p2 모두 한 직선 상에 있음
	if (sign1 == 0 && sign2 == 0) return 0;
	//직선을 기준으로 p1, p2가 반대편에 있음
	else if ((sign1 <= 0 && sign2 >= 0) || (sign1 >= 0 && sign2 <= 0)) return 1;
	//p1, p2가 같은 편에 있음
	else return -1;
}

//p1~p4가 같은 직선상에 있을 때
bool sameLineCase(Point& p1, Point& p2, Point& p3, Point& p4) {
	//각각 p1 p2 선분이 p3 p4 선분과 겹치지 않으면서 오른쪽, 왼쪽, 위쪽, 아래쪽에 있는지 확인
	if (p1.x > p3.x && p1.x > p4.x && p2.x > p3.x && p2.x > p4.x) return false;
	if (p1.x < p3.x && p1.x < p4.x && p2.x < p3.x && p2.x < p4.x) return false;
	if (p1.y > p3.y && p1.y > p4.y && p2.y > p3.y && p2.y > p4.y) return false;
	if (p1.y < p3.y && p1.y < p4.y && p2.y < p3.y && p2.y < p4.y) return false;

	return true;
}

//결과 출력
void printAnswer(Point& p1, Point& p2, Point& p3, Point& p4) {
	//같은 직선상에 있을 때는 겹치는지 아닌지를 확인
	if (acrossLine(p1, p2, p3, p4) == 0 && acrossLine(p3, p4, p1, p2) == 0) {
		if (sameLineCase(p1, p2, p3, p4)) cout << 1;
		else cout << 0;
	}
	//아니라면 한 선분이 다른 선분을 연장한 직선에 걸치는지 아닌지를 확인
	else if (acrossLine(p1, p2, p3, p4) >= 0 && acrossLine(p3, p4, p1, p2) >= 0) cout << 1;
	else cout << 0;
}


int main() {

	//빠른 입출력
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	//입력받아서 Point로 만들기
	long long x1, y1, x2, y2, x3, y3, x4, y4;
	cin >> x1 >> y1 >> x2 >> y2;
	cin >> x3 >> y3 >> x4 >> y4;

	Point p1(x1, y1);
	Point p2(x2, y2);
	Point p3(x3, y3);
	Point p4(x4, y4);

	//판정
	printAnswer(p1, p2, p3, p4);

	return 0;
}
