
// 제목 : Good Pizza, Great Pizza
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/17235
// 메모리(kb) : 2020
// 실행시간(ms) : 52

#include <iostream>
using namespace std;

int main()
{
	//빠른 입출력
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	//파인애플 조각 수
	int N;
	//y = -x + c에서 c의 최대, 최솟값
	long long maxPlus = -1e18;
	long long minPlus = 1e18;
	//y = x + c에서 c의 최대, 최솟값
	long long maxMinus = -1e18;
	long long minMinus = 1e18;

	cin >> N;

	for (int i = 0; i < N; i++) {
		long long x, y;
		cin >> x >> y;

		maxPlus = max(maxPlus, x + y);
		minPlus = min(minPlus, x + y);
		maxMinus = max(maxMinus, -x + y);
		minMinus = min(minMinus, -x + y);
	}

	//마름모의 대각선 길이
	unsigned long long length = (unsigned long long) max(maxPlus - minPlus, maxMinus - minMinus);
	
	//출력
	if (length % 2 == 0) {
		cout << length / 2 * length;
	}
	else {
		cout << length * length / 2<< ".5";
	}
	return 0;
}