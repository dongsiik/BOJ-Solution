
// 제목 : 선 긋기
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/2170
// 메모리(kb) : 9836
// 실행시간(ms) : 364

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
	//빠른 입출력
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int N;
	cin >> N;
	
	vector<pair<int, int>> lines(N);

	for (int i = 0; i < N; i++) {
		int x, y;
		cin >> x >> y;
		lines[i] = make_pair(x, y);
	}

	sort(lines.begin(), lines.end());

	int answer = 0;

	int currentIndex = 0;

	while (currentIndex < N) {
		int start = lines[currentIndex].first;
		int end = lines[currentIndex].second;

		int nextIndex = currentIndex + 1;
		while (nextIndex < N) {
			if (lines[nextIndex].first <= end) {
				end = max(end, lines[nextIndex].second);
				nextIndex++;
			}
			else break;
		}

		answer += (end - start);
		currentIndex = nextIndex;

	}

	cout << answer;
	
	return 0;
}