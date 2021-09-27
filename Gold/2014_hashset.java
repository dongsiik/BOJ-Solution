// 제목 : 소수의 곱
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/2014
// 메모리(kb) : 55340
// 실행시간(ms) : 560

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입출력 도구들
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 소수의 수, 구하고자하는 수의 순서 n
		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		//중복 확인용 해시셋, 메모리 초과를 막기 위한 currentMax;
		HashSet<Long> dupChecker = new HashSet<>();
		long currentMax = 0;
		
		// 숫자들을 비교하기 위한 우선순위 큐, 소수 목록,
		PriorityQueue<Long> pq = new PriorityQueue<>();
		long[] primes = new long[k+1];
		
		//소수 목록을 입력받고, 큐에 넣어주고, 중복 명단에 등록하기
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<k;i++) {
			primes[i] = Integer.parseInt(st.nextToken());
			pq.add(primes[i]);
			dupChecker.add(primes[i]);
			currentMax = Math.max(currentMax, primes[i]);
		}
		
		
		while(--n>0) {
			//현재 보고 있는 수
			long current = pq.poll();
			
			for(int i=0;i<k;i++) {
				//소수 하나를 곱해 만든 다음 수
				long next = current*primes[i];
				
				//pq가 너무 크면서 다음 수가 너무 크면 생략
				if(pq.size()>=n+1 && next>=currentMax) continue;
				
				//그렇지 않으면서 중복되지 않았다면
				if(dupChecker.add(next)) {
					//큐에 넣고 최댓값 갱신
					pq.add(next);
					currentMax = Math.max(currentMax, next);
				}
			}
			
			
		}
		//출력
		System.out.println(pq.poll());
	}
}
