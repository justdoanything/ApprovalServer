# ApprovalServer

### 구현 내용
MSA 구현 교육을 기반으로 Off-line 결제 승인을 중계해주는 승인 서버를 MSA Architecture를 사용하여 구현해본다.
결제 흐름은 IC 단말기로부터부터 시작되는 승인 요청 데이터를 수신하고 VAN사 중계 혹은 카드사로 직승인 요청을 전달하고 처리 결과를 Client(POS 프로그램)로 응답해준다.
  - POS 프로그램 : Postman
  - VAN, 카드사 : Mok Server

### 구현 시나리오
  - 승인 서버는 여러개의 VAN사에 대해서 VAN Fee 비율에 맞게 분배해주고 특정 카드사는 카드사 직승인으로 승인 요청을 한다.
  - POS 프로그램으로부터 전달받은 승인 데이터로 VAN 혹은 카드사에 맞는 요청 데이터를 생성하고 송신한다.
  - VAN 혹은 카드사에서 주는 응답에 따라 응답 전문을 만들어 POS 프로그램에게 전달하며 승인 요청 응답이 없는 것을 고려하여 `망취소` 기능도 구현한다.
  - 승인 서버는 결제 대사 및 정산을 위해서 각 트랜잭션을 DB에 저장한다.

### 승인 서버의 기능
  - Header/Body 생성/분리
  - 송수신 데이터 암/복호화
  - 승인 데이터 분석 (VAN or 카드사 분기 및 VAN Fee)
  - 승인 전문 생성 및 VAN or 카드사로 전송
  - 망취소 기능
  - 기타 데이터 저장 기능

### 사용 기술 및 아키텍처
  - Spring Boot
  - JPA or Mybatis
  - Circuit breaker : Spring Cloud Hystrix
  - Load Balance : Spring Cloud Ribbon
  - Service Discovery : Spring Cloud Eureka
  - API Gateway
  - Message Queue : Kafka

### Docker 환경 구축
