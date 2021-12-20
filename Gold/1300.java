
// 제목 : K번째 수
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/1300
// 메모리(kb) : 14224
// 실행시간(ms) : 268

// 이 코드의 시간복잡도는 O(N*(log N)^2) 입니다.
// 각 열 별로 target 이하의 원소 개수를 구할 때 주석 처리한 부분처럼 하면 O(N * log N)이 됩니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	
	//N과 K
	static long N, K;

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		System.out.println(lowerBound(0, N*N+1));

	}
	
	//이분탐색으로 자신보다 작거나 같은 원소가 K개 있으면서 가장 작은 수 찾기
	static long lowerBound(long start, long end) {
		
		while(start<end) {
			long mid = (start + end)/2;
			
			if(count(mid)>=K) {
				end = mid ;
			}
			else {
				start = mid + 1;
			}
		}
		
		return end;
	}
	
	//target보다 작은 원소의 수
	static long count(long target) {
		long res = 0;
		
		//각각의 행마다
		for(int i=1;i<=N;i++) {
//			res += Math.min(target/i, N);
			//다시 이분탐색으로 자신보다 작거나 같은 원소 수 세서 더하기
			res += upperBoundInRow(i, target);
		}
		
		return res;
	}
	
	//자신보다 큰 원소를 찾고, 거기서 하나를 빼서 자신과 작거나 같은 원소 찾기
	static long upperBoundInRow(long rowIndex, long target) {
		
		long start = 0;
		long end = N+1;
		
		while(start<end) {
			long mid = (start+end)/2;
			if(rowIndex*mid<=target) {
				start = mid+1;
			}
			else {
				end = mid;
			}
		}
		
		return end-1;
	}
}