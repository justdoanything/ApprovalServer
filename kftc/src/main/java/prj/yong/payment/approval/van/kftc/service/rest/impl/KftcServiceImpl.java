package prj.yong.payment.approval.van.kftc.service.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import prj.yong.payment.approval.van.kftc.domain.entity.ClientRxTransanction;
import prj.yong.payment.approval.van.kftc.domain.entity.IntegrityCheckEntity;
import prj.yong.payment.approval.van.kftc.domain.entity.KeyDownloadEntity;
import prj.yong.payment.approval.van.kftc.domain.entity.KeyUpdateEntity;
import prj.yong.payment.approval.van.kftc.domain.entity.KftcTxTransanction;
import prj.yong.payment.approval.van.kftc.service.rest.KftcService;
import prj.yong.payment.approval.van.kftc.service.transfer.impl.KftcApprovalTransferServiceImpl;
import prj.yong.payment.approval.van.kftc.service.transfer.impl.KftcCancelTransferServiceImpl;
import prj.yong.payment.approval.van.kftc.service.transfer.impl.KftcNetCancelTransferServiceImpl;

@Service
public class KftcServiceImpl implements KftcService{

    @Autowired
    private KftcApprovalTransferServiceImpl approvalTransferService;

    @Autowired
    private KftcCancelTransferServiceImpl cancelTransferService;

    @Autowired
    private KftcNetCancelTransferServiceImpl netCancelTransferService;

    @Override
    public ResponseEntity<Object> retrieveIntegrityStatus(IntegrityCheckEntity request) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public ResponseEntity<Object> retrieveKeyDownload(KeyDownloadEntity request) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public ResponseEntity<Object> updateKeyUpdate(KeyUpdateEntity request) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @HystrixCommand(commandKey = "createApprovalMessage", fallbackMethod = "fallbackCreateApprovalMessage")
    public ResponseEntity<Object> createApprovalMessage(ClientRxTransanction request) throws Exception {
        // 승인
        KftcTxTransanction kftcTxTransanction = approvalTransferService.createMessage(request);
        approvalTransferService.vaildMessage(kftcTxTransanction);
        approvalTransferService.sendMessage(kftcTxTransanction);
        return null;
    }

    @Override
    @HystrixCommand(commandKey = "createCancelMessage", fallbackMethod = "fallbackCreateCancelMessage")
    public ResponseEntity<Object> createCancelMessage(ClientRxTransanction request) throws Exception {
        // 취소
        KftcTxTransanction kftcTxTransanction = cancelTransferService.createMessage(request);
        cancelTransferService.vaildMessage(kftcTxTransanction);
        cancelTransferService.sendMessage(kftcTxTransanction);
        return null;
    }
    
    @Override
    @HystrixCommand(commandKey = "createNetCancelMessage", fallbackMethod = "fallbackCreateNetCancelMessage")
    public ResponseEntity<Object> createNetCancelMessage(ClientRxTransanction request) throws Exception {
        // 망취소
        KftcTxTransanction kftcTxTransanction = netCancelTransferService.createMessage(request);
        netCancelTransferService.vaildMessage(kftcTxTransanction);
        netCancelTransferService.sendMessage(kftcTxTransanction);
        return null;
    }
    
    public ResponseEntity<Object> fallbackCreateApprovalMessage(JsonObject request, Throwable throwable){
        
        return null;
    }

    public ResponseEntity<Object> fallbackCreateCancelMessage(JsonObject request, Throwable throwable){
        return null;
    }

    public ResponseEntity<Object> fallbackCreateNetCancelMessage(JsonObject request, Throwable throwable){
        // Gateway를 다시 호출해서 다른 VAN 서비스가 호출되게 해야함.
        // 다른 서비스에 url을 호출해서 결제 태운다.
        return null;
    }
}
