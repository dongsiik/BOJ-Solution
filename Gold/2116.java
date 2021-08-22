// 제목 : 주사위 쌓기
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/2116
// 메모리(kb) : 22244
// 실행시간(ms) : 252

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//정답
		int answer=0;
		
		//주사위 수를 입력받고, 그만큼의 배열 만들기
		int n = Integer.parseInt(br.readLine());
		int[][] dice = new int[n][6];
		
		//주사위마다 정보 입력받기
		for(int i=0;i<n;i++) {
			//마주보는 곳과 인덱스 차이가 3이 나오도록 순서를 조정하였음
			st = new StringTokenizer(br.readLine());
			dice[i][0] = Integer.parseInt(st.nextToken());
			dice[i][1] = Integer.parseInt(st.nextToken());
			dice[i][2] = Integer.parseInt(st.nextToken());
			dice[i][4] = Integer.parseInt(st.nextToken());
			dice[i][5] = Integer.parseInt(st.nextToken());
			dice[i][3] = Integer.parseInt(st.nextToken());
		}
		
		//맨 밑면만 결정하면 나머지 주사위가 어떤 방향으로 놓일지가 결정되므로 6가지 경우만 테스트해보면 됨
		for(int i=0;i<6;i++) {
			//맨 밑면에 i번째 인덱스에 있는 수가 놓여있다고 가정하고, 옆면 최댓값을 구함
			int curMax = diceMax(dice[0], i);
			//윗면은 그 수로부터 인덱스가 3 차이나는 곳에 있음
			int previousTop = dice[0][(i+3)%6];
			
			//나머지 주사위에 대해서 반복
			for(int j=1;j<n;j++) {
				//현재 주사위의 밑면은 전 주사위의 윗면임
				int currentBot = diceIdx(dice[j],previousTop);
				//밑면, 윗면 빼고 최댓값을 구해서 더해줌
				int localMax = diceMax(dice[j],currentBot);
				curMax += localMax;
				//다음 주사위의 밑면을 결정하도록 현재 주사위의 밑면을 넘김 
				previousTop = dice[j][(currentBot+3)%6];
			}
			
			//지금까지 구한 최댓값과 비교
			answer = Math.max(curMax,answer);
		}
		
		
		//출력
		System.out.println(answer);
	}
	
	//아래 주사위의 윗면에 맞춰서 주사위 밑면을 뭘로 해야할지 찾아주는 함수
	public static int diceIdx(int[] die, int previousTop) {
		for(int i=0;i<6;i++)
			if(die[i]==previousTop) return i;
		
		return -1;
	}
	
	//윗면과 밑면을 빼고 옆면 중 최댓값을 찾아주는 함수
	public static int diceMax(int[] die, int idx) {
		int max =0;
		for(int i=0;i<6;i++) {
			if((i-idx)%3==0) continue;
			max = Math.max(max, die[i]);
		}
		
		return max;
	}
	
}
