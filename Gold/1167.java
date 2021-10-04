// 제목 : 트리의 지름
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/1167
// 메모리(kb) : 91828
// 실행시간(ms) : 832

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	//정점의 개수
	static int V;
	//인접 리스트로 표현한 트리
	static ArrayList<Node>[] graph;
	//DFS에서 방문여부 처리할 때 쓸 배열. 방문하지 않았다면 -1, 방문했다면 시작점부터의 거리를 저장
	static int[] visited;
	//현재까지 시작점부터 가장 긴 거리와 그 때 정점 번호
	static int answer;
	static int lastNode;
	
	public static void main(String args[]) throws IOException {
		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//그래프 만들고 입력 받기
		V = Integer.parseInt(br.readLine());
		graph = new ArrayList[V+1];
		for(int i=1;i<=V;i++) graph[i] = new ArrayList<>();
		
		for(int i=0;i<V;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			while(to!=-1) {
				int dist = Integer.parseInt(st.nextToken());
				graph[from].add(new Node(to,dist));
				
				to = Integer.parseInt(st.nextToken());
			}
		}
		
		//dfs
		startDfs(1);
		startDfs(lastNode);
		
		System.out.println(answer);
		
		}
	
	//DFS에 필요한 변수들을 초기화하고, dfs를 시작함
	static void startDfs(int startNode) {
		visited = new int[V+1];
		for(int i=1;i<=V;i++) visited[i] = -1;
		
		lastNode = startNode;
		answer = 0;
		
		visited[startNode] = 0;
		dfs(startNode);
	}

	
	//dfs
	static void dfs(int curNodeNum) {
		
		for(int i=0;i<graph[curNodeNum].size();i++) {
			//다음 노드의 번호와 비용
			Node nextNode = graph[curNodeNum].get(i);
			int nextNodeNum = nextNode.to;
			int nextNodeCost = nextNode.dist + visited[curNodeNum];
			
			//방문하지 않았다면
			if(visited[nextNodeNum]==-1) {
				//거리 계산
				visited[nextNodeNum] = nextNodeCost;
				
				//최댓값과 비교 후 갱신
				if(nextNodeCost>answer) {
					answer = nextNodeCost;
					lastNode = nextNodeNum;				
				}
				
				//재귀
				dfs(nextNodeNum);
			}
		}
	}

	//노드 클래스
	static class Node{
		int to;
		int dist;
		
		public Node(int to, int dist) {
			this.to = to;
			this.dist = dist;
		}	
	}
}