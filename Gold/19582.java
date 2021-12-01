
// 제목 : 200년간 폐관수련했더니 PS 최강자가 된 건에 대하여
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/19582
// 메모리(kb) : 38236
// 실행시간(ms) : 376

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 대회 수
		int N = Integer.parseInt(br.readLine());
		
		//지금까지 대회를 개근, 정근했을 때 상금 총액
		long gaegeun = 0;
		long jeongeun = 0;
		
		//상금 제한에 걸려서 강제로 못 나간 대회가 없다면 true, 아니면 false
		boolean life = true;
		
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			
			//상금제한, 상금
			int x = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			
			//상금제한에 걸렸다면
			if(gaegeun>x && jeongeun>x) {
				//전에 대회를 못 나간 적이 없다면 이번 대회를 쉬고, 다음부터는 쉬는 경우가 없도록 함
				if(life) {
					life = false;
					jeongeun = Long.MAX_VALUE;
				}
				//전에 대회를 못 나간 적이 있다면, 두 번 이상 결석하므로 슬픈 메시지 출력
				else {
					System.out.println("Zzz");
					return;					
				}
			}
			//상금 제한이 안 걸렸다면
			else {
				//다음에 결석이 가능하다면 이번 대회를 참가할 때, 아닐때를 따져서 상금 총액을 기록
				if(life) {
					jeongeun = Math.min(jeongeun+p, gaegeun);
					gaegeun += p;					
				}
				//다음에 결석이 불가능하다면 개근할 때만 따져서 기록
				else {
					gaegeun += p;
				}
			}
		}
		
		System.out.println("Kkeo-eok");
	}
}