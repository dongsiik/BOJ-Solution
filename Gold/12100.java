
// 제목 : 2048(Easy)
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/12100
// 메모리(kb) : 16564
// 실행시간(ms) : 216

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 보드의 크기와 초기 보드
	static int N;
	static int[][] initialBoard;
	// 최대 이동 횟수
	static int movementLimit = 5;
	// 블록의 가장 큰 값
	static int answer;

	public static void main(String[] args) throws IOException {

		// 초기화
		init();
		// dfs
		dfs(initialBoard, 0);
		// 출력
		System.out.println(answer);

	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		initialBoard = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				initialBoard[i][j] = Integer.parseInt(st.nextToken());
		}

		answer = 0;
	}

	static void dfs(int[][] board, int level) {

		// 다 이동했다면, 최댓값을 갱신
		if (level == movementLimit) {
			answer = getMax(board);
			return;
		}

		// DFS를 위한 보드 복사본
		int[][] copyOfBoard = new int[N][N];

		for (int direction = 0; direction < 4; direction++) {
			// 보드 복사하기
			copy(board, copyOfBoard);
			// 보드 이동시키기
			simulate(copyOfBoard, direction);

			// 최댓값을 갱신할 가능성이 보인다면, 다음 단계로 넘어감
			if (isPromising(copyOfBoard, level + 1)) {
				dfs(copyOfBoard, level + 1);
			}
		}

	}

	// 시뮬레이션 : 블록을 한 방향으로 이동시키고, 합치고, 빈 자리를 메우기 위해 다시 이동시킴
	static void simulate(int[][] board, int direction) {
		fall(board, direction);
		merge(board, direction);
		fall(board, direction);

	}

	// 블록 이동시키기 : direction 0,1,2,3은 각각 위, 아래 , 왼쪽, 오른쪽으로 몰기
	// 어떻게 중복을 제거하면 좋을까요...
	private static void fall(int[][] board, int direction) {
		switch (direction) {
		case 0:
			for (int j = 0; j < N; j++) {
				// 블록이 떨어질 자리
				int blockPosition = 0;
				for (int i = 0; i < N; i++) {
					// 블록을 발견하면, 블록을 떨어뜨림(블록 위치와 맞바꿈)
					if (board[i][j] != 0) {
						int temp = board[blockPosition][j];
						board[blockPosition][j] = board[i][j];
						board[i][j] = temp;
						blockPosition++;
					}
				}
			}
			break;
		case 1:
			for (int j = 0; j < N; j++) {
				int blockPosition = N - 1;
				for (int i = N - 1; i >= 0; i--) {
					if (board[i][j] != 0) {
						int temp = board[blockPosition][j];
						board[blockPosition][j] = board[i][j];
						board[i][j] = temp;
						blockPosition--;
					}
				}
			}
			break;
		case 2:
			for (int i = 0; i < N; i++) {
				int blockPosition = 0;
				for (int j = 0; j < N; j++) {
					if (board[i][j] != 0) {
						int temp = board[i][blockPosition];
						board[i][blockPosition] = board[i][j];
						board[i][j] = temp;
						blockPosition++;
					}
				}
			}
			break;
		case 3:
			for (int i = 0; i < N; i++) {
				int blockPosition = N - 1;
				for (int j = N - 1; j >= 0; j--) {
					if (board[i][j] != 0) {
						int temp = board[i][blockPosition];
						board[i][blockPosition] = board[i][j];
						board[i][j] = temp;
						blockPosition--;
					}
				}
			}
			break;
		}

	}

	// 합치기 : direction 0,1,2,3은 각각 위, 아래, 왼쪽, 오른쪽으로 합친 후를 의미
	// 어떻게 중복을 제거하면 좋을까요...
	private static void merge(int[][] board, int direction) {
		switch (direction) {
		case 0:
			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N - 1; i++) {
					if (board[i][j] == board[i + 1][j]) {
						board[i][j] *= 2;
						board[i + 1][j] = 0;
					}
				}
			}
			break;
		case 1:
			for (int j = 0; j < N; j++) {
				for (int i = N - 1; i > 0; i--) {
					if (board[i][j] == board[i - 1][j]) {
						board[i][j] *= 2;
						board[i - 1][j] = 0;
					}
				}
			}
			break;
		case 2:
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					if (board[i][j] == board[i][j + 1]) {
						board[i][j] *= 2;
						board[i][j + 1] = 0;
					}
				}
			}
			break;
		case 3:
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j > 0; j--) {
					if (board[i][j] == board[i][j - 1]) {
						board[i][j] *= 2;
						board[i][j - 1] = 0;
					}
				}
			}
			break;

		}

	}

	// 최댓값을 갱신할 수 있는지 따져보기. 한 번 이동에서 최댓값은 잘해야 2배 증가하므로, 2배씩 증가했을 때 현재 최댓값과 비교
	static boolean isPromising(int[][] board, int level) {

		if (getMax(board) * Math.pow(2, movementLimit - level) <= answer)
			return false;

		return true;
	}

	// 블록의 최댓값 구하기
	static int getMax(int[][] board) {
		int max = 0;

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				max = Math.max(max, board[i][j]);

		return max;
	}

	// 블록 복사하기
	static void copy(int[][] board, int[][] copyOfBoard) {

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				copyOfBoard[i][j] = board[i][j];

	}
}