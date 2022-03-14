package org.chobit.core;

import com.google.protobuf.Int32Value;
import org.chobit.core.proto.ByteDanceRta;
import org.chobit.core.req.ByteDanceReq;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.chobit.core.Config.*;
import static org.chobit.core.constant.DeviceIdType.*;
import static org.chobit.core.tools.ToolKit.listOf;

public class ByteDanceRequestTest extends AbstractRtaTest {


    private final String host = RTA_PROD;

    private final ByteDanceReq req = new ByteDanceReq();


    @Test
    public void applyNewIdfa() {
        Long rtaId = 12173L;
        String idfa = "FF30B203-BBBB-4EF5-DDDD-9A32A0D97FB2";
        ByteDanceRta.RtaResponse response = execute(req, host, listOf(rtaId), IDFA, idfa);
        assertApply(response);
    }


    @Test
    public void excludeHistoryIdfa() {
        Long rtaId = 12173L;
        String idfa = "FF30B203-D3C2-4EF5-B66F-9A32A0D97FB2";
        ByteDanceRta.RtaResponse response = execute(req, host, listOf(rtaId), IDFA, idfa);
        assertExclude(response);
    }


    @Test
    public void applyNewImei() {
        Long rtaId = 12173L;
        String imei = "869067033888875";
        ByteDanceRta.RtaResponse response = execute(req, host, listOf(rtaId), IMEI, imei);
        assertApply(response);
    }

    @Test
    public void excludeHistoryImei() {
        Long rtaId = 12173L;
        String imei = "866952035861413";
        ByteDanceRta.RtaResponse response = execute(req, host, listOf(rtaId), IMEI, imei);
        assertExclude(response);
    }


    //@Test
    public void frequencyCtrl() {
        Long rtaId = 12174L;
        String imei = "866952035861413";
        ByteDanceRta.RtaResponse response = execute(req, host, listOf(rtaId), IMEI, imei);
        assertExclude(response);
    }

    //@Test
    public void all() {
        String idfa = "ABF3AD6DC967D2471456DDF448230E36";
        ByteDanceRta.RtaResponse response = execute(req, host, TEST_RTA_IDS, IMEI_MD5, idfa);
        System.out.println(response.getBidType().getValue());
        System.out.println("-----------------------------------");
        List<ByteDanceRta.UserInfo> userInfos = response.getUserInfosList();
        for (ByteDanceRta.UserInfo ui : userInfos) {
            System.out.println(ui.getRtaId());
            System.out.println(ui.getIsInterested());
            System.out.println("--------");
        }
    }


    private void assertApply(ByteDanceRta.RtaResponse response) {
        assertResponse(response);
        Assert.assertEquals(0, response.getBidType().getValue());
        ByteDanceRta.UserInfo info = response.getUserInfos(0);
        System.out.println(info.getRtaId());
        Assert.assertTrue(info.getIsInterested());
    }


    private void assertExclude(ByteDanceRta.RtaResponse response) {
        assertResponse(response);
        Assert.assertEquals(1, response.getBidType().getValue());
        ByteDanceRta.UserInfo info = response.getUserInfos(0);
        System.out.println(info.getRtaId());
        Assert.assertFalse(info.getIsInterested());
    }


    private void assertResponse(ByteDanceRta.RtaResponse response) {
        //验证结果
        Int32Value bidType = response.getBidType();
        //Assert.assertTrue(isNotBlank(bidType.toString()));
    }

}
