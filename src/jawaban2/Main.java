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
            String tambahLagi = "n";

            System.out.println("======== Selamat Datang di ParkirChan ========\n");

            do {
                String jenisKendaraanInput;
                boolean jenisValid;
                do {
                    System.out.print("Masukkan jenis kendaraan (Motor/Mobil/Truk) : ");
                    jenisKendaraanInput = scanner.nextLine().trim();

                    jenisValid = jenisKendaraanInput.equalsIgnoreCase("Motor")
                        || jenisKendaraanInput.equalsIgnoreCase("Mobil")
                        || jenisKendaraanInput.equalsIgnoreCase("Truk");

                    if (!jenisValid) {
                        System.out.println("Jenis kendaraan tidak valid.");
                    }
                } while (!jenisValid);

                String jenisKendaraan;
                if (jenisKendaraanInput.equalsIgnoreCase("Motor")) {
                    jenisKendaraan = "Motor";
                } else if (jenisKendaraanInput.equalsIgnoreCase("Mobil")) {
                    jenisKendaraan = "Mobil";
                } else {
                    jenisKendaraan = "Truk";
                }

                Kendaraan kendaraan = new Kendaraan(jenisKendaraan);

                String tipeDurasiInput;
                boolean tipeDurasiValid;
                do {
                    System.out.print("Masukkan tipe durasi (Manual/Waktu) : ");
                    tipeDurasiInput = scanner.nextLine().trim();

                    tipeDurasiValid = tipeDurasiInput.equalsIgnoreCase("Manual")
                        || tipeDurasiInput.equalsIgnoreCase("Waktu")
                        || tipeDurasiInput.equalsIgnoreCase("Time");

                    if (!tipeDurasiValid) {
                        System.out.println("Pilihan durasi tidak valid.");
                    }
                } while (!tipeDurasiValid);

                String tipeDurasi;
                if (tipeDurasiInput.equalsIgnoreCase("Manual")) {
                    tipeDurasi = "Manual";
                } else {
                    tipeDurasi = "Waktu";
                }

                double totalBiaya;

                if (tipeDurasi.equals("Manual")) {
                    int durasiManual = 0;
                    do {
                        System.out.print("Masukkan durasi parkir (jam) : ");

                        if (scanner.hasNextInt()) {
                            durasiManual = scanner.nextInt();
                            scanner.nextLine();

                            if (durasiManual <= 0) {
                                System.out.println("Durasi harus lebih dari 0.");
                            }
                        } else {
                            System.out.println("Durasi harus berupa angka.");
                            scanner.nextLine();
                            durasiManual = 0;
                        }
                    } while (durasiManual <= 0);

                    totalBiaya = kendaraan.hitungBiayaParkir(durasiManual);
                } else {
                    int jamMasuk = -1;
                    do {
                        System.out.print("Masukkan jam masuk  : ");

                        if (scanner.hasNextInt()) {
                            jamMasuk = scanner.nextInt();
                            scanner.nextLine();

                            if (jamMasuk < 0 || jamMasuk > 23) {
                                System.out.println("Jam harus di antara 0 sampai 23.");
                            }
                        } else {
                            System.out.println("Jam harus berupa angka.");
                            scanner.nextLine();
                            jamMasuk = -1;
                        }
                    } while (jamMasuk < 0 || jamMasuk > 23);

                    int jamKeluar = -1;
                    do {
                        System.out.print("Masukkan jam keluar : ");

                        if (scanner.hasNextInt()) {
                            jamKeluar = scanner.nextInt();
                            scanner.nextLine();

                            if (jamKeluar < 0 || jamKeluar > 23) {
                                System.out.println("Jam harus di antara 0 sampai 23.");
                            } else if (jamKeluar <= jamMasuk) {
                                System.out.println("Jam keluar harus lebih besar dari jam masuk.");
                            }
                        } else {
                            System.out.println("Jam harus berupa angka.");
                            scanner.nextLine();
                            jamKeluar = -1;
                        }
                    } while (jamKeluar < 0 || jamKeluar > 23 || jamKeluar <= jamMasuk);

                    totalBiaya = kendaraan.hitungBiayaParkir(jamMasuk, jamKeluar);
                }

                if (totalBiaya < 0) {
                    System.out.println("Data parkir tidak valid. Silakan input ulang.");
                    continue;
                }

                kendaraan.tampilkanRingkasan(totalBiaya);

                totalKendaraan++;
                totalSemuaBiaya += totalBiaya;

                do {
                    System.out.print("\nTambah kendaraan lagi? (y/n): ");
                    tambahLagi = scanner.nextLine().trim();

                    if (!tambahLagi.equalsIgnoreCase("y") && !tambahLagi.equalsIgnoreCase("n")) {
                        System.out.println("Masukkan hanya y atau n.");
                    }
                } while (!tambahLagi.equalsIgnoreCase("y") && !tambahLagi.equalsIgnoreCase("n"));

                System.out.println();
            } while (tambahLagi.equalsIgnoreCase("y"));

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

    // Format rupiah agar output konsisten
    public static String formatRupiah(double nominal) {
        DecimalFormatSymbols simbol = new DecimalFormatSymbols(Locale.forLanguageTag("id-ID"));
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.##", simbol);
        return "Rp" + decimalFormat.format(nominal);
    }
}
