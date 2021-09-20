// 제목 : 내려가기
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/2096
// 메모리(kb) : 48036
// 실행시간(ms) : 440

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		//줄의 수
		int n = Integer.parseInt(br.readLine());
		
		//DP를 이용하여 최근 두 줄까지의 최소, 최대만 구한다
		int[] oldMax;
		int[] oldMin;
		int[] newMax = new int[] {0,0,0};
		int[] newMin = new int[] {0,0,0};
		
		for(int i=0;i<n;i++) {
			//최근 줄을 지난 줄로 만들기
			oldMax = newMax;
			oldMin = newMin;
			
			//현재 줄에 있는 숫자들
			int left, middle, right;
			st = new StringTokenizer(br.readLine());
			left = Integer.parseInt(st.nextToken());
			middle = Integer.parseInt(st.nextToken());
			right = Integer.parseInt(st.nextToken());
			
			//현재 줄의 값을 계산하며 최소, 최대치를 구하기
			newMax = new int[3];
			newMin = new int[3];
			
			newMax[0] = left + Math.max(oldMax[0], oldMax[1]);
			newMax[1] = middle + Math.max(oldMax[0], Math.max(oldMax[1], oldMax[2]));
			newMax[2] = right + Math.max(oldMax[1], oldMax[2]);
			
			newMin[0] = left + Math.min(oldMin[0], oldMin[1]);
			newMin[1] = middle + Math.min(oldMin[0], Math.min(oldMin[1], oldMin[2]));
			newMin[2] = right + Math.min(oldMin[1], oldMin[2]);
		}
		
		//마지막 줄에서 최종 최대, 최소치 구하기
		int max = Math.max(newMax[0], Math.max(newMax[1], newMax[2]));
		int min = Math.min(newMin[0], Math.min(newMin[1], newMin[2]));
		
		//출력
		System.out.print(max);
		System.out.print(' ');
		System.out.print(min);
		
	}
}