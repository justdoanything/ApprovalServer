package prj.yong.payment.approval.van.kftc.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import prj.yong.payment.approval.van.kftc.service.VanKftcService;

@Service
public class VanKftcServiceImpl implements VanKftcService{

    @Override
    @HystrixCommand(commandKey = "sendApprovalMessageToVankftc", fallbackMethod = "fallbackSendApprovalMessageToVankftc")
    public ResponseEntity<Object> sendApprovalMessageToVankftc(JsonObject request) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public ResponseEntity<Object> fallbackSendApprovalMessageToVankftc(JsonObject request, Throwable throwable){
        // Gateway를 다시 호출해서 다른 VAN 서비스가 호출되게 해야함.
        return null;
    }
    
}
