// 제목 : N-Queen
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/9663
// 메모리(kb) : 2020
// 실행시간(ms) : 1772

#define _CRT_SECURE_NO_WARNINGS 
#include<iostream>

using namespace std;

bool col[15], diag1[30], diag2[30]; //열, 왼쪽 아래 대각선, 오른쪽 아래 대각선이 사용중인지 나타냄
int n;  //보드판의 크기
int answer; //가능한 경우의 수

//막 놓은 퀸이 다른 퀸과 열이나 대각선 방향에서 잡히지 않는지 확인해주는 함수
bool chk(int r, int c) {
    if (col[c] || diag1[c + r] || diag2[c - r + n - 1]) return false;
    return true;
}

//재귀적으로 n번째 행에 퀸을 놓는 함수
void setQueen(int r) {
    //무사히 다 놓았다면 경우의 수 하나 증가
    if (r == n) answer++;
    else {
        //행에 놓아보고, 무사히 놓을 수 있다면 다음 행에 반복
        for (int c = 0; c < n; c++) {
            if (chk(r, c)) {
                //열과 대각선 점유
                col[c] = true;
                diag1[r + c] = true;
                diag2[c - r + n - 1] = true;
                //재귀
                setQueen(r + 1);
                //열과 대각선 점유 해제
                col[c] = false;
                diag1[r + c] = false;
                diag2[c - r + n - 1] = false;

            }
        }
    }
}

int main(int argc, char** argv)
{
    cin.tie(0);
    ios::sync_with_stdio(0);


    //입력받아서 계산
    cin >> n;
    answer = 0;

    //퀸 놓기 시작
    setQueen(0);

    //출력
    cout << answer;
    

    return 0;
}