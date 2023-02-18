package co.microservicios.documents.repository;

import org.springframework.data.repository.CrudRepository;

import co.microservicios.documents.model.ProductoEntity;

public interface ProductoRepository extends CrudRepository<ProductoEntity, String> {
}
