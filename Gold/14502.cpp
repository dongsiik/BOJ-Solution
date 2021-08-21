// 제목 : 연구소
// 티어 : 골드5
// 링크 : https://www.acmicpc.net/problem/14502
// 메모리(kb) : 2020
// 실행시간(ms) : 256

//아이디어 : 재귀적으로 가능한 위치에 벽을 하나씩 세워보고, 세 개가 되는 순간 복사본 지도에 바이러스를 퍼뜨려서 안전영역의 칸 수를 세본 후 비교한다.

#include <iostream>
using namespace std;

int n, m;		//지도의 세로, 가로 크기
int arr[8][8];	//최대 크기로 설정한 지도
int tmp[8][8];	//복사본 지도
int answer = 0;	//지금까지 구해본 안전영역의 최대 칸 수
int dx[4] = { -1,0,1,0 };	//델타탐색용 배열
int dy[4] = { 0,1,0,-1 };

//x,y 위치에 바이러스가 있다면, 재귀로 사방에 퍼뜨림
void virus(int x, int y) {
	if (tmp[x][y] != 2) return;
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
			if (tmp[nx][ny] == 0) {
				tmp[nx][ny] = 2;
				virus(nx, ny);
			}
		}
	}
}

//복사본 지도에서 안전영역의 칸 수를 세서 반환함
int getSize() {
	int result = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (tmp[i][j] == 0) result++;
		}
	}
	return result;
}

//벽을 세 곳 설치해보는 함수
void DFS(int cnt) {
	//설치한 벽의 개수가 3곳이라면
	if (cnt == 3) {
		//복사본 지도를 만들고
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				tmp[i][j] = arr[i][j];
			}
		}
		//곳곳에 바이러스가 있다면 퍼뜨린 다음
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				virus(i, j);
			}
		}
		//안전영역 수를 세서 지금까지 구한 값과 비교 후 갱신
		answer = max(answer, getSize());
		return;
	}
	//벽의 개수가 3개 이하라면
	if (cnt < 3) {
		//재귀적으로 설치함
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 0) {
					arr[i][j] = 1;
					cnt += 1;
					DFS(cnt);
					arr[i][j] = 0;
					cnt -= 1;
				}
			}
		}
	}

}

int main() {
	//지도의 크기와, 지도 칸의 정보를 입력받음
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> arr[i][j];
		}
	}

	//실행 후 출력
	DFS(0);
	cout << answer;
}