package co.uptc.frw.proyectomongo.service;

import co.uptc.frw.proyectomongo.model.CharacteristicsV;
import co.uptc.frw.proyectomongo.repository.CharacteristicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;


@Service
public class CharacteristicsService {
   @Autowired
    private CharacteristicsRepository characteristicsRepository;

    @Autowired
    private AuditService auditService;

    public List<CharacteristicsV> findAll() {
        return characteristicsRepository.findAll();
    }

    public CharacteristicsV findById(long id) {
        return characteristicsRepository.findById(id).orElse(null);
    }

    public CharacteristicsV save(CharacteristicsV characteristicsV) {

        CharacteristicsV savedCharacteristics = characteristicsRepository.save(characteristicsV);
        auditService.logAudit("CharacteristicsV", "Creado: " + savedCharacteristics.toString());
        return characteristicsRepository.save(characteristicsV);
    }

    public void deleteCharacteristics(long id) {
        CharacteristicsV characteristicsToDelete = findById(id);
        if (characteristicsToDelete != null) {
            characteristicsRepository.deleteById(id);
            auditService.logAudit("CharacteristicsV", "Borrado: ID " + id + ", Detalles: " + characteristicsToDelete.toString());
        }
    }

    public CharacteristicsV update(CharacteristicsV newCharacteristic,long id) {
        CharacteristicsV characteristicsID = findById(id);
        if (characteristicsID != null) {
            characteristicsID.setBrand(newCharacteristic.getBrand());
            characteristicsID.setModel (newCharacteristic.getModel());
            characteristicsID.setLine (newCharacteristic.getLine());
            CharacteristicsV updatedCharacteristics = save(characteristicsID);
            auditService.logAudit("CharacteristicsV", "Modificado: " + updatedCharacteristics.toString());
            return save(characteristicsID);
        }
        throw new RuntimeException("Characteristics not found");
    }
}
