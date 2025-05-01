package com.mycom.myapp.user.controller;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mycom.myapp.user.dto.TestDto;
import com.mycom.myapp.user.dto.TestResultDto;
import com.mycom.myapp.user.dto.TestUserDto;
import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
public class TestController {
    // #1
    @GetMapping("/hello")
    public void hello() {
        log.info("hello");
    }

    // #2
    @PostMapping("/param1")
    public void param1(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name
    ) {
        log.info("param1");
        log.info("id : " + id + " name : " + name);
    }

    // #3
    @PostMapping("/param2")
    public void param2(TestDto testDto) {
        log.info("param2");
        log.info("id : " + testDto.getId() + " name : " + testDto.getName());
    }

    // #4
    @PostMapping("/response1")
    public String response1(TestDto testDto) {
        log.info("response1");
        log.info("id : " + testDto.getId() + " name : " + testDto.getName());
        return "success";
    }

    // #5
    @PostMapping("/response2")
    public TestResultDto response2(TestDto testDto) {
        log.info("response2");
        log.info("id : " + testDto.getId() + " name : " + testDto.getName());
        TestResultDto testResultDto = new TestResultDto();
        testResultDto.setResult("success");
        return testResultDto;
    }

    // #6 #5 와 동일
    @PostMapping("/response3")
    public TestResultDto response3(TestDto testDto) {
        log.info("response3");
        log.info("id : " + testDto.getId() + " name : " + testDto.getName());
        TestResultDto testResultDto = new TestResultDto();
        testResultDto.setResult("success");
        return testResultDto;
    }

    // #7
    @PostMapping("/response4")
    public TestResultDto response4(TestDto testDto) {
        log.info("response4");
        log.info("id : " + testDto.getId() + " name : " + testDto.getName());
        TestResultDto testResultDto = new TestResultDto();

        TestUserDto testUserDto = TestUserDto.builder()
                .id(1L)
                .name("홍길동")
                .email("hong@gildong.com")
                .phone(List.of("010-0000-0000", "010-1111-1111"))
                .build();
        testResultDto.setTestUserDto(testUserDto);
        testResultDto.setResult("success");
        return testResultDto;
    }
}