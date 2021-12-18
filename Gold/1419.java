
// 제목 : 등차수열의 합
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/1419
// 메모리(kb) : 14212
// 실행시간(ms) : 124

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//입력 받기
		int left = Integer.parseInt(br.readLine());
		int right = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		//전체 정답은 1이상 right 이하 개수에서, 1 이상 left미만 개수를 뺀 것임
		System.out.println(oneToRight(right, k)-oneToRight(left-1, k));
		
	}
	
	static int oneToRight(int right, int k) {
		
		//k에 따라
		switch(k) {
		
		case 2:
			//3 이상의 모든 수는 표현 가능함
			return Math.max(0, right-2);
		case 3:
			//6 이상의 3의 배수 표현 가능
			return Math.max(0, (right-3)/3);
		case 4:
			//4k꼴은 16 이상, 4k+2꼴은 10 이상부터 표현가능
			return Math.max(0, (right-6)/4) + Math.max(0, (right-12)/4);
		case 5:
			//15 이상의 5의 배수 표현 가능
			return Math.max(0, (right-10)/5);
		}
		
		return 0;
	}
}