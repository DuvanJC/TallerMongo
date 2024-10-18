package co.uptc.frw.proyectomongo.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "VEHICULO_USADO")
public class UsedVehicle {
    public UsedVehicle() {}

    @Id
    @Column(name = "ID_VEHICULO_USADO")
    private Long id;

    @Column(name = "MARCA_USADO")
    private String brand;

    @Column(name = "MODELO_USADO")
    private String model;

    @Column(name = "MATRICULA_USADO")
    private String registration;

    @Column(name = "PRECIO_TASACION")
    private double appraisalPrice;

    @Column(name = "FECHA_CESION")
    private Date dateTransfered;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_CESION")
    private Person personTransfered;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public double getAppraisalPrice() {
        return appraisalPrice;
    }

    public void setAppraisalPrice(double appraisalPrice) {
        this.appraisalPrice = appraisalPrice;
    }

    public Date getDateTransfered() {
        return dateTransfered;
    }

    public void setDateTransfered(Date dateTransfered) {
        this.dateTransfered = dateTransfered;
    }

    public Person getPersonTransfered() {
        return personTransfered;
    }

    public void setPersonTransfered(Person personTransfered) {
        this.personTransfered = personTransfered;
    }

    @Override
    public String toString() {
        return "UsedVehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", registration='" + registration + '\'' +
                ", appraisalPrice=" + appraisalPrice +
                ", dateTransfered=" + dateTransfered +
                '}';
    }
}