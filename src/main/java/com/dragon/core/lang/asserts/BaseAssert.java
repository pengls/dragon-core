package com.dragon.core.lang.asserts;

import com.dragon.core.lang.Objects;
import com.dragon.core.lang.exception.BaseRuntimeException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * @ClassName: Assert
 * @Description: validate something
 * @Author: pengl
 * @Date: 2020/5/1 22:29
 * @Version V1.0
 */
public interface BaseAssert {

    BaseRuntimeException ofException(Object... args);

    default void isTrue(boolean expression, Object... args) {
        if (!expression) {
            throw ofException(args);
        }
    }

    default void isFalse(boolean expression, Object... args) {
        if (expression) {
            throw ofException(args);
        }
    }

    default void isNull(Object object, Object... args) {
        if (object != null) {
            throw ofException(args);
        }
    }

    default void isNotNull(Object object, Object... args) {
        if (object == null) {
            throw ofException(args);
        }
    }

    /**
     * assert all not blank
     */
    default void isAllNotBlank(CharSequence... str) {
        if (StringUtils.isAnyBlank(str)) {
            throw ofException();
        }
    }

    default void isAllBlank(CharSequence... str) {
        if (!StringUtils.isAllBlank(str)) {
            throw ofException();
        }
    }

    default void isNotEmpty(CharSequence str, Object... args) {
        if (StringUtils.isEmpty(str)) {
            throw ofException(args);
        }
    }

    default void isNotEmpty(Collection<?> collection, Object... args) {
        if (CollectionUtils.isEmpty(collection)) {
            throw ofException(args);
        }
    }

    default void isNotEmpty(Object[] array, Object... args) {
        if (Objects.isEmpty(array)) {
            throw ofException(args);
        }
    }

    default void isEmpty(CharSequence str, Object... args) {
        if (StringUtils.isNotEmpty(str)) {
            throw ofException(args);
        }
    }

    default void isEmpty(Collection<?> collection, Object... args) {
        if (CollectionUtils.isNotEmpty(collection)) {
            throw ofException(args);
        }
    }

    default void isEmpty(Object[] array, Object... args) {
        if (Objects.isNotEmpty(array)) {
            throw ofException(args);
        }
    }


    default void isNotBlank(CharSequence str, Object... args) {
        if (StringUtils.isBlank(str)) {
            throw ofException(args);
        }
    }

    default void isBlank(CharSequence str, Object... args) {
        if (StringUtils.isNotBlank(str)) {
            throw ofException(args);
        }
    }

    default void isEndWith(CharSequence str, CharSequence suffix, Object... args) {
        if (!StringUtils.endsWith(str, suffix)) {
            throw ofException(args);
        }
    }

    default void isEndsWithIgnoreCase(CharSequence str, CharSequence suffix, Object... args) {
        if (!StringUtils.endsWithIgnoreCase(str, suffix)) {
            throw ofException(args);
        }
    }

    default void isNotEndWith(CharSequence str, CharSequence suffix, Object... args) {
        if (StringUtils.endsWith(str, suffix)) {
            throw ofException(args);
        }
    }

    default void isNotEndsWithIgnoreCase(CharSequence str, CharSequence suffix, Object... args) {
        if (StringUtils.endsWithIgnoreCase(str, suffix)) {
            throw ofException(args);
        }
    }

    default void isStartWith(CharSequence str, CharSequence suffix, Object... args) {
        if (!StringUtils.startsWith(str, suffix)) {
            throw ofException(args);
        }
    }

    default void isStartsWithIgnoreCase(CharSequence str, CharSequence suffix, Object... args) {
        if (!StringUtils.startsWithIgnoreCase(str, suffix)) {
            throw ofException(args);
        }
    }

    default void isNotStartWith(CharSequence str, CharSequence suffix, Object... args) {
        if (StringUtils.startsWith(str, suffix)) {
            throw ofException(args);
        }
    }

    default void isNotStartsWithIgnoreCase(CharSequence str, CharSequence suffix, Object... args) {
        if (StringUtils.startsWithIgnoreCase(str, suffix)) {
            throw ofException(args);
        }
    }

    default void isAssignable(Class<?> superType, Class<?> subType, Object... args) {
        isNotNull(superType, "Super type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw ofException(args);
        }
    }

    default void isInstanceOf(Class<?> type, Object obj, Object... args) {
        isNotNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            throw ofException(args);
        }
    }

    default void isEquals(CharSequence str, CharSequence str1, Object... args) {
        if (!StringUtils.equals(str, str1)) {
            throw ofException(args);
        }
    }

    default void isNotEquals(CharSequence str, CharSequence str1, Object... args) {
        if (StringUtils.equals(str, str1)) {
            throw ofException(args);
        }
    }


}
