// 제목 : 경쟁적 전염
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/18405
// 메모리(kb) : 2664
// 실행시간(ms) : 36

#include <queue>
#include <iostream>

using namespace std;

int main() {
	//최대 크기로 잡은 시험관 지도
	int arr[201][201];
	//최대 크기로 잡은 바이러스 목록(바이러스의 좌표가 저장됨)
	queue<pair<int, int>> virus[1001];
	//문제와 동일한 변수들
	int k, n, s, x, y;
	//델타 탐색용 배열
	int dx[4] = { -1,0,1,0 };
	int dy[4] = { 0,1,0,-1 };
	//값
	int t = 0;

	//배열 초기화
	for (int i = 0; i < 201; i++) {
		for (int j = 0; j < 201; j++) {
			arr[i][j] = 0;
		}
	}

	//지도를 읽으면서 바이러스가 있다면 목록에도 넣어줌
	cin >> n >> k;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			int a;
			cin >> a;
			if (a != 0) {
				arr[i][j] = a;
				virus[a].push({ i,j });
			}
		}
	}

	//s위치에 x,y,좌표를 확인할 예정
	cin >> s >> x >> y;

	//s시간까지 진행
	while (t < s) {
		t++;
		//각각의 바이러스들에 대하여
		for (int i = 1; i <= k; i++) {
			//지난 단계에서 추가되어 현재 단계에서 번져나갈 가능성이 있는 바이러스들 수
			int t_num = virus[i].size();
			//그 바이러스들마다
			if (t_num != 0) {
				for (int j = 0; j < t_num; j++) {
					//바이러스 위치 확인
					int cx = virus[i].front().first;
					int cy = virus[i].front().second;
					virus[i].pop();
					//주변을 봐서 감염시킬만하면 감염시키고 바이러스 목록에 넣어줌
					for (int k = 0; k < 4; k++) {
						int nx = cx + dx[k];
						int ny = cy + dy[k];
						if (nx > 0 && ny > 0 && nx <= n && ny <= n && arr[nx][ny] == 0) {
							arr[nx][ny] = arr[cx][cy];
							virus[i].push({ nx,ny });
						}
					}
				}
			}
		}
	}

	//출력
	cout << arr[x][y];
}