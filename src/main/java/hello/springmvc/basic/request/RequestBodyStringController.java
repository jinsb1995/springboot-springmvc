package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}", messageBody);
        responseWriter.write("ok");
    }


    /**
     * HttpEntity : HTTP header, body 정보를 편리하게 조회
     *    메시지 바디 정보를 직접 조회
     *    요청 파라미터를 조회하는 기능과 관계 없음 @RequestParam X, @ModelAttribute X
     * HttpEntity 는 응답에도 사용 가능
     *    메시지 바디 정보 직접 반환
     *    헤더 정보 포함 가능
     *    view 조회X
     * HttpEntity 를 상속받은 다음 객체들도 같은 기능을 제공한다.
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();
//        httpEntity.getHeaders();
        log.info("messageBody = {} ", messageBody);

        return new HttpEntity<>("ok");
    }
    @PostMapping("/request-body-string-v33")
    public HttpEntity<String> requestBodyStringV33(RequestEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();
//        httpEntity.getHeaders();
        log.info("messageBody = {} ", messageBody);

        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }

//    @RequestBody는 Body정보
//    HttpEntity는, @RequestHeader - 헤더정보
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {

        log.info("messageBody = {} ", messageBody);

        return "ok";
    }

}
