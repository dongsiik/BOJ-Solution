
// 제목 : 회의실 배정 4
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/19623
// 메모리(kb) : 53572
// 실행시간(ms) : 736

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 회의 수
		int N = Integer.parseInt(br.readLine());

		// 편의를 위해 0시에 시작해서 0시에 끝나는 0명 회의를 가정
		Meeting[] meetings = new Meeting[N + 1];
		meetings[0] = new Meeting(0, 0, 0);

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int people = Integer.parseInt(st.nextToken());
			meetings[i] = new Meeting(start, end, people);
		}

		// 정렬
		Arrays.sort(meetings);

		// i번 회의까지 고려했을 때 최대 인원
		int[] totalCount = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			// 이전 회의 중 i번 회의 시작 전에 열리면서 가장 늦게 끝나는 회의
			int targetIndex = upperBound(meetings, 0, i, meetings[i]) - 1;
			// 지금 회의 안 여는 경우와, 지금 회의를 여는 경우 비교
			totalCount[i] = Math.max(totalCount[i - 1], totalCount[targetIndex] + meetings[i].people);
		}

		// 출력
		System.out.println(totalCount[N]);
	}

	// 지금 회의와 겹치면서 가장 빨리 끝나는 회의 찾기
	static int upperBound(Meeting[] meetings, int start, int end, Meeting target) {

		while (start < end) {
			int mid = (start + end) / 2;
			if (meetings[mid].end <= target.start) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}

		return end;
	}

	// 미팅 클래스
	static class Meeting implements Comparable<Meeting> {
		int start, end, people;

		public Meeting(int start, int end, int people) {
			super();
			this.start = start;
			this.end = end;
			this.people = people;
		}

		@Override
		public int compareTo(Meeting o) {
			if (this.end != o.end) {
				return this.end - o.end;
			} else if (this.start != o.start) {
				return this.start - o.start;
			} else {
				return this.people - o.people;
			}
		}

	}
}