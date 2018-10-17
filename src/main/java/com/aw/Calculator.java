package com.aw;


public interface Calculator {

    /**
     * calculate accounting to the parameter
     * @param parameter data
     * @return result string
     */
    String calculate(String[] parameter);

    /**
     * stack result
     * @return stack result
     */
    String getResult();
}
