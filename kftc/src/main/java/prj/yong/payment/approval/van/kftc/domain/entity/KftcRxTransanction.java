package prj.yong.payment.approval.van.kftc.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class KftcRxTransanction {
    @Id @GeneratedValue
    private Long rxId;
    private Long txId;
    private String resCd;
    private String resMsg;
    private String approvalNo;
    private String approvalDt;
    private String approvalMsg;
}
