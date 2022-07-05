package prj.yong.payment.approval.van.kftc.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class KftcTxTransanction {
    @Id @GeneratedValue
    private Long txId;
    private Long clientRxId;
    private String transaction_type;
    private String tid_van;
    private String sign_data_decoding;
    private String sign_yn;
    private String tran_date;
    private String save_fg;
    private String app_dt_van;
    private String naco_cd;
    private String naco_nm;
    private String card_kind;
    private String iso;
    private String iso_header;
    private String msg_type;
    private String primary_bit;
    private String secondary_bitmap;
    private String tran_kind;
    private String membership_point1;
    private String membership_point2;
    private String jm_send_dt;
    private String shop_filler;
    private String seq;
    private String tran_time;
    private String product_cd;
    private String wcc_van;
    private String kikwan_cd_len;
    private String kikwan_cd;
    private String track_ii_len;
    private String track_ii;
    private String pg_info_len;
    private String pg_no;
    private String pg_domain;
    private String pg_ip_addr;
    private String tran_sn;
    private String res_cd;
    private String term_id;
    private String shop_id;
    private String bonus_msg;
    private String used_point;
    private String useable_point;
    private String remain_point;
    private String res_msg1;
    private String res_msg2;
    private String money_cd;
    private String pin;
    private String cash_ic_length;
    private String cash_ic_issue_cd;
    private String cash_ic_trackiii;
    private String cash_ic_encrypt_data;
    private String inst_m_len;
    private String inst_m;
    private String app_no_len;
    private String app_no_van;
    private String card_message_len;
    private String viss_nm;
    private String vaco_cd;
    private String card_msg;
    private String emv_data;
    private String cheq_len;
    private String cheq_bank;
    private String cheq_dt;
    private String cheq_no;
    private String cheq_mn;
    private String cheq_amt;
    private String cheq_seq;
    private String remain_amt;
    private String isp_visa_data;
    private String sign_data_len;
    private String sign_data;
    private String total_amt;
    private String wechat_len;
    private String wechat_trade_no;
    private String wechat_order_no;
    private String card_info_len;
    private String cash_viss_cd;
    private String cash_viss_nm;
    private String cash_viss_branch_cd;
    private String cash_account_no;
    private String cash_ledger_balance_cd;
    private String cash_ledger_balance;
    private String cash_remain_amt;
    private String cash_vaco_cd;
    private String cash_vaco_nm;
    private String cash_vaco_branch_cd;
    private String cash_charge_rate;
    private String cash_shop_charge;
    private String cash_viss_charge;
    private String cash_vaco_charge;
}
