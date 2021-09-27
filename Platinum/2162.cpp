// 제목 : 선분 그룹
// 티어 : 플래티넘 5
// 링크 : https://www.acmicpc.net/problem/2162
// 메모리(kb) : 2168
// 실행시간(ms) : 68

#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

//선분 수, disjoint set 연산에 쓸 조상 벡터, vector에 담은 선분들
int n;
vector<int> parent(3000);
vector<pair<pair<int, int>, pair<int, int>>> lines(3000);

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

//경로 압축을 사용한 findParent
int findParent(int lineNum) {
	if (parent[lineNum] == lineNum) return lineNum;
	else return parent[lineNum] = findParent(parent[lineNum]);
}

//두 선분이 겹친다면 union 연산 수행
bool makeUnion(int lineNum1, int lineNum2) {
	if (intersectionCheck(lines[lineNum1], lines[lineNum2])) {
		int parent1 = findParent(lineNum1);
		int parent2 = findParent(lineNum2);
		if (parent1 < parent2) parent[parent2] = parent1;
		else parent[parent1] = parent2;
		return true;
	}
	else return false;
}


int main() {

	//빠른 입출력
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	//선분 입력받기
	cin >> n;
	for (int i = 0; i < n; i++) {
		pair<int, int> point1, point2;
		cin >> point1.first >> point1.second >> point2.first >> point2.second;
		//첫번째 점이 왼쪽이나 아래에 있도록 조정
		if (point1 > point2) swap(point1, point2);
		lines[i] = make_pair(point1, point2);
	}

	//조상 초기화
	for (int i = 0; i < n; i++) {
		parent[i] = i;
	}

	//i번째 선분에 대해서, 이전의 선분들과 교차하는지 확인
	for (int i = 1; i < n; i++) {
		for (int j = 0; j < i; j++) {
			makeUnion(i, j);
		}
	}

	//그룹별 선분 개수
	int* sizeOfSet = new int[n];
	fill(sizeOfSet, sizeOfSet + n, 0);

	//그룹 수, 가장 크기가 큰 그룹에 속한 선분 수
	int numOfSet = 0;
	int maxSizeOfSet = 0;

	for (int i = 0; i < n; i++) {
		int parentOfi = findParent(i);
		
		//자신이 부모인 경우, 그룹의 개수를 하나 늘려줌
		if (parentOfi == i) numOfSet++;
		//최대 선분 수 갱신
		maxSizeOfSet = max(maxSizeOfSet, ++sizeOfSet[parentOfi]);
	}

	//출력
	cout << numOfSet << '\n' << maxSizeOfSet;

	delete[] sizeOfSet;
	return 0;
}
