// 제목 : Parcel
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/16287
// 메모리(kb) : 2940
// 실행시간(ms) : 64

#include<iostream>
#include<vector>

using namespace std;

int main() {

	//빠른 입출력
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	//맞춰야 하는 무게 수, 물품의 개수
	int w, n;
	vector<int> items;

	cin >> w >> n;

	//물품 무게 입력받기
	for (int i = 0; i < n; i++) {
		int item;
		cin >> item;
		//w-6을 넘기는 무게는 있어봐야 w를 딱 못 맞추므로 배제
		if (item <= w - 6) {
			items.push_back(item);
		}
	}

	//dp[i] = true는 i보다 작은 번호의 물품 두 개의 합으로 무게 i가 나온다는 뜻
	bool* dp = new bool [w+1];
	fill(dp, dp + w + 1, false);

	//몇 개를 걸렀으므로 물품의 개수를 다시 셈
	int vSize = items.size();

	//i번 물품에 대해서
	for (int i = 0; i < vSize; i++) {
		int itemI = items[i];
		//자신보다 큰 물품 j, 자신보다 작은 물품 두 개를 합해서 w가 되는지 확인
		for (int j = i + 1; j < vSize; j++) {
			int itemJ = items[j];
			if (itemI+itemJ<=w && dp[w - itemI - itemJ]) {
				cout << "YES";
				return 0;
			}
		}

		//자신보다 작은 물품 j와 합해서 item[i]+item[j]가 된다고 표시
		for (int j = 0; j < i - 1; j++) {
			int itemJ = items[j];
			if (itemI + itemJ <= w) {
				dp[itemI + itemJ] = true;
			}
		}
	}

	//여기까지 왔으면 안 되는 경우이므로 NO 출력
	cout << "NO";

	return 0;
}