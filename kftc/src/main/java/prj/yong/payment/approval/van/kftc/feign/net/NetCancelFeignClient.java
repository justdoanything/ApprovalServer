package prj.yong.payment.approval.van.kftc.feign.net;

import org.apache.kafka.common.requests.TransactionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import feign.hystrix.FallbackFactory;
import prj.yong.payment.approval.van.kftc.domain.entity.ClientRxTransanction;

@FeignClient(name="van-fdk", fallbackFactory = FdkFeignClientFallbackFactory.class)
public interface NetCancelFeignClient {
    @GetMapping("/van/fdk/mang/cancel/v1.0")
    TransactionResult mangCancel(@RequestBody ClientRxTransanction clientRxTransanction) throws Exception;
}

@Component
class FdkFeignClientFallbackFactory implements FallbackFactory<NetCancelFeignClient> {

    @Override
    public NetCancelFeignClient create(Throwable cause) {
        return new NetCancelFeignClient(){
            private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

            @Override
            public TransactionResult mangCancel(ClientRxTransanction clientRxTransanction) throws Exception {
                // TODO Auto-generated method stub
                return null;
            }
        };
    }
    
}
