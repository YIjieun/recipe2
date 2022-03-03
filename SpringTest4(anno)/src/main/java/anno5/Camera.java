package anno5;

import org.springframework.stereotype.Component;

/*
 * 빈즈에 관련된 클래스가 30개 이상
 * 
 *  <bean id="camera" class="anno5.Camera"/>=>수동으로 등록하는 방식
 *  ,,,,,
 * 
 * @Conponent=>클래스와 관련된 어노테이션
 * 스프링컨테이너가 어느 특정패키지를 지정-> 자동적으로 그 패키지에 들어가 있는 모든 클래스 중에서
 * @Conponent가 붙어있는 클래스를 빈즈로 등록시켜준다.
 */
@Component
public class Camera {}
