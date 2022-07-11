package prj.yong.payment.approval.van.kftc.service.transfer;

import prj.yong.payment.approval.van.kftc.domain.entity.ClientRxTransanction;
import prj.yong.payment.approval.van.kftc.domain.entity.KftcTxTransanction;

public interface KftcTransferService {
    // 전문 준비
    public void readyMessage(ClientRxTransanction request);

    // 전문 생성
    public KftcTxTransanction createMessage(ClientRxTransanction request);

    // 전문 검증
    public void vaildMessage(KftcTxTransanction kftcTxTransanction);

    // 전문 전송
    public void sendMessage(KftcTxTransanction kftcTxTransanction);
}
