
// 제목 : 달팽이팽이
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/23890
// 메모리(kb) : 16084
// 실행시간(ms) : 156

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//반지름
		int R = Integer.parseInt(br.readLine());
		
		//y좌표는 R-1, x좌표는 그 중 가장 오른쪽에 있는 점
		int y = R-1;
		int x = (int)Math.sqrt(Math.pow(R, 2)-Math.pow(y, 2));
		
		//만약 달팽이의 경계에 위치하는 경우, x 좌표를 한 칸 왼쪽으로 이동
		if(Math.pow(x, 2)+Math.pow(y, 2)>=Math.pow(R, 2)) {
			x--;
		}
		
		System.out.println(x+" "+y);
	}

}