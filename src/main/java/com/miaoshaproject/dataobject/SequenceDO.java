package com.miaoshaproject.dataobject;

public class SequenceDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequence_info.name
     *
     * @mbg.generated Sat Apr 13 19:53:12 CST 2024
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequence_info.current_value
     *
     * @mbg.generated Sat Apr 13 19:53:12 CST 2024
     */
    private Integer currentValue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequence_info.step
     *
     * @mbg.generated Sat Apr 13 19:53:12 CST 2024
     */
    private Integer step;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequence_info.name
     *
     * @return the value of sequence_info.name
     *
     * @mbg.generated Sat Apr 13 19:53:12 CST 2024
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequence_info.name
     *
     * @param name the value for sequence_info.name
     *
     * @mbg.generated Sat Apr 13 19:53:12 CST 2024
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequence_info.current_value
     *
     * @return the value of sequence_info.current_value
     *
     * @mbg.generated Sat Apr 13 19:53:12 CST 2024
     */
    public Integer getCurrentValue() {
        return currentValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequence_info.current_value
     *
     * @param currentValue the value for sequence_info.current_value
     *
     * @mbg.generated Sat Apr 13 19:53:12 CST 2024
     */
    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequence_info.step
     *
     * @return the value of sequence_info.step
     *
     * @mbg.generated Sat Apr 13 19:53:12 CST 2024
     */
    public Integer getStep() {
        return step;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequence_info.step
     *
     * @param step the value for sequence_info.step
     *
     * @mbg.generated Sat Apr 13 19:53:12 CST 2024
     */
    public void setStep(Integer step) {
        this.step = step;
    }
}