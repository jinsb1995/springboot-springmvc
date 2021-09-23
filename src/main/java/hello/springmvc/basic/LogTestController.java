package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());
//    private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        // 이렇게 + 로 출력을 하게 되면
        // log.trace를 읽기도 전에 ()안의 문자열들을 + 로 인해서 연산을 하게 되므로
        // 쓸데없는 자원 낭비가 발생한다.
        log.trace("trace log = " + name);

        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name);   // 개발서버에서 보는 로그이다.
        log.info("info log = {}", name);
        log.warn("warn log = {}", name);
        log.error("error log = {}", name);

        return "ok";
    }

}
