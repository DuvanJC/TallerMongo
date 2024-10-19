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
        try {
            List<UsedVehicle> vehiculosUsados = usedVehicleRepository.findAll();
            auditService.logAudit("UsedVehicle", "Se consultaron todos los vehículos usados");
            return vehiculosUsados;
        } catch (Exception e) {
            auditService.logAudit("UsedVehicle", "Error al consultar los vehículos usados: " + e.getMessage());
            throw e;
        }
    }

    public UsedVehicle findVehiculoUsadoById(Long id) {
        try {
            UsedVehicle vehiculoUsado = usedVehicleRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Vehículo usado con id " + id + " no encontrado"));
            auditService.logAudit("UsedVehicle", "Se consultó el vehículo usado con ID: " + id);
            return vehiculoUsado;
        } catch (Exception e) {
            auditService.logAudit("UsedVehicle", "Error al consultar el vehículo usado con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public UsedVehicle saveVehiculoUsado(UsedVehicle vehiculoUsado) {
        try {
            UsedVehicle vehiculoGuardado = usedVehicleRepository.save(vehiculoUsado);
            auditService.logAudit("UsedVehicle", "Se creó el vehículo usado: " + vehiculoGuardado.toString());
            return vehiculoGuardado;
        } catch (Exception e) {
            auditService.logAudit("UsedVehicle", "Error al crear el vehículo usado: " + e.getMessage());
            throw e;
        }
    }

    public UsedVehicle updateVehiculoUsado(Long id, UsedVehicle vehiculoUsado) {
        try {
            vehiculoUsado.setId(id);
            UsedVehicle vehiculoActualizado = usedVehicleRepository.save(vehiculoUsado);
            auditService.logAudit("UsedVehicle", "Se actualizó el vehículo usado: " + vehiculoActualizado.toString());
            return vehiculoActualizado;
        } catch (Exception e) {
            auditService.logAudit("UsedVehicle", "Error al actualizar el vehículo usado con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public void deleteVehiculoUsado(Long id) {
        try {
            usedVehicleRepository.deleteById(id);
            auditService.logAudit("UsedVehicle", "Se eliminó el vehículo usado con ID: " + id);
        } catch (Exception e) {
            auditService.logAudit("UsedVehicle", "Error al eliminar el vehículo usado con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }
}
