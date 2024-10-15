package co.uptc.frw.proyectomongo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CARACTERISTICAS")
public class CharacteristicsV {
    @Id
    @Column(name = "ID_CARACTERISTICA")
    private long id;
    @Column(name = "MARCA")
    private String brand;
    @Column(name = "MODELO")
    private String model;
    @Column(name = "LINEA")
    private String line;

    public CharacteristicsV() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "CharacteristicsV{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", line='" + line + '\'' +
                '}';
    }
}
