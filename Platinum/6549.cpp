// 제목 : 히스토그램에서 가장 큰 직사각형
// 티어 : 플래티넘 5
// 링크 : https://www.acmicpc.net/problem/6549
// 메모리(kb) : 2940
// 실행시간(ms) : 36

#include <iostream>
#include <stack>

using namespace std;

int height[100010];

int main() {

	//빠른 입출력
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	while (true) {
		//직사각형 수
		int n = 0;
		cin >> n;
		
		//종료 조건
		if (n == 0) break;

		//양 끝을 0으로 패딩하면서 높이 입력받기
		for (int i = 1; i <= n; i++) {
			cin >> height[i];
		}
		height[n+1] = 0;

		long long answer = 0;
		stack<int> s;
		s.push(0);

		//스택에 증가하는 순서대로 저장
		for (int i = 1; i <= n+1; i++) {
			//새로운 직사각형의 높이가 낮다면, 기존의 사각형부터 오른쪽 방향으로 만들어지는 직사각형 높이 정산
			while (!s.empty() && height[s.top()] > height[i]) {
				int topIdx = s.top();
				s.pop();
				answer = max(answer, ((long long)height[topIdx]) * (i - s.top() - 1));
			}
			s.push(i);
		}

		cout << answer << '\n';
	}

	return 0;
}