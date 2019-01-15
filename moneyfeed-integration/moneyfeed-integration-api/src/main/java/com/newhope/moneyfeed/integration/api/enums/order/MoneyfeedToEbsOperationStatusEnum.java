package com.newhope.moneyfeed.integration.api.enums.order;

/**
 * Created by yuyanlin on 2018/11/20
 */
public enum MoneyfeedToEbsOperationStatusEnum {

    UNCOMMIT("未提交"),
    OPERATING("处理中"),
    BIZ_OPERATION_FAIL("业务处理失败"),
    BIZ_OPERATION_SUCCESS("业务处理成功"),
    INVOKE_EBS_FAIL("调用EBS失败"),
    WAIT_FOR_EBS_TIMEOUT("等待EBS处理结果超时");

    private String desc;

    MoneyfeedToEbsOperationStatusEnum(String desc) {
        this.desc = desc;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
