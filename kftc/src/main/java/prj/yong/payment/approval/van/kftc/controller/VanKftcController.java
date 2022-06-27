package prj.yong.payment.approval.van.kftc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

import io.swagger.annotations.ApiOperation;
import prj.yong.payment.approval.van.kftc.service.VanKftcService;

@RestController
public class VanKftcController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private VanKftcService vanKftcService;

    @ApiOperation(value = "kftc 전문 전송", httpMethod = "PUT", notes = "kftc 전문 전송")
    @PutMapping("/rest/van/kftc/v1.0")
    public ResponseEntity<Object> sendApprovalMessageToVankftc(@RequestParam JsonObject request) throws Exception {
        
        LOGGER.debug("Calling url : /rest/van/kftc/v1.0\n- request : " + request.toString());
        return vanKftcService.sendApprovalMessageToVanKftc(request);
    }
    
}
 