package com.legalease.config;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class DatabaseTestExecutionListener implements TestExecutionListener {

    @Override
    public void beforeTestClass(final TestContext testContext) {

        testContext.getApplicationContext()
                .getAutowireCapableBeanFactory()
                .autowireBean(this);
    }

    @Override
    public void beforeTestMethod(final TestContext testContext) {
        //TODO clean tables between tests
    }
}
