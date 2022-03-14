package org.chobit.core.req;

import org.chobit.core.constant.DeviceIdType;
import org.chobit.core.proto.TencentRta;
import org.chobit.core.tools.MD5;

import java.io.IOException;
import java.util.List;

import static org.chobit.core.constant.DeviceIdType.*;
import static org.chobit.core.tools.ToolKit.uuid;

public class TencentReq implements RtaRequest<TencentRta.RtaResponse> {
    @Override
    public String path() {
        return "/rta/tencent";
    }

    @Override
    public byte[] body(List<Long> rtaIds, DeviceIdType didType, String did) {
        TencentRta.RtaRequest.Builder builder = TencentRta.RtaRequest.newBuilder();
        builder.setId(uuid());
        builder.setIsPing(false);
        builder.setIsTest(false);

        TencentRta.RtaRequest.Device.Builder deviceBuilder = TencentRta.RtaRequest.Device.newBuilder();
        if (IMEI == didType) {
            String imeiMd5 = MD5.encode(did);
            System.out.println(imeiMd5);
            deviceBuilder.setOs(TencentRta.RtaRequest.OperatingSystem.OS_ANDROID);
            deviceBuilder.setImeiMd5Sum(imeiMd5);
        } else if (IDFA == didType) {
            String idfaMd5 = MD5.encode(did);
            System.out.println(idfaMd5);
            deviceBuilder.setOs(TencentRta.RtaRequest.OperatingSystem.OS_IOS);
            deviceBuilder.setIdfaMd5Sum(idfaMd5);
        } else if (IMEI_MD5 == didType) {
            deviceBuilder.setOs(TencentRta.RtaRequest.OperatingSystem.OS_ANDROID);
            deviceBuilder.setImeiMd5Sum(did);
        } else {
            return null;
        }

        deviceBuilder.setIp("127.0.0.1");
        builder.setDevice(deviceBuilder);

        return builder.build().toByteArray();
    }

    @Override
    public TencentRta.RtaResponse parseResponse(byte[] content) throws IOException {
        TencentRta.RtaResponse.Builder builder = TencentRta.RtaResponse.newBuilder();
        builder.mergeFrom(content);
        return builder.build();
    }
}
