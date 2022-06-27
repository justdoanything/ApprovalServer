package prj.yong.payment.approval.van.fdk.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

import prj.yong.payment.approval.van.fdk.service.VanFdkService;

@Service
public class VanFdkServiceImpl implements VanFdkService{

    @Override
    public ResponseEntity<Object> sendApprovalMessageToVanFdk(JsonObject request) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
    
}
