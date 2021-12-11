package webhost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webhost.entities.EndDevice;

@Repository
public interface EndDeviceRepository extends JpaRepository<EndDevice, Integer> {

}
