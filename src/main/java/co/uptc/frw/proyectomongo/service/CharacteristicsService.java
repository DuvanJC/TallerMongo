package co.uptc.frw.proyectomongo.service;

import co.uptc.frw.proyectomongo.model.CharacteristicsV;
import co.uptc.frw.proyectomongo.repository.CharacteristicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacteristicsService {

    @Autowired
    private CharacteristicsRepository characteristicsRepository;

    @Autowired
    private AuditService auditService;

    public List<CharacteristicsV> findAll() {
        try {
            List<CharacteristicsV> characteristicsList = characteristicsRepository.findAll();
            auditService.logAudit("CharacteristicsV", "Se consultaron todas las características");
            return characteristicsList;
        } catch (Exception e) {
            auditService.logAudit("CharacteristicsV", "Error al consultar todas las características: " + e.getMessage());
            throw e;
        }
    }

    public CharacteristicsV findById(long id) {
        try {
            CharacteristicsV characteristics = characteristicsRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Característica con id " + id + " no encontrada"));
            auditService.logAudit("CharacteristicsV", "Se consultó la característica con ID: " + id);
            return characteristics;
        } catch (Exception e) {
            auditService.logAudit("CharacteristicsV", "Error al consultar la característica con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public CharacteristicsV save(CharacteristicsV characteristicsV) {
        try {
            CharacteristicsV savedCharacteristics = characteristicsRepository.save(characteristicsV);
            auditService.logAudit("CharacteristicsV", "Creado: " + savedCharacteristics.toString());
            return savedCharacteristics;
        } catch (Exception e) {
            auditService.logAudit("CharacteristicsV", "Error al crear la característica: " + e.getMessage());
            throw e;
        }
    }

    public void deleteCharacteristics(long id) {
        try {
            CharacteristicsV characteristicsToDelete = findById(id);
            characteristicsRepository.deleteById(id);
            auditService.logAudit("CharacteristicsV", "Borrado: ID " + id + ", Detalles: " + characteristicsToDelete.toString());
        } catch (Exception e) {
            auditService.logAudit("CharacteristicsV", "Error al borrar la característica con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public CharacteristicsV update(CharacteristicsV newCharacteristic, long id) {
        try {
            CharacteristicsV characteristicsID = findById(id);
            characteristicsID.setBrand(newCharacteristic.getBrand());
            characteristicsID.setModel(newCharacteristic.getModel());
            characteristicsID.setLine(newCharacteristic.getLine());

            CharacteristicsV updatedCharacteristics = save(characteristicsID);
            auditService.logAudit("CharacteristicsV", "Modificado: " + updatedCharacteristics.toString());
            return updatedCharacteristics;
        } catch (Exception e) {
            auditService.logAudit("CharacteristicsV", "Error al modificar la característica con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }
}
