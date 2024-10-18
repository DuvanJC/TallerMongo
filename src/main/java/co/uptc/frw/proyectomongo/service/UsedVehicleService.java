package co.uptc.frw.proyectomongo.service;

import co.uptc.frw.proyectomongo.model.UsedVehicle;
import co.uptc.frw.proyectomongo.model.Vehicle;
import co.uptc.frw.proyectomongo.repository.UsedVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsedVehicleService {

    @Autowired
    private UsedVehicleRepository usedVehicleRepository;

    public List<UsedVehicle> findAllVehiculosUsados() {
        return usedVehicleRepository.findAll();
    }

    public UsedVehicle findVehiculoUsadoById(Long id) {
        return usedVehicleRepository.findById(id).orElse(null);
    }

    public UsedVehicle saveVehiculoUsado(UsedVehicle vehiculoUsado) {
        return usedVehicleRepository.save(vehiculoUsado);
    }

    public UsedVehicle updateVehiculoUsado(Long id, UsedVehicle vehiculoUsado) {
        UsedVehicle vehicleId = findVehiculoUsadoById(id);
        if(vehicleId != null) {
            vehiculoUsado.setId(id);
            return usedVehicleRepository.save(vehiculoUsado);
        }
        throw new RuntimeException("Used Vehicle not found");
    }

    public void deleteVehiculoUsado(Long id) {
        usedVehicleRepository.deleteById(id);
    }
}
