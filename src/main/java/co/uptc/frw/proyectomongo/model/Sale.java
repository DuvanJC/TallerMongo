package co.uptc.frw.proyectomongo.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "VENTAS")
public class Sale {
    public Sale() {}

    @Id
    @Column(name = "ID_VENTA")
    private Long id;

    @Column(name = "FECHA_VENTA", nullable = false)
    private Date date;

    @Column(name = "PRECIO_TOTAL", nullable = false)
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "ID_VEHICULO", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "ID_PERSONA_VENDE", nullable = false)
    private Person seller;

    @ManyToOne
    @JoinColumn(name = "ID_PERSONA_COMPRA", nullable = false)
    private Person buyer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Person getSeller() {
        return seller;
    }

    public void setSeller(Person seller) {
        this.seller = seller;
    }

    public Person getBuyer() {
        return buyer;
    }

    public void setBuyer(Person buyer) {
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", date=" + date +
                ", totalPrice=" + totalPrice +
                '}';
    }
}