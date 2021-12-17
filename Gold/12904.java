
// 제목 : A와 B
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/12904
// 메모리(kb) : 14152
// 실행시간(ms) : 124

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//시작 문자열과 목표 문자열
		char[] S = br.readLine().toCharArray();
		char[] T = br.readLine().toCharArray();

		//역발상으로 목표 문자열부터 하나씩 빼면서 시작 문자열로 바꿔볼 예정
		//뒤집기 대신 목표문자열의 양쪽 끝 중 한 쪽을 빼는 식으로 구현, head와 tail은 각각 한 쪽 끝의 인덱스
		int head = 0;
		int tail = T.length-1;
		//현재 뒤집혔는지 아닌지
		boolean reverse = false;
		
		//길이가 같아질 때까지 문자열을 빼고 필요하다면 뒤집음
		while(tail-head + 1 > S.length) {
			if(reverse) {
				if(T[head]=='B') {
					reverse = !reverse;
				}
				head++;
			}
			else {
				if(T[tail]=='B') {
					reverse = !reverse;
				}
				tail--;
			}
		}
		
		//문자열이 같은지 아닌지 하나하나 비교하면서 아니라면 0 출력 후 종료
		if(reverse) {
			for(int i=tail;i>=head;i--) {
				if(T[i]!=S[tail-i]) {
					System.out.println(0);
					return;
				}
			}
		}
		else {
			for(int i=head;i<=tail;i++) {
				if(T[i]!=S[i-head]) {
					System.out.println(0);
					return;
				}
			}
		}
		
		//여기까지 왔다면 성공이므로 1 출력
		System.out.println(1);
	}

}