// 제목 : 트리 순회
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/1991
// 메모리(kb) : 2020
// 실행시간(ms) : 0

#include<iostream>

using namespace std;

//노드의 개수
int n;
//그래프를 나타낼 배열
int node[26][2];

void preorder(int parent) {
    cout << (char)(parent + 'A');
    if (node[parent][0] >= 0) preorder(node[parent][0]);
    if (node[parent][1] >= 0) preorder(node[parent][1]);
}

void inorder(int parent) {
    if (node[parent][0] >= 0) inorder(node[parent][0]);
    cout << (char)(parent + 'A');
    if (node[parent][1] >= 0) inorder(node[parent][1]);
}

void postorder(int parent) {
    if (node[parent][0] >= 0) postorder(node[parent][0]);
    if (node[parent][1] >= 0) postorder(node[parent][1]);
    cout << (char)(parent + 'A');
}

int main(int argc, char** argv)
{
    cin.tie(0);
    ios::sync_with_stdio(0);

    //입력받기
    cin >> n;
    for (int i = 0; i < n; i++) {
        char parent, left, right;
        cin >> parent>> left>> right;
        node[parent - 'A'][0] = left - 'A';
        node[parent - 'A'][1] = right - 'A';
    }

    //실행
    preorder(0);
    cout << '\n';
    inorder(0);
    cout << '\n';
    postorder(0);
    cout << '\n';

    return 0;
}