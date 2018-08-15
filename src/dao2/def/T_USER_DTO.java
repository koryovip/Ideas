package dao2.def;

public final class T_USER_DTO {

    private java.lang.String userId;
    private java.lang.Integer score;
    private java.util.Date regDt;

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    public java.lang.String getUserId() {
        return this.userId;
    }

    public void setScore(java.lang.Integer score) {
        this.score = score;
    }

    public java.lang.Integer getScore() {
        return this.score;
    }

    public void setRegDt(java.util.Date regDt) {
        this.regDt = regDt;
    }

    public java.util.Date getRegDt() {
        return this.regDt;
    }

    @Override
    public String toString() {
        return "T_USER_DTO [userId=" + userId + ", score=" + score + ", regDt=" + regDt + "]";
    }

}
