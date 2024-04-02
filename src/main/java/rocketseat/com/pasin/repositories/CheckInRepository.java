package rocketseat.com.pasin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import rocketseat.com.pasin.domain.checkin.CheckIn;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer>{

}
