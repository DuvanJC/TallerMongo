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

    public List<Sale> findAllSales() {
        return saleRepository.findAll();
    }

    public Sale findSaleById(long id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sale with id " + id + " not found"));
    }

    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public Sale updateSale(long id, Sale sale) {
        Sale existingSale = findSaleById(id);
        sale.setId(existingSale.getId()); // Usa el ID encontrado
        return saleRepository.save(sale);
    }

    public void deleteSale(long id) {
        Sale sale = findSaleById(id);
        saleRepository.deleteById(sale.getId());
    }
}
