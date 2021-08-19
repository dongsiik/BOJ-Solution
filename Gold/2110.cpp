// 제목 : 공유기 설치
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/2110
// 메모리(kb) : 2800
// 실행시간(ms) : 68

#include <algorithm>
#include <iostream>

using namespace std;

//집의 개수 최대치 200,000만큼 배열을 잡아놓음
int arr[200000];
//집 수 , 공유기 수
int n, c;

int main() {
	//집 수, 공유기 수, 집의 좌표 입력받기
	cin >> n >> c;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}

	//집 좌표 오름차순으로 정렬
	sort(arr, arr + n);

	//이진탐색으로 공유기를 딱 c개 우겨넣을 수 있는 공유기 사이의 최소 거리를 구할 예정
	int start = 1;	//이진탐색의 최소값은 1
	int end = arr[n - 1] - arr[0];	//최댓값은 왼쪽 끝집과 오른쪽 끝집의 차이
	int answer = 0;		//우리가 찾는 가장 인접한 두 공유기 사이의 거리

	//이진탐색
	while (start <= end) {
		//공유기 사이에 둘 거리
		int step = (start + end) / 2;
		
		//현재 맨 왼쪽 집에 공유기를 하나 설치했다고 가정
		int value = arr[0];
		int cnt = 1;

		//공유기 연결이 안 되는 거리라면 공유기를 하나 추가
		for (int i = 1; i < n; i++) {
			if (arr[i] >= value + step) {
				value = arr[i];
				cnt++;
			}
		}

		//공유기를 c 이상으로 설치했다면, 거리를 늘려서 다시 해봄
		if (cnt >= c) {
			start = step + 1;
			answer = step;
		}
		//공유기가 남았다면, 거리를 좁혀서 다시 해봄
		else {
			end = step - 1;
		}
	}

	//출력
	cout << answer;
}