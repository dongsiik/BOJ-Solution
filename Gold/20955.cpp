// 제목 : 민서의 응급 수술
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/20955
// 메모리(kb) : 2412
// 실행시간(ms) : 20

#include <iostream>
using namespace std;

//분리집합 알고리즘에 쓰이는 부모 배열
int parent[100100];

//부모 찾기
int find(int child) {
	if (parent[child] == child) return child;
	else return parent[child] = find(parent[child]);
}

//합치기
bool uni(int a, int b) {
	int aParent = find(a);
	int bParent = find(b);
	if (aParent == bParent) return false;

	parent[max(aParent, bParent)] = min(aParent, bParent);
	return true;
}

int main()
{
	//빠른 입출력
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	//뉴런 수, 시냅스 수
	int N, M;
	cin >> N >> M;

	//부모 배열 초기화
	for (int i = 1; i <= N; i++) {
		parent[i] = i;
	}

	//제거할 뉴런 수
	int deleteCount = 0;

	//시냅스를 하나씩 따져보며 사이클이 생기면 제거
	for (int i = 0; i < M; i++) {
		int u, v;
		cin >> u >> v;
		if (uni(u, v)) {
		}
		else {
			deleteCount++;
		}
	}

	//남은 시냅스 수 : M - deleteCount
	//트리가 완성되었을 때 시냅스 수 : N-1
	//이어야할 시냅스 수 : N - M -1 + deleteCount

	int answer = 2 * deleteCount + N - M - 1;
	cout << answer;

	return 0;
}