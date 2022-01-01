
// 제목 : 성싶당 밀키트
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/24041
// 메모리(kb) : 122936
// 실행시간(ms) : 892

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	//재료 수, 세균 수 제한, 빼도 되는 재료 수
	static int N, G, K;
	//중요한 재료, 안 중요한 재료들과 그 수
	static Ingredient[] important;
	static Ingredient[] unimportant;
	static int importantCount, unimportantCount;	
	
	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		important = new Ingredient[N];
		unimportant = new Ingredient[N];
		
		//가장 짧은 유통기한
		int lowerBound = Integer.MAX_VALUE;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int O = Integer.parseInt(st.nextToken());
			if(O==0) {
				important[importantCount++] = new Ingredient(S, L);
			}
			else {
				unimportant[unimportantCount++] = new Ingredient(S, L);
			}
			lowerBound = Math.min(lowerBound, L);
		}
		
		//이진탐색
		System.out.println(binary_search(lowerBound, 2_000_000_001));
		
	}
	
	//이진 탐색. 세균 수가 G를 넘는 첫 날짜를 구하면 그 전날까지는 먹어도 됨
	static long binary_search(long start, long end) {
		
		while(start<end) {
			//start, end를 모두 int로 했다면 start가 10억, end가 20억일 때 mid를 구하며 오버플로가 납니다!!!
			long mid = (start+end)/2;
			if(safe((int)mid)) {
				start = mid + 1;
			}
			else {
				end = mid;
			}
		}
		
		return Math.max(0, end-1);
	}
	
	//해당하는 날에 세균수가 G 이하라면 true, 아니라면 false
	static boolean safe(int day) {
		//세균 수 합
		long gem = 0;
		
		//필수 재료에서 세균수 더하기
		for(int i=0;i<importantCount;i++) {
			gem += important[i].getGem(day);
			if(gem>G) return false;
		}

		//빼도 되는 재료에서 세균수를 구하고 정렬
		long[] gemFromUnimportant = new long[unimportantCount];
		for(int i=0;i<unimportantCount;i++) {
			gemFromUnimportant[i] = unimportant[i].getGem(day);
		}
		Arrays.sort(gemFromUnimportant);
		
		//최대한 빼고 남은 개수
		int upperBound = Math.max(0, unimportantCount-K);
		
		//남는 재료가 있고, 남은 재료 중에 가장 세균이 많은 애들만 더해도 세균이 G 이하라면 true
		if(upperBound>0 && gem+(long)upperBound*gemFromUnimportant[upperBound-1]<=G) return true;
		
		//애매한 경우 세균 수 더해보면서 확인
		for(int i=upperBound-1;i>=0;i--) {
			gem += gemFromUnimportant[i];
			if(gem>G) return false;
		}
		
		return true;
	}
	
	//재료
	static class Ingredient{
		//부패속도, 유통기한
		int S, L;

		public Ingredient(int s, int l) {
			super();
			S = s;
			L = l;
		}
		
		long getGem(int day) {
			return ((long)S)*Math.max(1, day-L);
		}
	}
}