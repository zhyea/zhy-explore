package org.chobit.core;

import org.chobit.core.proto.TencentRta;
import org.chobit.core.req.TencentReq;
import org.junit.Assert;
import org.junit.Test;

import static org.chobit.core.Config.*;
import static org.chobit.core.constant.DeviceIdType.*;

public class TencentRequestTest extends AbstractRtaTest {


    private final String host = RTA_TEST;

    private final TencentReq req = new TencentReq();


    @Test
    public void applyNewIdfa() {
        String rtaId = "11111";
        String idfa = "FF30B203-BBBB-4EF5-DDDD-9A32A0D97FB2";
        TencentRta.RtaResponse response = execute(req, host, null, IDFA, idfa);
        assertApply(response, rtaId);
    }


    @Test
    public void excludeHistoryIdfa() {
        String idfa = "FF30B203-D3C2-4EF5-B66F-9A32A0D97FB2";
        TencentRta.RtaResponse response = execute(req, host, null, IDFA, idfa);
        assertExclude(response);
    }


    @Test
    public void applyNewImei() {
        String rtaId = "11111";
        String imei = "869067033888875";
        TencentRta.RtaResponse response = execute(req, host, null, IMEI, imei);
        assertApply(response, rtaId);
    }

    @Test
    public void excludeHistoryImei() {
        String imei = "866952035861413";
        TencentRta.RtaResponse response = execute(req, host, null, IMEI, imei);
        assertExclude(response);
    }


    //@Test
    public void frequencyCtrl() {
        String expectRtaId = "10180";
        String imei = "866952035861413";
        TencentRta.RtaResponse response = execute(req, host, null, IMEI, imei);
        assertApply(response, expectRtaId);
    }

    //@Test
    public void all() {
        String idfa = "FF30B203-BBBB-4EF5-DDDD-9A32A0D97FB2";
        TencentRta.RtaResponse response = execute(req, host, PROD_RTA_IDS, IDFA, idfa);
        System.out.println(response.getCode());
        System.out.println(response.getOutTargetIdList());
    }


    private void assertApply(TencentRta.RtaResponse response, String rtaId) {
        int code = response.getCode();
        Assert.assertEquals(0, code);
        System.out.println(response.getOutTargetIdList());
        boolean containsRta = response.getOutTargetIdList().contains(rtaId);
        Assert.assertTrue(containsRta);
    }


    private void assertExclude(TencentRta.RtaResponse response) {
        int code = response.getCode();
        Assert.assertEquals(1, code);
    }


}
