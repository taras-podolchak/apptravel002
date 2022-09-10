package com.appvisibility.apptravel002.ui.controller.interfaces;

public interface CheckDigit {

    boolean isValid(String code);
    String calculate(String code) throws Exception;
}
