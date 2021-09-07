// 제목 : 파티
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/1238
// 메모리(kb) : 2344
// 실행시간(ms) : 0

#define _CRT_SECURE_NO_WARNINGS 
#include<iostream>
#include<vector>
#include<queue>

#define MAX 1000    //마을 수 최댓값
#define INF 1e9     // 거리 최댓값

using namespace std;

//마을 수, 도로 수, 파티가 열리는 장소
int n, m, x;    
//그래프 정보. 하나만 써도 되지만 dijkstra 코드를 두 번 짜기 싫어서 이렇게 했습니다.
vector<pair<int, int>> graphToParty[MAX + 1];
vector<pair<int, int>> graphFromParty[MAX + 1];

//파티장으로 갈 때, 집으로 갈 때
int distanceToParty[MAX + 1];
int distanceFromParty[MAX + 1];

//(E+V)logV의 시간복잡도를 가지는 dijkstra 알고리즘
void dijkstra(vector<pair<int,int>>* graph, int* d, int start) {
    //우선순위 큐로 거리와 도착점을 저장함, C++에는 최대힙을 지원하므로, 거리는 -를 붙여서 넣었음
    priority_queue<pair<int, int>> pq;
    //거리 무한대로 초기화 및 시작점 거리 0으로 두고 큐에 넣기
    fill(d, d + MAX + 1, INF);
    d[start] = 0;
    pq.push({ 0, start });

    while (!pq.empty()) {
        //가장 가까운 마을 뽑아오기
        int dist = -pq.top().first;
        int cur = pq.top().second;
        pq.pop();

        //이미 방문처리 했다면 생략
        if (d[cur] < dist) continue;

        //아니라면 이 마을과 연결된 마을의 최단거리를 갱신하고, 갱신된 마을이 있으면 큐에 집어넣음
        for (int i = 0; i < graph[cur].size(); i++) {
            int cost = dist + graph[cur][i].second;
            if (cost < d[graph[cur][i].first]) {
                d[graph[cur][i].first] = cost;
                pq.push({ -cost, graph[cur][i].first });
            }
        }
    }

}

//갈 때와 올 때를 합해서 가장 많이 걸린 시간 구하기
int getMaximumTotalDistance() {
    int res = 0;

    for (int i = 1; i <= n; i++)
        res = res > (distanceFromParty[i] + distanceToParty[i]) ? res : (distanceFromParty[i] + distanceToParty[i]);

    
    return res;
}

int main(int argc, char** argv)
{
    cin.tie(0);
    ios::sync_with_stdio(0);

    //입력받기
    cin >> n >> m >> x;

    for (int i = 0; i < m; i++) {
        int start, end, value;
        cin >> start >> end >> value;
        graphFromParty[start].push_back({ end, value });
        graphToParty[end].push_back({ start,value });
    }
    
    //파티에 갈 때와 올 때 시간을 각각 다익스트라 알고리즘으로 계산
    dijkstra(graphFromParty, distanceFromParty,x);
    dijkstra(graphToParty, distanceToParty, x);

    //출력
    cout << getMaximumTotalDistance();

    return 0;
}