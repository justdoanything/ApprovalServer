package prj.yong.payment.approval.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
  @Bean
  public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
    return builder.routes()
                    .route(route -> route.path("/van/**")
                      .filters(filter -> filter.addRequestHeader("sampleRequestHeader", "sample")
                                               .addResponseHeader("sampleResponseHeader", "sample"))
                      .uri("https://localhost:8081/"))   // lb://FDK : van 공통으로 적용하려면 LoadBalancer에도 van으로 공통 매핑 해야함.
                    .route(route -> route.path("/card/**")
                      .filters(filter -> filter.addRequestHeader("sampleRequestHeader", "sample")
                                               .addResponseHeader("sampleResponseHeader", "sample"))
                      .uri("https://localhost:8082/"))  
                  .build();
  }
}
