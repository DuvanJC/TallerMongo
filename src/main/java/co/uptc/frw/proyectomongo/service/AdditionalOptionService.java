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
        try {
            List<AdditionalOption> options = additionalOptionRepository.findAll();
            auditService.logAudit("AdditionalOption", "Se consultaron todas las opciones adicionales");
            return options;
        } catch (Exception e) {
            auditService.logAudit("AdditionalOption", "Error al consultar todas las opciones adicionales: " + e.getMessage());
            throw e;
        }
    }

    public AdditionalOption finById(long id) {
        try {
            AdditionalOption option = additionalOptionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Opción adicional con id " + id + " no encontrada"));
            auditService.logAudit("AdditionalOption", "Se consultó la opción adicional con ID: " + id);
            return option;
        } catch (Exception e) {
            auditService.logAudit("AdditionalOption", "Error al consultar la opción adicional con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public AdditionalOption saveAdditionalOption(AdditionalOption additionalOption) {
        try {
            AdditionalOption savedOption = additionalOptionRepository.save(additionalOption);
            auditService.logAudit("AdditionalOption", "Creado: " + savedOption.toString());
            return savedOption;
        } catch (Exception e) {
            auditService.logAudit("AdditionalOption", "Error al crear la opción adicional: " + e.getMessage());
            throw e;
        }
    }

    public void deleteAdditionalOption(long id) {
        try {
            AdditionalOption additionalOptionToDelete = finById(id);
            additionalOptionRepository.deleteById(id);
            auditService.logAudit("AdditionalOption", "Eliminado: ID " + id + ", Detalles: " + additionalOptionToDelete.toString());
        } catch (Exception e) {
            auditService.logAudit("AdditionalOption", "Error al eliminar la opción adicional con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public AdditionalOption updateAdditionalOption(AdditionalOption newAdditionalOption, long id) {
        try {
            AdditionalOption additionalOptionId = finById(id);
            additionalOptionId.setName(newAdditionalOption.getName());
            additionalOptionId.setDescription(newAdditionalOption.getDescription());
            additionalOptionId.setPrice(newAdditionalOption.getPrice());

            AdditionalOption updatedOption = additionalOptionRepository.save(additionalOptionId);
            auditService.logAudit("AdditionalOption", "Modificado: " + updatedOption.toString());
            return updatedOption;
        } catch (Exception e) {
            auditService.logAudit("AdditionalOption", "Error al modificar la opción adicional con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }
}
