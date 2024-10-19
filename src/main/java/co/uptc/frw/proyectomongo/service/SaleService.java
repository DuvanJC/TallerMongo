package co.uptc.frw.proyectomongo.service;

import co.uptc.frw.proyectomongo.model.Sale;
import co.uptc.frw.proyectomongo.repository.SaleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private AuditService auditService;

    public List<Sale> findAllSales() {
        try {
            List<Sale> sales = saleRepository.findAll();
            auditService.logAudit("Sale", "Se consultaron todas las ventas");
            return sales;
        } catch (Exception e) {
            auditService.logAudit("Sale", "Error al consultar todas las ventas: " + e.getMessage());
            throw e;
        }
    }

    public Sale findSaleById(long id) {
        try {
            Sale sale = saleRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Venta con id " + id + " no encontrada"));
            auditService.logAudit("Sale", "Se consultó la venta con ID: " + id);
            return sale;
        } catch (Exception e) {
            auditService.logAudit("Sale", "Error al consultar la venta con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public Sale saveSale(Sale sale) {
        try {
            Sale savedSale = saleRepository.save(sale);
            auditService.logAudit("Sale", "Se creó la venta: " + savedSale.toString());
            return savedSale;
        } catch (Exception e) {
            auditService.logAudit("Sale", "Error al crear la venta: " + e.getMessage());
            throw e;
        }
    }

    public Sale updateSale(long id, Sale sale) {
        try {
            Sale existingSale = findSaleById(id);
            sale.setId(existingSale.getId()); // Usa el ID encontrado
            Sale updatedSale = saleRepository.save(sale);
            auditService.logAudit("Sale", "Se actualizó la venta: " + updatedSale.toString());
            return updatedSale;
        } catch (Exception e) {
            auditService.logAudit("Sale", "Error al actualizar la venta con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public void deleteSale(long id) {
        try {
            Sale saleToDelete = findSaleById(id);
            saleRepository.deleteById(saleToDelete.getId());
            auditService.logAudit("Sale", "Se eliminó la venta con ID: " + id);
        } catch (Exception e) {
            auditService.logAudit("Sale", "Error al eliminar la venta con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }
}
