package jawaban2;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            int totalKendaraan = 0;
            double totalSemuaBiaya = 0;

            System.out.println("======== Selamat Datang di ParkirChan ========\n");

            while (true) {
                String jenisKendaraan = inputJenisKendaraanValid(scanner);
                Kendaraan kendaraan = new Kendaraan(jenisKendaraan);

                String tipeDurasi = inputTipeDurasiValid(scanner);
                double totalBiaya;

                if (tipeDurasi.equalsIgnoreCase("Manual")) {
                    int durasiManual = inputDurasiManualValid(scanner);
                    totalBiaya = kendaraan.hitungBiayaParkir(durasiManual);
                } else {
                    int jamMasuk = inputJamValid(scanner, "Masukkan jam masuk  : ");
                    int jamKeluar = inputJamKeluarValid(scanner, jamMasuk);
                    totalBiaya = kendaraan.hitungBiayaParkir(jamMasuk, jamKeluar);
                }

                if (totalBiaya < 0) {
                    System.out.println("Data parkir tidak valid. Silakan input ulang.");
                    continue;
                }

                kendaraan.tampilkanRingkasan(totalBiaya);

                totalKendaraan++;
                totalSemuaBiaya += totalBiaya;

                if (!lanjutInput(scanner)) {
                    break;
                }

                System.out.println();
            }

            System.out.println("\n======== LAPORAN AKHIR ========");
            System.out.println("Total Kendaraan      : " + totalKendaraan);
            System.out.println("Total Biaya Parkir   : " + formatRupiah(totalSemuaBiaya));
            System.out.println("Terima kasih.....");
        } catch (java.util.NoSuchElementException noSuchElementException) {
            System.out.println("Input dihentikan. Program selesai.");
        } catch (Exception exception) {
            System.out.println("Terjadi kesalahan saat menjalankan program: " + exception.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Validasi agar jenis kendaraan hanya motor, mobil, atau truk
    public static String inputJenisKendaraanValid(Scanner scanner) {
        while (true) {
            System.out.print("Masukkan jenis kendaraan (Motor/Mobil/Truk) : ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("Motor") || input.equalsIgnoreCase("Mobil") || input.equalsIgnoreCase("Truk")) {
                if (input.equalsIgnoreCase("Motor")) {
                    return "Motor";
                } else if (input.equalsIgnoreCase("Mobil")) {
                    return "Mobil";
                } else {
                    return "Truk";
                }
            }

            System.out.println("Jenis kendaraan tidak valid.");
        }
    }

    // Validasi cara input durasi: manual atau time
    public static String inputTipeDurasiValid(Scanner scanner) {
        while (true) {
            System.out.print("Masukkan tipe durasi (Manual/Waktu) : ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("Manual")) {
                return "Manual";
            }

            if (input.equalsIgnoreCase("Waktu") || input.equalsIgnoreCase("Time")) {
                return "Waktu";
            }

            System.out.println("Pilihan durasi tidak valid.");
        }
    }

    // Validasi durasi manual harus angka dan > 0
    public static int inputDurasiManualValid(Scanner scanner) {
        while (true) {
            System.out.print("Masukkan durasi parkir (jam) : ");
            String input = scanner.nextLine();

            try {
                int durasi = Integer.parseInt(input);
                if (durasi > 0) {
                    return durasi;
                }
                System.out.println("Durasi harus lebih dari 0.");
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Durasi harus berupa angka.");
            }
        }
    }

    // Validasi jam harus 0 sampai 23
    public static int inputJamValid(Scanner scanner, String pesan) {
        while (true) {
            System.out.print(pesan);
            String input = scanner.nextLine();

            try {
                int jam = Integer.parseInt(input);
                if (jam >= 0 && jam <= 23) {
                    return jam;
                }
                System.out.println("Jam harus di antara 0 sampai 23.");
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Jam harus berupa angka.");
            }
        }
    }

    // Jam keluar harus lebih besar dari jam masuk
    public static int inputJamKeluarValid(Scanner scanner, int jamMasuk) {
        while (true) {
            int jamKeluar = inputJamValid(scanner, "Masukkan jam keluar : ");
            if (jamKeluar > jamMasuk) {
                return jamKeluar;
            }

            System.out.println("Jam keluar harus lebih besar dari jam masuk.");
        }
    }

    public static boolean lanjutInput(Scanner scanner) {
        while (true) {
            System.out.print("\nTambah kendaraan lagi? (y/n): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("y")) {
                return true;
            } else if (input.equalsIgnoreCase("n")) {
                return false;
            }

            System.out.println("Masukkan hanya y atau n.");
        }
    }

    // Format rupiah agar output konsisten
    public static String formatRupiah(double nominal) {
        DecimalFormatSymbols simbol = new DecimalFormatSymbols(Locale.forLanguageTag("id-ID"));
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.##", simbol);
        return "Rp" + decimalFormat.format(nominal);
    }
}
