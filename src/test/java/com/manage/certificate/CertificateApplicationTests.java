package com.manage.certificate;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class CertificateApplicationTests {

    private static Logger logger = LoggerFactory.getLogger(CertificateApplicationTests.class);

    @Test
    void contextLoads() {
    }

    @Test
    void testBCryptPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encode_pwd_1 = passwordEncoder.encode("234");
        String encode_pwd_2 = passwordEncoder.encode("rabbit");
        logger.info("encode_pwd_1:{}", encode_pwd_1);
        logger.info("encode_pwd_2:{}", encode_pwd_2);
    }

    @Test
    void testBCryptPasswordEncoder2() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean flag_true = passwordEncoder.
                matches("123", "$2a$10$7zt07bPN.3SkWuPiLG87nOlMRyKchFpviFAIBkM3Pcw58fswcga.y");

        boolean flag_false = passwordEncoder.
                matches("root", "$2a$10$xv0vEVrG8iuvhT0q2leGu.PjiGdp4igJHe3EKljn8N/m1AgNSLT5m");

        logger.info("flag_true:{}", flag_true);
        logger.info("flag_false:{}", flag_false);
    }

}
