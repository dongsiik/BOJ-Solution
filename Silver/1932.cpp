// 제목 : 정수 삼각형
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/1932
// 메모리(kb) : 2944
// 실행시간(ms) : 44

#define _CRT_SECURE_NO_WARNINGS 
#include<iostream>

using namespace std;

int main(int argc, char** argv)
{
    //삼각형의 크기
    int n;
    cin >> n;

    //삼각형을 저장할 배열을 할당하고 초기화
    int** arr = new int*[n + 1];
    for (int i = 0; i <= n; i++) {
        arr[i] = new int[n + 1];

    }

    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= n; j++) {
            arr[i][j] = 0;
        }
    }

    //배열에 값을 입력받으며, 그 위치에는 지금까지 선택한 수의 합 중 최댓값을 저장
    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= i; j++) {
            int value;
            cin >> value;
            arr[i][j] = value + max(arr[i - 1][j - 1], arr[i - 1][j]);
        }

    //마지막 줄에서 최댓값을 찾은 후 출력
    int answer = 0;
    for (int j = 1; j <= n; j++) answer = answer > arr[n][j] ? answer : arr[n][j];

    cout << answer;
    return 0;
}