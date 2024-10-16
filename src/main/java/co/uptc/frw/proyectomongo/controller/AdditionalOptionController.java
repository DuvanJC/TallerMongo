package co.uptc.frw.proyectomongo.controller;

import co.uptc.frw.proyectomongo.model.AdditionalOption;
import co.uptc.frw.proyectomongo.service.AdditionalOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/AdditionalOptions")
public class AdditionalOptionController {
    @Autowired
    private AdditionalOptionService additionalOptionService;
    @GetMapping
    public List<AdditionalOption> getAllAdditionalOptions() {
        return additionalOptionService.finAll();
    }
    @GetMapping("/{id}")
    public AdditionalOption getAdditionalOptionById(@PathVariable long id) {
        return additionalOptionService.finById(id);
    }
    @PostMapping
    public AdditionalOption addAdditionalOption(@RequestBody AdditionalOption additionalOption) {
     return additionalOptionService.saveAdditionalOption(additionalOption);
    }
    @DeleteMapping("/{id}")
    public void deleteAdditionalOptionById(@PathVariable long id) {
        additionalOptionService.deleteAdditionalOption(id);
    }
    @PutMapping
    public AdditionalOption modifyAdditionalOptionService(@RequestBody AdditionalOption additionalOption, @RequestParam long id) {
        return additionalOptionService.updateAdditionalOption(additionalOption,id);
    }
}
