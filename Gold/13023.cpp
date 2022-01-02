// 제목 : ABCDE
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/13023
// 메모리(kb) : 2188
// 실행시간(ms) : 56

#include <iostream>
#include <vector>

using namespace std;

//사람 수, 관계 수
int N, M;

bool dfs(vector<vector<int>>& graph, vector<bool>& visited, int nodeNum, int count) {
	//방문 처리
	visited[nodeNum] = true;
	
	//5명이 되었다면 true 리턴
	if (count == 4) {
		return true;
	}

	//친구 관계에 있는 사람들과
	for (int i = 0; i < graph[nodeNum].size(); i++) {
		//방문하지 않았으면 방문, 5명이 되었다면 따라서 true 리턴
		if (!visited[graph[nodeNum][i]] && dfs(graph, visited, graph[nodeNum][i], count + 1)) {
			return true;
		}
	}

	//5명이 원을 이루는 경우를 고려하여 false로 변경
	visited[nodeNum] = false;

	return false;
}

int main() {

	//빠른 입출력
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N >> M;

	vector<vector<int>> graph(N);
	vector<bool> visited(N, false);

	for (int i = 0; i < M; i++) {
		int a, b;
		cin >> a >> b;
		graph[a].push_back(b);
		graph[b].push_back(a);
	}

	//각각을 시작점으로 하여 5명이 이어지는지 확인
	for (int i = 0; i < N; i++) {
	
		if (dfs(graph, visited, i, 0)) {
			cout << 1;
			return 0;
		}
			
	}

	cout << 0;
	return 0;
}