// 제목 : 감시
// 티어 : 골드5
// 링크 : https://www.acmicpc.net/problem/15683
// 메모리(kb) : 47924
// 실행시간(ms) : 288

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	//델타탐색용 변수들
	public static int[] dx = {0,-1,0,1};
	public static int[] dy = {1,0,-1,0};
	
	//정답
	public static int answer;
	//사무실의 세로 크기, 가로 크기, CCTV 개수
	public static int n,m,k;
	
	//사무실
	public static int[][] office;
	//CCTV 범위를 확인할 때 쓸 복사된 사무실
	public static int[][] copiedOffice;
	//CCTV 배열
	public static CCTV[] cctvs;
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//n,m,k 초기화
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = 0;
		
		//사무실, CCTV, answer 초기화
		office = new int[n][m];
		cctvs = new CCTV[8];
		answer=Integer.MAX_VALUE;

		//사무실 정보 입력받기
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				office[i][j] = Integer.parseInt(st.nextToken());
				//중간에 CCTV가 있으면 CCTV 목록에 집어넣기
				if(office[i][j]>=1 && office[i][j]<=5) {
					cctvs[k++] = new CCTV(i,j,office[i][j]);
				}
			}
		}
		
		//CCTV를 하나하나 돌려보면서 모든 가능한 경우의 수에 대해 시험해봄
		recursiveObserve(0);
		
		//출력
		System.out.println(answer);
	}
	
	//CCTV 클래스
	public static class CCTV{
		int x, y;	//위치
		int type;	//종류. 1~5까지 문제에서 주어진 대로
		int RotationCount;	//CCTV가 감시할 수 있는 서로 다른 방향 조합의 수
		int currentDirection;	//지금 바라보는 방향
		int[] deltas;		//맨 처음 감시하고 있는 방향들
		
		//생성자
		CCTV(int x, int y, int type){
			this.x = x;
			this.y = y;
			this.type = type;
			currentDirection=0;
			
			//CCTV 종류에 따라 초기화를 다르게 해줌
			switch(type) {
			case 1:
				RotationCount = 4;
				deltas = new int[] {0};
				break;
			case 2:
				RotationCount = 2;
				deltas = new int[] {0,2};
				break;
			case 3:
				RotationCount = 4;
				deltas = new int[] {0,1};
				break;
			case 4:
				RotationCount = 4;
				deltas = new int[] {0,1,2};
				break;
			case 5:
				RotationCount = 1;
				deltas = new int[] {1,2,3,4};
				break;
				
			}
		}
		
		//CCTV가 보고 있는 방향 설정
		public void setDirection(int inputDirection) {
			currentDirection = inputDirection;
		}
		
		//현재 보고 있는 방향에서 감시할 수 있는 영역을 -1로 바꿈
		public void observe() {
			//델타 탐색
			for(int i=0;i<deltas.length;i++) {
				int cx = x;
				int cy = y;
				int direction = (deltas[i]+currentDirection)%4;
				
				
				while(cx>=0 && cx<n && cy>=0 && cy<m) {
					
					//벽을 만나면 탐색 중지
					if(copiedOffice[cx][cy]==6) break;
					//다른 CCTV가 아닌 사각지대를 만나면 색칠
					if(copiedOffice[cx][cy]==0) copiedOffice[cx][cy]=-1;
					
					int nx = cx+dx[direction];
					int ny = cy+dy[direction];
					cx = nx;
					cy = ny;
				}
			}
		}
	}
	
	//CCTV의 방향들이 다 정해졌을 때 사각지대 수를 구하는 함수
	public static void observeAll() {
		//시험용 복사본 사무실을 만듦
		copiedOffice = new int[n][];
		for(int i=0;i<n;i++) {
			copiedOffice[i] = Arrays.copyOf(office[i], m);
		}
		
		//각각의 CCTV들에 대해 감시 시작
		for(int i=0;i<k;i++) {
			cctvs[i].observe();
	//		printCopiedOffice();
		}
		
		//사각지대 수 세기
		int count =0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(copiedOffice[i][j]==0) count++;
			}
		}
		
		//최솟값으로 갱신
		answer = Math.min(answer, count);
	}
	
	//CCTV 방향을 결정하는 함수
	public static void recursiveObserve(int count) {
		//다 결정했다면 사각지대를 셈
		if(count==k) {
			observeAll();
			return;
		}
		
		//아니라면 현재 CCTV의 방향 경우의 수마다, 다음 CCTV 방향을 정함
		for(int i=0;i<cctvs[count].RotationCount;i++) {
			cctvs[count].setDirection(i);
			recursiveObserve(count+1);
		}
		
	}
	
	//디버깅용 맵 출력 함수
	public static void printCopiedOffice() {
		System.out.println("---------Printing coppied office-----");
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				System.out.print(copiedOffice[i][j]);
			}
			System.out.println();
		}
		System.out.println("---------Printing is done-----");

	}
}

