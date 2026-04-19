package jawaban1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Perusahaan perusahaan = new Perusahaan();

            // Data awal agar menu tampilkan data bisa langsung dicoba
            perusahaan.tambahKaryawan(new Karyawan("002", "Denis", "Staff", 5000000));
            perusahaan.tambahKaryawan(new Karyawan("003", "Jarwo", "Staff", 4000000));

            while (true) {
                System.out.println("=== SISTEM MANAJEMEN KARYAWAN ===");
                System.out.println("1. Tambah Karyawan");
                System.out.println("2. Hapus Karyawan");
                System.out.println("3. Ubah Posisi");
                System.out.println("4. Ubah Gaji");
                System.out.println("5. Tampilkan Semua Karyawan");
                System.out.println("6. Keluar");

                System.out.print("Masukkan pilihan: ");
                String pilihan = scanner.nextLine().trim();

                if (pilihan.equals("1")) {
                    String id = inputTidakBolehKosong(scanner, "Masukkan ID: ");
                    String nama = inputTidakBolehKosong(scanner, "Masukkan Nama: ");
                    String posisi = inputTidakBolehKosong(scanner, "Masukkan Posisi: ");
                    double gaji = inputGajiValid(scanner, "Masukkan Gaji: ");

                    Karyawan karyawanBaru = new Karyawan(id, nama, posisi, gaji);
                    if (perusahaan.tambahKaryawan(karyawanBaru)) {
                        System.out.println("Karyawan berhasil ditambahkan.");
                    } else {
                        System.out.println("Gagal menambahkan karyawan. ID sudah digunakan.");
                    }
                } else if (pilihan.equals("2")) {
                    String id = inputTidakBolehKosong(scanner, "Masukkan ID karyawan yang akan dihapus: ");
                    if (perusahaan.hapusKaryawan(id)) {
                        System.out.println("Karyawan berhasil dihapus.");
                    } else {
                        System.out.println("Karyawan dengan ID tersebut tidak ditemukan.");
                    }
                } else if (pilihan.equals("3")) {
                    String id = inputTidakBolehKosong(scanner, "Masukkan ID karyawan: ");
                    String posisiBaru = inputTidakBolehKosong(scanner, "Masukkan posisi baru: ");

                    if (perusahaan.ubahPosisiKaryawan(id, posisiBaru)) {
                        System.out.println("Posisi berhasil diubah.");
                    } else {
                        System.out.println("Karyawan dengan ID tersebut tidak ditemukan.");
                    }
                } else if (pilihan.equals("4")) {
                    String id = inputTidakBolehKosong(scanner, "Masukkan ID karyawan: ");
                    double gajiBaru = inputGajiValid(scanner, "Masukkan gaji baru: ");

                    if (perusahaan.ubahGajiKaryawan(id, gajiBaru)) {
                        System.out.println("Gaji berhasil diubah.");
                    } else {
                        System.out.println("Gagal mengubah gaji. Pastikan ID ada dan gaji tidak negatif.");
                    }
                } else if (pilihan.equals("5")) {
                    perusahaan.tampilkanSemuaKaryawan();
                } else if (pilihan.equals("6")) {
                    System.out.println("Terima kasih telah menggunakan program.");
                    break;
                } else {
                    System.out.println("Pilihan tidak valid.");
                }

                System.out.println();
            }
        } catch (java.util.NoSuchElementException noSuchElementException) {
            System.out.println("Input dihentikan. Program selesai.");
        } catch (Exception exception) {
            System.out.println("Terjadi kesalahan saat menjalankan program: " + exception.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Membaca input String sampai pengguna mengisi nilai yang tidak kosong
    public static String inputTidakBolehKosong(Scanner scanner, String pesan) {
        while (true) {
            System.out.print(pesan);
            String input = scanner.nextLine();

            if (input != null && !input.trim().isEmpty()) {
                return input.trim();
            }

            System.out.println("Input tidak boleh kosong.");
        }
    }

    // Membaca input gaji dengan validasi harus angka dan tidak negatif
    public static double inputGajiValid(Scanner scanner, String pesan) {
        while (true) {
            System.out.print(pesan);
            String input = scanner.nextLine();

            try {
                double gaji = Double.parseDouble(input);
                if (gaji < 0) {
                    System.out.println("Gaji tidak boleh negatif.");
                } else {
                    return gaji;
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Input gaji harus berupa angka.");
            }
        }
    }
}
