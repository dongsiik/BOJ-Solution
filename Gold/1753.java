// 제목 : 최단경로
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/1753
// 메모리(kb) : 106492
// 실행시간(ms) : 2180

//이 알고리즘은 정점의 수를 v라고 했을 때, 시간복잡도가 O(v^2)입니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
  
class Main
{
	public static void main(String args[]) throws IOException
    {
		//빠른 입출력을 위한 도구들
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());	//정점의 수
        int e = Integer.parseInt(st.nextToken());	//간선의 수
        
        final int INF = Integer.MAX_VALUE;			//정수 최댓값

        ArrayList<int[]>[] graph = new ArrayList[v+1];	//1번부터 v번까지, 
        for(int i=0;i<=v;i++) graph[i] = new ArrayList<>();

        int[] distance = new int[v+1];
        Arrays.fill(distance, INF);
        boolean[] visited = new boolean[v+1];
        
        
        int start = Integer.parseInt(br.readLine());
        distance[start] = 0;
        
        for(int i=0;i<e;i++) {
        	st = new StringTokenizer(br.readLine());
        	int u1 = Integer.parseInt(st.nextToken());
        	int v1 = Integer.parseInt(st.nextToken());
        	int w1 = Integer.parseInt(st.nextToken());
        	graph[u1].add(new int[] {v1,w1});
        }
        
        int minDistance = 0;
        int current = -1;
        
        for(int i=0;i<v;i++) {
        	current = -1;
        	minDistance = INF;
        	for(int j=1;j<=v;j++) {
        		if(!visited[j] && distance[j]<minDistance) {
        			current = j;
        			minDistance = distance[j];
        		}
        	}
        	
        	if(current==-1) break;
        	visited[current] = true;
        	
        	for(int[] node : graph[current]) {
        		int num = node[0];
        		int weight = node[1];
        		if(!visited[num] && distance[num]>minDistance+weight) {
        			distance[num] = minDistance+weight;
        		}
        	}
        }
        
        for(int i=1;i<=v;i++) {
        	if(!visited[i]) sb.append("INF\n");
        	else sb.append(distance[i]).append('\n');
        }
        	
        
        //출력
        System.out.print(sb.toString());
    }
	
	
}