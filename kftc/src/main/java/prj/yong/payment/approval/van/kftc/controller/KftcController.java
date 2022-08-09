package prj.yong.payment.approval.van.kftc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import prj.yong.payment.approval.van.kftc.domain.entity.ClientRxTransanction;
import prj.yong.payment.approval.van.kftc.domain.entity.IntegrityCheckEntity;
import prj.yong.payment.approval.van.kftc.domain.entity.KeyDownloadEntity;
import prj.yong.payment.approval.van.kftc.domain.entity.KeyUpdateEntity;
import prj.yong.payment.approval.van.kftc.service.rest.KftcService;

@RestController
public class KftcController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private KftcService vanKftcService;

    // GET : retrieve
    // PUT : update
    // POST : create
    // DELETE : delete
    @ApiOperation(value = "kftc health check", httpMethod = "GET", notes = "kftc server health check")
    @GetMapping("/rest/van/kftc/health")
    public ResponseEntity<Object> retrieveHealthStatus() throws Exception {
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "kftc 무결성 점검", httpMethod = "GET", notes = "kftc 무결성 점검")
    @GetMapping("/rest/van/kftc/integrity")
    public ResponseEntity<Object> retrieveIntegrityStatus(@RequestParam IntegrityCheckEntity request) throws Exception {
        return vanKftcService.retrieveIntegrityStatus(request);
    }

    @ApiOperation(value = "kftc 암호화키 다운로드", httpMethod = "GET", notes = "kftc 암호화키 다운로드")
    @GetMapping("/rest/van/kftc/key/download")
    public ResponseEntity<Object> retrieveKeyDownload(@RequestParam KeyDownloadEntity request) throws Exception {
        return vanKftcService.retrieveKeyDownload(request);
    }

    @ApiOperation(value = "kftc 암호화키 업데이트", httpMethod = "PUT", notes = "kftc 암호화키 업데이트")
    @PutMapping("/rest/van/kftc/key/download")
    public ResponseEntity<Object> updateKeyUpdate(@RequestParam KeyUpdateEntity request) throws Exception {
        return vanKftcService.updateKeyUpdate(request);
    }

    @ApiOperation(value = "kftc 승인 전문 수신", httpMethod = "POST", notes = "kftc 승인 요청 전문 수신")
    @PostMapping("/rest/van/kftc/v1.0")
    public ResponseEntity<Object> createApprovalMessage(@RequestParam ClientRxTransanction request) throws Exception {
        
        LOGGER.debug("Calling url : /rest/van/kftc/v1.0\n- request : " + request.toString());
        return vanKftcService.createApprovalMessage(request);
    }

    @ApiOperation(value = "kftc 취소 전문 수신", httpMethod = "POST", notes = "kftc 취소 요청 전문 수신")
    @PostMapping("/rest/van/kftc/v1.0")
    public ResponseEntity<Object> createCancelMessage(@RequestParam ClientRxTransanction request) throws Exception {
        
        LOGGER.debug("Calling url : /rest/van/kftc/v1.0\n- request : " + request.toString());
        return vanKftcService.createCancelMessage(request);
    }

    @ApiOperation(value = "망취소 처리 후에 보내진 승인 전문 수신", httpMethod = "POST", notes = "망취소 처리 후에 보내진 승인 전문 수신")
    @PostMapping("/rest/van/kftc/cancel/net/v1.0")
    public ResponseEntity<Object> createNetCancelMessage(@RequestParam ClientRxTransanction request) throws Exception {
        
        LOGGER.debug("Calling url : /rest/van/kftc/cancel/net/v1.0\n- request : " + request.toString());
        return vanKftcService.createNetCancelMessage(request);
    }
}
 