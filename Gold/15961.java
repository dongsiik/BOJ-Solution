// 제목 : 회전 초밥
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/15961
// 메모리(kb) : 170544
// 실행시간(ms) : 564

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//접시의 수, 초밥의 가짓수, 연속해서 먹는 접시의 수, 쿠폰 번호
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		//벨트 상태 입력받기
		int[] belt = new int[n];
		for(int i=0;i<n;i++) {
			belt[i] = Integer.parseInt(br.readLine());
		}
		
		//최대 가짓수와 현재 가짓수
		int MaxNumOfDifferentDishes = 0;
		int currentNumOfDifferentDishes = 0;
		
		//현재 고민중인 접시들에서 나온 초밥들
		int[] candidates = new int[d+1];
		
		//n-k부터 n-1번까지 k개의 벨트에서 나온 초밥부터 시작
		for(int i=n-k;i<n;i++) {
			//새로운 접시면 가짓수를 하나 늘려줌
			int chosenDish = belt[i];
			if(candidates[chosenDish]++==0) {
				currentNumOfDifferentDishes++;
			}
		}
		//최대 가짓수를 현재 가짓수로 갱신, 쿠폰이 의미있다면 최대 가짓수를 하나 증가
		MaxNumOfDifferentDishes = currentNumOfDifferentDishes;
		if(candidates[c]==0) MaxNumOfDifferentDishes++;
		
		//오른쪽으로 초밥을 더하고 왼쪽 초밥을 뺄 예정
		for(int i=0;i<n-1;i++) {
			//왼쪽 초밥을 빼고, 그 초밥 종류가 이제 접시들에 없다면 가짓수를 하나 감소
			int deletedDish = belt[(i-k+n)%n];
			if(--candidates[deletedDish]==0)
				currentNumOfDifferentDishes--;
			
			//오른쪽 초밥을 더하고, 그 초밥 종류가 전에 없었다면 가짓수를 하나 증가
			int chosedDish = belt[i];
			if(candidates[chosedDish]++==0)
				currentNumOfDifferentDishes++;
			
			//쿠폰을 쓸 수 있는지를 따져서 최대 가짓수를 갱신
			if(candidates[c]==0) MaxNumOfDifferentDishes = Math.max(MaxNumOfDifferentDishes, currentNumOfDifferentDishes+1);
			else MaxNumOfDifferentDishes = Math.max(MaxNumOfDifferentDishes, currentNumOfDifferentDishes);
		}
		
		//출력
		System.out.println(MaxNumOfDifferentDishes);
	}
}