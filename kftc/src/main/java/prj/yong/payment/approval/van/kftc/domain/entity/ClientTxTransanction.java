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
    private String res_cd;
    private String res_msg;
    private String seq;
    private String track_ii;
    private String approval_dt;
    private String approval_no;
    private String approval_van;
    private String viss_cd;
    private String viss_nm;
    private String vaco_cd;
    private String vaco_nm;
    private String shop_id;
    private String dc_point;
    private String valid_point;
    private String saved_point;
    private String dc_amt;
    private String pay_amt;
    private String new_point;
    private String old_point;
    private String used_point;
    private String useable_point;
    private String remain_point;
    private String dc_cnt;
    private String dc_approval_no;
    private String dc_fd;
    private String sk_dc_type;
    private String sk_approval_no;
    private String sk_dc_vip_r_cnt;
    private String sk_dc_vip_yn;
    private String sk_dc_norm_yn;
    private String sk_dc_cnt;
    private String sk_dc_couple_fg;
    private String sk_dc4couple_y_cnt;
    private String sk_dc4couple_d_cnt;
    private String sk_dc_level;
    private String sk_dc_halfday_fg;
    private String sk_club_yn;
    private String sk_club_cnt;
    private String section_id;
    private String member_no;
    private String isp_tid;
    private String cash_viss_cd;
    private String cash_viss_nm;
    private String cash_viss_branch_cd;
    private String cash_vaco_cd;
    private String cash_vaco_nm;
    private String cash_vaco_branch_cd;
    private String cash_charge_rate;
    private String cash_shop_charge;
    private String cash_viss_charge;
    private String cash_vaco_charge;
    private String cash_account_no;
    private String cash_ledger_balance_cd;
    private String cash_ledger_balance;
    private String cash_remain_amt;
    private String alipay_customer;
    private String alipay_cny;
    private String wechat_trade_no;
    private String wechat_order_no;
}


