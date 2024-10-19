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
        try {
            List<Vehicle> vehicles = vehicleRepository.findAll();
            auditService.logAudit("Vehicle", "Se consultaron todos los vehículos");
            return vehicles;
        } catch (Exception e) {
            auditService.logAudit("Vehicle", "Error al consultar los vehículos: " + e.getMessage());
            throw e;
        }
    }

    public Vehicle findById(long id) {
        try {
            Vehicle vehicle = vehicleRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Vehículo con id " + id + " no encontrado"));
            auditService.logAudit("Vehicle", "Se consultó el vehículo con ID: " + id);
            return vehicle;
        } catch (Exception e) {
            auditService.logAudit("Vehicle", "Error al consultar el vehículo con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        try {
            Vehicle savedVehicle = vehicleRepository.save(vehicle);
            auditService.logAudit("Vehicle", "Creado: " + savedVehicle.toString());
            return savedVehicle;
        } catch (Exception e) {
            auditService.logAudit("Vehicle", "Error al crear el vehículo: " + e.getMessage());
            throw e;
        }
    }

    public void deleteVehicle(long id) {
        try {
            Vehicle vehicleToDelete = findById(id);
            vehicleRepository.deleteById(id);
            auditService.logAudit("Vehicle", "Borrado: ID " + id + ", Detalles: " + vehicleToDelete.toString());
        } catch (Exception e) {
            auditService.logAudit("Vehicle", "Error al eliminar el vehículo con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public Vehicle updateVehicle(Vehicle vehicle, long id) {
        try {
            Vehicle vehicleId = findById(id);
            vehicleId.setDisplacement(vehicle.getDisplacement());
            vehicleId.setRegistration(vehicle.getRegistration());
            vehicleId.setPrice(vehicle.getPrice());
            vehicleId.setCharacteristics(vehicle.getCharacteristics());

            Vehicle updatedVehicle = vehicleRepository.save(vehicleId);
            auditService.logAudit("Vehicle", "Modificado: " + updatedVehicle.toString());
            return updatedVehicle;
        } catch (Exception e) {
            auditService.logAudit("Vehicle", "Error al actualizar el vehículo con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public Vehicle agregarOpcion(Long vehiculoId, Long opcionAdicionalId) {
        try {
            Optional<Vehicle> vehiculo = vehicleRepository.findById(vehiculoId);
            Optional<AdditionalOption> opcion = additionalOptionRepository.findById(opcionAdicionalId);

            if (vehiculo.isPresent() && opcion.isPresent()) {
                vehiculo.get().getAdditionalOptions().add(opcion.get());
                Vehicle updatedVehicle = vehicleRepository.save(vehiculo.get());
                auditService.logAudit("Vehicle", "Opción adicional agregada al vehículo con ID: " + vehiculoId);
                return updatedVehicle;
            }
            throw new RuntimeException("Vehículo u opción adicional no encontrados");
        } catch (Exception e) {
            auditService.logAudit("Vehicle", "Error al agregar opción adicional al vehículo con ID " + vehiculoId + ": " + e.getMessage());
            throw e;
        }
    }
}
