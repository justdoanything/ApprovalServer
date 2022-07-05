package prj.yong.payment.approval.van.kftc.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import prj.yong.payment.approval.van.kftc.domain.entity.ClientRxTransanction;
import prj.yong.payment.approval.van.kftc.domain.entity.ClientTxTransanction;
import prj.yong.payment.approval.van.kftc.domain.entity.KftcRxTransanction;
import prj.yong.payment.approval.van.kftc.domain.entity.KftcTxTransanction;
import prj.yong.payment.approval.van.kftc.exception.SystemException;

@Component
public class KftcTransferProducer {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaTemplate<String, ClientRxTransanction> clientRxTransactionKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, ClientTxTransanction> clientTxTransactionKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, KftcRxTransanction> kftcRxTransactionKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, KftcTxTransanction> kftcTxTransactionProducerFactory;

    @Value(value = "${kftc.transfer.topic.name}")
    private String kftcTransferTopicName;

    public void sendTransactionMessage(ClientRxTransanction transactionHistory){
        ListenableFuture<SendResult<String, ClientRxTransanction>> future = clientRxTransactionKafkaTemplate.send(kftcTransferTopicName, transactionHistory);

        future.addCallback(new ListenableFutureCallback<SendResult<String, ClientRxTransanction>>() {

            @Override
            public void onSuccess(SendResult<String, ClientRxTransanction> result) {
                ClientRxTransanction th = result.getProducerRecord().value();
                LOGGER.info("Sent message=[" + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                LOGGER.error("Unable to send message=[" + "] due to : " + ex.getMessage());
                throw new SystemException("Kafka 전송 에러 발생!");
            }
        });
    }



}
