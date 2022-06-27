package prj.yong.payment.approval.van.fdk.service;

import org.springframework.http.ResponseEntity;

import com.google.gson.JsonObject;

public interface VanFdkService {
    ResponseEntity<Object> sendApprovalMessageToVanFdk(JsonObject request) throws Exception;
}
