package prj.yong.payment.approval.van.kftc.service.rest;

import org.springframework.http.ResponseEntity;

import prj.yong.payment.approval.van.kftc.domain.entity.ClientRxTransanction;

public interface KftcService {
    ResponseEntity<Object> sendApprovalMessageToVanKftc(ClientRxTransanction request) throws Exception;
    ResponseEntity<Object> sendCancelMessageToVanKftc(ClientRxTransanction request) throws Exception;
    ResponseEntity<Object> sendNetCancelMessageToVanKftc(ClientRxTransanction request) throws Exception;
}
