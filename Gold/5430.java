// 제목 : AC
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/5430
// 메모리(kb) : 77492
// 실행시간(ms) : 724

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
  
class Main
{
	public static void main(String args[]) throws IOException
    {
		//빠른 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		//테스트 케이스 수
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			//입력 받기
			String p = br.readLine();
			int n = Integer.parseInt(br.readLine());
			String nums = br.readLine();
			
			//AC 실행
			AC(p, n, nums);
			
			

		}
    }
	
	static void AC(String p, int n, String nums) {
		//괄호 안에 싸여있는 숫자들을 저장할  ArrayDeque
		ArrayDeque<Integer> deque = new ArrayDeque<>(n);
		//지금 읽어들이려고 하는 위치
		int currentPosition = 0;
		for(int i=0;i<n;i++) {
			//다음 쉼표까지 위치 찾기
			int nextPosition = nums.indexOf(',', currentPosition+1);
			//다음 쉼표가 없다면 ] 전까지 읽으면 됨
			if(nextPosition==-1) nextPosition = nums.length()-1;
			//숫자 읽어서 저장하기
			deque.add(Integer.parseInt(nums.substring(currentPosition+1,nextPosition)));
			currentPosition = nextPosition;
		}
		
		//함수 p 실행
		boolean reverse = false;
		for(int i=0;i<p.length();i++) {
			//R인지 D인지 확인
			char command = p.charAt(i);
			switch(command) {
			//R이라면 뒤집기
			case 'R':
				reverse = !reverse;
				break;
			//D라면
			case 'D':
				//비어 있으면 에러
				if(deque.isEmpty()) {
					System.out.print("error\n");
					return;
				}
				//아닐 때 뒤집혔는지에 따라 버림
				else {
					if(reverse) deque.pollLast();
					else deque.pollFirst();
				}
				break;
			}
		}
		//남은 값 출력문에 저장하기
		StringBuilder sb = new StringBuilder();
		//비어있으면 []
		if(deque.isEmpty()) {
			sb.append("[]\n");
		}
		else {
			//아니라면 [n, m, .... ,으로 저장하다가 마지막에 쉼표 지우고 ] 붙이기
			sb.append('[');
			if(reverse) {
				while(!deque.isEmpty()) {
					sb.append(deque.pollLast()).append(',');
				}
			}
			else {
				while(!deque.isEmpty()) {
					sb.append(deque.pollFirst()).append(',');
				}
			}
			
			sb.setLength(sb.length()-1);
			sb.append("]\n");
			
		}
		//출력
		System.out.print(sb.toString());
	}
	
}