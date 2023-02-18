package co.microservicios.documents.repository;

import org.springframework.data.repository.CrudRepository;

import co.microservicios.documents.model.DocumentEntity;

public interface DocumentRepository extends CrudRepository<DocumentEntity, String> {
}
