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
        List<CharacteristicsV> characteristicsList = characteristicsRepository.findAll();
        auditService.logAudit("CharacteristicsV", "Se consultaron todas las características");
        return characteristicsList;
    }

    public CharacteristicsV findById(long id) {
        CharacteristicsV characteristics = characteristicsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Característica con id " + id + " no encontrada"));
        auditService.logAudit("CharacteristicsV", "Se consultó la característica con ID: " + id);
        return characteristics;
    }

    public CharacteristicsV save(CharacteristicsV characteristicsV) {
        CharacteristicsV savedCharacteristics = characteristicsRepository.save(characteristicsV);
        auditService.logAudit("CharacteristicsV", "Creado: " + savedCharacteristics.toString());
        return savedCharacteristics;
    }

    public void deleteCharacteristics(long id) {
        CharacteristicsV characteristicsToDelete = findById(id);
        characteristicsRepository.deleteById(id);
        auditService.logAudit("CharacteristicsV", "Borrado: ID " + id + ", Detalles: " + characteristicsToDelete.toString());
    }

    public CharacteristicsV update(CharacteristicsV newCharacteristic, long id) {
        CharacteristicsV characteristicsID = findById(id);
        characteristicsID.setBrand(newCharacteristic.getBrand());
        characteristicsID.setModel(newCharacteristic.getModel());
        characteristicsID.setLine(newCharacteristic.getLine());

        CharacteristicsV updatedCharacteristics = save(characteristicsID);
        auditService.logAudit("CharacteristicsV", "Modificado: " + updatedCharacteristics.toString());
        return updatedCharacteristics;
    }
}
