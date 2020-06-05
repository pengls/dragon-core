package com.dragon.core.lang;

import com.dragon.core.lang.exception.BaseRuntimeException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * @ClassName: Assert
 * @Description: Assert
 * @Author: pengl
 * @Date: 2020/3/31 20:14
 * @Version V1.0
 */
public final class Assert {

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isTrue(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isFalse(boolean expression, Supplier<String> messageSupplier) {
        if (expression) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isNull(Object object, Supplier<String> messageSupplier) {
        if (object != null) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isNotNull(Object object, String message) {
        if (object == null) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isNotNull(Object object, Supplier<String> messageSupplier) {
        if (object == null) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * assert all not blank
     */
    public static void isAllNotBlank(String message, CharSequence... css) {
        if (StringUtils.isAnyBlank(css)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isAllNotBlank(Supplier<String> messageSupplier, CharSequence... css) {
        if (StringUtils.isAnyBlank(css)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * assert all blank
     */
    public static void isAllBlank(String message, CharSequence... css) {
        if (!StringUtils.isAllBlank(css)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isAllBlank(Supplier<String> messageSupplier, CharSequence... css) {
        if (!StringUtils.isAllBlank(css)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isNotEmpty(String str, String message) {
        if (StringUtils.isEmpty(str)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isNotEmpty(String str, Supplier<String> messageSupplier) {
        if (StringUtils.isEmpty(str)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isEmpty(String str, String message) {
        if (StringUtils.isNotEmpty(str)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isEmpty(String str, Supplier<String> messageSupplier) {
        if (StringUtils.isNotEmpty(str)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isNotBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isNotBlank(String str, Supplier<String> messageSupplier) {
        if (StringUtils.isBlank(str)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isBlank(String str, String message) {
        if (StringUtils.isNotBlank(str)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isBlank(String str, Supplier<String> messageSupplier) {
        if (StringUtils.isNotBlank(str)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isEndWith(String str, String suffix, String message) {
        if (!StringUtils.endsWith(str, suffix)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isEndWith(String str, String suffix, Supplier<String> messageSupplier) {
        if (!StringUtils.endsWith(str, suffix)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isNotEndWith(String str, String suffix, Supplier<String> messageSupplier) {
        if (StringUtils.endsWith(str, suffix)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isNotEndWith(String str, String suffix, String message) {
        if (StringUtils.endsWith(str, suffix)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isEndsWithIgnoreCase(String str, String suffix, String message) {
        if (!StringUtils.endsWithIgnoreCase(str, suffix)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isEndsWithIgnoreCase(String str, String suffix, Supplier<String> messageSupplier) {
        if (!StringUtils.endsWithIgnoreCase(str, suffix)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isNotEndsWithIgnoreCase(String str, String suffix, String message) {
        if (StringUtils.endsWithIgnoreCase(str, suffix)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isNotEndsWithIgnoreCase(String str, String suffix, Supplier<String> messageSupplier) {
        if (StringUtils.endsWithIgnoreCase(str, suffix)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isStartWith(String str, String prefix, String message) {
        if (!StringUtils.startsWith(str, prefix)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isStartWith(String str, String prefix, Supplier<String> messageSupplier) {
        if (!StringUtils.startsWith(str, prefix)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isNotStartWith(String str, String prefix, String message) {
        if (StringUtils.startsWith(str, prefix)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isNotStartWith(String str, String prefix, Supplier<String> messageSupplier) {
        if (StringUtils.startsWith(str, prefix)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isStartsWithIgnoreCase(String str, String prefix, String message) {
        if (!StringUtils.startsWithIgnoreCase(str, prefix)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isStartsWithIgnoreCase(String str, String prefix, Supplier<String> messageSupplier) {
        if (!StringUtils.startsWithIgnoreCase(str, prefix)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isNotStartsWithIgnoreCase(String str, String prefix, String message) {
        if (StringUtils.startsWithIgnoreCase(str, prefix)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isNotStartsWithIgnoreCase(String str, String prefix, Supplier<String> messageSupplier) {
        if (StringUtils.startsWithIgnoreCase(str, prefix)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }


    public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
        isNotNull(superType, "Super type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new BaseRuntimeException(message);
        }

    }

    public static void isAssignable(Class<?> superType, Class<?> subType, Supplier<String> messageSupplier) {
        isNotNull(superType, "Super type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj, String message) {
        isNotNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj, Supplier<String> messageSupplier) {
        isNotNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isNotEmpty(Object[] array, String message) {
        if (Objects.isEmpty(array)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isNotEmpty(Object[] array, Supplier<String> messageSupplier) {
        if (Objects.isEmpty(array)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isNotEmpty(byte[] bytes, Supplier<String> messageSupplier) {
        if (bytes == null || bytes.length == 0) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isNotEmpty(byte[] bytes, String message) {
        if (bytes == null || bytes.length == 0) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isNotEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isNotEmpty(Collection<?> collection, Supplier<String> messageSupplier) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isEmpty(Object[] array, String message) {
        if (Objects.isNotEmpty(array)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isEmpty(Object[] array, Supplier<String> messageSupplier) {
        if (Objects.isNotEmpty(array)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isNotEmpty(collection)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isEmpty(Collection<?> collection, Supplier<String> messageSupplier) {
        if (CollectionUtils.isNotEmpty(collection)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isRangeIndex(int index, int size, String message) {
        if (index < 0 || index >= size) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isRangeIndex(int index, int size, Supplier<String> messageSupplier) {
        if (index < 0 || index >= size) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    private static String nullSafeGet(Supplier<String> messageSupplier) {
        return messageSupplier != null ? messageSupplier.get() : null;
    }

    public static void isEquals(String str, String str2, String message) {
        if (!StringUtils.equals(str, str2)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isEquals(String str, String str2, Supplier<String> messageSupplier) {
        if (!StringUtils.equals(str, str2)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }

    public static void isNotEquals(String str, String str2, String message) {
        if (StringUtils.equals(str, str2)) {
            throw new BaseRuntimeException(message);
        }
    }

    public static void isNotEquals(String str, String str2, Supplier<String> messageSupplier) {
        if (StringUtils.equals(str, str2)) {
            throw new BaseRuntimeException(nullSafeGet(messageSupplier));
        }
    }
}
