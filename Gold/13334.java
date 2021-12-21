
// 제목 : 철로
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/13334
// 메모리(kb) : 56004
// 실행시간(ms) : 916

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		//사람 수
		int N = Integer.parseInt(br.readLine());
		
		//통근길 정보
		PriorityQueue<Line> lines = new PriorityQueue<>(N+1, (l1, l2)->(Long.compare(l1.end, l2.end)));
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			long start = Long.parseLong(st.nextToken());
			long end = Long.parseLong(st.nextToken());
			lines.offer(new Line(Math.min(start, end), Math.max(start, end)));
		}
		//마무리 처리를 위한 무의미한 허수아비 사람
		lines.offer(new Line(-100_000_001,100_000_001));
		
		//철로 길이
		long D = Long.parseLong(br.readLine());
		
		//현재까지 철로로 연결할 수 있는 통근길
		PriorityQueue<Line> considering = new PriorityQueue<>((l1,l2)->(Long.compare(l1.start, l2.start)));
		
		//철로로 혜택볼 수 있는 최대 사람 수
		int answer = 0;
		
		//모든 사람들을 처리할 때까지
		while(!lines.isEmpty()) {
			//현재 통근길 정보
			long currentEnd = lines.peek().end;
			
			//이 길과 도착지가 같은 통근길을 다 집어넣음
			while(!lines.isEmpty() && lines.peek().end==currentEnd) {
				considering.offer(lines.poll());
			}
			
			//이 도착지를 도착지로 하는 철로로 시작점을 연결할 수 없는 통근길을 다 뺌
			while(!considering.isEmpty() && considering.peek().start<currentEnd-D) {
				considering.poll();
			}
			
			//남은 통근길의 개수를 현재와 비교
			answer = Math.max(answer, considering.size());
		}
		
		
		System.out.println(answer);

	}
	
	static class Line{
		long start, end;

		public Line(long start, long end) {
			this.start = start;
			this.end = end;
		}
		
	}
}