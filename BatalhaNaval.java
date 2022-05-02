package batalhaNaval;

import java.util.Random;
import java.util.Scanner;

public class BatalhaNaval {

	

	private static void showboard(char[][] board) {
		int NCOLs = board.length;
		int NROWS = board[0].length;
	
		System.out.println("   A B C D E F G H I J ");

		for (int linha = 0; linha < NROWS; linha++) {
			if (linha < NROWS - 1) {
				System.out.print(linha + 1 + " ");
			} else
				System.out.print(linha + 1);
			System.out.print("|");
			for (int coluna = 0; coluna < NCOLs; coluna++) {

				if (board[coluna][linha] == '\u0000') {
					System.out.print(" |");
				}
				if (board[coluna][linha] == 'S') {
					System.out.print("S|");

				}
				if (board[coluna][linha] == 'A') {
					System.out.print("W|");
				}
				if (board[coluna][linha] == 'X') {
					System.out.print("X|");
				}

			}
			System.out.println("");
			if (linha < NROWS - 1)
				System.out.println("  |-|-|-|-|-|-|-|-|-|-|");

		}
		

	}

	private static void allRandomShips(char[][] board, int tamanho) {
		Random random = new Random();
		int max = 4;
		int min = 1;
		int direcao = random.nextInt(max) + min;
		int randomColuna;
		int randomLinha;
		do {
			randomColuna = random.nextInt(10);
			randomLinha = random.nextInt(10);
		} while (board[randomColuna][randomLinha] != '\u0000');

		boolean verificacao = false;
		if (board[randomColuna][randomLinha] == '\u0000') {

			while (verificacao == false) {

				boolean okShip = true;

				direcao = random.nextInt(max) + min;
				do {
					randomColuna = random.nextInt(10);
					randomLinha = random.nextInt(10);
				} while (board[randomColuna][randomLinha] != '\u0000');

				switch (direcao) {

				case 1:
					// direita

					for (int i = 0; i < tamanho; i++) {

						if (checkIndiceIsOutOfBorder(board, randomColuna + i, randomLinha)
								|| checkAroundIndex(board, randomColuna + i, randomLinha)) {

							okShip = false;
						}
					}

					if (okShip == true) {

						for (int i = 0; i < tamanho; i++) {

							board[randomColuna + i][randomLinha] = 'S';
							verificacao = true;
						}
						break;
					}

				case 2:
					// esquerda

					for (int i = 0; i < tamanho; i++) {
						if (checkIndiceIsOutOfBorder(board, randomColuna - i, randomLinha)
								|| checkAroundIndex(board, randomColuna - i, randomLinha)) {

							okShip = false;
						}

					}

					if (okShip == true) {

						for (int i = 0; i < tamanho; i++) {
							board[randomColuna - i][randomLinha] = 'S';
							verificacao = true;
						}
						break;
					}

				case 3:
					// baixo

					for (int i = 0; i < tamanho; i++) {
						if (checkIndiceIsOutOfBorder(board, randomColuna, randomLinha + i)
								|| checkAroundIndex(board, randomColuna, randomLinha + i)) {

							okShip = false;
						}

					}
					if (okShip == true) {

						for (int i = 0; i < tamanho; i++) {
							board[randomColuna][randomLinha + i] = 'S';
							verificacao = true;
						}
						break;
					}

				case 4:
					// cima

					for (int i = 0; i < tamanho; i++) {
						if (checkIndiceIsOutOfBorder(board, randomColuna, randomLinha - i)
								|| checkAroundIndex(board, randomColuna, randomLinha - i)) {

							okShip = false;
						}

					}
					if (okShip == true) {

						for (int i = 0; i < tamanho; i++) {
							board[randomColuna][randomLinha - i] = 'S';
							verificacao = true;
						}
						break;
					}
				}

			}

		}

	}

	private static boolean checkAroundIndex(char[][] board, int coluna, int linha) {

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				if (!checkIndiceIsOutOfBorder(board, coluna - 1 + i, linha - 1 + j)
						&& (board[coluna - 1 + i][linha - 1 + j] == 'S')) {
					return true;
				}

			}

		}
		return false;
	}

	private static boolean checkIndiceIsOutOfBorder(char[][] board, int coluna, int linha) {

		if (linha < 0 || linha > board[0].length - 1 || coluna < 0 || coluna > board.length - 1) {
			return true;
		}
		return false;
	}

	private static void randomShips(char[][] board) {
		allRandomShips(board, 5);
		allRandomShips(board, 4);
		allRandomShips(board, 4);
		allRandomShips(board, 3);
		allRandomShips(board, 3);
		allRandomShips(board, 3);
		allRandomShips(board, 2);
		allRandomShips(board, 2);
		allRandomShips(board, 2);
		allRandomShips(board, 2);
	}

	private static boolean canHit(char[][] board, int coluna, int linha) {

		if ((board[coluna - 1][linha - 1] == '\u0000') || (board[coluna - 1][linha - 1] == 'S')) {
			return true;
		} else
			return false;
	}

	private static void Hit(char[][] board) {
		boolean falhou = false;
		Scanner object = new Scanner(System.in);
		while (falhou == false) {
			System.out.println("");
			System.out.print("Escolha a coluna:");

			int coluna = object.nextInt();
			System.out.println("");
			System.out.println("Escolha a linha:");
			int linha = object.nextInt();
			if (canHit(board, coluna, linha) == false) {
				System.out.println("Já tentou");

			}
			if (canHit(board, coluna, linha) && board[coluna - 1][linha - 1] == 'S') {
				board[coluna - 1][linha - 1] = 'X';
				System.out.println("Acertou!!");
				falhou = true;
			}
			if (canHit(board, coluna, linha) && board[coluna - 1][linha - 1] == '\u0000') {
				board[coluna - 1][linha - 1] = 'A';
				System.out.println("Falhou :(");
				falhou = true;
			}

		}

	}

	private static boolean victory(char[][] board) {
		int NCOLS = board.length;
		int NROWS = board[0].length;
		for (int i = 0; i < NROWS; i++) {
			for (int j = 0; j < NCOLS; j++) {
				if (board[j][i] == 'S')
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int colunas = 10;
		int linhas = 10;
		char[][] board1 = new char[colunas][linhas];
		char[][] board2 = new char[colunas][linhas];
		randomShips(board1);
		randomShips(board2);
		while ((victory(board1) == false) || (victory(board2) == false)) {
			showboard(board1);
			System.out.println("");
			showboard(board2);
			Hit(board1);
			Hit(board2);
			System.out.println("");
		}

	}

}