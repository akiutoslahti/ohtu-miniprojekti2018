package ohtutips.repository;

import java.util.List;
import ohtutips.domain.LinkTip;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkTipRepository extends JpaRepository<LinkTip, Long> {
    
    public List<LinkTip> findByType(String type, Sort sort);
    
}
