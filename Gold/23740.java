
// 제목 : 버스 노선 개편하기
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/23740
// 메모리(kb) : 108584
// 실행시간(ms) : 1112

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		Line[] lines = new Line[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			lines[i] = new Line(from, to, cost);
		}

		// 시작 노선이 앞선 순, 시작 노선이 같다면 종료 노선이 뒤인 순서대로 정렬
		Arrays.sort(lines);

		// 개편된 노선들
		ArrayList<Line> answerLine = new ArrayList<>();

		// 합쳐 나가기 시작하는 노선 번호
		int startLine = 0;
		while (startLine < N) {
			// 다음 노선 번호
			int endLine = startLine + 1;
			// 다음 노선이 겹친다면 합치기
			while (endLine < N && lines[endLine].from <= lines[startLine].to) {
				lines[startLine].to = Math.max(lines[startLine].to, lines[endLine].to);
				lines[startLine].cost = Math.min(lines[startLine].cost, lines[endLine].cost);
				endLine++;
			}

			// 개편된 노선에 합친 노선 넣기
			answerLine.add(lines[startLine]);
			startLine = endLine;
		}

		// 출력
		sb.append(answerLine.size()).append('\n');
		for (Line line : answerLine) {
			sb.append(line.from).append(' ').append(line.to).append(' ').append(line.cost).append('\n');
		}

		System.out.println(sb);

	}

	// 노선 클래스
	static class Line implements Comparable<Line> {
		int from;
		int to;
		int cost;

		// 생성자
		public Line(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		// 시작 노선이 앞서는 순, 같다면 종료 노선이 뒤인 순서
		@Override
		public int compareTo(Line o) {
			if (this.from != o.from) {
				return Integer.compare(this.from, o.from);
			} else {
				return Integer.compare(o.to, this.to);
			}
		}

	}
}