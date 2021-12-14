
// 제목 : 별 찍기 - 11
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/2448
// 메모리(kb) : 51880
// 실행시간(ms) : 324

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	public static void main(String args[]) throws IOException {

		//입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 출력
		printStar(N);

	}

	static void printStar(int N) {

		//배열로 출력할 그림 저장하기
		char[][] board = new char[N][2 * N - 1];
		
		//그림 공백으로 초기화 -> 이 부분 없으면 오답
		for (int i = 0; i < N; i++) {
			Arrays.fill(board[i], ' ');
		}

		//처음 가장 작은 패턴
		int center = N - 1;
		board[0][center] = '*';
		board[1][center - 1] = board[1][center + 1] = '*';
		board[2][center - 2] = board[2][center - 1] = board[2][center] = board[2][center + 1] = board[2][center + 2] = '*';

		// 패턴 반복하기
		for (int step = 3; step < N; step *= 2) {
			copy(board, step, center, center - step);
			copy(board, step, center, center + step);
		}

		//출력
		for (int i = 0; i < N; i++) {
			System.out.println(board[i]);
		}
	}

	//step만큼의 크기로 from부터 to까지 복사하는 함수
	static void copy(char[][] board, int step, int from, int to) {

		for (int row = 0; row < step; row++) {
			for (int column = -row; column <= row; column++) {
				board[step + row][to + column] = board[row][from + column];
			}
		}
	}

}