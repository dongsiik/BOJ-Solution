// 제목 : 캐슬 디펜스
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/17135
// 메모리(kb) : 16292
// 실행시간(ms) : 180

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	//행 수, 열 수, 사정거리
	static int n,m,d;
	//출력할 정답, 현재 궁수 위치로 죽인 적의 수
	static int answer, cur_death;
	//궁수들이 놓여있는 열에는 값이 1임
	public static int[] archers;
	//지도와, 조합으로 궁수를 배치해서 시험할 때 쓸 복사된 지도
	public static int[][] map;
	public static int[][] copiedMap;
	
	
	public static void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//정보 입력받기
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		answer=Integer.MIN_VALUE;
		
		map = new int[n][m];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		//오름차순으로 궁수 3명 배치 후 next_permutation에 넣고 돌릴 예정
		archers = new int[m];
		for(int i=0;i<3;i++) archers[m-1-i] = 1;
		
		//궁수 배치하는 조합에 따라 결과 계산 후 비교
		do {
			//현재 죽인 수, 이 배치로 쓸 복사본 맵 초기화
			cur_death=0;
			copy();
			for(int i=0;i<n;i++) {
				shot();
		//		printArr(copiedMap);
				eliminate();
				move();
			}
			answer = Math.max(answer, cur_death);
		}while(next_permutation(archers));
		
		
		System.out.println(answer);
	}

	//디버깅하면서 쓴 배열을 출력하는 함수
	static void printArr(int[][] arr) {
		System.out.println("배열 출력중");
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) System.out.printf("%d ",arr[i][j]);
			System.out.println();
		}
		System.out.println("배열 출력 끝");
	}
	
	static boolean next_permutation(int[] arr) {
		int n = arr.length;
		
		int i=n-1;
		while(i>0 && arr[i-1]>=arr[i]) --i;
		if(i==0) return false;
		
		int j=n-1;
		while(arr[i-1]>=arr[j]) --j;
		
		swap(arr, i-1, j);
		
		int k=n-1;
		while(i<k) {
			swap(arr,i,k);
			i++; k--;
		}
			
			
		return true;
	}
	
	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	//지도 복사본 만들기
	public static void copy() {
		copiedMap = new int[n][m];
		for(int i=0;i<n;i++) copiedMap[i] = Arrays.copyOf(map[i],m);
	}
	
	//궁수들이 화살 쏘는 함수
	public static void shot() {
		//왼쪽에 있는 궁수부터
		for(int j=0;j<m;j++) {
			if(archers[j]==1) {
				//가까운 적부터
				loopA: for(int range = 1;range<=d;range++) {
					//왼쪽에 있는 적부터
					for(int a=-range+1;a<range;a++) {
						int nx = n+Math.abs(a)-range;
						int ny = j+a;
						//적이 있었다면 화살을 날림, 맞은 적은 1보다 큰 값을 갖게 됨
						if(nx>=0 && nx<n && ny>=0 && ny<m && copiedMap[nx][ny]!=0) {
							copiedMap[nx][ny]++;
							break loopA;
						}
					}
				}
				
				
				
			}
		}
	}
	
	//화살에 맞아 1보다 커진 값 제거하고, 죽인 수 올려주기
	public static void eliminate() {
		for(int i=n-1;i>=n-d && i>=0;i--) {
			for(int j=0;j<m;j++) {
				if(copiedMap[i][j]>1) {
					copiedMap[i][j]=0;
					cur_death++;
				}
			}
		}
	}
	
	//지도 한 칸씩 내리기 = 적 한 칸씩 이동하기
	public static void move() {
		for(int i=n-1;i>0;i--) copiedMap[i] = copiedMap[i-1];
		copiedMap[0] = new int[m];
	}
}
