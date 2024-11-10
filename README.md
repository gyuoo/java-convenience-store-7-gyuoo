# 🏪 편의점

- - -

## 🚀 기능 요구 사항

- - -

### <결제 시스템 구현>

- 사용자가 입력한 상품의 가격과 수량 기반, 최종 결제 금액을 계산
- 총 구매액은 상품별 가격과 수량을 곱하여 계산
    - 프로모션 및 멤버십 할인 정책을 반영하여 최종 결제 금액 산출
- 구매 내역, 산출한 금액 정보를 영수증으로 출력
- 영수증 출력 후 추가 구매 **진행** / **종료** 여부를 선택할 수 있음
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException` 발생
    - `[ERROR]`로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받음
    - 상황에 맞는 안내를 함께 출력
- `Exception`이 아닌 `IllegalArgumentException`, `IllegalStateException` 등과 같은 명확한 유형 처리

**재고 관리**

- 각 상품의 재고 수량을 고려하여 결제 가능 여부 확인
- 고객이 상품을 구매할 때마다 결제된 수량만큼 해당 상품의 재고에서 차감하여 수량을 관리
- 재고를 차감함으로써 시스템은 **최신 재고 상태**를 유지
- 다음 고객이 구매할 때 정확한 재고 정보 제공

**프로모션 할인**

- 오늘 날짜가 프로모션 기간 내에 포함된 경우에만 할인 적용
- 프로모션은 N개 구매 시 1개 무료 증정(Buy N Get 1 Free)의 형태
    - 1+1 또는 2+1 프로모션이 각각 지정된 상품에 적용
    - 동일 상품에 여러 프로모션이 적용되지 않음
- 프로모션 혜택은 프로모션 재고 내에서만 적용 가능
- 프로모션 기간 중이라면 프로모션 재고를 우선적으로 차감, 프로모션 재고가 부족할 시 일반 재고 사용
- 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 필요한 수량을 추가로 가져오면 혜택을 받을 수 있음을 안내
- 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제하게 됨을 안내

**멤버십 할인**

- 멤버십 회원은 **프로모션 미적용 금액의 30%** 할인
    - 프로모션 적용 후 남은 금액에 대해 멤버십 할인 적용
    - 멤버십 할인의 최대 한도는 **8,000원**

**영수증 출력**

- 고객의 구매 내역과 할인을 요약하여 출력
- 영수증 항목
    - 구매 상품 내역: 구매한 상품명, 수량, 가격
    - 증정 상품 내역: 프로모션에 따라 무료로 제공된 증정 상품의 목록
    - 금액 정보
        - 총구매액: 구매한 상품의 총 수량과 총 금액
        - 행사할인: 프로모션에 의해 할인된 금액
        - 멤버십할인: 멤버십에 의해 추가로 할인된 금액
        - 내실돈: 최종 결제 금액
- 영수증의 구성 요소를 보기 좋게 정렬, 고객이 쉽게 금액과 수량을 확인할 수 있게 함

## 📃 추가된 프로그래밍 요구 사항

- - -

- 메서드의 길이가 10라인을 넘어가지 않도록 구현
- 메서드가 한 가지 일만 잘 하도록 구현
- 입출력을 담당하는 클래스를 별도로 구현

## 📂 패키지 구조

- - -

```

```

## 📌 계획

- - -

- 편의점 결제 시스템의 흐름 파악
- 입출력 요구 사항 확인
- 구현할 기능 목록 정리
- README.md 작성
- 어울리는 디자인 패턴 선정
- 패키지 구조 설계
- 프로젝트 초기 설정
- 구현
- 테스트 코드 작성
- 예외 처리에 대한 검토
- 리팩토링

## 💡 구현할 기능 목록

- - -

### 1. 초기 설정

- [x] 상품 및 프로모션 데이터 파일 로드
    - [x] `products.md`와 `promotions.md` 파일에서 상품 목록과 행사 목록을 파일 입출력을 통해 불러옴
    - [x] 파일 입출력 처리
    - [x] 데이터 파싱
    - [x] 상품 목록, 재고, 프로모션 정보 등으로 분리하여 저장
- [ ] 상품 재고 및 프로모션 적용 가능 여부 초기화
    - [ ] 각 상품의 재고 수량을 초기화
    - [ ] 프로모션 기간과 해당 상품이 프로모션 대상인지 확인
    - [ ] 프로모션 적용 가능 여부 설정

### 2. 사용자 인터페이스 초기화

- [x] 환영 인사 출력
- [x] 현재 재고 상태와 가격, 프로모션 정보 출력
- [x] 프로모션 여부에 따라 상품 정보에 할인 및 증정 혜택을 표시
- [x] 재고가 없는 경우 "재고 없음" 메시지 출력

### 3. 사용자 입력 및 검증

- [x] 구매 상품 및 수량 입력 받기
    - [x] 입력받기 위한 문구 출력
    - [x] 사용자로부터 구매할 상품과 수량을 입력 받음
    - [x] 올바르지 않은 입력 시 오류 메시지를 출력
    - [x] 오류 발생 시 재입력 받기
- [ ] 상품 재고 및 유효성 검사
    - [ ] 사용자가 입력한 상품과 수량의 유효성 확인
    - [ ] 존재하지 않는 상품일 경우 오류 메시지 출력
    - [ ] 재고를 초과한 수량을 입력한 경우 오류 메시지 출력
    - [ ] 오류 발생 시 재입력 받기

### 4. 결제 금액 계산 - 할인 적용

- [ ] 프로모션 혜택 적용
    - [ ] 입력된 상품이 프로모션 기준을 충족하는지 확인
    - [ ] 입력된 수량이 프로모션 기준을 충족하는지 확인
    - [ ] N개 구매 시 1개 무료 증정 규칙에 따라 프로모션 재고에서 증정 상품 차감
        - [ ] 필요시 일반 재고 사용
    - [ ] 구매 수량이 혜택 기준 미달 시, 추가 수량을 가져와 혜택을 받을 것인지 확인
        - [ ] Y / N에 따라 혜택 적용
    - [ ] 프로모션 혜택을 받을 수 없는 경우 정가로 구매할지 여부 확인
        - [ ] 선택적으로 혜택 적용
- [ ] 멤버십 할인 적용
    - [ ] 프로모션 적용 후 남은 금액에 대해 멤버십 할인 적용
    - [ ] 멤버십 회원이라면 프로모션 미적용 금액의 30% 할인을 적용
        - [ ] 최대 할인 한도는 8,000원
    - [ ] 멤버십 할인 적용 여부를 물음
        - [ ] Y / N에 따라 할인 적용

### 5. 영수증 생성 및 출력

- [ ] 구매 내역 표시
    - [ ] 각 항목에는 상품명, 수량, 금액 포함
- [ ] 증정 받은 상품 내역 표시
    - [ ] 각 항목에는 상품명, 수량 포함
- [ ] 총 구매 금액 및 할인 정보 출력
    - [ ] 구매 내역 하단에 총 구매액, 행사 할인액, 멤버십 할인액, 최종 결제 금액 출력

### 6. 추가 구매 처리

- [ ] 영수증 출력 후 감사 인사 전달
- [ ] 추가 구매 여부 확인
- [ ] Y를 선택한 경우, 업데이트된 상품 목록을 다시 출력한 뒤 위와 같은 절차 진행
- [ ] N을 선택한 경우, 구매 종료
- [ ] 잘못된 값 입력의 경우 오류 메시지 출력

### 7. 예외 처리 및 에러 메시지 출력

- [ ] 입력 단계마다 오류가 발생할 경우 IllegalArgumentException 등 명확한 예외 발생
- [ ] 오류 메시지에 따라 재입력받아 잘못된 입력에 대한 즉각적인 피드백 제공

### 8. 테스트 코드 작성

- [ ] JUnit 5와 AssertJ를 사용한 단위 테스트 작성
- [ ] 정상 동작 테스트: 재고 차감, 할인 적용, 프로모션 증정, 멤버십 할인 등 각 정책이 올바르게 적용되는지 확인
- [ ] 예외 처리 테스트: 잘못된 입력 시 올바른 오류 메시지와 예외가 발생하는지 확인
