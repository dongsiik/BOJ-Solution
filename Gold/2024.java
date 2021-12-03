
// 제목 : 선분 덮기
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/2024
// 메모리(kb) : 44696
// 실행시간(ms) : 580

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 덮을 구간의 오른쪽 끝
		int M = Integer.parseInt(br.readLine());

		ArrayList<Line> lines = new ArrayList<>(100_000);

		// 선 입력받기
		while (true) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			Line line = new Line(from, to);
			if (line.endOfInput())
				break;

			lines.add(line);
		}

		// 왼쪽을 기준으로 선 정렬
		Collections.sort(lines);

		// 현재 덮은 구간, 현재까지 살펴본 선의 수, 덮는데 쓴 선의 수
		int currentCoverage = 0;
		int currentIndex = 0;
		int answer = 0;

		// 왼쪽에서부터 선 이어붙이기
		while (currentCoverage < M && currentIndex < lines.size()) {
			int maxCoverage = currentCoverage;
			// 현재와 이어지면서 가장 오른쪽으로 긴 선 찾기
			while (currentIndex < lines.size()) {
				Line curLine = lines.get(currentIndex);
				// 이어진다면 가장 오른쪽으로 긴 선 길이 갱신
				if (curLine.from <= currentCoverage) {
					maxCoverage = Math.max(maxCoverage, curLine.to);
					currentIndex++;
				}
				// 이어지지 않는다면
				else {
					// 중간에 끊어졌다면 실패했으므로 0 출력
					if (currentCoverage == maxCoverage) {
						System.out.println("0");
						return;
					}
					// 끊어지지 않았다면 일단 현재까지 살펴본 선 붙이기
					else {
						break;
					}
				}
			}
			// 선 붙이기
			currentCoverage = maxCoverage;
			answer++;

		}

		// 다 덮는데 실패했다면 0 출력
		if (currentCoverage < M) {
			System.out.println(0);
		}
		// 덮었다면 갯수 출력
		else {
			System.out.println(answer);

		}
	}

	static class Line implements Comparable<Line> {
		int from, to;

		public Line(int from, int to) {
			super();
			this.from = from;
			this.to = to;
		}

		public boolean endOfInput() {
			if (from == 0 && to == 0) {
				return true;
			}
			return false;
		}

		@Override
		public int compareTo(Line o) {
			return Integer.compare(this.from, o.from);
		}
	}
}