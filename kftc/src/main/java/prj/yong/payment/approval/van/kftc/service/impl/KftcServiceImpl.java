package prj.yong.payment.approval.van.kftc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import prj.yong.payment.approval.van.kftc.domain.entity.ClientRxTransanction;
import prj.yong.payment.approval.van.kftc.domain.entity.KftcTxTransanction;
import prj.yong.payment.approval.van.kftc.service.KftcService;

@Service
public class KftcServiceImpl implements KftcService{

    @Autowired
    private KftcApprovalTransferServiceImpl approvalTransferService;

    @Autowired
    private KftcCancelTransferServiceImpl cancelTransferService;

    @Autowired
    private KftcNetCancelTransferServiceImpl netCancelTransferService;

    @Override
    @HystrixCommand(commandKey = "sendApprovalMessageToVanKftc", fallbackMethod = "fallbackSendApprovalMessageToVanKftc")
    public ResponseEntity<Object> sendApprovalMessageToVanKftc(ClientRxTransanction request) throws Exception {
        
        // 전문 준비
        if(request.getM1().equals("APPROVAL")){
            // 승인
            KftcTxTransanction kftcTxTransanction = approvalTransferService.createMessage(request);
            approvalTransferService.vaildMessage(kftcTxTransanction);
            approvalTransferService.sendMessage(kftcTxTransanction);
        } else if(request.getM1().equals("CANCEL")){
            // 취소
            KftcTxTransanction kftcTxTransanction = cancelTransferService.createMessage(request);
            cancelTransferService.vaildMessage(kftcTxTransanction);
            cancelTransferService.sendMessage(kftcTxTransanction);
        } else if(request.getM1().equals("NET_CANCEL")){
            // 망취소
            KftcTxTransanction kftcTxTransanction = netCancelTransferService.createMessage(request);
            netCancelTransferService.vaildMessage(kftcTxTransanction);
            netCancelTransferService.sendMessage(kftcTxTransanction);
        }

        return null;
    }

    public ResponseEntity<Object> fallbackSendApprovalMessageToVanKftc(JsonObject request, Throwable throwable){
        // Gateway를 다시 호출해서 다른 VAN 서비스가 호출되게 해야함.
        return null;
    }
    
}
