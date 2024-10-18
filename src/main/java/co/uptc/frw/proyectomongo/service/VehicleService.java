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

    @Autowired
    private AuditService auditService;

    public List<Vehicle> findAll() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        auditService.logAudit("Vehicle", "Se consultaron todos los vehículos");
        return vehicles;
    }

    public Vehicle findById(long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo con id " + id + " no encontrado"));
        auditService.logAudit("Vehicle", "Se consultó el vehículo con ID: " + id);
        return vehicle;
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        auditService.logAudit("Vehicle", "Creado: " + savedVehicle.toString());
        return savedVehicle;
    }

    public void deleteVehicle(long id) {
        Vehicle vehicleToDelete = findById(id);
        vehicleRepository.deleteById(id);
        auditService.logAudit("Vehicle", "Borrado: ID " + id + ", Detalles: " + vehicleToDelete.toString());
    }

    public Vehicle updateVehicle(Vehicle vehicle, long id) {
        Vehicle vehicleId = findById(id);
        vehicleId.setDisplacement(vehicle.getDisplacement());
        vehicleId.setRegistration(vehicle.getRegistration());
        vehicleId.setPrice(vehicle.getPrice());
        vehicleId.setCharacteristics(vehicle.getCharacteristics());

        Vehicle updatedVehicle = vehicleRepository.save(vehicleId);
        auditService.logAudit("Vehicle", "Modificado: " + updatedVehicle.toString());
        return updatedVehicle;
    }

    public Vehicle agregarOpcion(Long vehiculoId, Long opcionAdicionalId) {
        Optional<Vehicle> vehiculo = vehicleRepository.findById(vehiculoId);
        Optional<AdditionalOption> opcion = additionalOptionRepository.findById(opcionAdicionalId);

        if (vehiculo.isPresent() && opcion.isPresent()) {
            vehiculo.get().getAdditionalOptions().add(opcion.get());
            Vehicle updatedVehicle = vehicleRepository.save(vehiculo.get());
            auditService.logAudit("Vehicle", "Opción adicional agregada al vehículo con ID: " + vehiculoId);
            return updatedVehicle;
        }
        throw new RuntimeException("Vehículo u opción adicional no encontrados");
    }

}
