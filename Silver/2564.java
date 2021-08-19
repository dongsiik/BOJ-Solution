// 제목 : 경비원
// 티어 : 실버1
// 링크 : https://www.acmicpc.net/problem/2564
// 메모리(kb) : 14244
// 실행시간(ms) : 128

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int width;
	static int height;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//블록의 가로 길이, 세로 길이
		st = new StringTokenizer(br.readLine());
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		
		//동근이까지 n+1개의 좌표를 저장할 배열
		int n = Integer.parseInt(br.readLine());
		int[] store = new int[n];
		
		
		//상점의 좌표 읽어와서 저장하기
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			int direction = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			store[i] = changeToInt(direction, distance);
		}

		//동글이 좌표 읽어와서 저장하기
		st = new StringTokenizer(br.readLine());
		int x_direction = Integer.parseInt(st.nextToken());
		int x_distance = Integer.parseInt(st.nextToken()); 
		int x = changeToInt(x_direction, x_distance);
		
		
		int answer=0;
		//상점과 동근이의 거리를 각각 계산해서 더하기
		for(int i=0;i<n;i++) {
			int clockwise = Math.abs(x-store[i]);
			int counterclockwise = 2*(width+height)-clockwise;
			answer+= Math.min(clockwise, counterclockwise);
		}
		
		System.out.println(answer);
	}

	//블록 테두리만으로 이동하므로 테두리를 잘라서 1차원으로 만들어줌
	public static int changeToInt(int direction, int distance) {
		switch(direction) {
		case 1:
			return distance;
		case 2:
			return 2*width+height-distance;
		case 3:
			return 2*(width+height)-distance;
		case 4:
			return width+distance;
		}
		
		return -1;
	}
}
