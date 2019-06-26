package com.works.leagueservice.service.impl;

import com.works.sharedlibrary.exceptions.InvalidFieldException;
import org.apache.commons.lang.StringUtils;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author mali.sahin
 * @since 2019-06-27.
 */
public class BaseService {

    static Predicate<String> isBlank() {
        return s -> StringUtils.isEmpty(s) && StringUtils.isBlank(s);
    }

    static Predicate<String> isNotBlank() {
        return s -> !StringUtils.isEmpty(s) && !StringUtils.isBlank(s);
    }


    static Supplier<InvalidFieldException> invalidFieldSupplier(String message) {
        return () -> new InvalidFieldException(message);
    }




}
