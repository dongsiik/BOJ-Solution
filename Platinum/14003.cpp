// 제목 : 가장 긴 증가하는 부분 수열 5
// 티어 : 플래티넘 5
// 링크 : https://www.acmicpc.net/problem/14003
// 메모리(kb) : 21560
// 실행시간(ms) : 232

#include<iostream>
#include<algorithm>

using namespace std;

int main(int argc, char** argv)
{
    cin.tie(0);
    ios::sync_with_stdio(0);

    //n은 수열의 길이, arr는 수열, parent는 지금 인덱스가 포함된 증가하는 부분수열에서 직전 항의 인덱스
    int n;
    cin >> n;
    int* arr = new int[n];
    int* parent = new int[n];

    //LIS_tail[n]은 길이가 n인 증가하는 부분수열의 마지막 값 중 가장 작은 값
    int* LIS_tail = new int[n + 1];
    //LIS_Parent는 지금 값의 arr에서의 인덱스
    int* LIS_Parent = new int[n + 1];
    //현재까지 구한 증가 부분 수열 길이의 최댓값;
    int size = 0;

    //수열 입력 및 LIS값 초기화
    for (int i = 0; i < n; i++) cin >> arr[i];
    LIS_tail[0] = -1000000001;

    //수열의 원소들마다
    for (int i = 0; i < n; i++) {
        //여러 길이의 증가 부분 수열 중에 이 원소를 꼬리로 붙일 가장 긴 증가 부분 수열 찾기
        int pos = lower_bound(LIS_tail, LIS_tail + size+1, arr[i]) - LIS_tail;
        //현재 만들어놓은 증가 부분 수열의 꼬리가 다 현재값 이하라면, 최대 길이 수열에 이 원소를 붙임
        if (pos > size) {
            LIS_tail[pos] = arr[i];
            LIS_Parent[pos] = i;
            parent[i] = LIS_Parent[pos-1];
            size++;
        }
        //아니라면 현재값보다 바로 다음으로 큰 꼬리를 현재값으로 갈아끼움
        else {
            LIS_tail[pos] = arr[i];
            LIS_Parent[pos] = i;
            parent[i] = LIS_Parent[pos - 1];
        }
    }

    //출력
    cout << size << '\n';

    //진짜 LIS를 만들기 위해 역추적
    int* LIS = new int[size];
    int trace = LIS_Parent[size];
    for (int i = size - 1; i >= 0; i--) {
        LIS[i] = arr[trace];
        trace = parent[trace];
    }
    
    for (int i = 0; i < size; i++) {
        cout << LIS[i] << ' ';
    }

    delete[] LIS_Parent;
    delete[] LIS_tail;
    delete[] parent;
    delete[] arr;

    return 0;
}