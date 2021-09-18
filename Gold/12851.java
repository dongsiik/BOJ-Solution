// 제목 : 숨바꼭질 2
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/12851
// 메모리(kb) : 17080
// 실행시간(ms) : 160

//아이디어 : 칸마다 가장 빨리 방문할 수 있는 경우의 수를 따로 저장해두자.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	//칸의 수 상한선
	static final int maxLength = 100000;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		//가장 빨리 잡을 수 있는 시간, 현재 시간
		int minTime = Integer.MAX_VALUE;
		int currentTime=0;
		
		//BFS용 큐
		ArrayDeque<Integer> q = new ArrayDeque<>();
		//이 칸을 방문한 적이 있었는지
		boolean[] visited = new boolean[maxLength+1];
		//이 칸을 처음 방문한 시간
		int[] visitedTime = new int[maxLength+1];
		Arrays.fill(visitedTime, Integer.MAX_VALUE);
		//이 칸을 가장 빨리 방문할 수 있는 경우의 수
		int[] numOfWays = new int[maxLength+1];
		
		//n부터 시작
		visitedTime[n] = 0;
		numOfWays[n] = 1;
		q.add(n);
		
		//BFS
		while(!q.isEmpty()) {
			//이미 술래를 잡은 시간 이후라면 종료
			if(currentTime>minTime) break;
			
			//현재 시간대에 있는 칸들끼리 묶어서 반복
			int size = q.size();
			
			for(int i=0;i<size;i++) {
				//현재 위치
				int position = q.poll();
				
				//이미 방문처리했다면 건너뛰고, 아니라면 방문처리
				if(visited[position]) continue;
				visited[position] = true;
				
				//술래를 잡았다면 시간 기록
				if(position==k) {
					minTime = currentTime;
					continue;
				}
				
				//새로운 순간이동한 새로운 위치
				int newPosition = 2*position;
				//배열 범위 안이고, 이전 시간대에 방문한 적이 없는 칸이라면
				if(newPosition<=maxLength && visitedTime[newPosition]>currentTime) {
					//1초 후에 방문할 것이라고 시간 기록
					visitedTime[newPosition] = currentTime+1;
					//이 점에 방문할 수 있는 경우의 수에 현재 점까지의 경우의 수 추가
					numOfWays[newPosition]+=numOfWays[position];
					//큐에 넣기
					q.add(newPosition);
				}
				// 앞뒤로 한칸 이동도 비슷하게 처리
				newPosition = position+1;
				if(newPosition<=maxLength && visitedTime[newPosition]>currentTime) {
					visitedTime[newPosition] = currentTime+1;
					numOfWays[newPosition]+=numOfWays[position];
					q.add(newPosition);
				}
				newPosition = position-1;
				if(newPosition>=0 && visitedTime[newPosition]>currentTime) {
					visitedTime[newPosition] = currentTime+1;
					numOfWays[newPosition]+=numOfWays[position];
					q.add(newPosition);
				}
			}
			//현재 시간 증가
			currentTime++;
		}
		//출력
		System.out.println(minTime);
		System.out.println(numOfWays[k]);
	}
}