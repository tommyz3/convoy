package nju.software.convoy.data.entity;

public class ApprovalCar {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval_car.ID
     *
     * @mbg.generated Wed Apr 29 18:28:50 SGT 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval_car.LEADER
     *
     * @mbg.generated Wed Apr 29 18:28:50 SGT 2020
     */
    private String leader;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval_car.APPLY_ID
     *
     * @mbg.generated Wed Apr 29 18:28:50 SGT 2020
     */
    private Integer applyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval_car.KIND
     *
     * @mbg.generated Wed Apr 29 18:28:50 SGT 2020
     */
    private Byte kind;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval_car.REASON
     *
     * @mbg.generated Wed Apr 29 18:28:50 SGT 2020
     */
    private String reason;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval_car.STATUS
     *
     * @mbg.generated Wed Apr 29 18:28:50 SGT 2020
     */
    private Byte status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval_car.ID
     *
     * @return the value of approval_car.ID
     *
     * @mbg.generated Wed Apr 29 18:28:50 SGT 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval_car.ID
     *
     * @param id the value for approval_car.ID
     *
     * @mbg.generated Wed Apr 29 18:28:50 SGT 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval_car.LEADER
     *
     * @return the value of approval_car.LEADER
     *
     * @mbg.generated Wed Apr 29 18:28:50 SGT 2020
     */
    public String getLeader() {
        return leader;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval_car.LEADER
     *
     * @param leader the value for approval_car.LEADER
     *
     * @mbg.generated Wed Apr 29 18:28:50 SGT 2020
     */
    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval_car.APPLY_ID
     *
     * @return the value of approval_car.APPLY_ID
     *
     * @mbg.generated Wed Apr 29 18:28:50 SGT 2020
     */
    public Integer getApplyId() {
        return applyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval_car.APPLY_ID
     *
     * @param applyId the value for approval_car.APPLY_ID
     *
     * @mbg.generated Wed Apr 29 18:28:50 SGT 2020
     */
    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval_car.KIND
     *
     * @return the value of approval_car.KIND
     *
     * @mbg.generated Wed Apr 29 18:28:50 SGT 2020
     */
    public Byte getKind() {
        return kind;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval_car.KIND
     *
     * @param kind the value for approval_car.KIND
     *
     * @mbg.generated Wed Apr 29 18:28:50 SGT 2020
     */
    public void setKind(Byte kind) {
        this.kind = kind;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval_car.REASON
     *
     * @return the value of approval_car.REASON
     *
     * @mbg.generated Wed Apr 29 18:28:50 SGT 2020
     */
    public String getReason() {
        return reason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval_car.REASON
     *
     * @param reason the value for approval_car.REASON
     *
     * @mbg.generated Wed Apr 29 18:28:50 SGT 2020
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval_car.STATUS
     *
     * @return the value of approval_car.STATUS
     *
     * @mbg.generated Wed Apr 29 18:28:50 SGT 2020
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval_car.STATUS
     *
     * @param status the value for approval_car.STATUS
     *
     * @mbg.generated Wed Apr 29 18:28:50 SGT 2020
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
}