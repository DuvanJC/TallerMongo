package co.uptc.frw.proyectomongo.service;

import co.uptc.frw.proyectomongo.model.UsedVehicle;
import co.uptc.frw.proyectomongo.repository.UsedVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsedVehicleService {

    @Autowired
    private UsedVehicleRepository usedVehicleRepository;

    @Autowired
    private AuditService auditService;

    public List<UsedVehicle> findAllVehiculosUsados() {
        List<UsedVehicle> vehiculosUsados = usedVehicleRepository.findAll();
        auditService.logAudit("UsedVehicle", "Se consultaron todos los vehículos usados");
        return vehiculosUsados;
    }

    public UsedVehicle findVehiculoUsadoById(Long id) {
        UsedVehicle vehiculoUsado = usedVehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo usado con id " + id + " no encontrado"));
        auditService.logAudit("UsedVehicle", "Se consultó el vehículo usado con ID: " + id);
        return vehiculoUsado;
    }

    public UsedVehicle saveVehiculoUsado(UsedVehicle vehiculoUsado) {
        UsedVehicle vehiculoGuardado = usedVehicleRepository.save(vehiculoUsado);
        auditService.logAudit("UsedVehicle", "Se creó el vehículo usado: " + vehiculoGuardado.toString());
        return vehiculoGuardado;
    }

    public UsedVehicle updateVehiculoUsado(Long id, UsedVehicle vehiculoUsado) {
        UsedVehicle vehicleId = findVehiculoUsadoById(id);
        vehiculoUsado.setId(id); // Asigna el ID existente
        UsedVehicle vehiculoActualizado = usedVehicleRepository.save(vehiculoUsado);
        auditService.logAudit("UsedVehicle", "Se actualizó el vehículo usado: " + vehiculoActualizado.toString());
        return vehiculoActualizado;
    }

    public void deleteVehiculoUsado(Long id) {
        UsedVehicle vehiculoAEliminar = findVehiculoUsadoById(id);
        usedVehicleRepository.deleteById(id);
        auditService.logAudit("UsedVehicle", "Se eliminó el vehículo usado con ID: " + id);
    }
}
