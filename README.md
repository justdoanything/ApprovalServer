# PaymentApprovalServer
### ➕ Tech Badge
![SpringBoot Badge](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white)
![PostgreSQL Badge](https://img.shields.io/badge/POSTGRESQL-4479A1?style=for-the-badge&logo=POSTGRESQL&logoColor=white)
![Hystrix Badge](https://img.shields.io/badge/Hystrix-E50914?style=for-the-badge&logo=Netflix&logoColor=white)
![Ribbon Badge](https://img.shields.io/badge/Ribbon-E50914?style=for-the-badge&logo=Netflix&logoColor=white)
![Eureka Badge](https://img.shields.io/badge/Eureka-E50914?style=for-the-badge&logo=Netflix&logoColor=white)
![API Gateway Badge](https://img.shields.io/badge/Gateway-E50914?style=for-the-badge&logo=Netflix&logoColor=white)
![Kafka Badge](https://img.shields.io/badge/Kafka-231F20?style=for-the-badge&logo=ApacheKafka&logoColor=white)
![Docker Badge](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)
![Flyway Badge](https://img.shields.io/badge/Flyway-BD0707?style=for-the-badge&logo=flyway&logoColor=white)

### ➕ 프로젝트 구현
  - 결제 승인 서버를 MSA로 구현해보고 Hystrix, Ribbon, Eureka, Kafka 등을 적용해본다.
  - 배포는 Docker Container 기반으로 되도록 한다.
  - 프로젝트 구현은 한 번에 모든 기술을 적용하지 않고 Hystrix부터 한개씩 차례대로 적용해보면서 각 기능이 하는 동작과 이점에 대해서 알아본다. 각 동작마다 새로운 Branch를 생성한다.
    - `main` : feign 등 프로젝트 기능 개발
    - `P01-hystrix` : hystrix 적용
    - `P02-eureka` : Eureka Server, Client 적용
    - `P03-gateway` : Gateway 적용
  - Dockerfile 및 테스트 코드까지 작성한다.
  - 구현하면서 메모한 내용 : https://github.com/justdoanything/self-study/blob/main/WIS/04%20MSA.md
  - 참고 모델
    
    ![image](https://user-images.githubusercontent.com/21374902/176366449-68b59fc8-97c7-49e6-b791-6a5e11b28fd0.png)

### ➕ 구현 내용
MSA을 공부하고 Off-line 결제 승인을 중계해주는 승인 서버를 MSA Architecture를 사용하여 구현해본다.
결제 흐름은 IC 단말기로부터부터 시작되는 승인 요청 데이터를 수신하고 VAN사 중계 혹은 카드사로 직승인 요청을 전달하고 처리 결과를 Client(POS 프로그램)로 응답해준다.
  - POS 프로그램 : Postman, Swagger
  - VAN, 카드사 : Mok Server

### ➕ 프로그램 기능 요구사항
  - POS 프로그램으로부터 전달받은 승인 데이터로 VAN 혹은 카드사에 맞는 `요청 데이터를 생성하고 송신`하고 POS 프로그램에게 응답한다. `망취소` 기능도 갖고 있어야 한다.
  - `망취소` : VAN 혹은 카드사 승인 서버에서 응답이 없거나 timeout이 발생했을 때 취소 전문을 보내는 기능
  - 승인 종류는 `카드사 직승인`, `VAN 승인`으로 크게 2가지로 분류한다.
  - `카드사 직승인` : 특정 카드사로 결제한 승인 건은 카드사에 맞는 전문을 생성하고 카드사로 직승인 전문을 요청한다.
  - `VAN 승인` : `VAN Fee 비율`에 따라 특정 VAN으로 승인 전문을 요청한다.
  - 승인 서버는 모든 트랜잭션을 Kafka를 통해 Message Queue에 저장하고 CQRS 서버가 결제 `대사` 및 `정산`을 위해서 각 트랜잭션을 DB에 저장 및 후처리를 한다.
### ➕ 서비스 기능 요구사항
  - #### Gateway
    - 인증/인가 처리
    - 송수신 전문 처리
      - Header/Body 분리
      - 데이터 압축/압축해제
      - 데이터 암/복호화
    - 승인 종류에 따른 서비스 분기 (VAN 서비스 혹은 카드사 서비스)
      - VAN 승인의 경우 VAN Fee에 따른 분배가 이뤄져야함
    - 1개의 VAN/카드사에서 1분 내 10회 이상 에러가 발생한다면 다른 VAN으로 승인을 우회하고 알람을 보내야함

  - #### Micro Service
    - VAN 혹은 카드사에 맞는 승인 전문 생성 및 전송
    - 카드 승인, 현금 영수증 등 거래 유형에 따라 전문 형식이 달라지기 때문에 이를 능동적으로 수용해야 한다.
    - 망취소 기능을 갖고 있어야하며 단일 VAN을 사용할 경우 망취소 기능이 동작하지 않도록 해야 한다.
    - 정산을 위한 데이터 저장
    - 모든 Transaction을 Message Queue에 저장 (Kafka)

  - #### CQRS
    - Message Queue를 구독하면서(Subscriber) 정산 데이터 처리, 보상 트랜잭션 처리 기능을 일부분 수행
    - 최종 결과가 실패 혹은 망취소 실패인 경우, 망취소를 수행했는데 그것도 실패가 났을 경우 알람을 해주거나 해당 거래건에 대해서 처리하는 기능이 필요하다.
    - 필요하면 운영자를 위한 Dashboard를 만들 때 사용한다.
    - Transaction은 여러 서비스에서 기록되기 때문에 한 거래건에 대해서 최종 상태를 잘 관리해야하며 운영자를 위해서 이력을 볼 수 있으면 좋다.

  - #### Container
    - 각 서비스는 여러개의 Container로 구성해서 Scailing 구성이 가능하도록 함
    - 여러개의 Container는 단일 Endpoint를 갖고 Eureka Server에 등록됨

### ➕ MSA 서비스의 이점
  - 특정 VAN/카드사가 동작하지 않아도 우회 승인을 자동으로 할 수 있다.
  - 트랜잭션이 많이 일어나는 특정 카드사에 대해서만 Scale을 조정할 수 있어서 비용을 절약할 수 있다.\
    (VAN사는 VAN FEE에 따라 Scale을 같이 적용하면 되고 카드사 이벤트가 있거나 사용량이 많은 특정 카드사에 대한 Scale을 유동적으로 적용할 수 있다.)
  - 각 Micro Service는 다른 Service와 VAN/카드사에 영향을 받지 않는다.
  - Client는 모든 VAN/카드사가 Down되지 않는 이상 결제는 정상적으로 가능하다.
  - CQRS 서버에서 망취소에 대한 검증을 할 수 있다.
  - 정합성이 맞지 않은 승인 트랜잭션에 대해서 선감지 및 처리를 할 수 있다.

### ➕ 사용 기술 및 아키텍처
  - #### Service Framework : ![SpringBoot Badge](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white)
  - #### Database : ![PostgreSQL Badge](https://img.shields.io/badge/PostgreSQL-4479A1?style=for-the-badge&logo=POSTGRESQL&logoColor=white) ![Flyway Badge](https://img.shields.io/badge/Flyway-BD0707?style=for-the-badge&logo=flyway&logoColor=white)
  - #### Circuit Breaker : ![SpringCloud Badge](https://img.shields.io/badge/SpringCloud-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)![Hystrix Badge](https://img.shields.io/badge/Hystrix-E50914?style=for-the-badge&logo=Netflix&logoColor=white)
  - #### Load Balance : ![SpringCloud Badge](https://img.shields.io/badge/SpringCloud-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)![Ribbon Badge](https://img.shields.io/badge/Ribbon-E50914?style=for-the-badge&logo=Netflix&logoColor=white)
  - #### Service Discovery : ![SpringCloud Badge](https://img.shields.io/badge/SpringCloud-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)![Eureka Badge](https://img.shields.io/badge/Eureka-E50914?style=for-the-badge&logo=Netflix&logoColor=white)
  - #### API Gateway : ![SpringCloud Badge](https://img.shields.io/badge/SpringCloud-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)![API Gateway Badge](https://img.shields.io/badge/Gateway-E50914?style=for-the-badge&logo=Netflix&logoColor=white)
  - #### Message Queue : ![Spring Badge](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)![Kafka Badge](https://img.shields.io/badge/Kafka-231F20?style=for-the-badge&logo=ApacheKafka&logoColor=white)
  - #### Container Management : ![Docker Badge](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)
  - #### Testing : 
  - #### Infra : AWS, SAM, JPA, Flyway
![image](https://user-images.githubusercontent.com/21374902/152474692-b7a595bf-89eb-4e34-b93a-c5c912da3194.png)

### ➕ 프로젝트 테스트 구동
- 아래의 순서대로 진행
- MYSQL 자원이 생성되지 않았으면 실행
  - `docker run -p 3310:3306 -e MYSQL_ROOT_PASSWORD=yongwoo -e MYSQL_DATABASE=approval -d --name mysql-approval mysql:8.0`
  - `docker exec -it mysql-approval /bin/bash`
  - `mysql -u root -p`
  - `yongwoo` 입력
  - `create database ktfc` 등 필요한 데이터베이스 생성
- Eureka Server 구동
  - `eureka/src/main/java/prj/yong/payment/approval/eureka/EurekaApplication.java`
- Gateway 구동
  - `gateway/src/main/java/prj/yong/payment/approval/gateway/GatewayApplication.java`
  - Eureka Server에 Gateway가 등록됐는지 확인 : `http://localhost:8761` 접속 후 확인
  - 


### ➕ 참고 자료
- ###### MSA Architecture : https://github.com/justdoanything/self-study/blob/main/WIS/04%20MSA.md
- ###### 오프라인 결제와 온라인 결제의 정산
  - 온라인 결제 정산
    - 매입요청은 정상적으로 결제승인이 떨어진것에 대하여 가맹점 -> 카드사에게 매입해 달라고 요청(PG사가 대행)
    - 매입요청을 받은 PG사는 이를 카드사에 통보하고 카드사는 고객이 결제한 금액 중 수수료를 제외한 나머지 금액을 PG사에게 대금정산(청구)
    - 보통 승인이랑 매입요청은 동시에 이루어지고, VAN에서 그날 전체 거래건 모았다가 00시에 카드사로 보내는걸 매입청구라고 하는 것 같다.
    - PG 정산
      - 승인>매입요청(PG)>매입청구(VAN)>매입진행(카드사)>대금지급(카드사-PG)>가맹점에정산(PG)
      - 승인이 발생 > PG(가맹점)가 VAN에게 매입 요청 > VAN이 00시에 카드사에서 거래건 모아서 요청 > 카드사가 PG에게 바로 대금 지급 > PG가 가맹점에 정산
        (승인, 매입 fee는 카드사가 VAN에게 따로 지급, 업무대행 수수료이기 때문에 VAN한테 별도로 지급)
        (PG정산은 PG에서 수수료 떼고 별도 지급)
  - 오프라인 결제 정산
    - 대사 > 밴사통해서 전달받은 승인내역중에 누락이있을까봐 가맹점에 직접 대사내역 받음
    - 청구 > 거래건 모두 취합 후 밴사에 오늘거래건 마감이라고 청구넣음
    - 매입 > 우리 청구내역 받은 밴사가 각 카드사에 매입요청
    - 입반송 > 카드사에서 입금인지 아닌지 밴사에 결과내려줌
    - 입반송적용 > 전달받은 입반송결과를 우리가갖고있는 청구내역에 반영함
    - 정산 > 가맹점에서 한달치 통장내역 올려주면 입반송적용한데까지 금액이맞는지 누락된건없는지 짝맞춰봄

### ➕ Kafka 실행
- Zookeeper 실행 : `bin/zookeeper-server-start.sh -daemon config/zookeeper.properties`
- Kafka 실행 : `bin/kafka-server-start.sh -daemon config/server.properties`
- Zookeeper&Kafka 실행 확인 : `netstat -an | grep 2181`
- Kafka topic 생성 : `bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test`
- Kafka topic 생성 확인 : `bin/kafka-topics.sh --list --bootstrap-server localhost:9092`
- Kafka 메세지 발행 : `bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic test`
- Kafka 메세지 소비 : `bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning`

- Reference : https://developer-youngjun.tistory.com/13