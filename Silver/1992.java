// 제목 : 쿼드트리
// 티어 : 실버1
// 링크 : https://www.acmicpc.net/problem/1992
// 메모리(kb) : 14624
// 실행시간(ms) : 156

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static StringBuilder sb;
	public static char[][] image;
	
	public static void main(String []args) throws IOException{
		//입력을 빠르게 받기 위한 BufferedReader와 모아서 출력하기 위한 StringBuilder
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//이미지의 크기를 입력받고, 이미지도 입력받음
		int n = Integer.parseInt(br.readLine());
		image = new char[n][n];
		for(int i=0;i<n;i++) 
			image[i] = br.readLine().toCharArray();
		
		//재귀적으로 0,0 위치에 있는 n*n 이미지부터 진행
		recursiveQuad(0,0,n);
		
	
		//출력
		System.out.printf(sb.toString());
	}

	//이미지 시작 위치는 x,y이고 정사각형 한 변의 위치는 length임
	public static void recursiveQuad(int x, int y, int length) {
		//정사각형 안의 내용이 모두 같다면 그 색깔을 더해줌
		if(checkSameColor(x, y, length)) {
			sb.append(image[x][y]);
		}
		else {
			//아니라면 더 작은 정사각형 길이로 나눈 다음 각각에 대해 재귀적으로 처리
			length/=2;
			sb.append('(');
			recursiveQuad(x, y, length);
			recursiveQuad(x, y+length, length);
			recursiveQuad(x+length, y, length);
			recursiveQuad(x+length, y+length, length);
			sb.append(')');
		}
	}
	
	//시작 위치 x,y이고 정사각형 한 변의 길이가 length일 때 모두 같은 색인지 확인하는 함수
	public static boolean checkSameColor(int x, int y, int length) {
		char color = image[x][y];
		
		for(int i=x;i<x+length;i++) {
			for(int j=y;j<y+length;j++) {
				if(color!=image[i][j]) return false;
			}
		}
		
		
		return true;
	}
}