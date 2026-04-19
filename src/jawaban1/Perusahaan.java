package jawaban1;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

public class Perusahaan {
    private ArrayList<Karyawan> daftarKaryawan;

    public Perusahaan() {
        daftarKaryawan = new ArrayList<Karyawan>();
    }

    // Menambahkan data karyawan jika ID belum digunakan
    public boolean tambahKaryawan(Karyawan karyawanBaru) {
        if (karyawanBaru == null) {
            return false;
        }

        if (cariKaryawanById(karyawanBaru.getId()) != null) {
            return false;
        }

        daftarKaryawan.add(karyawanBaru);
        return true;
    }

    // Menghapus data karyawan berdasarkan ID
    public boolean hapusKaryawan(String id) {
        if (id == null) {
            return false;
        }

        Karyawan ditemukan = cariKaryawanById(id);
        if (ditemukan == null) {
            return false;
        }

        daftarKaryawan.remove(ditemukan);
        return true;
    }

    // Mengubah posisi karyawan jika ID ditemukan
    public boolean ubahPosisiKaryawan(String id, String posisiBaru) {
        if (id == null || posisiBaru == null || posisiBaru.trim().isEmpty()) {
            return false;
        }

        Karyawan ditemukan = cariKaryawanById(id);
        if (ditemukan == null) {
            return false;
        }

        ditemukan.setPosisi(posisiBaru);
        return true;
    }

    // Mengubah gaji karyawan jika ID ditemukan dan gaji valid
    public boolean ubahGajiKaryawan(String id, double gajiBaru) {
        if (id == null || gajiBaru < 0) {
            return false;
        }

        Karyawan ditemukan = cariKaryawanById(id);
        if (ditemukan == null) {
            return false;
        }

        ditemukan.setGaji(gajiBaru);
        return true;
    }

    public Karyawan cariKaryawanById(String id) {
        if (id == null) {
            return null;
        }

        for (int i = 0; i < daftarKaryawan.size(); i++) {
            Karyawan karyawan = daftarKaryawan.get(i);
            if (karyawan.getId().equals(id)) {
                return karyawan;
            }
        }
        return null;
    }

    public void tampilkanSemuaKaryawan() {
        if (daftarKaryawan.size() == 0) {
            System.out.println("Belum ada data karyawan.");
            return;
        }

        for (int i = 0; i < daftarKaryawan.size(); i++) {
            Karyawan karyawan = daftarKaryawan.get(i);
            System.out.println(
                "ID: " + karyawan.getId()
                    + ", Nama: " + karyawan.getNama()
                    + ", Posisi: " + karyawan.getPosisi()
                    + ", Gaji: " + formatRupiah(karyawan.getGaji())
            );
        }
    }

    // Format rupiah agar output konsisten dan mudah dibaca
    private String formatRupiah(double nominal) {
        DecimalFormatSymbols simbol = new DecimalFormatSymbols(Locale.forLanguageTag("id-ID"));
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.##", simbol);
        return "Rp" + decimalFormat.format(nominal);
    }
}
