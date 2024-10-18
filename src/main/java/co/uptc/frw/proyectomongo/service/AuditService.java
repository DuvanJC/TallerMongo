package co.uptc.frw.proyectomongo.service;

import co.uptc.frw.proyectomongo.model.Audit;
import co.uptc.frw.proyectomongo.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuditService {

    @Autowired
    private AuditRepository auditRepository;

    public void logAudit(String entity, String description) {
        Audit audit = new Audit();
        audit.setEntity(entity);
        audit.setAuditDate(new Date());
        audit.setDescription(description);
        auditRepository.save(audit);
    }
}
