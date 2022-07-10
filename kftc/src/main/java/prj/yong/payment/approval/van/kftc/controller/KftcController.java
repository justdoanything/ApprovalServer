package prj.yong.payment.approval.van.kftc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import prj.yong.payment.approval.van.kftc.domain.entity.ClientRxTransanction;
import prj.yong.payment.approval.van.kftc.service.KftcService;

@RestController
public class KftcController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private KftcService vanKftcService;

    @ApiOperation(value = "kftc 승인 전문 수신", httpMethod = "POST", notes = "kftc 승인 요청 전문 수신")
    @PutMapping("/rest/van/kftc/v1.0")
    public ResponseEntity<Object> sendApprovalMessageToVankftc(@RequestParam ClientRxTransanction request) throws Exception {
        
        LOGGER.debug("Calling url : /rest/van/kftc/v1.0\n- request : " + request.toString());
        return vanKftcService.sendApprovalMessageToVanKftc(request);
    }

    @ApiOperation(value = "kftc 취소 전문 수신", httpMethod = "POST", notes = "kftc 취소 요청 전문 수신")
    @PutMapping("/rest/van/kftc/v1.0")
    public ResponseEntity<Object> sendCancelMessageToVankftc(@RequestParam ClientRxTransanction request) throws Exception {
        
        LOGGER.debug("Calling url : /rest/van/kftc/v1.0\n- request : " + request.toString());
        return vanKftcService.sendCancelMessageToVanKftc(request);
    }

    @ApiOperation(value = "망취소 처리 후에 보내진 승인 전문 수신", httpMethod = "POST", notes = "망취소 처리 후에 보내진 승인 전문 수신")
    @PutMapping("/rest/van/kftc/cancel/net/v1.0")
    public ResponseEntity<Object> sendNetCancelMessageToVankftc(@RequestParam ClientRxTransanction request) throws Exception {
        
        LOGGER.debug("Calling url : /rest/van/kftc/cancel/net/v1.0\n- request : " + request.toString());
        return vanKftcService.sendNetCancelMessageToVanKftc(request);
    }
}
 