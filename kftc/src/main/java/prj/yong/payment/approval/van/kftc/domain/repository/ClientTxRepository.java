package prj.yong.payment.approval.van.kftc.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prj.yong.payment.approval.van.kftc.domain.entity.ClientTxTransanction;

public interface ClientTxRepository extends JpaRepository<ClientTxTransanction, Long> {
    
}
