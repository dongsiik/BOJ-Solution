// 제목 : 특정한 최단 경로
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/1504
// 메모리(kb) : 5744
// 실행시간(ms) : 56

#define _CRT_SECURE_NO_WARNINGS 
#include<iostream>
#include<vector>
#include<queue>

#define MAX 800    // 정점의 최대 개수
#define INF 1e9     // 거리 최댓값

using namespace std;

//정점의 수, 간선의 수, 그래프
int n, e;    
vector<pair<int, int>> graph[MAX + 1];

//거리
int d[MAX + 1];

//시작점부터의 거리를 찾는 다익스트라 알고리즘, 끝점까지의 거리를 찾는 순간 바로 종료
int dijkstra(int start, int end) {
    fill(d, d + n + 1, INF);

    //우선순위 큐로 거리와 도착점을 저장, C++에서는 최대힙이므로 거리는 음수로 바꾸어서 넣었음
    priority_queue<pair<int, int>> pq;
    //시작점을 거리 0으로 큐에 넣어줌
    d[start] = 0;
    pq.push({ 0, start });

while (!pq.empty()) {
        //가장 가까운 정점 찾기
        int cur = pq.top().second;
        int dist = -pq.top().first;
        pq.pop();

        //정점이 도착점이면 거리를 반환하고 종료
        if (cur == end) return dist;
        //정점을 이미 처리했다면 생략
        if (d[cur] < dist) continue;
        
        for (int i = 0; i < graph[cur].size(); i++) {
            //그 정점과 연결된 다른 정점 찾기
            int next = graph[cur][i].first;
            int cost = dist + graph[cur][i].second;
            //이미 처리하지 않았다면, 거리를 갱신 후 큐에 집어넣음
            if (d[next] > cost) {
                d[next] = cost;
                pq.push({ -cost, next });

            }
        }



    }


    return -1;
}


int main(int argc, char** argv)
{
    cin.tie(0);
    ios::sync_with_stdio(0);

    //입력받기
    cin >> n >> e;

    for (int i = 0; i < e; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        graph[a].push_back({ b,c });
        graph[b].push_back({ a,c });

    }

    int v1, v2;
    cin >> v1 >> v2;

    //1 - v1 - v2- n의 거리 구하기
    int d1 = dijkstra(1, v1);
    int d2 = dijkstra(v1, v2);
    int d3 = dijkstra(v2, n);

    int dist_1_v1_v2_n = 0;
    if (d1 != -1 && d2 != -1 && d3 != -1) dist_1_v1_v2_n =  d1 + d2 + d3;
    else dist_1_v1_v2_n = -1;

    //-1이라면, 1 - v2 - v1 - n도 연결이 안 되므로 -1 출력 후 종료
    if (dist_1_v1_v2_n == -1) {
        cout << -1;
        return 0;
    }

    //1 - v2 - v1 - n의 거리 구하기
    d1 = dijkstra(1, v2);
    d2 = dijkstra(v2, v1);
    d3 = dijkstra(v1, n);

    int dist_1_v2_v1_n = 0;
    if (d1 != -1 && d2 != -1 && d3 != -1) dist_1_v2_v1_n = d1 + d2 + d3;
    else dist_1_v2_v1_n = -1;

    //최솟값 출력
    cout << min(dist_1_v1_v2_n, dist_1_v2_v1_n);
    
    return 0;
}