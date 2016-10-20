package com.jxufe.enums;
/**
 * Created by moonie on 16/5/31.
 */
public enum  SeckillStatEnum {
    SUCCESS_KILL(1,"秒杀成功"),
    TIME_OVER_EXCEPTION(-1,"秒杀时间已结束"),
    NO_BEGIN_EXCEPTION(-2,"秒杀时间未开始"),
    STORE_EMPTY_EXCEPTION(-3,"秒杀库存已空"),
    REPEAT_EXCEPTION(-4,"重复秒杀"),
    ILLEAGLE_EXCEPTION(-5,"试图非法秒杀"),
    INNER_ERROR(-4,"系统内部异常");

    private int stateNum;

    private String stateInfo;

    SeckillStatEnum(int state, String stateInfo) {
        this.stateNum = state;
        this.stateInfo = stateInfo;
    }

    public int getStateNum() {
        return stateNum;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStatEnum stateOf(int index){
        for (SeckillStatEnum state : values()) {
            if(state.getStateNum() == index){
                return state;
            }
        }
        return null;
    }


    
}
