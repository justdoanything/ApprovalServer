package prj.yong.payment.approval.van.kftc.subscriber;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import prj.yong.payment.approval.van.kftc.domain.entity.ClientRxTransanction;
import prj.yong.payment.approval.van.kftc.exception.SystemException;
import prj.yong.payment.approval.van.kftc.feign.fdk.FdkFeignClient;
import prj.yong.payment.approval.van.kftc.publisher.KftcTransferProducer;
import prj.yong.payment.approval.van.kftc.service.VanKftcService;

public class KftcTransferResultConsumer {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "vanKftcService")
    private VanKftcService vanKftcService;

    @Autowired
    private KftcTransferProducer kftcTransferProducer;

    @Autowired
    FdkFeignClient fdkFeignClient;

    @KafkaListener(topics = "${kftc.transfer.cancel.fdk.topic.name}", containerFactory = "kftcNetCancelKafkaListenerContainerFactory")
    private void kftcTransferCancelFdkListener(ClientRxTransanction clientRxTransanction, Acknowledgment ack) throws Exception {
        LOGGER.info("Received KFTC-To-Fdk Net Cancel result message : ");

        try {
            // 보상 트랜잭션에 대한 처리 시작
    
            // 망취소 호출
            fdkFeignClient.mangCancel(clientRxTransanction);
    
            // 모든 CUD 작업이 완료되야지 kafka의 read off-set 값을 변경하도록 한다.
            ack.acknowledge();

        }catch(Exception e) {
            LOGGER.error("시스템에 예상하지 못한 문제가 발생했습니다!", e);
            throw new SystemException(e.getMessage());
        }
    }
}
