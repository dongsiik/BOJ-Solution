// 제목 : 전깃줄 - 2
// 티어 : 플래티넘 5
// 링크 : https://www.acmicpc.net/problem/2568
// 메모리(kb) : 8240
// 실행시간(ms) : 36

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
	
	//빠른 입출력
	cin.tie(0);
	ios::sync_with_stdio(0);

	//전봇대에 연결된 위치 최댓값
	int maxNumOfPoles = 500000;

	//전선줄 갯수
	int numOfLines;
	cin >> numOfLines;

	//poles[i]=j는 A쪽 i칸에 연결된 전선은 B쪽 j칸에 연결되었다는 뜻
	int* poles = new int[maxNumOfPoles + 1];
	fill(poles, poles + maxNumOfPoles + 1, 0);

	//parent[i]=j는 i가 포함된 LIS의 이전 칸은 j라는 뜻
	int* parent = new int[maxNumOfPoles + 1];
	fill(parent, parent + maxNumOfPoles + 1, -1);

	//전깃줄 정보 입력받기
	for (int i = 0; i < numOfLines; i++) {
		int from, to;
		cin >> from >> to;
		poles[from] = to;
	}

	//n log n의 시간복잡도인 LIS 알고리즘
	int* LIS = new int[numOfLines + 1];
	LIS[0] = 0;
	int lengthOfLIS = 0;

	//LIS의 이전 칸을 저장해두는 배열
	int* LISParent = new int[maxNumOfPoles + 1];
	LISParent[0] = 0;

	for (int i = 1; i <= maxNumOfPoles; i++) {
		//연결되지 않은 위치라면 생략
		if (poles[i] == 0) continue;

		int pos = lower_bound(LIS, LIS + lengthOfLIS + 1, poles[i]) - LIS;
		//LIS에 위치와 값을 표시하고, 자신의 왼쪽 항을 기록함
		LIS[pos] = poles[i];
		LISParent[pos] = i;
		parent[i] = LISParent[pos - 1];
		if (pos > lengthOfLIS) lengthOfLIS++;
	}

	int numOfUselessLines = numOfLines - lengthOfLIS;

	//LIS에 포함된 항들에 대해 parent를 -1로 표시
	//이 작업이 끝나면 포함되지 않은 항들에 대해서만 parent 값이 -1이 아니게 됨
	int lastPole = LISParent[lengthOfLIS];
	while (lastPole > 0) {
		int lastParent = parent[lastPole];
		parent[lastPole] = -1;
		lastPole = lastParent;
	}

	//결과 출력
	cout << numOfUselessLines << '\n';
	for (int i = 1; i <= maxNumOfPoles; i++) {
		if (parent[i] != -1) cout<<i<<'\n';
	}
}