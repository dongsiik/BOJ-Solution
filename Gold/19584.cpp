// 제목 : 난개발
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/19584
// 메모리(kb) : 9072
// 실행시간(ms) : 188

#include <iostream>
#include <queue>
using namespace std;

int main()
{
	//빠른 입출력
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	//장소 수, 도로 수
	int N, M;
	cin >> N >> M;

	//장소별 y좌표
	int* yCoordinates = new int[N + 1];

	for (int i = 1; i <= N; i++) {
		int x, y;
		cin >> x >> y;
		yCoordinates[i] = y;
	}

	//y좌표로 a이상 b이하에 도로가 있다면, b이하부터 교통량이 늘다가 a-1에서 교통량이 줄어드는 것으로 볼 수 있음
	//우선순위 큐로 힙정렬을 한 다음, 장소별로 교통량이 어떻게 변하는지 볼 예정
	priority_queue<pair<int, int>> pq;

	//도로 정보 입력받기
	for (int i = 0; i < M; i++) {
		int u, v, w;
		cin >> u >> v >> w;
		int higher = max(yCoordinates[u], yCoordinates[v]);
		int lower = min(yCoordinates[u], yCoordinates[v]) - 1;

		pq.push(make_pair(higher, w));
		pq.push(make_pair(lower, -w));
	}

	//정답, 현재 좌표에 철도를 놓으면 파괴되는 교통량
	long long answer = 0;
	long long current = 0;

	//모든 장소들에 대해서
	while (!pq.empty()) {
		//현재 y좌표
		long long marginal = pq.top().second;
		int y = pq.top().first;
		pq.pop();

		//지금 좌표와 y좌표가 같은 다른 점들을 모두 지나는 교통량의 합 구하기
		while (!pq.empty() && y == pq.top().first) {
			marginal += pq.top().second;
			pq.pop();
		}

		//y좌표의 교통량 구하고, 전체 최댓값과 비교
		current += marginal;
		answer = max(answer, current);
	}

	//출력
	cout << answer;

	return 0;
}
