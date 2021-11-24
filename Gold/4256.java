
// 제목 : 트리
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/4256
// 메모리(kb) : 35660
// 실행시간(ms) : 396

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	//노드 수
	static int n;
	//전위 순회, 중위 순회, 중위 순회의 값 별 인덱스
	static int[] preOrder, inOrder, inOrderIndex;
	static StringBuilder sb;
	
	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		//테스트 케이스 수
		int TC = Integer.parseInt(br.readLine());
		
		while(TC-->0) {
			n = Integer.parseInt(br.readLine());
			
			//배열 만들고 입력받기
			preOrder = new int[n];
			inOrder = new int[n];
			inOrderIndex = new int[n+1];

			st = new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				preOrder[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				int num = Integer.parseInt(st.nextToken());
				inOrder[i] = num;
				inOrderIndex[num] = i;
			}
			
			//재귀
			recursive(0,n,0,n);
			sb.append('\n');
		}
		
		//출력
		System.out.println(sb);
	}
	
	static void recursive(int preOrderStart, int preOrderEnd, int inOrderStart, int inOrderEnd) {
		
		//리프 노드일 경우 생략
		if(preOrderStart==preOrderEnd || inOrderStart==inOrderEnd) return;
		
		//루트 노드는 전위순회한 값의 맨 앞
		int root = preOrder[preOrderStart];
		//루트 노드의 인덱스 찾기
		int rootIndex = inOrderIndex[root];
		
		//중위 순회 중 왼쪽 가지의 크기
		int leftFamilySize = rootIndex - inOrderStart;
		
		//후위순회
		recursive(preOrderStart+1, preOrderStart + leftFamilySize+1, inOrderStart, rootIndex);
		recursive(preOrderStart + leftFamilySize+1, preOrderEnd, rootIndex+1, inOrderEnd);
		
		sb.append(root).append(' ');
	}
}