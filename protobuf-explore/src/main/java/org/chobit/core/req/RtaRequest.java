package org.chobit.core.req;

import org.chobit.core.constant.DeviceIdType;

import java.io.IOException;
import java.util.List;

public interface RtaRequest<T> {

    /**
     * 请求路径
     *
     * @return 请求路径
     */
    String path();

    /**
     * 请求体
     *
     * @return 请求体
     */
    byte[] body(List<Long> rtaIds, DeviceIdType didType, String id);

    /**
     * 解析响应信息
     *
     * @param content 响应信息
     */
    T parseResponse(byte[] content) throws IOException;
}
