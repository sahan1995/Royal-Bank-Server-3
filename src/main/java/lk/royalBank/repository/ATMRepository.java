package lk.royalBank.repository;

import lk.royalBank.entity.ATMcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ATMRepository extends JpaRepository<ATMcard,String> {


    @Query(value = "SELECT * FROM atmcard a WHERE a.pin=:pin ",nativeQuery = true)
    ATMcard loginATM(@Param("pin") String atmPIN);

}
