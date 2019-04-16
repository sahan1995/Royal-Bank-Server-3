package lk.royalBank.repository;

import lk.royalBank.entity.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawRepository extends JpaRepository<Withdraw,Integer> {
}
