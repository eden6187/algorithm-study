##알고리즘 문제 풀이 시 유념 할 사항

### 1. 입출력 조건 에서 봤을 때 유념 해야 할 숫자 들

> 10억 :
> - java에서 **int**는 대략 **-20억 ~ 20억** 까지를 저장 할 수 있다. 따라서 20억이 넘는 숫자를 저장 해야 할 경우에는 **long형 사용** 을 고려하자.

> 10만 : 
> - 100,000의 제곱 = 100억 즉, **O(N^2)** 의 브루트 포스 알고리즘을 사용하는 것이 아니다.
> - NlogN 알고리즘으로는 풀이가 가능하다.

> 1만 : 
> - 1만의 제곱은 1억 즉, **브루트 포스 알고리즘으로도 시도해 볼 만 하다**는 의미이다.
