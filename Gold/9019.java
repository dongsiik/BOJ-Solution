
// 제목 : DSLR
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/9019
// 메모리(kb) : 303268
// 실행시간(ms) : 4760

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		//테스트 케이스 수
		int TC = Integer.parseInt(br.readLine());
		
		//테케마다
		while(TC-->0) {
			
			//시작 수, 도착 수
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			
			//BFS
			Queue<Node> q = new LinkedList<>();
			boolean[] visited = new boolean[10000];
			
			q.offer(new Node(src, new StringBuilder()));
			visited[src] = true;
			
			while(!q.isEmpty()) {
				Node curNode = q.poll();
				
				if(curNode.number==dest) {
					sb.append(curNode.sb).append('\n');
					break;
				}
				
				for(int d=0;d<4;d++) {
					int operationRes = operation(curNode.number, d);
					if(!visited[operationRes]) {
						visited[operationRes] = true;
						q.add(new Node(operationRes,new StringBuilder(curNode.sb).append(getOperationType(d))));
					}
				}
				
			}
		}
		
		System.out.println(sb);
	}
	
	//type에 따라 순서대로 D,S,L,R
	static int operation(int givenNumber, int type) {
		switch(type) {
		case 0:
			return (givenNumber*2)%10000;
		case 1:
			return (givenNumber+9999)%10000;
		case 2:
			return givenNumber/1000 + (givenNumber%1000) * 10;
		default:
			return (givenNumber%10)*1000 + givenNumber/10;	
		}
	}
	
	//type에 따라 D,S,L,R 반환
	static char getOperationType(int type) {
		switch(type) {
		case 0:
			return 'D';
		case 1:
			return 'S';
		case 2:
			return 'L';
		default:
				return 'R';
		}
	}
	
	//숫자, 그 숫자로 올 때까지 있었던 연산들
	static class Node{
		int number;
		StringBuilder sb;
		
		public Node() {
			number = 0;
			sb = new StringBuilder();
		}
		public Node(int number, StringBuilder sb) {
			super();
			this.number = number;
			this.sb = sb;
		}
		
		
	}
}