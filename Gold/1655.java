
// 제목 : 가운데를 말해요
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/1655
// 메모리(kb) : 33460
// 실행시간(ms) : 488

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		//백준이 외치는 정수의 수
		int N = Integer.parseInt(br.readLine());
		//각각 작은 쪽, 큰 쪽 절반을 저장할 우선순위 큐
		PriorityQueue<Integer> smallPQ = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> bigPQ = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			//숫자 입력받기
			int num = Integer.parseInt(br.readLine());

			//두 큐가 같다면 작은 쪽 큐에 넣고, 아니라면(==작은 쪽 큐가 하나 더 크다면) 큰 큐에 넣음
			if (smallPQ.size() == bigPQ.size()) 
				smallPQ.add(num);
			else
				bigPQ.add(num);

			//작은 쪽 큐와 큰 큐가 작은 값, 큰 값들만 갖도록 조정
			if (!smallPQ.isEmpty() && !bigPQ.isEmpty()) {
				if (smallPQ.peek() > bigPQ.peek()) {
					smallPQ.add(bigPQ.poll());
					bigPQ.add(smallPQ.poll());

				}
			}

			//출력문 저장
			sb.append(smallPQ.peek()).append('\n');
		}

		System.out.println(sb);
	}

}