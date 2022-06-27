package prj.yong.payment.approval.van.fdk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

import io.swagger.annotations.ApiOperation;
import prj.yong.payment.approval.van.fdk.service.VanFdkService;

@RestController
public class VanFdkController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private VanFdkService vanFdkService;

    @ApiOperation(value = "FDK 전문 전송", httpMethod = "PUT", notes = "FDK 전문 전송")
    @PutMapping("/rest/van/fdk/v1.0")
    public ResponseEntity<Object> sendApprovalMessageToVanFdk(@RequestParam JsonObject request) throws Exception {
        
        LOGGER.debug("Calling url : /rest/van/fdk/v1.0\n- request : " + request.toString());
        return vanFdkService.sendApprovalMessageToVanFdk(request);
    }
    
}
 