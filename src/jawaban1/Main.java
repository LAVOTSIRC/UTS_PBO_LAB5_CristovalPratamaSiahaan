package jawaban1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Perusahaan perusahaan = new Perusahaan();

        boolean programBerjalan = true;

        try {
            do {
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
                    String id;
                    do {
                        System.out.print("Masukkan ID: ");
                        id = scanner.nextLine().trim();

                        if (id.isEmpty()) {
                            System.out.println("Input tidak boleh kosong.");
                        }
                    } while (id.isEmpty());

                    String nama;
                    do {
                        System.out.print("Masukkan Nama: ");
                        nama = scanner.nextLine().trim();

                        if (nama.isEmpty()) {
                            System.out.println("Input tidak boleh kosong.");
                        }
                    } while (nama.isEmpty());

                    String posisi;
                    do {
                        System.out.print("Masukkan Posisi: ");
                        posisi = scanner.nextLine().trim();

                        if (posisi.isEmpty()) {
                            System.out.println("Input tidak boleh kosong.");
                        }
                    } while (posisi.isEmpty());

                    double gaji = -1;
                    do {
                        System.out.print("Masukkan Gaji: ");

                        if (scanner.hasNextDouble()) {
                            gaji = scanner.nextDouble();
                            scanner.nextLine();

                            if (gaji < 0) {
                                System.out.println("Gaji tidak boleh negatif.");
                            }
                        } else {
                            System.out.println("Input gaji harus berupa angka.");
                            scanner.nextLine();
                            gaji = -1;
                        }
                    } while (gaji < 0);

                    Karyawan karyawanBaru = new Karyawan(id, nama, posisi, gaji);
                    if (perusahaan.tambahKaryawan(karyawanBaru)) {
                        System.out.println("Karyawan berhasil ditambahkan.");
                    } else {
                        System.out.println("Gagal menambahkan karyawan. ID sudah digunakan.");
                    }
                } else if (pilihan.equals("2")) {
                    String id;
                    do {
                        System.out.print("Masukkan ID karyawan yang akan dihapus: ");
                        id = scanner.nextLine().trim();

                        if (id.isEmpty()) {
                            System.out.println("Input tidak boleh kosong.");
                        }
                    } while (id.isEmpty());

                    if (perusahaan.hapusKaryawan(id)) {
                        System.out.println("Karyawan berhasil dihapus.");
                    } else {
                        System.out.println("Karyawan dengan ID tersebut tidak ditemukan.");
                    }
                } else if (pilihan.equals("3")) {
                    String id;
                    do {
                        System.out.print("Masukkan ID karyawan: ");
                        id = scanner.nextLine().trim();

                        if (id.isEmpty()) {
                            System.out.println("Input tidak boleh kosong.");
                        }
                    } while (id.isEmpty());

                    String posisiBaru;
                    do {
                        System.out.print("Masukkan posisi baru: ");
                        posisiBaru = scanner.nextLine().trim();

                        if (posisiBaru.isEmpty()) {
                            System.out.println("Input tidak boleh kosong.");
                        }
                    } while (posisiBaru.isEmpty());

                    if (perusahaan.ubahPosisiKaryawan(id, posisiBaru)) {
                        System.out.println("Posisi berhasil diubah.");
                    } else {
                        System.out.println("Karyawan dengan ID tersebut tidak ditemukan.");
                    }
                } else if (pilihan.equals("4")) {
                    String id;
                    do {
                        System.out.print("Masukkan ID karyawan: ");
                        id = scanner.nextLine().trim();

                        if (id.isEmpty()) {
                            System.out.println("Input tidak boleh kosong.");
                        }
                    } while (id.isEmpty());

                    double gajiBaru = -1;
                    do {
                        System.out.print("Masukkan gaji baru: ");

                        if (scanner.hasNextDouble()) {
                            gajiBaru = scanner.nextDouble();
                            scanner.nextLine();

                            if (gajiBaru < 0) {
                                System.out.println("Gaji tidak boleh negatif.");
                            }
                        } else {
                            System.out.println("Input gaji harus berupa angka.");
                            scanner.nextLine();
                            gajiBaru = -1;
                        }
                    } while (gajiBaru < 0);

                    if (perusahaan.ubahGajiKaryawan(id, gajiBaru)) {
                        System.out.println("Gaji berhasil diubah.");
                    } else {
                        System.out.println("Gagal mengubah gaji. Pastikan ID ada dan gaji tidak negatif.");
                    }
                } else if (pilihan.equals("5")) {
                    perusahaan.tampilkanSemuaKaryawan();
                } else if (pilihan.equals("6")) {
                    System.out.println("Terima kasih telah menggunakan program.");
                    programBerjalan = false;
                } else {
                    System.out.println("Pilihan tidak valid.");
                }

                System.out.println();
            } while (programBerjalan);
        } catch (java.util.NoSuchElementException noSuchElementException) {
            System.out.println("Input dihentikan. Program selesai.");
        } catch (Exception exception) {
            System.out.println("Terjadi kesalahan saat menjalankan program: " + exception.getMessage());
        } finally {
            scanner.close();
        }
    }
}
