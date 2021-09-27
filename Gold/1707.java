// 제목 : 이분 그래프
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/1707
// 메모리(kb) : 277040
// 실행시간(ms) : 1296

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		//입출력 도구들
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		//테스트 케이스 수
		int TC = Integer.parseInt(br.readLine());

		//테스트 케이스 마다
		tcloop: for (int tc = 0; tc < TC; tc++) {
			
			//정점 수, 간선 수 입력받기
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			//인접리스트로 그래프 만들고 초기화
			ArrayList<Integer>[] graph = new ArrayList[v + 1];
			for (int i = 0; i <= v; i++)
				graph[i] = new ArrayList<>();

			//간선마다
			for (int i = 0; i < e; i++) {
				
				//두 정점을 입력받아 인접리스트에 넣어줌(문제에 명시되어 있지는 않지만 무방향 그래프라고 가정)
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph[a].add(b);
				graph[b].add(a);
			}

			//방문 여부 배열
			boolean[] visited = new boolean[v + 1];
			//이분 그래프에서 어느 편에 속하는지를 저장할 배열, 속한 곳에 따라 -1이나 1 값을 가지게 됨.
			int[] type = new int[v + 1];

			//각각의 정점마다
			for (int i = 1; i <= v; i++) {
				
				//이미 방문한 정점이라면 생략
				if (visited[i])
					continue;

				//방문하지 않은 정점이라면, 이 점을 시작으로 BFS
				Queue<Integer> q = new LinkedList<Integer>();
				
				//지금 점을 방문처리하고, 1번 타입으로 선언하고, 큐에 넣어줌
				visited[i] = true;
				type[i] = 1;
				q.add(i);

				//큐가 빌 때까지
				while (!q.isEmpty()) {
					//큐에서 꺼낸 점
					int current = q.poll();

					//현재 점과 연결된 다른 점들에 대해서
					for (int j = 0; j < graph[current].size(); j++) {
						//현재 점과 연결된 다음 점
						int next = graph[current].get(j);
						
						//같은 타입인데 인접했다면 이분그래프가 아님 
						if (type[current] == type[next]) {
							//NO를 출력문에 저장하고 지금 테스트 케이스를 건너뜀
							sb.append("NO\n");
							continue tcloop;
						}
						
						//아직 방문하지 않은 점이라면
						if (!visited[next]) {
							//방문처리하고, 현재 점과 다른 타입으로 표시하고, 큐에 넣어줌
							visited[next] = true;
							type[next] = type[current] * (-1);
							q.add(next);
						}
					}
				}
			}

			//여기까지 왔다면 이분그래프이므로 YES를 출력문에 저장
			sb.append("YES\n");
		}

		//출력
		System.out.printf(sb.toString());
	}
}
