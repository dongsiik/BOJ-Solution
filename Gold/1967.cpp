// 제목 : 1967
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/1967
// 메모리(kb) : 3028
// 실행시간(ms) : 4

//1167과 거의 똑같은 문제입니다.
//아이디어 : 임의의 한 점부터 시작해서 가장 먼 점 x를 찾고, 그 점 x부터 가장 먼 점 y를 찾아서 x와 y 사이의 거리를 구하면 된다.
//아이디어 출처 : https://blog.myungwoo.kr/112

#define _CRT_SECURE_NO_WARNINGS 
#include<iostream>
#include<vector>
//V의 최대 크기
#define MAX 10000

using namespace std;

//그래프
vector<pair<int, int>> graph[MAX+1];
// DFS 시작점부터의 거리, 아직 방문하지 않았으면 -1
int visited[MAX+1];
// 시작점부터의 최대 거리와 그 때 번호
int maxDistance;
int maxIndex;

//DFS
void dfs(int start) {
    //매개변수로 받은 점에 연결된 모든 점들에 대해서
    for (int i = 0; i < graph[start].size(); i++) {
        //점 번호와 거리
        int end = graph[start][i].first;
        int value = graph[start][i].second;
        //방문하지 않았다면 거리를 구하고, 최대 거리와 비교한 후, 재귀
        if (visited[end] == -1) {
            visited[end] = visited[start] + value;
            if (maxDistance < visited[end]) {
                maxDistance = visited[end];
                maxIndex = end;
            }
            dfs(end);
        }
    }
}

int main(int argc, char** argv)
{
    cin.tie(0);
    ios::sync_with_stdio(0);

    //정점 수 입력받기
    int n;
    cin >> n;

    //그래프 입력받기
    for (int i = 1; i <= n-1; i++) {
        int start, end, value;
        cin >> start >> end >> value;
        graph[start].push_back({ end,value });
        graph[end].push_back({ start,value });
    }
       
    //1번점부터 DFS해서 가장 먼 점1을 찾음
    maxDistance = 0;
    maxIndex = 0;
    fill(visited, visited + n + 1, -1);
    visited[1] = 0;
    dfs(1);

    //가장 먼 점1부터 가장 먼 점2를 찾아 두 점 사이의 거리를 구함
    maxDistance = 0;
    fill(visited, visited + n + 1, -1);
    visited[maxIndex] = 0;
    dfs(maxIndex);

    //출력
    cout << maxDistance;

    return 0;
}