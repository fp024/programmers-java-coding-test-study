# 프로그래머스 Java 코딩 테스트 - 스터디

> 원래는 코딩 테스트 연습을 하는게 JS/TS로 하는 것이 매우 편해서 아래서 진행하고 있었는데..
>
> * https://github.com/fp024/programmers-js-coding-test-study
>
> JavaScript가 적용되지 않은 문제도 있어서, Java 프로젝트도 다시 진행하기로 했다.
>
> * C, C++, Java 문제는 반드시 준비 되어있는 것 같다.



### 스터디 프로젝트  구성

* Spring + JUnit + Gradle 프로젝트이고 Boot 프로젝트는 아니다.
*  VSCode에서 Test 환경에서 Mockito agent 추가를 하거나 -parameters 컴파일러 옵션 추가를 위해 Node 스크립트를 추가한 부분이 있다.
*  💡 Java 버전은 프로그래머스의 Java컴파일러가 14 버전이긴한데, 21 버전으로 사용하고 제출할 때 컴파일 오류가 발생하는 코드가 있다면 14버전에 호환되게 바꿔서 쓰도록 하자!



### 프로젝트 초기화 (only VSCode 😂)

* **VSCode 환경을 위한 프로젝트 설정**

  
  > 💡Node 25 부터는 Corepack이 기본 포함되어있지 않아서 먼저 corepack을 전역 설치를 해줘야한다.
  >
  > ```sh
  > npm install -g corepack
  > ```
  
  * corepack 활성화
  
    ```sh
    corepack enable
    ```
  
  * 프로젝트 디펜던시 설치
  
    ```
    pnpm install
    ```
  
  * 프로젝트 초기화
  
    ```
    pnpm init-project
    ```
  
    * 다음 3가지 테스크를 모두 실행시킨다.
  
      * **add-javac-parameters-option**
  
        * `.settings/org.eclipse.jdt.core.prefs` 파일에  다음내용 추가하여 `-parameters` 옵션추가가 기본 값이 되도록한다.
  
          ```properties
          org.eclipse.jdt.core.compiler.codegen.methodParameters=generate
          ```
  
      * **copy-mockito-jar**
  
        * `build.gradle`에 정의해둔 `copyMockitoJar` 테스크를 실행
  
          `${projectRoot}/javaagent-libs` 경로에 현재 프로젝트에 디펜던시된 `mockito-core.jar`를 복사한다.
  
      * **init-test-jvm-options**
  
        * VSCode에서 테스트를 실행시킬 때, Gradle이나 Maven+Surefire에다 정의한 jvm 옵션을 로드할 수 없어서 `.vscode/settings.json`에 직접 jvm 옵션을 추가
  
          ```
          -javaagent:\${workspaceFolder}/javaagent-libs/${mockitoJar}
          ```
  
        * Java 21부터는 보안상의 이유로 라이브러리가 자체 JVM에 Java agent를 동적으로 attach 하는 행위를 제한하여 경고가 나오므로 위처럼 직접 -javaagent 옵션에 명시하는 것을 추천함
  
        
  
    > 💡 위와 같은 동작 코드들이... IntelliJ라면 Gradle의 build.gradle이나 Maven의 pom.xml에 정의된 내용을 잘 인식해서 처리하는 부분인데, VSCode는 아직 그러지 못해서 위와 같은 헬퍼 JS 스크립트들을 만들게 되었다. 😂



### JUnit 테스트

JS/TS 환경이라면, 그냥 파일내에서 함수를 실행하면서 console함수로 출력만하면되서 간편한데, Java 환경에서는 JUnit의 테스트 케이스로 만드는게 편해서 그렇게 했다.

* 전체 테스트 실행

  ```sh
  gradle clean test
  ```

* lv00 패키지만 테스트

  ```sh
  gradle test --tests "org.fp024.lv00.*"
  ```

* 특정 클래스만 테스트

  ```sh
  gradle test --tests "org.fp024.lv00.BeginnerExam01Tests"
  ```

* 특정 클래스의 메서드만 테스트

  ```sh
  gradle test --tests "org.fp024.lv00.BeginnerExam06Tests.test_120956"
  ```
