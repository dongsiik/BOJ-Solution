// 제목 : 치킨 배달
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/15686
// 메모리(kb) : 2024
// 실행시간(ms) : 4

#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

//선택받은 m개의 치킨집 목록과, 가정집 목록을 매개변수로 받아 치킨거리를 구하는 함수
int getSum(vector<pair<int, int>> mChicken, vector<pair<int, int>> house) {
	//도시의 치킨거리
	int sum = 0;
	//각각의 집마다
	for (int i = 0; i < house.size(); i++) {
		//비교하며 최솟값을 구할 치킨거리
		int tmp = 1e9;
		int hx = house[i].first;
		int hy = house[i].second;
		//치킨집들과의 치킨거리를 구하며 최솟값으로 갱신
		for (int j = 0; j < mChicken.size(); j++) {
			int mx = mChicken[j].first;
			int my = mChicken[j].second;
			int mLength = abs(hx - mx) + abs(hy - my);
			tmp = min(tmp, mLength);
		}
		//도시의 치킨거리에 더함
		sum += tmp;
	}
	return sum;
}

int main() {
	//최대 크기로 잡은 도시 지도와 도시의 크기
	int arr[51][51];
	int n, m;
	//집, 치킨집 목록
	vector<pair<int, int>> house;
	vector<pair<int, int>> chicken;
	cin >> n >> m;
	
	//지도에 저장하면서 가정집과 치킨집이 있다면 목록에 저장하기
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cin >> arr[i][j];
			if (arr[i][j] == 1) house.push_back({ i,j });
			if (arr[i][j] == 2) chicken.push_back({ i,j });
		}
	}


	//next_permutation에 넣고 돌려서 m개의 치킨집을 구해줄 벡터
	vector<bool> permuHelper;
	for (int i = 0; i < (chicken.size() - m); i++) {
		permuHelper.push_back(false);
	}
	for (int i = 0; i < m; i++) {
		permuHelper.push_back(true);
	}

	int result = 1e9;

	//치킨집을 m개 고르는 각각의 조합마다 다 시험
	do {
		//선택받은 m개의 치킨집 목록
		vector<pair<int, int>> mChicken;
		for (int i = 0; i < chicken.size(); i++) {
			if (permuHelper[i] == true) {
				int mx = chicken[i].first;
				int my = chicken[i].second;
				mChicken.push_back({ mx,my });
			}
		}
		//최솟값과 비교
		result = min(result, getSum(mChicken, house));
	} while (next_permutation(permuHelper.begin(), permuHelper.end()));

	//출력
	cout << result;
}