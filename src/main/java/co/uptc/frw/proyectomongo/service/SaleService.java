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
        List<Sale> sales = saleRepository.findAll();
        auditService.logAudit("Sale", "Se consultaron todas las ventas");
        return sales;
    }

    public Sale findSaleById(long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venta con id " + id + " no encontrada"));
        auditService.logAudit("Sale", "Se consultó la venta con ID: " + id);
        return sale;
    }

    public Sale saveSale(Sale sale) {
        Sale savedSale = saleRepository.save(sale);
        auditService.logAudit("Sale", "Se creó la venta: " + savedSale.toString());
        return savedSale;
    }

    public Sale updateSale(long id, Sale sale) {
        Sale existingSale = findSaleById(id);
        sale.setId(existingSale.getId()); // Usa el ID encontrado
        Sale updatedSale = saleRepository.save(sale);
        auditService.logAudit("Sale", "Se actualizó la venta: " + updatedSale.toString());
        return updatedSale;
    }

    public void deleteSale(long id) {
        Sale saleToDelete = findSaleById(id);
        saleRepository.deleteById(saleToDelete.getId());
        auditService.logAudit("Sale", "Se eliminó la venta con ID: " + id);
    }
}
