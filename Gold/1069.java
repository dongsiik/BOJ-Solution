// 제목 : 집으로
// 티어 : 골드2
// 링크 : https://www.acmicpc.net/problem/1069
// 메모리(kb) : 14368
// 실행시간(ms) : 128

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
  
class Main
{
	public static void main(String args[]) throws IOException
    {
		//빠른 입출력 도구들
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
				
		//문제의 입력값
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		//집까지 거리 구하기
		double distance = Math.sqrt(x*x+y*y);

		//집까지의 거리는 걸어서만 갈 때
		//집 방향으로 집과 최대한 가까워지도록 뛰고 남은 거리를 걸어갈 때
		//한 번 더 뛰는 대신 살짝 돌아서 뛰면서 집에 딱 도착할 때를 비교하면 됨
		int numOfJump = (((int)distance)/d);
		//집까지의 거리가 점프 거리보다 짧을 때는 점프 횟수를 1로 해야 올바른 비교가 됨
		if(numOfJump==0) numOfJump=1;
		
		//걸어서 갈 때와 살짝 돌아가더라도 점프로만 갈 때를 비교
		double minTime = Math.min(distance, (numOfJump+1)*t);
		//위의 값과 집 방향으로 점프하다가 남은 거리는 걸어갈 때를 비교
		minTime = Math.min(minTime, Math.abs(distance-d*numOfJump)+t*numOfJump);
		System.out.println(minTime);
    }
	
}