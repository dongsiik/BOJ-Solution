// 제목 : 와드
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/23747
// 메모리(kb) : 47108
// 실행시간(ms) : 84

// 똑같은 로직이어도 자바는 추가시간이 없어서 시간초과가 나옵니다.

#include <iostream>
#include<string>
using namespace std;

//이세계의 크기, 한별이의 좌표
int R, C, posX, posY;
//이세계
char map[1000][1000];
//여행기록
string str;

//델타탐색용 배열
int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };

//델타탐색을 할 때 범위를 벗어나는지 아닌지 판별
bool checkInterior(int nx, int ny) {
	if (nx >= 0 && nx < R && ny >= 0 && ny < C) return true;
	else return false;
}

//인접한 영역에 와드를 깔 때, DFS로 시야를 밝힘
void setWard(int cx, int cy) {
	if (map[cx][cy] == '.') return;

	int currentChar = map[cx][cy];
	map[cx][cy] = '.';

	for (int d = 0; d < 4; d++) {
		int nx = cx + dx[d];
		int ny = cy + dy[d];
		if (checkInterior(nx, ny) && map[nx][ny] == currentChar) {
			setWard(nx, ny);
		}
	}
}

int main()
{
	//빠른 입출력
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	//입력받기
	cin >> R >> C;

	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			cin >> map[i][j];
		}
	}

	cin >> posX >> posY;
	posX--;
	posY--;

	//여행
	cin >> str;
	int strlen = str.length();

	for (int i = 0; i < strlen; i++) {
		switch (str[i]) {
		case 'L':
			posY--;
			break;
		case 'R':
			posY++;
			break;
		case 'U':
			posX--;
			break;
		case 'D':
			posX++;
			break;
		case 'W':
			setWard(posX, posY);
			break;

		}
	}

	//마지막에 자신과 주변의 시야를 밝힘
	map[posX][posY] = '.';
	for (int d = 0; d < 4; d++) {
		int nx = posX + dx[d];
		int ny = posY + dy[d];
		if (checkInterior(nx, ny)) {
			map[nx][ny] = '.';
		}
	}

	//출력
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			cout << (map[i][j] == '.' ? '.' : '#');
		}
		cout << '\n';
	}

	return 0;
}