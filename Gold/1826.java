
// 제목 : 연료 채우기
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/1826
// 메모리(kb) : 20580
// 실행시간(ms) : 264

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 주유소 수
		int N = Integer.parseInt(br.readLine());

		// 마지막 도착지까지 주유소로 치고, 주유소들의 배열
		GasStation[] stations = new GasStation[N + 1];

		// 주유소 입력받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int dist = Integer.parseInt(st.nextToken());
			int gas = Integer.parseInt(st.nextToken());
			stations[i] = new GasStation(dist, gas);
		}

		// 도착지와 처음 연료의 양
		st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		stations[N] = new GasStation(L, 0);

		// 가까운 주유소 순으로 정렬
		Arrays.sort(stations, (s1, s2) -> (s1.dist - s2.dist));

		// 지나온 주유소들을 연료가 큰 순서대로 정렬하는 우선순위 큐
		PriorityQueue<GasStation> prevStations = new PriorityQueue<>((s1, s2) -> (s2.gas - s1.gas));

		// 충전한 주유소 수
		int answer = 0;

		// 각각의 주유소까지 갈 수 있는지 따져보기
		for (int i = 0; i <= N; i++) {
			// 현재 주유소
			GasStation curStation = stations[i];

			// 현재 주유소까지 못 간다면
			if (P < curStation.dist) {
				// 지나온 주유소 중에서 연료를 많이 받을 수 있는 주유소부터 따져서 연료를 충전해봄
				while (!prevStations.isEmpty() && P < curStation.dist) {
					answer++;
					P += prevStations.poll().gas;
				}
				// 그래도 현재 주유소까지 못 온다면 -1 출력
				if (P < curStation.dist) {
					System.out.println(-1);
					return;
				}
			}

			// 현재 주유소도 지나온 주유소 큐에 넣기
			prevStations.add(curStation);

			// 마을까지 도착했다면 탐색 종료(주유소가 모두 시작 위치와 주유소 사이에 있다는 보장이 문제에 없어서 넣었음)
			if (curStation.dist == L)
				break;
		}

		// 주유소에서 멈춘 횟수 출력
		System.out.println(answer);

	}

	// 주유소 클래스
	static class GasStation {
		int dist, gas;

		public GasStation(int dist, int gas) {
			super();
			this.dist = dist;
			this.gas = gas;
		}

	}
}