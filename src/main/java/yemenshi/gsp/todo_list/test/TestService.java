package com.yemenshi.gsp.test;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TestService {

    private final TestDAO dao;

    public String test() {
        dao.test();

        return "finished";
    }
}
