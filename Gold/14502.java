// 제목 : 연구소
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/14502
// 메모리(kb) : 167540
// 실행시간(ms) : 364

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[][] map;
	static int[][] copyOfMap;
	static int answer = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		//입력받기
		input();
		//0,0 좌표부터 벽 설치해보기
		setBlock(0,0);
				
		//출력
		System.out.println(answer);
	}
	
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void setBlock(int position, int count) {
		//벽을 세 개 설치했으면, 바이러스를 퍼뜨리고, 안전영역 수를 갱신함
		if(count==3) {
			spreadVirus();
		}
		//아니라면
		else {
			//position부터 시작해서 지금부터 벽을 놓아볼 곳들을 구해보고 벽을 놓아봄
			for(int i=position;i<n*m;i++) {
				//x,y 좌표로 변환
				int x = i/m;
				int y = i%m;
				//빈 공간이라면 벽을 설치하고, 재귀함수를 호출하고, 다시 벽을 치움
				if(map[x][y]==0) {
					map[x][y]=1;
					setBlock(i+1,count+1);
					map[x][y]=0;
				}
			}
		}
	}
	
	//벽이 세 개 있을 때 바이러스 퍼뜨리기
	static void spreadVirus() {
		//복사본 맵 만들기
		copyOfMap = new int[n][];
		for(int i=0;i<n;i++) copyOfMap[i] = Arrays.copyOf(map[i], m);
		
		//바이러스가 있는 점이라면, DFS로 바이러스 퍼뜨리기
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(copyOfMap[i][j]==2) spreadVirusDFS(i, j);
			}
		}
		//안전영역 수 구하기
		getSafetyArea();
		
	}
	
	static void spreadVirusDFS(int x, int y) {
		//델타 탐색
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		for(int d=0;d<4;d++) {
			//4방향 중 다음 방향
			int nx = x + dx[d];
			int ny = y + dy[d];
			//빈 공간이라면, 바이러스를 퍼뜨리고 재귀함수 호출
			if(nx>=0 && nx<n && ny>=0 && ny<m && copyOfMap[nx][ny]==0) {
				copyOfMap[nx][ny] = 3;
				spreadVirusDFS(nx, ny);
			}
		}
	}
	
	//안전영역 수를 센 다음, 현재까지 구한 값과 비교후 갱신
	static void getSafetyArea() {
		int count=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(copyOfMap[i][j]==0) count++;
			}
		}
		answer = Math.max(answer, count);
	}
}