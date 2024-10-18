package co.uptc.frw.proyectomongo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PERSONAS")
public class Person {
    public Person() {}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personas_seq")
    @SequenceGenerator(name = "personas_seq", sequenceName = "personas_seq", allocationSize = 1)
    @Column(name = "ID_PERSONA")
    private long id;
    @Column(name = "NUMERO_DOCUMENTO", nullable = false)
    private String documentId;
    @Column(name = "NOMBRE")
    private String name;
    @Column(name = "TELEFONO")
    private String phoneNumber;
    @Column(name = "DIRECCION")
    private String address;
    @Column(name = "TIPO")
    private String type;

    @JsonIgnore
    @OneToMany(mappedBy = "seller")
    private List<Sale> sales;

    @JsonIgnore
    @OneToMany(mappedBy = "buyer")
    private List<Sale> buys;

    @JsonIgnore
    @OneToMany(mappedBy = "personTransfered")
    private List<UsedVehicle> usedVehicles;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public List<Sale> getBuys() {
        return buys;
    }

    public void setBuys(List<Sale> buys) {
        this.buys = buys;
    }

    public List<UsedVehicle> getUsedVehicles() {
        return usedVehicles;
    }

    public void setUsedVehicles(List<UsedVehicle> usedVehicles) {
        this.usedVehicles = usedVehicles;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", documentId='" + documentId + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
