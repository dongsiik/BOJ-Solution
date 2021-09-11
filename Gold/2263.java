// 제목 : 트리의 순회
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/2263
// 메모리(kb) : 50380
// 실행시간(ms) : 500
// 참고한 링크 : https://dundung.tistory.com/47

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;	//트리의 정점 수
	static StringBuilder sb;//출력 도구
	static int[] inorder;	//인오더로 받은 순서
	static int[] postorder;	//포스트오더로 받은 순서
	static int[] inorderToIndex;//노드를 넣으면 인오더 배열에서 위치를 알려줌
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//입력받기
		n = Integer.parseInt(br.readLine());
		inorder = new int[n+1];
		postorder = new int[n+1];
		inorderToIndex = new int[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) inorder[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) postorder[i] = Integer.parseInt(st.nextToken());
		
		//inorderToIndex 배열 초기화
		for(int i=1;i<=n;i++) inorderToIndex[inorder[i]] = i;
		
		//재귀
		findPreorder(1,n,1,n);
		
		System.out.print(sb.toString());
	}
	
	
	static void findPreorder(int inStart, int inEnd, int postStart, int postEnd) {
		//트리 순회가 끝난 경우
		if(inStart>inEnd || postStart>postEnd) return;
		
		//포스트오더 맨 끝이 현재 트리에서 루트이므로 출력문에 저장
		int root = postorder[postEnd];
		sb.append(root).append(' ');
		
		//인오더에서 루트 위치 찾기
		int inRoot = inorderToIndex[root];
		//왼쪽 자식의 개수 구하기
		int leftChild = inRoot-inStart;
		//왼쪽 자식
		findPreorder(inStart, inRoot-1, postStart, postStart+leftChild-1);
		//오른쪽 자식
		findPreorder(inRoot+1, inEnd, postStart+leftChild, postEnd-1);
		
	}

}