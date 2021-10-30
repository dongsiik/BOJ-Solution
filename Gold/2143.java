
// 제목 : 두 배열의 합
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/2143
// 메모리(kb) : 59040
// 실행시간(ms) : 436
// 해시맵 대신 트리맵 사용시 메모리와 실행시간이 각각 54484, 1112

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	//찾고자 하는 합, A, B의 원소 수
	static int T, N, M;
	//문제에서 찾고자 하는 경우의 수
	static long answer;
	//두 배열
	static int[] A;
	static int[] B;
	//A의 부 배열의 합이 몇 번씩 나오는지를 저장하는 배열
	static HashMap<Integer, Long> partialSumOfA;

	public static void main(String[] args) throws NumberFormatException, IOException {

		init();
		getPartialSumOfA();
		getPartialSumOfB();
		System.out.println(answer);

	}
	
	static void init() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) A[i] = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		B = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) B[i] = Integer.parseInt(st.nextToken());
		
		answer = 0L;
	}
	
	static void getPartialSumOfA() {
		partialSumOfA = new HashMap<>();
		
		//누적합 배열
		int[] accumultation = new int[N+1];
		for(int i=1;i<=N;i++) {
			//누적합 구하기
			accumultation[i] = accumultation[i-1] + A[i-1];
			//이전 구간의 누적합을 빼서 부분합으로 만들고, 트리에 넣기
			for(int j=0;j<i;j++) {
				int partialSum = accumultation[i]-accumultation[j];
				partialSumOfA.put(partialSum, partialSumOfA.getOrDefault(partialSum, 0L)+1L);
			}
		}
		
	}
	static void getPartialSumOfB() {
		//누적합 배열
		int[] accumultation = new int[M+1];
		//이전 구간의 누적합을 빼서 부분합으로 만들고, A의 부분합과 합쳐서 T가 되는 경우가 있는지 살펴봄
		for(int i=1;i<=M;i++) {
			accumultation[i] = accumultation[i-1] + B[i-1];
			for(int j=0;j<i;j++) {
				int partialSum = accumultation[i]-accumultation[j];
				answer += partialSumOfA.getOrDefault(T-partialSum, 0L);
			}
		}
	}
}