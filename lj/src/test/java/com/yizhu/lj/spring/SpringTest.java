package com.yizhu.lj.spring;

import com.yizhu.lj.AqsTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 功能描述
 *
 * @author Lj
 * @date 2019/9/27
 */
public class SpringTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext apc = new AnnotationConfigApplicationContext(AqsTest.class);

        apc.getBean("11");
    }
}
