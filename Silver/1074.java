// 제목 : Z
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/1074
// 메모리(kb) : 14260
// 실행시간(ms) : 136

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String []args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());	//배열의 크기는 2^n * 2^n
		int r = Integer.parseInt(st.nextToken());	//찾을 행과 열 번호
		int c = Integer.parseInt(st.nextToken());
		
		int answer=0;
		
		//한 칸의 크기가 2^n * 2^n인 배열에 대하여
		while(n>0) {
			//4개의 작은 배열로 나누고
			int len = 1<<(--n);
			//아래에 있을 때, 오른쪽에 있을 때 숫자를 더함
			if(r>=len) answer+= 2* len*len;
			if(c>=len) answer+= len*len;
			
			//다음 반복을 위해 r과 c의 상대적인 위치를 구함
			r%=len;
			c%=len;
		}
	
		//출력
		System.out.println(answer);
	}

}