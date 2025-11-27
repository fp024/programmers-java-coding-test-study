## C# 클로저 함정 코드를 Java에서 테스트

```csharp
var actions = new List<Action>();

for (int i = 0; i < 3; i++)
{
    actions.Add(() => Console.WriteLine(i));  // ← 모두 같은 i를 캡처!
}

foreach (var action in actions)
{
    action();  // 출력: 3, 3, 3 ❌
}
```

위와 같은 스타일의 코드가 Java에서는 어떻게 동작하는지 궁금해서 Java에서 작성해보았다.

```java
class CloserTest {

  @Test
  void test() {
    var actions = new ArrayList<Runnable>();

    for (int i = 0; i < 3; i++) {
      int j = i; // 반복마다 새로운 effectively final 변수 → 개별 값 캡처
      actions.add(() -> System.err.println(j));
    }

    for (var action : actions) {
      action.run(); // 출력: 0 1 2
    }
  }

  @Test
  void testSharedValueLikeCSharp() {
    var actions = new ArrayList<Runnable>();
    AtomicInteger shared = new AtomicInteger();

    for (int i = 0; i < 3; i++) {
      shared.set(i); // 매 반복마다 같은 shared 객체 값을 덮어씀
      actions.add(() -> System.err.println(shared.get())); // 모든 람다가 동일 shared 참조
    }

    // C#은 for 종료 후 i == 3이라 람다 실행 시 3,3,3 출력
    // 여기서는 루프 내 마지막 set 값(2)을 본 그대로 2,2,2 출력
    actions.forEach(Runnable::run); // 출력: 2 2 2
  }
}
```