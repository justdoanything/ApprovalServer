package prj.yong.payment.approval.van.kftc.service.impl;

import org.springframework.stereotype.Service;

import prj.yong.payment.approval.van.kftc.domain.entity.ClientRxTransanction;
import prj.yong.payment.approval.van.kftc.domain.entity.KftcTxTransanction;
import prj.yong.payment.approval.van.kftc.service.KftcTransferService;

@Service
public class KftcApprovalTransferServiceImpl implements KftcTransferService {

    @Override
    public void readyMessage(ClientRxTransanction request) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public KftcTxTransanction createMessage(ClientRxTransanction request) {

        
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void vaildMessage(KftcTxTransanction kftcTxTransanction) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendMessage(KftcTxTransanction kftcTxTransanction) {
        // TODO Auto-generated method stub
        
    }
}
