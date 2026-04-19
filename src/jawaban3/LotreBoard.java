package jawaban3;

import java.util.Random;

public class LotreBoard {
    private char[][] board;
    private boolean[][] revealed;
    private int[][] data;
    private boolean bomDitemukan;
    private int jumlahAmanTerbuka;

    private final int BARIS = 4;
    private final int KOLOM = 5;
    private final int JUMLAH_BOM = 2;
    private final int TOTAL_AMAN = (BARIS * KOLOM) - JUMLAH_BOM;

    public LotreBoard() {
        board = new char[BARIS][KOLOM];
        revealed = new boolean[BARIS][KOLOM];
        data = new int[BARIS][KOLOM];
        bomDitemukan = false;
        jumlahAmanTerbuka = 0;
        generateBoard();
    }

    // Membuat papan awal dan menaruh 2 bom secara acak
    public void generateBoard() {
        for (int i = 0; i < BARIS; i++) {
            for (int j = 0; j < KOLOM; j++) {
                board[i][j] = '*';
                revealed[i][j] = false;
                data[i][j] = 0;
            }
        }

        Random random = new Random();
        int bomTerpasang = 0;

        while (bomTerpasang < JUMLAH_BOM) {
            int row = random.nextInt(BARIS);
            int col = random.nextInt(KOLOM);

            if (data[row][col] == 0) {
                data[row][col] = 1;
                bomTerpasang++;
            }
        }
    }

    public void displayBoard() {
        for (int i = 0; i < BARIS; i++) {
            for (int j = 0; j < KOLOM; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Return false jika pemain mengenai bom, true jika kotak aman
    public boolean guess(int row, int col) {
        if (!isValidPosisi(row, col)) {
            System.out.println("Posisi tidak valid. Baris 0-3 dan kolom 0-4.");
            return true;
        }

        if (revealed[row][col]) {
            System.out.println("Kotak telah dibuka sebelumnya!");
            if (board[row][col] == 'X') {
                return false;
            }
            return true;
        }

        revealed[row][col] = true;

        if (data[row][col] == 1) {
            board[row][col] = 'X';
            bomDitemukan = true;
            System.out.println("BOOM! Anda menemukan bom! Permainan berakhir.");
            return false;
        }

        board[row][col] = 'O';
        jumlahAmanTerbuka++;
        System.out.println("Kotak Aman");
        return true;
    }

    public boolean isGameOver() {
        return bomDitemukan || jumlahAmanTerbuka == TOTAL_AMAN;
    }

    public boolean isMenang() {
        return !bomDitemukan && jumlahAmanTerbuka == TOTAL_AMAN;
    }

    public boolean isValidPosisi(int row, int col) {
        return row >= 0 && row < BARIS && col >= 0 && col < KOLOM;
    }
}
