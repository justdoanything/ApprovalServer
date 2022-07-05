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
    private String res_cd;
    private String res_msg;
    private String approval_no;
    private String approval_dt;
    private String approval_msg;
}
