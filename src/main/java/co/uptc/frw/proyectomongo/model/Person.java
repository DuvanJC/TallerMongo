package co.uptc.frw.proyectomongo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PERSONAS")
public class Person {
    public Person() {}

    @Id
    @Column(name = "ID_PERSONA")
    private long id;
    @Column(name = "NUMERO_DOCUMENTO")
    private String documentId;
    @Column(name = "NOMBRE")
    private String name;
    @Column(name = "TELEFONO")
    private String phoneNumber;
    @Column(name = "DIRECICON")
    private String address;
    @Column(name = "TIPO")
    private String type;

    @OneToMany(mappedBy = "seller")
    private List<Sale> sales;

    @OneToMany(mappedBy = "buyer")
    private List<Sale> buys;

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
