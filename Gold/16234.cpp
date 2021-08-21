// 제목 : 인구 이동
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/16234
// 메모리(kb) : 2044
// 실행시간(ms) : 460

#include <iostream>
#include <queue>

using namespace std;

//나라 수, 인구 차이 하한선, 상한선
int n, l, r;
//인구 정보가 담긴 지도
int a[50][50];
//연합 정보가 담긴 지도
int u[50][50];

//델타탐색용 배열
int dx[4] = { 1,0,-1,0 };
int dy[4] = { 0,1,0,-1 };

//x,y 위치에서 idx번 연합을 만드는 함수
void makeU(int x, int y, int idx) {
	//연합에 있는 나라의 위치 정보
	vector<pair<int, int>> uni;
	//BFS로 탐색하기 위한 queue
	queue<pair<int, int>> q;

	//시작점을 넣어주고, 연합 번호를 저장
	uni.push_back({ x,y });
	q.push({ x,y });
	u[x][y] = idx;

	//연합의 총 인구수와, 가입한 나라 수를 초기화
	int po = a[x][y];
	int num = 1;


	//BFS
	while (!q.empty()) {
		int cx = q.front().first;
		int cy = q.front().second;
		q.pop();

		//4방향을 보면서
		for (int i = 0; i < 4; i++) {
			int nx = cx + dx[i];
			int ny = cy + dy[i];
			//지도 밖으로 안 벗어나면서 연합이 없는 나라라면
			if (nx >= 0 && nx < n && ny >= 0 && ny < n && u[nx][ny] == -1) {
				int gap = abs(a[cx][cy] - a[nx][ny]);
				//인구수 조건도 만족한다면
				if (gap >= l && gap <= r) {
					//연합에 가입시키고 BFS를 위한 queue에도 넣어줌
					u[nx][ny] = idx;
					uni.push_back({ nx,ny });
					q.push({ nx,ny });
					po += a[nx][ny];
					num++;
				}
			}
		}
	}

	//연합에 가입한 나라끼리 인구수 통일
	int new_po = po / num;
	for (int i = 0; i < uni.size(); i++) {
		int nx = uni[i].first;
		int ny = uni[i].second;
		a[nx][ny] = new_po;
	}
}

int main() {
	//정보 입력받아서 지도에 표시
	cin >> n >> l >> r;
	int cnt = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> a[i][j];
		}
	}

	//날짜마다 연합 만들기
	while (true) {
		//연합 정보 초기화
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				u[i][j] = -1;
			}
		}

		//연합 번호
		int idx = 0;

		//각각의 나라마다 연합이 없다면 연합을 만듦
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (u[i][j] == -1) {
					makeU(i, j, idx);
					idx++;
				}
			}
		}
		//연합이 n*n이라면, 모든 나라가 국경선이 닫혀있다는 것이므로 종료
		if (idx == n * n) break;
		cnt++;
	}

	//출력
	cout << cnt;
}