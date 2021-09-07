// 제목 : 곱셈
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/1629
// 메모리(kb) : 2020
// 실행시간(ms) : 0

#define _CRT_SECURE_NO_WARNINGS 
#include<iostream>

using namespace std;

int main(int argc, char** argv)
{
    //입력받기
    long long a, b, c;
    cin >> a >> b >> c;

    long long answer = 1;

    //분할정복을 이용한 거듭제곱
    while (b > 0) {
        if (b % 2 == 0) {
            a = (a * a) % c;
            b /= 2;
        }
        else {
            answer = (a * answer) % c;
            b--;
        }
    }

    //출력
    cout << answer;
    
    return 0;
}