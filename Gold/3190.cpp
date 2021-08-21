// 제목 : 뱀
// 티어 : 골드5
// 링크 : https://www.acmicpc.net/problem/3190
// 메모리(kb) : 2024
// 실행시간(ms) : 0

#include <queue>
#include <iostream>
using namespace std;

int main() {
	//게임판 초기화
	int board[101][101];
	for (int i = 0; i < 101; i++) {
		for (int j = 0; j < 101; j++) {
			board[i][j] = 0;
		}
	}
	//게임 시간
	int t = 0;
	//보드 크기, 사과 수, 방향 전환 수
	int n, k, l;

	//뱀이 있는 곳들을 나타내는 Queue를 만들고, 시작점인 1,1 위치에 있다고 표시
	queue<pair<int, int>> snake;
	int headX = 1;
	int headY = 1;
	snake.push({ headX,headY });

	//int초에 char대로 방향전환 한다는 것을 나타내는 배열
	queue<pair<int, char>> rot;

	//델타탐색용 배열 및 현재 방향
	int dx[4] = { 0,1,0,-1 };
	int dy[4] = { 1,0,-1,0 };
	int d = 0;

	//사과 수와 사과 위치 입력받기
	cin >> n >> k;
	for (int i = 0; i < k; i++) {
		int x, y;
		cin >> x >> y;
		board[x][y] = 2;
	}

	//방향 전환 수와 방향 전환 시기 & 방향 입력 받기
	cin >> l;
	for (int i = 0; i < l; i++) {
		int x;
		char c;
		cin >> x >> c;
		rot.push({ x,c });
	}

	//게임이 끝날때까지 반복
	while (true) {
		//시간을 늘려주고 다음 칸 위치를 구함
		t++;
		int nx = headX + dx[d];
		int ny = headY + dy[d];

		//보드를 벗어나거나 뱀과 닿으면 종료
		if (nx < 1 || ny < 1 || nx > n || ny > n || board[nx][ny] == 1) {
			break;
		}

		//아무 칸도 아니라면 맵에서 꼬리 위치를 제거
		if (board[nx][ny] == 0) {
			int tailX = snake.front().first;
			int tailY = snake.front().second;
			board[tailX][tailY] = 0;
			snake.pop();
		}

		//다음 칸으로 전진하고 Queue에 넣어줌
		headX = nx;
		headY = ny;
		board[headX][headY] = 1;
		snake.push({ headX,headY });

		//지금 시간이 방향전환할 시간인지 확인 후 전환
		if (!rot.empty()) {
			if (t == rot.front().first) {
				if (rot.front().second == 'L') {
					d = (d + 3) % 4;
				}
				else if (rot.front().second == 'D') {
					d = (d + 1) % 4;
				}
				rot.pop();
			}
		}
	}

	//출력
	cout << t << endl;

}