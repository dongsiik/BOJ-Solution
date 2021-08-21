// 제목 : 빵집
// 티어 : 골드2
// 링크 : https://www.acmicpc.net/problem/3109
// 메모리(kb) : 44576
// 실행시간(ms) : 448

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int r;
	static int c;
	static char[][] map;
	static int answer;

	public static void main(String []args) throws IOException{
		//입력을 빠르게 받기 위한 BufferedReader와 줄을 끊고 정수로 만들기 위한 StringTokenizer
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//입력값 받기
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		answer=0;
		map = new char[r][c];
		
		for(int i=0;i<r;i++) 
			map[i] = br.readLine().toCharArray();
		
		//왼쪽 위부터 파이프 이어보기
		for(int i=0;i<r;i++)
			if(goRight(i,0)) answer++;
		
		System.out.println(answer);
	}
	
	public static boolean goRight(int row, int column) {
		//무사히 원웅이집까지 도착했으면 true 리턴
		if(column==c-1) return true;
		
		//지금 위치에 발도장 찍기. 지금 위치에서 파이프를 잇는데 성공했다면 다시 갈 일이 없고, 실패했다면 실패한 길이므로 다시 갈 일이 없음
		map[row][column]='C';
		for(int i=-1;i<=1;i++) {
			int nRow = row+i;
			//오른쪽 위, 오른쪽, 오른쪽 아래를 살펴보고 가본 적 없는 평지라면 가보기
			if(nRow>=0 && nRow<r && map[nRow][column+1]=='.') {
				if(goRight(nRow,column+1))
					return true;
			}
		}
		
		return false;
	}

}
