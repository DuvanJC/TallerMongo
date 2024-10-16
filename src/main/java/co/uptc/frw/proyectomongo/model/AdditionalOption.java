package co.uptc.frw.proyectomongo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "OPCION_ADICIONAL")
public class AdditionalOption {
    @Id
    @Column(name = "ID_OP_ADICIONAL")
    private long id;
    @Column(name = "NOMBRE")
    private String name;
    @Column(name = "DESCRIPCION")
    private String description;
    @Column(name = "PRECIO")
    private double price;
    @JsonIgnore
    @ManyToMany(mappedBy = "additionalOptions")
    private List<Vehicle> vehicles;
    public AdditionalOption() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "AdditionalOption{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
