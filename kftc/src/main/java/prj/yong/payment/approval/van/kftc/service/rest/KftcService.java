package prj.yong.payment.approval.van.kftc.service.rest;

import org.springframework.http.ResponseEntity;

import prj.yong.payment.approval.van.kftc.domain.entity.ClientRxTransanction;
import prj.yong.payment.approval.van.kftc.domain.entity.IntegrityCheckEntity;
import prj.yong.payment.approval.van.kftc.domain.entity.KeyDownloadEntity;
import prj.yong.payment.approval.van.kftc.domain.entity.KeyUpdateEntity;

public interface KftcService {
    ResponseEntity<Object> retrieveIntegrityStatus(IntegrityCheckEntity request) throws Exception;
    ResponseEntity<Object> retrieveKeyDownload(KeyDownloadEntity request) throws Exception;
    ResponseEntity<Object> updateKeyUpdate(KeyUpdateEntity request) throws Exception;
    ResponseEntity<Object> createApprovalMessage(ClientRxTransanction request) throws Exception;
    ResponseEntity<Object> createCancelMessage(ClientRxTransanction request) throws Exception;
    ResponseEntity<Object> createNetCancelMessage(ClientRxTransanction request) throws Exception;
}
