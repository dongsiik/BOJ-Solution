// 제목 : 플로이드
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/11404
// 메모리(kb) : 2060
// 실행시간(ms) : 24
// 이것이 취업을 위한 코딩테스트다. 나동빈 지음. 한빛미디어. 2020.08.05. 251쪽부터

#include<iostream>

using namespace std;

//그래프
int graph[101][101];
//버스가 닿지 않은 곳을 표시하기 위한 거리 최댓값
int INF = 1000000000;

int main(int argc, char** argv)
{
    cin.tie(0);
    ios::sync_with_stdio(0);

    //입력받기
    int n, m;
    cin >> n >> m;

    //그래프 초기화
    fill(&graph[0][0], &graph[n][n + 1], INF);
    for (int i = 1; i <= n; i++) graph[i][i] = 0;

    //그래프 입력받기, 여러 버스 노선이 있까봐 출발지와 도착지에 따른 최소 시간만 저장
    for (int i = 0; i < m; i++) {
        int start, end, time;
        cin >> start >> end >> time;
        graph[start][end] = min(graph[start][end], time);
    }

    //플로이드 워셜 알고리즘
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            for (int k = 1; k <= n; k++) {
                graph[j][k] = min(graph[j][i] + graph[i][k], graph[j][k]);
            }
        }
    }

    //출력
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            if (graph[i][j] == INF) cout << 0 << ' ';
            else cout << graph[i][j] << ' ';
        }
        cout << '\n';
    }


    return 0;
}