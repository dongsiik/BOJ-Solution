
// 제목 : 비드맨
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/19590
// 메모리(kb) : 22852
// 실행시간(ms) : 248

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//구슬 종류 수
		int N = Integer.parseInt(br.readLine());
		
		//총 구슬 수, 가장 많은 종류의 수
		long total = 0;
		int max = 0;
		
		//입력받기
		for(int i=0;i<N;i++){
		    int num = Integer.parseInt(br.readLine());
		    total += num;
		    max = Math.max(max, num);
		}
		
		//가장 많은 구슬이 과반수 미만이라면, 남는 구슬은 두 개씩 부딪쳐서 서로 없앨 수 있음
		if(total>2*max){
		    System.out.println(total%2);
		}
		//가장 많은 구술이 과반수 이상이라면, 그 종류의 구슬이 남게 됨
		else{
		    System.out.println(2*max - total);
		}

	}
}