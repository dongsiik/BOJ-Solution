// 제목 : 대피소 찾기
// 티어 : 플래티넘 5
// 링크 : https://www.acmicpc.net/problem/16491
// 메모리(kb) : 2020
// 실행시간(ms) : 0

#include<iostream>
#include<algorithm>

using namespace std;

//로봇의 좌표, 대피소의 좌표, 로봇의 수, 로봇별로 사용할 대피소 번호
pair<int, int> robots[11];
pair<int, int> shelters[11];
int n;
int answer[11];

int CCW(pair<int, int>& p1, pair<int, int>& p2, pair<int, int>& p3) {
	//P1P2와 P1P3의 외적값의 z좌표
	int crossProduct = (p2.first - p1.first) * (p3.second - p1.second) - (p2.second - p1.second) * (p3.first - p1.first);

	//P1P2의 반시계방향쪽에 P1P3가 있는 경우
	if (crossProduct > 0) return 1;
	//P1P2와 P1P3가 나란할 경우
	else if (crossProduct == 0) return 0;
	//P1P2의 시계방향쪽에 P1P3가 있는 경우
	else return -1;
}

//P1P2와 P3P4가 교차하는지 판정
bool intersectionCheck(pair<int, int>& p1, pair<int, int>& p2, pair<int, int>& p3, pair<int, int>& p4) {
	int p123 = CCW(p1, p2, p3);
	int p124 = CCW(p1, p2, p4);
	int p341 = CCW(p3, p4, p1);
	int p342 = CCW(p3, p4, p2);
	//두 선분이 한 직선 위에 있을 때
	if (p123 == 0 && p124 == 0 && p341 == 0 && p342 == 0) {
		//떨어진 두 경우는 false, 아닌 경우는 true
		if (p1 > p3 && p1 > p4 && p2 > p3 && p2 > p4) return false;
		else if (p1 < p3 && p1 < p4 && p2 < p3 && p2 < p4) return false;
		else return true;
	}
	//그 외에 서로 교차하면 true, 아니면 false
	else if (p123 * p124 <= 0 && p341 * p342 <= 0) return true;
	else return false;
}

//level번 로봇과 이전 로봇들의 대피소 이동 경로가 겹치는지 확인해서 겹치면 true, 아니면 false 반환
bool intersectionCheck(int level) {
	for (int i = 1; i < level; i++) {
		if (intersectionCheck(robots[i], shelters[answer[i]], robots[level], shelters[answer[level]])) return true;
	}

	return false;
}

//1번 로봇부터 이용하는 대피소 번호 출력하기
void printAnswer() {
	for (int i = 1; i <= n; i++) cout << answer[i] << '\n';
}

//재귀로 구현
bool dfs(int level) {
	//모든 로봇이 경로가 겹치지 않게 대피소 지정이 끝났다면 결과 출력
	if (level == n + 1) {
		printAnswer();
		return true;
	}

	//level번 로봇이 사용할 대피소로 i번 대피소 지정하기
	for (int i = 1; i <= n; i++) {
		answer[level] = i;
		//다른 로봇과 안 겹친다면 다음 로봇 대피소 지정
		if (!intersectionCheck(level)) {
			if(dfs(level + 1)) return true;
		}
	}

	return false;
}


int main() {

	//빠른 입출력
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	//로봇 수, 로봇 좌표, 대피소 좌표 입력받기
	cin >> n;
	for (int i = 1; i <= n; i++) {
		int lat, lon;
		cin >> lat >> lon;
		robots[i] = { lat, lon };
	}

	for (int i = 1; i <= n; i++) {
		int lat, lon;
		cin >> lat >> lon;
		shelters[i] = { lat, lon };
	}

	//재귀
	dfs(1);

	return 0;
}
