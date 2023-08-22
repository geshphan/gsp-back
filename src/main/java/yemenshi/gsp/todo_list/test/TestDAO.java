package com.yemenshi.gsp.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAO {

    @PersistenceContext
    private EntityManager em;

    public void test() {
        TestEntity te = new TestEntity();
        te.setContent("gsp test content");
        em.persist(te);

    }
}
