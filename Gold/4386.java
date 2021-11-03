
// 제목 : 별자리 만들기
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/4386
// 메모리(kb) : 14188
// 실행시간(ms) : 128

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	//별의 수, 좌표, 별자리와 연결된 다른 점과의 최단거리
	static int N;
	static double[][] coordinates;
	static int[] distance;
	
	public static void main(String[] args) throws IOException{
		
		init();
		Prim();
	}
	
	//입력받기
	static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		coordinates = new double[N][2];
		for(int i=0;i<N;i++) {
			st= new StringTokenizer(br.readLine());
			coordinates[i][0] = Double.parseDouble(st.nextToken());
			coordinates[i][1] = Double.parseDouble(st.nextToken());
		}
	}

	//프림 알고리즘
	static void Prim() {
		
		double[] distance = new double[N];
		boolean[] visited = new boolean[N];
		for(int i=1;i<N;i++) distance[i] = Double.MAX_VALUE;
		
		double answer = 0;
		
		for(int i=0;i<N;i++) {
			double minDistance = Double.MAX_VALUE;
			int minIndex = -1;
			
			for(int j=0;j<N;j++) {
				if(!visited[j] && distance[j]<minDistance) {
					minDistance = distance[j];
					minIndex = j;
				}
			}
			
			visited[minIndex] = true;
			answer += minDistance;
			
			for(int j=0;j<N;j++) {
				if(!visited[j]) {
					distance[j] = Math.min(distance[j], getDistance(minIndex, j));
				}
			}
		}
		
		
		System.out.println(answer);
	}
	
	//두 별 사이의 거리 구하기
	static double getDistance(int i, int j) {
		return Math.sqrt(Math.pow(coordinates[i][0]-coordinates[j][0], 2)+Math.pow(coordinates[i][1]-coordinates[j][1], 2));
	}
}
