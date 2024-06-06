1. Resource 구현체 목록
- UrlResource : java.net.URL을 래핑한 버전, 다양한 종류의 Resource에 접근 가능하지만 기본적으로 http(s) 로 원격 접근
- ClassPath : classpath(소스코드를 빌드한결과(기본적으로 target/classes 폴더)) 하위의 리소스 접근시 사용
- FileSystemResource : file 다루기위한 리소스 구현체
- ServeltContextResource, InputStreamResource, ByteArrayResource : Servlet 어플리케이션 루트 하위 파일, InputStream, ByteArrayInput 스트림을 가져오기 위한 구현체 

2. AOP (관점지향 프로그래밍) Aspect Oriented Programming
- 여러 메서드에 동일한 코드가 반복된다면 -> AOP 사용
- 공통적인 관심사(로깅, 트랜잭션, 인증)을 여러 메서드의 호출 전/후에 원할때마다 손쉽게 추가
- 용어가 중요  
Aspect : 여러 클래스나 기능에 걸쳐서있는 관심사, 그들을 모듈화함 ex) @Transactional @Cacheable  
Adive : AOP에서 실제로 적용하는 기능(로깅, 트랜잭션, 캐시, 인증 등)  
Join point : 모듈화된 특정 기능이 실행될 수 있는 연결 포인트  
Pointcut : Join point 중 해당 Aspect를 적용할 대상을 뽑을 조건식  
Traget Object : Advice가 적용될 대상 오브젝트  
AOP Proxy : 대상 오브젝트에 Aspect를 적용하는 경우 Advice를 덧붙이기 위해 하는 작업  
주로 CGLIB(Code Generation Library, 실행중 실시가능로 코드를 생성하는 라이브러리) 프록시를 사용해 프록싱 처리  
Weaving : Advice를 비즈니스 로직 코드에 삽입하는 것  

AspectJ : AOP를 제대로 사용하기위해 필요한 라이브러리

3. Validation
- 유효성 검증, 사용자 또는 타 서버의 요청(http request) 내용에서 잘못된 내용이 있는지 확인하는 행위
- 종류
데이터 검증 : 필수 데이터 존재 유무 / 값의 범위 / 특정 형식에 맞춘 데이터  
비즈니스 검증 : 서비스에 정책 따라 데이터 확인해 검증 / 외부 API호출하거나 DB의 데이터까지 조회해 검증하는 경우도 존재  
- Spring의 Validation
Java Bean Validation : JavaBean 기반으로 간편하게 개별 데이터 검증 -> 요즘 가장많이 활용  
@NotBlank() / @Size(max=64, message="") / @Email()  
-> 어노테이션 명시후 @Vaild머노테이션을 @RequestBody에 달면 수행  
Spring vaildator 인터페이스 구현통한 Validation : support()와 validate() 두개의 메서드 이용   
support() : validator가 동작할 조건정의, 주로 class 타입 비교  
validate() : 원하는 검증 진행  
- 주의 사항 및 패턴
주의사항 : 너무 여러군데 픝어져있으면 테스트 및 유지보수성 떨어짐 -> 가능한 로직 초기에 수행후 실패시에는 exception을 던지는게 처리 편함   
실무 활용 패턴
요청 dto에서 java bean validation으로 단순 데이터 1차 검증 -> 로직 초기 2차로 비즈니스 검증후 실패시에 custom eception해서 예외를 던지고 예외처리해 응답 생성  
<br/>          
- Data Binding : 사용자나 외부 서버의 요청 데이터를 특정 도메인 객체에 저장해 프로그램의 Request에 담아주는것  
- 종류  
Converter<S, T> Interface : Source라는 타입 받아서 Target이라는 타입으로 변환해주는 interface  
Formatter : 특정 개체 <-> String 간 변환 담당 / 실제로 더많이 사용
    
    
    
    
    
