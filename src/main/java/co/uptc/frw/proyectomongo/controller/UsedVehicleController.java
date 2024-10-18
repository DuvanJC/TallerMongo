package co.uptc.frw.proyectomongo.controller;

import co.uptc.frw.proyectomongo.model.UsedVehicle;
import co.uptc.frw.proyectomongo.service.UsedVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usedvehicle")
public class UsedVehicleController {

    @Autowired
    private UsedVehicleService usedVehicleService;

    @GetMapping
    public List<UsedVehicle> getAllUsedVehicles() {
        return usedVehicleService.findAllVehiculosUsados();
    }

    @GetMapping("/{id}")
    public UsedVehicle getUsedVehicleById(@PathVariable("id") long id) {
    return usedVehicleService.findVehiculoUsadoById(id);
    }

    @PostMapping
    public UsedVehicle addUsedVehicle(@RequestBody UsedVehicle vehiculoUsado) {
        return usedVehicleService.saveVehiculoUsado(vehiculoUsado);
    }

    @PutMapping("/{id}")
    public UsedVehicle updateUsedVehicle(@PathVariable("id") long id, @RequestBody UsedVehicle vehiculoUsado) {
            return usedVehicleService.updateVehiculoUsado(id, vehiculoUsado);
    }


    @DeleteMapping("/{id}")
    public void deleteUsedVehicle(@PathVariable long id) {
        usedVehicleService.deleteVehiculoUsado(id);
    }

}
