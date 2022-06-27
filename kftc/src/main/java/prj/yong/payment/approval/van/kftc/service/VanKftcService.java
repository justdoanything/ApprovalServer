package prj.yong.payment.approval.van.kftc.service;

import org.springframework.http.ResponseEntity;

import com.google.gson.JsonObject;

public interface VanKftcService {
    ResponseEntity<Object> sendApprovalMessageToVanKftc(JsonObject request) throws Exception;
}
