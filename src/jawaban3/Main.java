package jawaban3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            LotreBoard lotreBoard = new LotreBoard();
            boolean permainanBerjalan;

            System.out.println("Selamat datang di E-Lottery Gosok");
            lotreBoard.displayBoard();

            do {
                int row = -1;
                int col = -1;
                boolean inputValid;

                do {
                    inputValid = true;
                    System.out.print("Masukkan tebakan anda (baris dan kolom) : ");

                    if (scanner.hasNextInt()) {
                        row = scanner.nextInt();

                        if (scanner.hasNextInt()) {
                            col = scanner.nextInt();
                        } else {
                            inputValid = false;
                            System.out.println("Input harus 2 angka. Contoh: 2 4");
                        }
                    } else {
                        inputValid = false;
                        System.out.println("Input harus 2 angka. Contoh: 2 4");
                    }

                    scanner.nextLine();

                    if (inputValid && !lotreBoard.isValidPosisi(row, col)) {
                        inputValid = false;
                        System.out.println("Posisi tidak valid. Baris 0-3 dan kolom 0-4.");
                    }
                } while (!inputValid);

                lotreBoard.guess(row, col);
                lotreBoard.displayBoard();
                permainanBerjalan = !lotreBoard.isGameOver();
            } while (permainanBerjalan);

            if (lotreBoard.isMenang()) {
                System.out.println("Selamat, anda menang!");
            }
        } catch (java.util.NoSuchElementException noSuchElementException) {
            System.out.println("Input dihentikan. Permainan selesai.");
        } catch (Exception exception) {
            System.out.println("Terjadi kesalahan saat menjalankan permainan: " + exception.getMessage());
        } finally {
            scanner.close();
        }
    }
}
