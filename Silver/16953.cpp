// 제목 : A → B
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/16953
// 메모리(kb) : 2020
// 실행시간(ms) : 0

//아이디어 : 역으로 B에서 1로 만들어주자

#include<iostream>
using namespace std;

int main(int argc, char** argv)
{
	cin.tie(0);
	ios::sync_with_stdio(0);

	//입력받기
	int A, B;
	cin >> A >> B;

	//연산 횟수
	int count = 1;
	while (A < B) {
		//B의 1의 자리가 1이라면, 끝자리에 1을 추가한 연산일 수 밖에 없으므로, 그 전 상태대로 자릿수를 하나 지운다
		if (B % 10 == 1) {
			B /= 10;
			count++;
		}
		//B가 2로 나누어떨어진다면, 2로 곱한 연산이므로, 그 전 상태대로 2로 나눈다
		else if (B % 2 == 0) {
			B /= 2;
			count++;
		}
		//둘 다 안 된다면 A에서 B를 못 만드는 것이다.
		else {
			break;
		}

	}

	//A와 B가 같다면 횟수, 아니라면 -1 출력
	if (A == B) cout << count;
	else cout << -1;

	return 0;
}