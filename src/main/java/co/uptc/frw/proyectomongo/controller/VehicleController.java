package co.uptc.frw.proyectomongo.controller;

import co.uptc.frw.proyectomongo.model.Vehicle;
import co.uptc.frw.proyectomongo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.findAll();
    }
    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable long id) {
        return vehicleService.findById(id);
    }
    @PostMapping
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.saveVehicle(vehicle);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable long id) {
        vehicleService.deleteVehicle(id);
    }
    @PutMapping
    public Vehicle modifyVehicle(@RequestBody Vehicle vehicle,@RequestParam long id) {
        return vehicleService.updateVehicle(vehicle, id);
    }

    @PostMapping("/{vehiculoId}/AdditionalOptions/{opcionAdicionalId}")
    public Vehicle agregarOpcion(@PathVariable Long vehiculoId, @PathVariable Long opcionAdicionalId) {
        return vehicleService.agregarOpcion(vehiculoId, opcionAdicionalId);
    }

}
