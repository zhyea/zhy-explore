package org.chobit.spring.verifier;

import org.chobit.spring.TestBase;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class TraceClewVerifierTest extends TestBase {


    @Resource
    private TraceClewVerifier verifier;


    @Test
    public void start() {
        verifier.start();
        verifier.start();
    }

}
