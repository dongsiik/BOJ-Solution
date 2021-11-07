// 제목 : 피자판매
// 티어 : 골드 1
// 링크 : https://www.acmicpc.net/problem/2632
// 메모리(kb) : 22244
// 실행시간(ms) : 180

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	//고객이 원하는 피자 크기, A피자의 조각 수, B 피자의 조각 수
	static int wanted, M, N;
	//정답
	static long answer;
	//A 피자 조각들, B 피자 조각들, A 피자들의 연속된 부분합
	static int[] A, B, partialSumOfA;
	
	public static void main(String[] args) throws IOException {

		init();
		getPartialSumOfA();
		getPartialSumOfB();
		System.out.println(answer);
		
	}
	
	//입력받기
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		wanted = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		A = new int[M];
		B = new int[N];
		
		for(int i=0;i<M;i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=0;i<N;i++) {
			B[i] = Integer.parseInt(br.readLine());
		}
	}
	
	static void getPartialSumOfA() {
		//partialSumOfA[partialSum] = 크기가 partialSum인 A의 연속된 부분합이 count개 있다는 뜻
		partialSumOfA = new int[2_000_001];
		
		//누적합 배열
		int[] accumulation = new int[M+1];
		
		//한 바퀴 돌지 않고 연속되는 경우
		for(int i=1;i<=M;i++) {
			accumulation[i] = accumulation[i-1] + A[i-1];
			for(int j=0;j<i;j++) {
				int partialSum = accumulation[i]-accumulation[j];
				partialSumOfA[partialSum]++;
			}
		}
		
		//한 바퀴 돌아서 연속되는 경우
		for(int i=1;i<M;i++) {
			for(int j=1;j<i;j++) {
				int partialSum = accumulation[M] - accumulation[i] + accumulation[j];
				partialSumOfA[partialSum]++;
			}
		}
		
		//A에서 아무 조각도 안 집는 경우
		partialSumOfA[0]++;
		
	}

	static void getPartialSumOfB() {
		
		//누적합 배열
		int[] accumulation = new int[N+1];
		
		//한 바퀴 돌지 않고 연속될 때, A와 합쳐서 원하는 크기가 되는지 확인
		for(int i=1;i<=N;i++) {
			accumulation[i] = accumulation[i-1] + B[i-1];
			for(int j=0;j<i;j++) {
				int partialSum = accumulation[i]-accumulation[j];
				increaseAnswer(partialSum);
			}
		}
		
		//한 바퀴 돌아서 연속될 때, A와 합쳐서 원하는 크기가 되는지 확인
		for(int i=1;i<N;i++) {
			for(int j=1;j<i;j++) {
				int partialSum = accumulation[N] - accumulation[i] + accumulation[j];
				increaseAnswer(partialSum);
			}
		}
		
		//B에서 한 조각도 집지 않을 때, A에서 원하는 크기가 있는지 확인
		increaseAnswer(0);
	}
	
	//B조각 크기를 입력받은 후, A에서 합쳐서 고객이 원하는 경우가 있다면 그만큼 정답을 증가시킴
	static void increaseAnswer(int partialSum) {
		if(wanted-partialSum>=0) {
			answer += partialSumOfA[wanted-partialSum];
		}
	}

}