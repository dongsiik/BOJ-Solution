
// 제목 : 돌 굴러가유
// 티어 : 실버 1
// 링크 : https://www.acmicpc.net/problem/23889
// 메모리(kb) : 37864
// 실행시간(ms) : 776

// 공식 풀이를 참고하였습니다.
// https://upload.acmicpc.net/2e037db3-396a-4b24-a6bc-808f187335ac/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 마을, 벽, 돌
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// 마을별 모래성 종류
		st = new StringTokenizer(br.readLine());
		int[] castle = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			castle[i] = Integer.parseInt(st.nextToken());
		}

		// 돌이 굴러가는 위치. 돌의 간격별로 쓰러질 모래성을 구하기 위해 편의상 마지막에 가상의 돌을 추가하였음
		st = new StringTokenizer(br.readLine());
		int[] stone = new int[K + 1];
		for (int i = 0; i < K; i++) {
			stone[i] = Integer.parseInt(st.nextToken());
		}
		stone[K] = N + 1;

		// 돌의 간격 사이에서 쓰러질 모래성 개수를 구함
		Block[] candidates = new Block[K];
		for (int i = 0; i < K; i++) {
			int save = 0;
			for (int position = stone[i]; position < stone[i + 1]; position++) {
				save += castle[position];
			}
			candidates[i] = new Block(stone[i], save);
		}

		// 많이 쓰러뜨리는 돌, 앞쪽에 있는 돌 순서대로 정렬
		Arrays.sort(candidates);

		// 상위 M개의 돌을 위치 순서대로 정렬
		Arrays.sort(candidates, 0, M, (b1, b2) -> (b1.position - b2.position));

		// 출력
		for (int i = 0; i < M; i++) {
			sb.append(candidates[i].position).append('\n');
		}

		System.out.println(sb);

	}

	// 벽 후보 클래스
	static class Block implements Comparable<Block> {

		// 위치, 지킬 수 있는 모래성 수
		int position, save;

		public Block(int position, int save) {
			this.position = position;
			this.save = save;
		}

		@Override
		public int compareTo(Block o) {
			if (this.save != o.save) {
				return o.save - this.save;
			} else {
				return this.position - o.position;
			}
		}

	}
}