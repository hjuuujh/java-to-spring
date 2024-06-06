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
