package nju.software.convoy.data.entity;

import java.util.Date;

public class BusyCar {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column busy_car.ID
     *
     * @mbg.generated Tue Apr 28 22:19:49 SGT 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column busy_car.CAR
     *
     * @mbg.generated Tue Apr 28 22:19:49 SGT 2020
     */
    private Integer car;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column busy_car.START_TIME
     *
     * @mbg.generated Tue Apr 28 22:19:49 SGT 2020
     */
    private Date startTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column busy_car.END_TIME
     *
     * @mbg.generated Tue Apr 28 22:19:49 SGT 2020
     */
    private Date endTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column busy_car.KIND
     *
     * @mbg.generated Tue Apr 28 22:19:49 SGT 2020
     */
    private Integer kind;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column busy_car.ID
     *
     * @return the value of busy_car.ID
     *
     * @mbg.generated Tue Apr 28 22:19:49 SGT 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column busy_car.ID
     *
     * @param id the value for busy_car.ID
     *
     * @mbg.generated Tue Apr 28 22:19:49 SGT 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column busy_car.CAR
     *
     * @return the value of busy_car.CAR
     *
     * @mbg.generated Tue Apr 28 22:19:49 SGT 2020
     */
    public Integer getCar() {
        return car;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column busy_car.CAR
     *
     * @param car the value for busy_car.CAR
     *
     * @mbg.generated Tue Apr 28 22:19:49 SGT 2020
     */
    public void setCar(Integer car) {
        this.car = car;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column busy_car.START_TIME
     *
     * @return the value of busy_car.START_TIME
     *
     * @mbg.generated Tue Apr 28 22:19:49 SGT 2020
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column busy_car.START_TIME
     *
     * @param startTime the value for busy_car.START_TIME
     *
     * @mbg.generated Tue Apr 28 22:19:49 SGT 2020
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column busy_car.END_TIME
     *
     * @return the value of busy_car.END_TIME
     *
     * @mbg.generated Tue Apr 28 22:19:49 SGT 2020
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column busy_car.END_TIME
     *
     * @param endTime the value for busy_car.END_TIME
     *
     * @mbg.generated Tue Apr 28 22:19:49 SGT 2020
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column busy_car.KIND
     *
     * @return the value of busy_car.KIND
     *
     * @mbg.generated Tue Apr 28 22:19:49 SGT 2020
     */
    public Integer getKind() {
        return kind;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column busy_car.KIND
     *
     * @param kind the value for busy_car.KIND
     *
     * @mbg.generated Tue Apr 28 22:19:49 SGT 2020
     */
    public void setKind(Integer kind) {
        this.kind = kind;
    }
}