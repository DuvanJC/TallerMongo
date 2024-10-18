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
        List<AdditionalOption> options = additionalOptionRepository.findAll();
        auditService.logAudit("AdditionalOption", "Se consultaron todas las opciones adicionales");
        return options;
    }

    public AdditionalOption finById(long id) {
        AdditionalOption option = additionalOptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Opción adicional con id " + id + " no encontrada"));
        auditService.logAudit("AdditionalOption", "Se consultó la opción adicional con ID: " + id);
        return option;
    }

    public AdditionalOption saveAdditionalOption(AdditionalOption additionalOption) {
        AdditionalOption savedOption = additionalOptionRepository.save(additionalOption);
        auditService.logAudit("AdditionalOption", "Creado: " + savedOption.toString());
        return savedOption;
    }

    public void deleteAdditionalOption(long id) {
        AdditionalOption additionalOptionToDelete = finById(id);
        additionalOptionRepository.deleteById(id);
        auditService.logAudit("AdditionalOption", "Eliminado: ID " + id + ", Detalles: " + additionalOptionToDelete.toString());
    }

    public AdditionalOption updateAdditionalOption(AdditionalOption newAdditionalOption, long id) {
        AdditionalOption additionalOptionId = finById(id);
        additionalOptionId.setName(newAdditionalOption.getName());
        additionalOptionId.setDescription(newAdditionalOption.getDescription());
        additionalOptionId.setPrice(newAdditionalOption.getPrice());

        AdditionalOption updatedOption = additionalOptionRepository.save(additionalOptionId);
        auditService.logAudit("AdditionalOption", "Modificado: " + updatedOption.toString());
        return updatedOption;
    }
}
