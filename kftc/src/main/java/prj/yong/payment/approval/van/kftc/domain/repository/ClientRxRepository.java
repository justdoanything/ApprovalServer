package prj.yong.payment.approval.van.kftc.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prj.yong.payment.approval.van.kftc.domain.entity.ClientRxTransanction;

public interface ClientRxRepository extends JpaRepository<ClientRxTransanction, Long> {


}
