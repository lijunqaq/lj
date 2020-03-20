package com.yizhu.lj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
//@MapperScan("com.yizhu.lj.dao.mapper")
//@EnableRetry
public class LjApplication {

    public static void main(String[] args) {
        SpringApplication.run(LjApplication.class, args);
        System.err.println("(♥◠‿◠)ﾉﾞ  伍六七启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .---.       ____     __        \n" +
                " |   |    \\      \\   \\   /  /    \n" +
                " |   | ( ' )  |       \\  _. /  '       \n" +
                " |   |(_ o _) /        _( )_ .'         \n" +
                " |   |(_,_).' __  ___(_ o _)'          \n" +
                " |   |\\ \\  |  ||   |(_,_)'         \n" +
                " |   | \\ `'   /  |   `-'  /           \n" +
                " |   ------  \\    /  \\      /           \n" +
                " ''-'------.   `'-'    `-..-'              ");
    }


}

