package co.uptc.frw.proyectomongo.controller;

import co.uptc.frw.proyectomongo.model.CharacteristicsV;
import co.uptc.frw.proyectomongo.service.CharacteristicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Characteristics")
public class CharacteristicsController {

    @Autowired
    private CharacteristicsService characteristicsService;

    @GetMapping
    public List<CharacteristicsV> getCharacteristicsV() {
        return characteristicsService.findAll();
    }

    @GetMapping("/{id}")
    public CharacteristicsV getCharacteristicsById(@PathVariable long id) {
        return characteristicsService.findById(id);
    }

    @PostMapping
    public CharacteristicsV addCharacteristicV(@RequestBody CharacteristicsV characteristicsV) {
        return characteristicsService.save(characteristicsV);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacteristicsById(@PathVariable long id) {
        characteristicsService.deleteCharacteristics(id);
    }

    @PutMapping
    public CharacteristicsV updateCharacteristics(@RequestBody CharacteristicsV characteristicsV, @RequestParam long id) {
        return characteristicsService.update(characteristicsV, id);

    }
}