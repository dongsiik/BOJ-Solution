// 제목 : ACM Craft
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/1005
// 메모리(kb) : 215264
// 실행시간(ms) : 784

//아이디어 : 위상정렬을 사용한다. 건물마다 선행건물들을 짓기까지 걸리는 시간을 구하고, 그 최댓값과 그 건물의 건설시간을 더해서 걸리는 총 시간을 구한다. 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
  
class Main
{
	static BufferedReader br;
	static StringBuilder sb;
	
	static int n;
	static int k;
	static int w;
	
	static boolean[][] graph;
	static int[] constructionTime;
	static int[] accumulatedTime;
	static int[] preceding;
	public static void main(String args[]) throws IOException
    {
		//빠른 입출력 도구
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//테스트 케이스 수
		int T = Integer.parseInt(br.readLine());
		
		//테스트 케이스마다 반복. w번째 건물을 짓는 시간을 구하는 순간 다음 테스트 케이스로 넘어가기 위해 tcloop라는 label을 붙임
		for(int t=1;t<=T;t++) {
			
			getInput();
			sort();
			

		

		}
		//출력
		System.out.print(sb.toString());
    }
	
	public static void getInput() throws IOException {
		//건물 수와 규칙 수 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		//인접행렬로 그래프 만들기
		graph = new boolean[n+1][n+1];
		
		//건물별 건설 시간 입력받기
		constructionTime = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) constructionTime[i] = Integer.parseInt(st.nextToken());
		
		//선행 건물을 포함하여 그 건물을 짓는 데까지 걸리는 시간을 저장한 배열
		accumulatedTime = new int[n+1];
		
		//건물별로 선행건물이 몇 개 남았는지 나타내는 배열
		preceding = new int[n+1];
		
		//그래프 정보 입력받기
		for(int i=1;i<=k;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			preceding[y]++;
			graph[x][y] = true;
		}
		
		//우리가 관심있는 건물 번호
		w = Integer.parseInt(br.readLine());
	}
	
	public static void sort() {
		//선행건물이 필요 없는 건물부터 총 시간을 구하고, 큐에 넣어줌
		Queue<Integer> q = new LinkedList<>();
		for(int i=1;i<=n;i++) {
			if(preceding[i]==0) {
				q.offer(i);
				accumulatedTime[i] = constructionTime[i];
				if(i==w) {
					sb.append(accumulatedTime[w]).append('\n');
					return;
				}
			}
		}
		
		//위상정렬
		while(!q.isEmpty()) {
			//선행 건물이 없는 건물을 꺼내서
			int node = q.poll();
			//이 건물을 지어야만 지을 수 있는 건물을 살펴봄
			for(int i=1;i<=n;i++) {
				if(graph[node][i]) {
					//총 시간을 갱신하고, 이 건물이 다른 선행건물이 없다면 총 시간에 자신을 짓는 시간을 더하고 큐에 넣어줌
					accumulatedTime[i] = Math.max(accumulatedTime[i], accumulatedTime[node]);
					preceding[i]--;
					if(preceding[i]==0) {
						q.offer(i);
						accumulatedTime[i] += constructionTime[i];
						if(i==w) {
							sb.append(accumulatedTime[w]).append('\n');
							return;
						}
					}
				}
			}
		}
	}


	
}