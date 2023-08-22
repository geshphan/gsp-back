package com.yemenshi.gsp.test;

import com.yemenshi.gsp.test.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService service;

    @RequestMapping("/test")
    public String testSave() {
        return service.test();
    }
}
