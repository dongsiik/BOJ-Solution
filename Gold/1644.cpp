// 제목 : 소수의 연속합
// 티어 : 골드3
// 링크 : https://www.acmicpc.net/problem/1644
// 메모리(kb) : 21556
// 실행시간(ms) : 36
// 에라토스테네스 체와 투 포인터 알고리즘을 사용하였습니다.
// 투 포인터 알고리즘 참고 링크 : https://blog.naver.com/kks227/220795165570

#include <iostream>
using namespace std;

int main() {
	
	//빠른 입출력
	ios::sync_with_stdio(0);
	cin.tie(0);

	//n 입력받기
	int n;
	cin >> n;

	//n 이하의 소수 세기 (에라토스테네스의 체)
	int primeCount = 0;
	int* primes = new int[n];
	fill(primes, primes + n, 0);
	bool* isPrime = new bool[n + 1];
	fill(isPrime, isPrime + n + 1, true);

	for (int i = 2; i <= n; i++) {
		if (isPrime[i]) {
			primes[primeCount++] = i;
			for (int j = 2 * i; j <= n; j += i)
				isPrime[j] = false;
		}
	}

	// 투 포인터 알고리즘
	int start = 0;
	int end = 0;
	int sum = 0;

	int answer = 0;

	while (true) {
		if (sum >= n) sum -= primes[start++];
		else if (end == primeCount) break;
		else sum += primes[end++];

		if (sum == n) answer++;
	}

	//출력
	cout << answer;
}