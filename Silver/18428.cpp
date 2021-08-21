// 제목 : 감시 피하기
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/18428
// 메모리(kb) : 2020
// 실행시간(ms) : 0

#include <iostream>
using namespace std;

//최대 크기로 잡은 지도
char arr[6][6];
//실제 지도 크기
int n;


//선생님이 x,y 좌표에 있을 때 가려지지 않는 학생이 있는지 확인하는 함수
bool oversee(int x, int y) {

	//위쪽 확인
	for (int i = 0; i < x; i++) {
		//학생이 있을 때
		if (arr[i][y] == 'S') {
			bool block = false;
			//그 사이에 장애물이 있는지 확인
			for (int j = i + 1; j < x; j++) {
				if (arr[j][y] == 'O') {
					block = true;
					break;
				}
			}
			//장애물이 없다면 false 리턴
			if (block == false) return false;
		}
	}
	//나머지 3방향도 비슷하게 처리
	for (int i = x + 1; i < n; i++) {
		if (arr[i][y] == 'S') {
			bool block = false;
			for (int j = x + 1; j < i; j++) {
				if (arr[j][y] == 'O') {
					block = true;
					break;
				}
			}
			if (block == false) return false;
		}
	}
	for (int i = 0; i < y; i++) {
		if (arr[x][i] == 'S') {
			bool block = false;
			for (int j = i + 1; j < y; j++) {
				if (arr[x][j] == 'O') {
					block = true;
					break;
				}
			}
			if (block == false) return false;
		}
	}
	for (int i = y + 1; i < n; i++) {
		if (arr[x][i] == 'S') {
			bool block = false;
			for (int j = y + 1; j < i; j++) {
				if (arr[x][j] == 'O') {
					block = true;
					break;
				}
			}
			if (block == false) return false;
		}
	}
	return true;
}

//재귀적으로 놓을 수 있는 장애물 위치 3곳을 확인해봄
bool DFS(int cnt, int position) {
	//장애물 3개를 놓았다면
	if (cnt == 3) {
		bool AllTcheck = true;
		for (int i = 0; i < n; i++) {
			//각각의 선생님에 대해 들키는 학생이 있는지 확인
			for (int j = 0; j < n; j++) {
				if (arr[i][j] == 'T') AllTcheck = AllTcheck && oversee(i, j);
			}
		}
		//아무도 안 들킨다면 true를 리턴
		if (AllTcheck) return true;
		else return false;
	}
	//장애물이 3개 이하라면
	if (cnt < 3) {
		//다른 곳에 장애물을 놓을 수 있다면 재귀적으로 진행
		for (int cur_pos = position; cur_pos < n * n; cur_pos++) {
			int i = cur_pos / n;
			int j = cur_pos % n;
			if (arr[i][j] == 'X') {
				arr[i][j] = 'O';
				//만약 학생들이 다 가려진다면 종료함으로써 나머지 경우에 대해 시험하지 않음
				if (DFS(cnt+1, cur_pos + 1)) return true;
				arr[i][j] = 'X';
			}
		}
	}
	return false;
}

int main() {
	//지도의 크기를 입력받고, 지도를 읽어봄
	cin >> n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> arr[i][j];
		}
	}

	//재귀적으로 진행
	if (DFS(0,0)) cout << "YES";
	else cout << "NO";

}