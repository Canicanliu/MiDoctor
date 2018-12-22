package com.can.minidoctor.core;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 11:01 2018/12/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
@Rollback()
public abstract class BasicTest {

    protected static final Logger logger = LoggerFactory.getLogger(BasicTest.class);

}
