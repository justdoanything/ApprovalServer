package prj.yong.payment.approval.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import lombok.Data;
import reactor.core.publisher.Mono;

@Component
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {
  public GlobalFilter() {
      super(Config.class);
  }
  
  @Override
  public GatewayFilter apply(Config config) {
    return ((exchange, chain) -> {
      ServerHttpRequest request = exchange.getRequest();
      ServerHttpResponse response = exchange.getResponse();
      
      if (config.isPreLogger()) {
        // Pre Filter
        System.out.println("This is pre global filter ");

      }
      
      return chain.filter(exchange).then(Mono.fromRunnable(()->{
        if (config.isPostLogger()) {
          // Post Filter
          System.out.println("This is post global filter ");
        }
      }));
    });
  }

  @Data
  public static class Config {
    private String baseMessage;
    private boolean preLogger;
    private boolean postLogger;
  }
}
