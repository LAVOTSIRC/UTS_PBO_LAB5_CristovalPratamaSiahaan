package jawaban1;

public class Karyawan {
    private String id;
    private String nama;
    private String posisi;
    private double gaji;

    // Constructor untuk membuat objek karyawan baru
    public Karyawan(String id, String nama, String posisi, double gaji) {
        this.id = id;
        this.nama = nama;
        this.posisi = posisi;
        setGaji(gaji);
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public double getGaji() {
        return gaji;
    }

    public void setGaji(double gaji) {
        if (gaji < 0) {
            this.gaji = 0;
        } else {
            this.gaji = gaji;
        }
    }
}
