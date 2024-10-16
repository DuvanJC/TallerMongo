package co.uptc.frw.proyectomongo.service;

import co.uptc.frw.proyectomongo.model.AdditionalOption;
import co.uptc.frw.proyectomongo.model.Vehicle;
import co.uptc.frw.proyectomongo.repository.AdditionalOptionRepository;
import co.uptc.frw.proyectomongo.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    
    @Autowired
    private AdditionalOptionRepository additionalOptionRepository;


    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle findById(long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    public Vehicle saveVehicle(Vehicle vehicle) {

        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(long id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicle updateVehicle(Vehicle vehicle,long id) {
        Vehicle vehicleId = findById(id);
        if (vehicleId != null) {
            vehicleId.setDisplacement(vehicle.getDisplacement());
            vehicleId.setRegistration(vehicle.getRegistration());
            vehicleId.setPrice(vehicle.getPrice());
            vehicleId.setCharacteristics(vehicle.getCharacteristics());

            return saveVehicle(vehicleId);
        }
        throw new RuntimeException("Vehicle not found");

    }

    public Vehicle agregarOpcion(Long vehiculoId, Long opcionAdicionalId) {
        Optional<Vehicle> vehiculo = vehicleRepository.findById(vehiculoId);
        Optional<AdditionalOption> opcion = additionalOptionRepository.findById(opcionAdicionalId);

        if (vehiculo.isPresent() && opcion.isPresent()) {
            vehiculo.get().getAdditionalOptions().add(opcion.get());
            return vehicleRepository.save(vehiculo.get());
        }
        return null; // Manejar los casos cuando no existan
    }

}
