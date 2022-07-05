package prj.yong.payment.approval.van.kftc.service;

import org.springframework.http.ResponseEntity;

import prj.yong.payment.approval.van.kftc.domain.entity.ClientRxTransanction;

public interface VanKftcService {
    ResponseEntity<Object> sendApprovalMessageToVanKftc(ClientRxTransanction request) throws Exception;
}
