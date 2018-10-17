package com.aw.impl;

import java.util.EmptyStackException;
import java.util.Enumeration;

import com.aw.Calculator;
import com.aw.StackCache;
import com.aw.constants.OperateConstants;
import com.aw.utils.NumberUtils;


public class DefaultCalculator implements Calculator {

    // stack used to store numeric data
    private static StackCache<Double> stackCache = new DefaultStackCache<>();

    // stack used to store operation change history
    private static StackCache<Double> histStackCache = new DefaultStackCache<>();

    // stack used to store operation record
    private static StackCache<String> histOperation = new DefaultStackCache<>();

    @Override
    public String calculate(String[] parameter) {

        // check parameter
        if (parameter == null || parameter.length == 0) {
            return getResult();
        }

        // handle
        for (int i = 0; i < parameter.length; i++) {

            String str = parameter[i];

            try {
                if (NumberUtils.isNumeric(str)) {
                    doNumeric(str);
                } else if (OperateConstants.OPERATE_ADDITION.equals(str)) {
                    doAdd();
                } else if (OperateConstants.OPERATE_SUBTRACTION.equals(str)) {
                    doSubtract();
                } else if (OperateConstants.OPERATE_MULTIPLICATION.equals(str)) {
                    doMultiply();
                } else if (OperateConstants.OPERATE_DIVISION.equals(str)) {
                    doDivide();
                } else if (OperateConstants.OPERATE_CLEAR.equals(str)) {
                    doClear();
                } else if (OperateConstants.OPERATE_UNDO.equals(str)) {
                    undo();
                } else if (OperateConstants.OPERATE_SQRT.equals(str)) {
                    doSqrt();
                } else {
                    return "Illegal parameter";
                }
            } catch (EmptyStackException e) {
                return "operator " + str + " (position: "+ i +"): " + "insufficient parameters stack" + getResult();
            }

        }

        return getResult();
    }

    /**
     * undo operation
     */
    private void undo() {

        if (histOperation.empty() || histStackCache.empty()) {
            return;
        }

        String str = histOperation.pop();

        if (stackCache.empty() && !OperateConstants.OPERATE_CLEAR.equals(str)) {
            return;
        }

        if (OperateConstants.OPERATE_NUMERIC.equals(str)) {
            undoNumeric();
        } else if (OperateConstants.OPERATE_ADDITION.equals(str)) {
            undoCalculate();
        } else if (OperateConstants.OPERATE_SUBTRACTION.equals(str)) {
            undoCalculate();
        } else if (OperateConstants.OPERATE_MULTIPLICATION.equals(str)) {
            undoCalculate();
        } else if (OperateConstants.OPERATE_DIVISION.equals(str)) {
            undoCalculate();
        } else if (OperateConstants.OPERATE_CLEAR.equals(str)) {
            undoClear();
        } else if (OperateConstants.OPERATE_SQRT.equals(str)) {
            undoSqrt();
        }
    }

    private void undoCalculate() {
        stackCache.pop();
        stackCache.push(histStackCache.pop());
        stackCache.push(histStackCache.pop());
    }

    private void undoClear() {
        stackCache.clear();

        while (!histStackCache.empty()) {
            stackCache.push(histStackCache.pop());
        }

    }

    private void undoNumeric() {
        stackCache.pop();
        histStackCache.pop();
    }

    private void undoSqrt() {
        stackCache.pop();
        stackCache.push(histStackCache.pop());
    }

    private void doNumeric(String str) {
        stackCache.push(Double.parseDouble(str));
        histOperation.push(OperateConstants.OPERATE_NUMERIC);
        histStackCache.push(Double.parseDouble(str));
    }

    private void doAdd() {
        Double first = stackCache.pop();
        Double second = stackCache.pop();
        Double result = first + second;

        stackCache.push(result);

        histStackCache.push(first);
        histStackCache.push(second);

        histOperation.push(OperateConstants.OPERATE_ADDITION);
    }

    private void doSubtract() {
        Double second = stackCache.pop();
        Double first = stackCache.pop();
        stackCache.push(first - second);

        histStackCache.push(second);
        histStackCache.push(first);

        histOperation.push(OperateConstants.OPERATE_SUBTRACTION);
    }

    private void doMultiply() {
        Double first = stackCache.pop();
        Double second = stackCache.pop();
        Double result = first * second;

        stackCache.push(result);

        histStackCache.push(first);
        histStackCache.push(second);

        histOperation.push(OperateConstants.OPERATE_MULTIPLICATION);
    }

    private void doDivide() {
        Double second = stackCache.pop();
        Double first = stackCache.pop();
        stackCache.push(first / second);

        histStackCache.push(second);
        histStackCache.push(first);

        histOperation.push(OperateConstants.OPERATE_DIVISION);
    }

    private void doClear() {

        histStackCache.clear();

        while (!stackCache.empty()) {
            histStackCache.push(stackCache.pop());
        }

        histOperation.push(OperateConstants.OPERATE_CLEAR);
    }

    private void doSqrt() {

        Double first = stackCache.pop();
        stackCache.push(Math.sqrt(first));

        histStackCache.push(first);

        histOperation.push(OperateConstants.OPERATE_SQRT);
    }

    @Override
    public String getResult() {
        String str = "";

        Enumeration<Double> result = stackCache.elements();

        while (result.hasMoreElements()) {
            str = str.concat(" ").concat(String.valueOf(result.nextElement()));
        }

        return str;
    }
}
