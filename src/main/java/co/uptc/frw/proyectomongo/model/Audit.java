package co.uptc.frw.proyectomongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "auditoria")
public class Audit {
    @Id
    private String id; // El ID de la auditoría
    private String entity; // Nombre de la entidad que se audita
    private Date auditDate; // Fecha de la auditoría
    private String description; // Descripción de la auditoría

    public Audit() {}

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Audit{" +
                "id='" + id + '\'' +
                ", entity='" + entity + '\'' +
                ", auditDate=" + auditDate +
                ", description='" + description + '\'' +
                '}';
    }
}
