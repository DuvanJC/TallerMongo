package co.uptc.frw.proyectomongo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

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
    @JsonIgnore
    @OneToMany(mappedBy = "characteristics")
    private List<Vehicle> vehicles;

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

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
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
