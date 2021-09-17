// 제목 : 다리 만들기 2
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/17472
// 메모리(kb) : 14308
// 실행시간(ms) : 136

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	// 지도의 세로, 가로 크기, 섬의 갯수
	static int n, m, countOfIslands;
	// 지도
	static int[][] map;
	// i번 섬에서 설치할 수 있는 다리들의 목록
	static ArrayList<Edge>[] graph;
	// 델타 탐색용 배열
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입력받기
		input();
		// 섬마다 번호 매기기
		mapWithIslandNum();
		// 번호를 매긴 후, 다른 섬과 놓을 수 있는 다리 모두 구하기
		mapToGraph();
		// 프림 알고리즘으로 최소 신장 트리의 길이 구하기
		PRIM();
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 지도에 나중에 섬 번호로 표시하기 위해, 1 대신 -1로 육지 표시
				if (map[i][j] == 1)
					map[i][j] = -1;
			}
		}

	}

	// 섬 번호 매기기
	static void mapWithIslandNum() {
		// 섬의 총 개수
		countOfIslands = 0;
		// 아직 번호가 매겨지지 않은 섬이라면 DFS로 번호를 매김
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == -1)
					mapWithIslandNumDFS(i, j, ++countOfIslands);
			}
		}
	}

	// 섬 번호를 찍기 위한 재귀함수(DFS)
	private static void mapWithIslandNumDFS(int x, int y, int IslandNum) {
		// 현재 섬에 섬 번호 표시를 하고
		map[x][y] = IslandNum;

		// 사방을 돌아보며 번호가 매겨지지 않은 섬이 있다면 같은 처리를 하도록 재귀함수 호출
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == -1)
				mapWithIslandNumDFS(nx, ny, IslandNum);
		}

	}

	// 지도에서 설치할 수 있는 모든 다리 목록 만들기
	static void mapToGraph() {
		// 그래프 배열 초기화
		graph = new ArrayList[countOfIslands + 1];
		for (int i = 0; i <= countOfIslands; i++) {
			graph[i] = new ArrayList<>();
		}

		// 가로줄마다
		for (int i = 0; i < n; i++) {
			// 왼쪽 육지, 오른쪽 육지 좌표
			int leftLand = -1;
			int rightLand = -1;
			for (int j = 0; j < m; j++) {
				// 육지 발견
				if (map[i][j] != 0) {
					// 최근 발견한 두 육지 좌표 갱신
					leftLand = rightLand;
					rightLand = j;
					// 둘 다 육지이고, 거리가 3이상 차이나고, 다른 섬이면
					if (leftLand >= 0 && rightLand >= 0 && rightLand - leftLand >= 3
							&& map[i][leftLand] != map[i][rightLand]) {
						// 그래프에 넣어줌
						int leftLandNum = map[i][leftLand];
						int rightLandNum = map[i][rightLand];
						int distance = rightLand - leftLand - 1;
						// System.out.println("왼쪽섬번호, 오른쪽섬번호, 좌표들, 거리"+leftLandNum+" "+rightLandNum+"
						// "+leftLand+" "+rightLand+" "+distance);
						graph[leftLandNum].add(new Edge(rightLandNum, distance));
						graph[rightLandNum].add(new Edge(leftLandNum, distance));
					}
				}
			}
		}

		// 세로줄도 비슷하게 처리
		for (int j = 0; j < m; j++) {
			int upLand = -1;
			int downLand = -1;
			for (int i = 0; i < n; i++) {
				if (map[i][j] != 0) {
					upLand = downLand;
					downLand = i;
					if (upLand >= 0 && downLand >= 0 && downLand - upLand >= 3 && map[upLand][j] != map[downLand][j]) {
						int upLandNum = map[upLand][j];
						int downLandNum = map[downLand][j];
						int distance = downLand - upLand - 1;
						// System.out.println("위섬번호, 아래섬번호, 좌표들, 거리"+upLandNum+" "+downLandNum+"
						// "+upLand+" "+downLand+" "+distance);

						graph[upLandNum].add(new Edge(downLandNum, distance));
						graph[downLandNum].add(new Edge(upLandNum, distance));
					}
				}
			}
		}
	}

	// PRIM 알고리즘
	static void PRIM() {
		// 설치한 다리 수
		int countOfBridge = 0;
		// 총 비용
		int totalCost = 0;
		// 방문 여부 배열
		boolean[] visited = new boolean[countOfIslands + 1];

		// PRIM을 위한 우선순위 큐
		PriorityQueue<Edge> q = new PriorityQueue<>();

		// 1번섬부터 방문처리하고, 1번 섬과 연결되어 설치할 수 있는 다리들을 큐에 넣어줌
		visited[1] = true;
		for (int i = 0; i < graph[1].size(); i++) {
			q.add(graph[1].get(i));
		}

		while (!q.isEmpty()) {
			// 현재 다리, 목적지, 비용
			Edge currentEdge = q.poll();
			int curNode = currentEdge.to;
			int curCost = currentEdge.cost;

			// 이미 연결되었다면 통과
			if (visited[curNode])
				continue;

			// 아니라면 연결 처리하고, 다리 갯수 하나 늘려주고, 총 비용도 늘려줌
			visited[curNode] = true;
			countOfBridge++;
			totalCost += curCost;

			//다 연결했으면 그만두기
			if(countOfBridge==countOfIslands-1) break;

			// 새롭게 연결된 섬과 다리를 놓을 수 있는 다른 섬들에 대해서
			for (int i = 0; i < graph[curNode].size(); i++) {
				Edge NextEdge = graph[curNode].get(i);
				int nextNode = NextEdge.to;
				// 이미 연결되지 않았면 우선순위큐에 넣어줌
				if (!visited[nextNode])
					q.add(NextEdge);
			}

		}

		// 섬의 개수 -1보다 다리 수가 적다면, 다 연결되지 못했으므로 -1 출력, 아니라면 총 비용 출력
		if (countOfBridge < countOfIslands - 1)
			System.out.println(-1);
		else
			System.out.println(totalCost);
	}

	// 디버깅용 맵 출력 함수
	static void printMap() {
		System.out.println("-------시작---------");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("-----------끝------------");
	}

	// 다리 함수, 우선순위 큐를 위해 Comparable을 implements함
	static class Edge implements Comparable<Edge> {
		int to;
		int cost;

		public Edge(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}

	}
}