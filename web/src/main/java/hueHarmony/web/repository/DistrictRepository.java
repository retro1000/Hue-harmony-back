package hueHarmony.web.repository;

import hueHarmony.web.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    boolean existsByDistrictId(long id);

}
