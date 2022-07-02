package prj.yong.payment.approval.van.kftc.feign.fdk;

import org.apache.kafka.common.requests.TransactionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import feign.hystrix.FallbackFactory;
import prj.yong.payment.approval.van.kftc.domain.TransactionHistory;

@FeignClient(name="van-fdk", fallbackFactory = FdkFeignClientFallbackFactory.class)
public interface FdkFeignClient {
    @GetMapping("/van/fdk/mang/cancel/v1.0")
    TransactionResult mangCancel(@RequestBody TransactionHistory transactionHistory) throws Exception;
}

@Component
class FdkFeignClientFallbackFactory implements FallbackFactory<FdkFeignClient> {

    @Override
    public FdkFeignClient create(Throwable cause) {
        return new FdkFeignClient(){
            private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

            @Override
            public TransactionResult mangCancel(TransactionHistory transactionHistory) throws Exception {
                // TODO Auto-generated method stub
                return null;
            }
        };
    }
    
}
