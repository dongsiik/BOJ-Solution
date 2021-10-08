// 제목 : 부분수열의 합
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/1208
// 메모리(kb) : 92300
// 실행시간(ms) : 716

// 중간에서 만나기 알고리즘을 사용했습니다.
// 모든 가능한 합을 다 구하면서도 배열을 사용해서 빠른 다른 분의 놀라운 풀이를 보고 싶다면 아래 풀이를 참고해주세요.
// https://www.acmicpc.net/source/33625662

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	//정수의 개수, 구하고자 하는 합
	static int N, S;
	//문제의 조건을 만족하는 경우의 수
	static long answer;
	//정수들의 값
	static int[] nums;
	//중간에서 만나기 알고리즘에서, 왼쪽 절반에서 구한 합에 따른 개수
	static HashMap<Integer, Long> leftSum;
	
	public static void main(String[] args) throws IOException {

		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		//초기화
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		//S가 0일 경우, 크기가 0인 부분 수열이 항상 포함되므로 -1부터 시작
		answer = S==0? -1:0;
		
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) nums[i] = Integer.parseInt(st.nextToken());
		
		leftSum = new HashMap<>();
		
		//중간에서 만나기
		leftDFS(0, 0);
		rightDFS(N/2, 0);
		
		System.out.println(answer);
		
	}
	
	//왼쪽 절반 탐색
	static void leftDFS(int start, int partialSum) {
		//탐색이 끝났다면, 지금 합이 나온 경우의 수를 HashMap에 반영
		if(start==N/2) {
			leftSum.put(partialSum, leftSum.getOrDefault(partialSum, 0L)+1);
			return;
		}
		
		//지금 값을 더했을 때, 아닐 때 각각 재귀
		leftDFS(start+1,partialSum);
		leftDFS(start+1,partialSum+nums[start]);
	}
	
	//오른쪽 절반 탐색
	static void rightDFS(int start, int partialSum) {
		//탐색이 끝났다면, 지금 합과 왼쪽 절반에서 나온 값 중 합이 S가 되는 경우가 있는지 살펴보고 정답 경우의 수 반영
		if(start==N) {
			answer += leftSum.getOrDefault(S-partialSum, 0L);
			return;
		}
		
		//지금 값을 더했을 때, 아닐 때 각각 재귀
		rightDFS(start+1, partialSum);
		rightDFS(start+1, partialSum+nums[start]);
	}
	

}