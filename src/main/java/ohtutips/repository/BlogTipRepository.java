package ohtutips.repository;

import ohtutips.domain.BlogTip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogTipRepository extends JpaRepository<BlogTip, Long> {
}
