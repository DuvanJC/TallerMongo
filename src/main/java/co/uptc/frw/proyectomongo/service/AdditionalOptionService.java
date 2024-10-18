package co.uptc.frw.proyectomongo.service;

import co.uptc.frw.proyectomongo.model.AdditionalOption;
import co.uptc.frw.proyectomongo.repository.AdditionalOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdditionalOptionService {
    @Autowired
    private AdditionalOptionRepository additionalOptionRepository;

    @Autowired
    private AuditService auditService;

    public List<AdditionalOption> finAll() {
        return additionalOptionRepository.findAll();
    }
    public AdditionalOption finById(long id) {
        return additionalOptionRepository.findById(id).orElse(null);
    }

    public AdditionalOption saveAdditionalOption(AdditionalOption additionalOption) {
        AdditionalOption savedOption = additionalOptionRepository.save(additionalOption);
        auditService.logAudit("AdditionalOption", "Creado: " + savedOption.toString());
        return savedOption;
    }

    public void deleteAdditionalOption(long id) {
        AdditionalOption additionalOptionToDelete = finById(id);
        if (additionalOptionToDelete != null) {
            additionalOptionRepository.deleteById(id);
            auditService.logAudit("AdditionalOption", "Eliminado: ID " + id + ", Detalles: " + additionalOptionToDelete.toString());
        }
    }

    public AdditionalOption updateAdditionalOption(AdditionalOption newAdditionalOption, long id) {
        AdditionalOption additionalOptionId = finById(id);
        if (additionalOptionId != null) {
            additionalOptionId.setName(newAdditionalOption.getName());
            additionalOptionId.setDescription(newAdditionalOption.getDescription());
            additionalOptionId.setPrice(newAdditionalOption.getPrice());
            AdditionalOption updatedOption = saveAdditionalOption(additionalOptionId);
            auditService.logAudit("AdditionalOption", "Modificado: " + updatedOption.toString());
            return updatedOption;
        }
        throw new RuntimeException("Additional option not found");
    }
}
