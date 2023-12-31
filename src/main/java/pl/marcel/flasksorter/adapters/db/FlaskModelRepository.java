package pl.marcel.flasksorter.adapters.db;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface FlaskModelRepository extends CrudRepository<FlaskModel, UUID> {
}
