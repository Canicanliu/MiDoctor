package com.can.minidoctor.api.dto.request.miniwx;

import java.io.Serializable;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 20:08 2019/1/7
 */
public class MessageData implements Serializable {
    private MessageDataItem first;
    private MessageDataItem keyword1;
    private MessageDataItem keyword2;
    private MessageDataItem keyword3;
    private MessageDataItem keyword4;
    private MessageDataItem keyword5;
    private MessageDataItem keyword6;
    private MessageDataItem keyword7;
    private MessageDataItem keyword8;
    private MessageDataItem remark;

    private String emphasis_keyword;

    public MessageDataItem getKeyword5() {
        return keyword5;
    }

    public void setKeyword5(MessageDataItem keyword5) {
        this.keyword5 = keyword5;
    }

    public MessageDataItem getKeyword6() {
        return keyword6;
    }

    public void setKeyword6(MessageDataItem keyword6) {
        this.keyword6 = keyword6;
    }

    public MessageDataItem getKeyword7() {
        return keyword7;
    }

    public void setKeyword7(MessageDataItem keyword7) {
        this.keyword7 = keyword7;
    }

    public MessageDataItem getKeyword8() {
        return keyword8;
    }

    public void setKeyword8(MessageDataItem keyword8) {
        this.keyword8 = keyword8;
    }

    public String getEmphasis_keyword() {
        return emphasis_keyword;
    }

    public void setEmphasis_keyword(String emphasis_keyword) {
        this.emphasis_keyword = emphasis_keyword;
    }

    public void setFirst(MessageDataItem first) {
        this.first = first;
    }
    public MessageDataItem getFirst() {
        return first;
    }

    public void setKeyword1(MessageDataItem keyword1) {
        this.keyword1 = keyword1;
    }
    public MessageDataItem getKeyword1() {
        return keyword1;
    }

    public void setKeyword2(MessageDataItem keyword2) {
        this.keyword2 = keyword2;
    }
    public MessageDataItem getKeyword2() {
        return keyword2;
    }

    public void setKeyword3(MessageDataItem keyword3) {
        this.keyword3 = keyword3;
    }
    public MessageDataItem getKeyword3() {
        return keyword3;
    }

    public MessageDataItem getKeyword4() {
        return keyword4;
    }

    public void setKeyword4(MessageDataItem keyword4) {
        this.keyword4 = keyword4;
    }

    public void setRemark(MessageDataItem remark) {
        this.remark = remark;
    }
    public MessageDataItem getRemark() {
        return remark;
    }
}
