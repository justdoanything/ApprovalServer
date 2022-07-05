package prj.yong.payment.approval.van.kftc.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class ClientTxTransanction {
    @Id @GeneratedValue
    private Long txId;
    private Long rxId;
    private String resCd;
    private String resMsg;
    private String seq;
    private String trackIi;
    private String approvalDt;
    private String approvalNo;
    private String approvalVan;
    private String vissCd;
    private String vissNm;
    private String vacoCd;
    private String vacoNm;
    private String shopId;
    private String dcPoint;
    private String validPoint;
    private String savedPoint;
    private String dcAmt;
    private String payAmt;
    private String newPoint;
    private String oldPoint;
    private String usedPoint;
    private String useablePoint;
    private String remainPoint;
    private String dcCnt;
    private String dcApprovalNo;
    private String dcFd;
    private String skDcType;
    private String skApprovalNo;
    private String skDcVipRCnt;
    private String skDcVipYn;
    private String skDcNormYn;
    private String skDcCnt;
    private String skDcCoupleFg;
    private String skDc4coupleYCnt;
    private String skDc4coupleDCnt;
    private String skDcLevel;
    private String skDcHalfdayFg;
    private String skClubYn;
    private String skClubCnt;
    private String sectionId;
    private String memberNo;
    private String ispTid;
    private String cashVissCd;
    private String cashVissNm;
    private String cashVissBranchCd;
    private String cashVacoCd;
    private String cashVacoNm;
    private String cashVacoBranchCd;
    private String cashChargeRate;
    private String cashShopCharge;
    private String cashVissCharge;
    private String cashVacoCharge;
    private String cashAccountNo;
    private String cashLedgerBalanceCd;
    private String cashLedgerBalance;
    private String cashRemainAmt;
    private String alipayCustomer;
    private String alipayCny;
    private String wechatTradeNo;
    private String wechatOrderNo;
}


