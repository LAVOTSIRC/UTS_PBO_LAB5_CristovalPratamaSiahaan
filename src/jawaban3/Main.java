package jawaban3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            LotreBoard lotreBoard = new LotreBoard();

            System.out.println("Selamat datang di E-Lottery Gosok");
            lotreBoard.displayBoard();

            while (!lotreBoard.isGameOver()) {
                if (!scanner.hasNextLine()) {
                    System.out.println("Input dihentikan. Permainan selesai.");
                    break;
                }

                int[] tebakan = inputTebakan(scanner);
                int row = tebakan[0];
                int col = tebakan[1];

                if (!lotreBoard.isValidPosisi(row, col)) {
                    System.out.println("Posisi tidak valid. Baris 0-3 dan kolom 0-4.");
                    continue;
                }

                lotreBoard.guess(row, col);
                lotreBoard.displayBoard();
            }

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

    // Membaca input baris dan kolom dari satu baris teks
    public static int[] inputTebakan(Scanner scanner) {
        while (true) {
            System.out.print("Masukkan tebakan anda (baris dan kolom) : ");
            String input = scanner.nextLine();
            String[] bagian = input.trim().split("\\s+");

            if (bagian.length != 2) {
                System.out.println("Input harus 2 angka. Contoh: 2 4");
                continue;
            }

            try {
                int row = Integer.parseInt(bagian[0]);
                int col = Integer.parseInt(bagian[1]);
                return new int[]{row, col};
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Input harus berupa angka.");
            }
        }
    }
}
