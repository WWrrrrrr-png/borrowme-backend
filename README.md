# BorrowMe Backend

일상 속 혼자일 때 도움이 필요한 사용자와 도움을 제공하는 헬퍼를 매칭하고, 
관리자가 승인·정산하는 흐름을 처리하는 Spring Boot REST API 서버입니다.

## 배포 주소
- API: `http://43.201.10.177/api`
- 인프라: AWS EC2(Ubuntu) + Nginx 리버스 프록시 + systemd

## 기술 스택

- **Language**: Java 17
- **Framework**: Spring Boot 4.0.1
- **Persistence**: MyBatis (mybatis-spring-boot-starter) + MySQL 8
- **Auth**: JWT (jjwt 0.12.6), Spring Security Crypto (BCrypt)
- **External API**: KakaoPay 결제 준비/승인 API
- **Infra**: AWS EC2, Nginx, systemd
- **DB Log**: log4jdbc

> `spring-boot-starter-data-jpa` 의존성이 있으나 실제 DB 접근은 전부 MyBatis로 처리합니다.

## 프로젝트 구조 
src/main/java/com/borrowme
├─ auth/ # 회원가입·로그인·탈퇴 (User/Helper/Admin 공통)
├─ user/ # 도움 요청 CRUD
├─ helper/ # 승인된 요청 조회 및 수락
├─ matching/ # 매칭 조회 및 상태 변경
├─ admin/ # 요청 승인/거절/결제 금액 설정
├─ Kakaopayment/ # 카카오페이 결제 준비/승인
└─ common/
├─ ApiResponse.java / CustomException.java / ErrorCode.java
├─ GlobalExceptionHandler.java / WebConfig.java (CORS)
└─ fliter/JwtAuthFilter.java, util/JwtUtil.java  


## 주요 기능

- **회원가입 / 로그인**: User·Helper·Admin 3개 역할을 독립적으로 인증 처리. 비밀번호는 BCrypt로 암호화.
- **이메일 중복확인**: 가입 전 이메일 사용 가능 여부 조회
- **JWT 인증**: 로그인 성공 시 사용자 id·role을 담은 토큰 발급, `JwtAuthFilter`가 매 요청의 `Authorization` 헤더를 검사
- **회원 탈퇴**: 완전 삭제 대신 개인정보 익명화 처리(외래키 무결성 보존)
- **도움 요청 CRUD**: 요청 등록/조회/수정/삭제
- **관리자 승인/거절/금액 설정**: 대기중 요청 승인·거절, 승인된 요청에 결제 금액 설정
- **헬퍼 요청 수락**: 승인된 요청 조회 및 수락 → 매칭 생성
- **매칭 상태 관리**: User/Helper 각자의 매칭 목록 조회, 상태 변경(`IN_PROGRESS`/`COMPLETED`)
- **카카오페이 결제**: 결제 준비(`/ready`) 시 카카오페이 API 호출, 결제 승인(`/approve`) 시 `pg_token` 검증

## 인증 구조

- JWT는 사용자 id(subject)와 role(claim)을 담아 발급, 유효기간 1시간
- `JwtAuthFilter`는 유효한 토큰이 있으면 요청 속성(`memberId`, `memberRole`)에 정보를 저장할 뿐, 토큰이 없다고 요청 자체를 차단하지는 않음 — 인증이 필요한 각 Controller가 필요 시 role을 직접 검사(예: `AdminController.requireAdmin()`)
- 결제 승인 콜백(`/api/payments/approve`)은 인증 없이 열려있음 — 카카오 외부 리다이렉트로 도달하는 페이지이며, 실제 검증은 `paymentId`+`pg_token`으로 서버가 처리

## API 개요

| 기능 | Method | URL |
|---|---|---|
| User 로그인 | POST | `/api/auth/user/login` |
| 도움 요청 등록 | POST | `/api/requests` |
| 관리자 요청 승인 | PATCH | `/api/admin/requests/{id}/approve` |
| 관리자 결제금액 설정 | PATCH | `/api/admin/requests/{id}/amount` |
| 헬퍼 요청 수락 | POST | `/api/helper/requests/{id}/accept` |
| 결제 준비 | POST | `/api/payments/ready` |
| 결제 승인(카카오 콜백) | GET | `/api/payments/approve` |

## 배포

- systemd 서비스(`borrowme.service`)로 관리, `Restart=always` 설정으로 비정상 종료 시 자동 재기동
- 배포 시 `git pull` → `mvn clean package -DskipTests` → `systemctl restart borrowme`
- 민감정보(`application-private.properties`, `application-real.properties`, `kakaopay.properties`)는 `.gitignore`로 저장소에서 제외

## 트러블슈팅

### 문제: 배포 서버에서 결제 콜백이 로컬 주소로 이동하려 함
**원인**: `kakaopay.approval` 값이 개발용(`application-private.properties`)과 배포용(`application-real.properties`) 파일에 서로 뒤바뀌어 설정되어 있었음
**해결**: 각 프로필이 자기 환경의 프론트엔드 주소를 가리키도록 수정

### 문제: `systemctl restart` 없이 `kill`로 프로세스를 종료해도 즉시 되살아남
**원인**: 서비스 파일에 `Restart=always`가 설정되어 있어, 수동 `kill` 직후 systemd가 예전 jar로 즉시 재기동시킴
**해결**: 프로세스 관리는 `kill` 대신 `systemctl restart`로 통일

## 정리한 죽은 코드
- `com.borrowme.borrowme.order` 패키지 — 어떤 Controller에서도 참조되지 않던 미사용 코드
- 이메일 발송 관련 설정(`mail/email.properties`) — 실제 발송 로직(`JavaMailSender`) 미구현 상태로 방치되어 있던 설정