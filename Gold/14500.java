
// 제목 : 테트로미노
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/14500
// 메모리(kb) : 32460
// 실행시간(ms) : 564

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	//종류, 대칭, 회전별로 가능한 테트로미노 하드코딩
	static int[][][] blocks = {
			{{1,1,1,1}}, {{1},{1},{1},{1}},	//막대
			{{1,1},{1,1}},					//네모
			{{1,0},{1,0},{1,1}},{{1,1},{1,0},{1,0}},{{0,1},{0,1},{1,1}},{{1,1},{0,1},{0,1}},	//ㄱ
			{{1,0,0},{1,1,1}},{{0,0,1},{1,1,1}},{{1,1,1},{1,0,0}},{{1,1,1},{0,0,1}},			
			{{1,0},{1,1},{0,1}},{{0,1},{1,1},{1,0}},{{1,1,0},{0,1,1}},{{0,1,1},{1,1,0}},		//ㄹ
			{{1,0},{1,1},{1,0}},{{0,1},{1,1},{0,1}},{{0,1,0},{1,1,1}},{{1,1,1},{0,1,0}},		//ㅗ
	};
	
	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//종이 입력받기
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		
		//모든 가능한 경우에 대해 테트로미노 놓아보기
		for(int i=0;i<blocks.length;i++) {
			for(int j=0;j+blocks[i].length<=N;j++) {
				for(int k=0;k+blocks[i][0].length<=M;k++) {
					int current = 0;
					for(int row=0;row<blocks[i].length;row++) {
						for(int col=0;col<blocks[i][0].length;col++) {
							current+= map[j+row][k+col]*blocks[i][row][col];
						}
					}
					answer = Math.max(answer, current);
				}
			}
		}
		
		//출력
		System.out.println(answer);
	}
	
}