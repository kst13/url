문제  
전 세계에서 사용할 수 있는 URL 단축 서비스를 설계  
이 서비스는 1일 1억 개 이상의 URL 생성 및 100억 회 이상의 요청을 처리할 수 있어야 함

요구 사항  
1.단축 URL 생성 및 조회  
- 주어진 긴 URL을 짧은 URL로 변환 (예: https://long-url.com/abcd → https://short.ly/xyz123)  
- 짧은 URL을 원본 URL로 리다이렉트  

2. 고가용성 및 확장성  
- 초당 수천 개의 요청을 처리할 수 있도록 설계  
- 무중단 서비스를 유지해야 함  

3. 데이터 저장  
- 단축 URL의 매핑을 저장하는 DB 선택 및 설계   
- 단축 URL을 생성할 때 중복되지 않는 고유한 키를 생성해야 함  

4. 캐싱 및 성능 최적화  
- Redis 캐싱 적용을 고려  
- 데이터베이스 부하를 줄이는 전략 적용  

5. 보안 및 분석 기능  
- 만료 시간(Expiration Time) 설정 가능  
- 특정 사용자의 단축 URL 생성 수 제한 (Rate Limiting 적용)  
- 접속 통계 제공 (IP, 클릭 수, 국가별 분포 등)  


추가 질문 (확장 요구사항)  
이 서비스의 트래픽이 폭발적으로 증가하면 어떻게 확장할 것인가?  
URL 키를 충돌 없이 생성하는 방법은?  
데이터베이스 장애가 발생하면 어떻게 복구할 것인가?  

💡 보완할 기술 스택  
✅ Redis 캐싱 적용 (성능 최적화)  
✅ Kafka 이벤트 처리 (대량 트래픽을 고려한 비동기 아키텍처)  
docker run --rm bitnami/kafka:latest kafka-storage.sh random-uuid
✅ CQRS + 이벤트 소싱 적용 (읽기/쓰기 분리)  
✅ Docker + Kubernetes 기반 배포  
✅ AWS S3/CloudFront 연동하여 분석 대시보드 구축  
