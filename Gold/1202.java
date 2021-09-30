// 제목 : 보석 도둑
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/1202
// 메모리(kb) : 117852
// 실행시간(ms) : 1640

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// 입력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 보석 수, 가방 수
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// 보석을 무게가 낮은 순서대로 저장할 우선 순위 큐
		PriorityQueue<Gem> gems = new PriorityQueue<>((g1, g2) -> (g1.m - g2.m));

		// 보석 정보 입력받아 저장하기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int mi = Integer.parseInt(st.nextToken());
			int vi = Integer.parseInt(st.nextToken());
			gems.add(new Gem(mi, vi));
		}

		// 배낭을 최대 무게가 낮은 순서대로 저장할 우선 순위 큐
		PriorityQueue<Integer> bags = new PriorityQueue<>();

		// 배낭 정보 입력받아 저장하기
		for (int i = 0; i < K; i++) {
			bags.add(Integer.parseInt(br.readLine()));
		}

		// 일정 무게 이하의 보석들을 담은 후, 가격이 높은 순서대로 저장할 우선 순위 큐
		PriorityQueue<Gem> candidates = new PriorityQueue<>((g1, g2) -> (g2.v - g1.v));
		// 보석의 최대 가치, int로 하면 틀렸습니다가 뜹니다.
		long answer = 0;

		// 빈 가방이 없을 때까지
		while (!bags.isEmpty()) {
			// 무게 제한
			int weightLimit = bags.poll();

			// 무게 제한 이하인 보석을 꺼내서 후보군에 넣어둠
			while (!gems.isEmpty() && gems.peek().m <= weightLimit) {
				candidates.add(gems.poll());
			}

			// 후보군이 비었다면 건너뛰고, 아니라면 후보 중 제일 가치가 높은 보석을 가방에 담음
			if (candidates.isEmpty())
				continue;
			answer += candidates.poll().v;
		}

		// 출력
		System.out.println(answer);

	}

	static class Gem {
		//보석의 무게, 가격
		int m;
		int v;

		public Gem(int m, int v) {
			super();
			this.m = m;
			this.v = v;
		}

	}
}