package jawaban2;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Kendaraan {
    private String jenisKendaraan;
    private int durasiParkir;

    // Constructor menerima jenis kendaraan
    public Kendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
        this.durasiParkir = 0;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public int getDurasiParkir() {
        return durasiParkir;
    }

    // Overloading method: hitung biaya dari input durasi manual
    public double hitungBiayaParkir(int durasiParkir) {
        if (durasiParkir <= 0) {
            return -1;
        }

        this.durasiParkir = durasiParkir;
        return hitungTotalBiaya();
    }

    // Overloading method: hitung biaya dari jam masuk dan jam keluar
    public double hitungBiayaParkir(int jamMasuk, int jamKeluar) {
        if (jamMasuk < 0 || jamMasuk > 23 || jamKeluar < 0 || jamKeluar > 23) {
            return -1;
        }

        if (jamKeluar <= jamMasuk) {
            return -1;
        }

        this.durasiParkir = jamKeluar - jamMasuk;
        return hitungTotalBiaya();
    }

    // Menghitung biaya per jam sesuai jenis kendaraan
    private double getTarifPerJam() {
        if (jenisKendaraan.equalsIgnoreCase("Motor")) {
            return 2000;
        } else if (jenisKendaraan.equalsIgnoreCase("Mobil")) {
            return 5000;
        } else if (jenisKendaraan.equalsIgnoreCase("Truk")) {
            return 10000;
        }

        return 0;
    }

    // Menghitung total biaya termasuk diskon jika lebih dari 5 jam
    private double hitungTotalBiaya() {
        double totalBiaya = getTarifPerJam() * this.durasiParkir;

        if (this.durasiParkir > 5) {
            totalBiaya = totalBiaya - (totalBiaya * 0.10);
        }

        return totalBiaya;
    }

    public void tampilkanRingkasan(double totalBiaya) {
        System.out.println("\n---- RINGKASAN PARKIR ----");
        System.out.println("Jenis Kendaraan : " + this.jenisKendaraan);
        System.out.println("Durasi Parkir   : " + this.durasiParkir + " jam");
        System.out.println("Total Biaya     : " + formatRupiah(totalBiaya));
    }

    // Format rupiah agar output konsisten dan tidak terlalu panjang
    private String formatRupiah(double nominal) {
        DecimalFormatSymbols simbol = new DecimalFormatSymbols(Locale.forLanguageTag("id-ID"));
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.##", simbol);
        return "Rp" + decimalFormat.format(nominal);
    }
}
