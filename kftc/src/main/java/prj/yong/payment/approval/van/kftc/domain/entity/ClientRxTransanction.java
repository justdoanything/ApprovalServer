package prj.yong.payment.approval.van.kftc.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class ClientRxTransanction {
    @Id @GeneratedValue
    private Long rxId;
    private String transactionType;
    private String type1;
    private String m1;
    private String m2;
    private String siteCd;
    private String tid;
    private String organCd;
    private String posNo;
    private String accountDt;
    private String wcc;
    private String trackIi;
    private String buyerFg;
    private String instM;
    private String pCode;
    private String saveFg;
    private String svc;
    private String tax;
    private String totalAmt;
    private String approvalDt;
    private String approvalNo;
    private String approvalVan;
    private String cancelCause;
    private String passwd;
    private String personNbr;
    private String cheqCd;
    private String cheqMn;
    private String cheqNo;
    private String cheqDt;
    private String cheqAmt;
    private String cheqBank;
    private String cheqAcnt;
    private String orderNo;
    private String storeCd;
    private String customerNm;
    private String productNm;
    private String osType;
    private String dummy1;
    private String dummy2;
    private String dummy3;
    private String dummy4;
    private String dummy5;
    private String dummy6;
    private String dummy7;
    private String dummy8;
    private String dummy9;
    private String dummy10;
    private String dummy11;
    private String dummy12;
    private String dummy13;
    private String dummy14;
    private String dummy15;
    private String posEnCdata;
    private String icChipData;
    private String signHead;
    private String signData;
    private String screeningDt;
    private String bizNo;
    private String cnt;
    private String onlineFg;
    private String dcAmt;
    private String dcApprovalNo;
    private String dcFg;
    private String sectionId;
    private String memberNo;
    private String sessionKey;
    private String encryptData;
    private String cardCode;
    private String orgIsPtid;
    private String cavv;
    private String xid;
    private String eci;
    private String mallBizid;
    private String mallDomain;
    private String userIp;
    private String goodsName;
    private String merchantInfo;
    private String currencyCd;
    private String cashVissCd;
    private String trackIii;
    private String icDerial;
    private String icSimplify;
}
