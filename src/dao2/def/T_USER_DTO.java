package dao2.def;

import java.util.Date;

public final class T_USER_DTO {

    private String USER_ID;
    private Integer score;
    private Date regDt;

    public void setUserId(String userId) {
        this.USER_ID = userId;
    }

    public String getUserId() {
        return this.USER_ID;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setRegDt(Date regDt) {
        this.regDt = regDt;
    }

    public Date getRegDt() {
        return this.regDt;
    }

    @Override
    public String toString() {
        return "T_USER_NTT [userId=" + USER_ID + ", score=" + score + ", regDt=" + regDt + "]";
    }

}
