// 제목 : 평범한 배낭
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/12865
// 메모리(kb) : 2412
// 실행시간(ms) : 12

#include<iostream>

using namespace std;

int main(int argc, char** argv)
{
    cin.tie(0);
    ios::sync_with_stdio(0);

    //물건 수, 무게 상한
    int n, k;
    cin >> n >> k;
    
    //무게와 가치 입력받기
    int* weight = new int[n];
    int* value = new int[n];
    for (int i = 0; i < n; i++) {
        cin >> weight[i] >> value[i];
    }

    //무게 총합당 최고 가치
    int* bag = new int[k + 1];
    fill(bag, bag + k + 1, 0);
    //각각의 물건에 대해서
    for (int i = 0; i < n; i++) {
        //현재 무게, 현재 무게에서 짐을 덜고 이 물건을 넣은 것의 가치 비교
        for (int j = k; j >= weight[i]; j--) {
            bag[j] = max(bag[j], value[i] + bag[j - weight[i]]);
        }
    }
    
    //출력
    cout << bag[k];

    delete[] weight;
    delete[] value;
    delete[] bag;
    return 0;
}