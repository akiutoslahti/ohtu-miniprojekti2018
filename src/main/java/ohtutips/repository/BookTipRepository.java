package ohtutips.repository;

import ohtutips.domain.BookTip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTipRepository extends JpaRepository<BookTip, Long> {
}
