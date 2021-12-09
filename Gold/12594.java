
// 제목 : Theme Park (Large)
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/12594
// 메모리(kb) : 52508
// 실행시간(ms) : 244

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 수
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());

			// 롤러코스터 운행 횟수, 좌석 수, 그룹 수
			int R = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());

			// 대기열
			Queue<Pair> q = new ArrayDeque<>(N);
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				// 그룹 크기를 입력받고, 그룹 번호와 함께 큐에 넣기
				int groupSize = Integer.parseInt(st.nextToken());
				q.add(new Pair(i, groupSize));
			}

			// 지금 그룹으로 시작하는 대기열이 있었는지 확인하기 위한 배열
			Pair[] checked = new Pair[N];

			// 수입
			long answer = 0;

			// 롤러코스터 운행마다
			for (int i = 0; i < R; i++) {
				// 현재 대기열 맨 앞에 있는 그룹
				Pair currentGroup = q.peek();

				// 이 그룹으로 롤러코스터 탑승을 시작한 적이 없었다면
				if (checked[currentGroup.first] == null) {
					// 현재 운행횟수와 소수입기록
					checked[currentGroup.first] = new Pair(i, answer);

					// 롤러코스터에 최대한 사람 집어넣기
					answer += runCoaster(q, K);
				} else {
					// 이전 운행횟수와 그 때 수입
					int prevTime = checked[currentGroup.first].first;
					long prevEarn = checked[currentGroup.first].second;

					// 주기와, 주기별 수입 구하기
					int period = i - prevTime;
					long earningPerPeriod = answer - prevEarn;

					// 주기로 수입과 운행 횟수 건너뛰기
					long jumpEarn = (R - i - 1) / period * earningPerPeriod;
					answer += jumpEarn;
					i += ((R - i - 1) / period) * period;

					// 남은 운행횟수만큼 진행하기
					for (int j = i; j < R; j++) {
						answer += runCoaster(q, K);
					}
					break;

				}
			}

			// 출력문 저장
			sb.append("Case #").append(tc).append(": ").append(answer).append('\n');
		}

		// 정답 출력
		System.out.println(sb);

	}

	// 롤러코스터에 사람 태우기
	static long runCoaster(Queue<Pair> q, int K) {
		// 탄 사람 수 = 이번 롤러코스터 운행 수입
		long res = 0;

		// 롤러코스터에 앉은 사람들
		Queue<Pair> tempQ = new ArrayDeque<>(q.size());

		// 대기열이 비거나, 한 그룹이 못 들어갈 때까지
		while (!q.isEmpty() && res + q.peek().second <= K) {
			// 수입에 추가하고, 롤러코스터에 앉은 사람에 넣기
			Pair group = q.poll();
			res += group.second;
			tempQ.offer(group);
		}

		// 롤러코스터에 탔던 사람들 다시 대기열에 넣기
		while (!tempQ.isEmpty()) {
			q.offer(tempQ.poll());
		}

		return res;
	}

	// 숫자 두 개를 저장하기 위한 클래스. 제네릭으로 만들려다가 타입 넣는 게 더 귀찮아서 이렇게 만들었음
	static class Pair {
		int first;
		long second;

		public Pair(int first, long second) {
			this.first = first;
			this.second = second;
		}
	}

}