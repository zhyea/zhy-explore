package org.chobit.spring.web;

import org.chobit.spring.model.UserRequest;
import org.junit.jupiter.api.Test;

public class IndexControllerTest extends ApiTestBase{

    @Override
    protected String parentPath() {
        return "";
    }



    @Test
    public void user(){
        String path = "/user";

        UserRequest req = new UserRequest();

        String response = testPost(path, req);
        System.out.println(response);
    }

}
