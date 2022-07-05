package prj.yong.payment.approval.van.kftc.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prj.yong.payment.approval.van.kftc.domain.entity.KftcRxTransanction;

public interface KftcRxRepository extends JpaRepository<KftcRxTransanction, Long> {
    
}
