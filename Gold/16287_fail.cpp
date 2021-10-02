// 제목 : Parcel
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/16287
// 메모리(kb) : ?????
// 실행시간(ms) : 시간 초과

//정답을 받지 못하는 코드지만, 제 나름대로는 기발했다고 생각해서 올립니다.
//시간복잡도가 w*n인 이 코드는 정답이 안 되고, n*n인 다른 코드는 정답이 됩니다.

#include<iostream>
#include<vector>

using namespace std;

int main() {

	//빠른 입출력
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	//맞춰야 하는 무게, 물품의 개수
	int w, n;
	vector<int> items;

	cin >> w >> n;

	//물품 정보 입력받기
	for (int i = 0; i < n; i++) {
		int item;
		cin >> item;
		//다른 세 개와 더해서 w가 될 수 없는 무거운 물건은 배제
		if (item <= w - 6) {
			items.push_back(item);
		}
	}

	//dp[i] = j는 무게 i가 될 수 있는 집합의 크기들을 비트마스킹으로 j라고 표시한 것. dp[i] = 11111이라면 i무게는 크기가 4,3,2,1,0인 집합으로 나타낼 수 있다는 뜻
	int* dp = new int[w + 1];
	fill(dp, dp + w + 1, 0);
	// 무게 0은 공집합으로 나타낼 수 있음
	dp[0] = 1;

	int vSize = items.size();

	for (int i = 0; i < vSize; i++) {
		int item = items[i];
		//이 물건을 포함한 집합의 경우는은, 이 물건을 포함하기 전 집합의 경우에 물건을 하나 더 추가한 것을 포함
		for (int weightSum = w; weightSum >= item; weightSum--) {
			dp[weightSum] |= dp[weightSum - item] << 1;
		}
		//크기가 4인 집합으로 무게 w를 표시할 수 있다면 YES 출력
		if (dp[w] & 16 == 16) {
			cout << "YES";
			return 0;
		}
	}

	//여기까지 왔다면 안 된 경우이므로 NO 출력
	cout << "NO";

	return 0;
}