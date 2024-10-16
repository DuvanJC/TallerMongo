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

    public List<AdditionalOption> finAll() {
        return additionalOptionRepository.findAll();
    }
    public AdditionalOption finById(long id) {
        return additionalOptionRepository.findById(id).orElse(null);
    }

    public AdditionalOption saveAdditionalOption(AdditionalOption additionalOption){
        return additionalOptionRepository.save(additionalOption);
    }

    public void deleteAdditionalOption(long id){
         additionalOptionRepository.deleteById(id);
    }

    public AdditionalOption updateAdditionalOption(AdditionalOption newAdditionalOption,long id){
        AdditionalOption additionalOptionId = finById(id);
        if(additionalOptionId != null){
            additionalOptionId.setName(newAdditionalOption.getName());
            additionalOptionId.setDescription(newAdditionalOption.getDescription());
            additionalOptionId.setPrice(newAdditionalOption.getPrice());
            return saveAdditionalOption(additionalOptionId);
        }
        throw new RuntimeException("Additional option not found");
    }
}
