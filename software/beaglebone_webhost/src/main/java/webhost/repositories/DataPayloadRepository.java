package webhost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webhost.entities.DataPayload;

@Repository
public interface DataPayloadRepository extends JpaRepository<DataPayload, Integer> {

}
