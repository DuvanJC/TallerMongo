package co.uptc.frw.proyectomongo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "VEHICULOS")
public class Vehicle {
    @Id
    @Column(name = "ID_VEHICULO")
    private long id;
    @Column(name = "CILINDRADA")
    private long displacement;//cilindrada
    @Column(name = "MATRICULA")
    private String registration;
    @Column(name = "PRECIO")
    private double price;
    @Column(name = "ID_CARACTERISTICA",insertable = false, updatable = false)
    private long idCharacteristic;
    @ManyToOne
    @JoinColumn(name = "ID_CARACTERISTICA")
    private CharacteristicsV characteristics;
    @ManyToMany
    @JoinTable(name = "VEHICULO_OPCION",
    joinColumns = @JoinColumn(name = "ID_VEHICULO"),
    inverseJoinColumns = @JoinColumn(name = "ID_OP_ADICIONAL"))
    private List<AdditionalOption> additionalOptions;

    public Vehicle() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDisplacement() {
        return displacement;
    }

    public void setDisplacement(long displacement) {
        this.displacement = displacement;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getIdCharacteristic() {
        return idCharacteristic;
    }

    public void setIdCharacteristic(long idCharacteristic) {
        this.idCharacteristic = idCharacteristic;
    }

    public CharacteristicsV getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(CharacteristicsV characteristics) {
        this.characteristics = characteristics;
    }

    public List<AdditionalOption> getAdditionalOptions() {
        return additionalOptions;
    }

    public void setAdditionalOptions(List<AdditionalOption> additionalOptions) {
        this.additionalOptions = additionalOptions;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", displacement=" + displacement +
                ", registration='" + registration + '\'' +
                ", price=" + price +
                ", idCharacteristic=" + idCharacteristic +
                '}';
    }
}
