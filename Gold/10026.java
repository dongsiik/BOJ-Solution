// 제목 : 적록색약
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/10026
// 메모리(kb) : 16584
// 실행시간(ms) : 192

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
  
class Main
{
	//그림의 한 변 크기
	static int n;
	//그림
	static char[][] picture;
	//적록색약이 아닌 사람을 위한 DFS에 쓰일 방문 여부 배열
	static boolean[][] visitedNormal;
	//적록색약인 사람을 위한 DFS에 쓰일 방문 여부 배열
	static boolean[][] visitedBlind;
	//델타 탐색용 배열
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String args[]) throws IOException
    {
		//빠른 입출력을 위한 도구들
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //그림의 크기와 그림 입력받기
        n = Integer.parseInt(br.readLine());
        
        picture = new char[n][];
        for(int i=0;i<n;i++) picture[i] = br.readLine().toCharArray();
        
        //적록색약이 아닌 사람과 적록색약인 사람의 구역 수 세기
        int countNormal = getCountNormal();
        int countBlind = getCountBlind();
        
        //출력
        System.out.println(countNormal+" "+countBlind);
        
    }
	
	//적록색약이 아닌 사람이 보는 구역 수 세기
	public static int getCountNormal() {
		//방문 여부 배열 만들기
		visitedNormal = new boolean[n][n];
		//구역 수
		int count=0;
		
		//DFS로 탐색되지 않은 곳을 보면, 구역 개수를 추가하고 그 곳부터 DFS 시작
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++) {
				if(!visitedNormal[i][j]) {
					dfsNormal(i,j);
					count++;
				}
			}
		
		return count;
	}
	
	public static void dfsNormal(int x, int y) {
		//현재 지역 방문처리
		visitedNormal[x][y]= true;
		
		//상하좌우에 자신과 같은 색이 있다면 재귀적으로 호출
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx>=0 && nx<n && ny>=0 && ny<n && !visitedNormal[nx][ny]) {
				if(picture[x][y]==picture[nx][ny]) dfsNormal(nx,ny);
			}
		}
	}
	
	//적록색약인 사람이 보는 구역 수 세기
	public static int getCountBlind() {
		visitedBlind = new boolean[n][n];
		int count=0;
		
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++) {
				if(!visitedBlind[i][j]) {
					dfsBlind(i,j);
					count++;
				}
			}
		
		return count;
	}
	
	public static void dfsBlind(int x, int y) {
		//현재 지역 방문처리
		visitedBlind[x][y] = true;
		
		//상하좌우에 자신과 비슷한 색이 있으면 방문처리
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx>=0 && nx<n && ny>=0 && ny<n && !visitedBlind[nx][ny]) {
				if(blindSame(picture[x][y],picture[nx][ny])) dfsBlind(nx,ny);
			}
		}
	}
	
	//적록색약이 보기에 두 색이 같은 색인지 판단
	public static boolean blindSame(char c1, char c2) {
		if((c1=='B' && c2!='B') || (c1!='B' && c2=='B')) return false;
		else return true;
	}
}