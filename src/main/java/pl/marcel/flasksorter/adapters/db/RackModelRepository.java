package pl.marcel.flasksorter.adapters.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface RackModelRepository extends JpaRepository<RackModel, UUID> {
}
