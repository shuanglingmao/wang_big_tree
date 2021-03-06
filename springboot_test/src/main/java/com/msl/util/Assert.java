package com.msl.util;

/**
 * Description:
 *
 * @author shuangling.mao
 * @date 2019/3/14 10:18
 */
public final class Assert {
    private Assert() {
    }

    public static void isExist(Object[] objects, Object obj, String message) {
        if (objects == null) {
            throw new NullPointerException("校验的数组为null!");
        } else if (obj == null) {
            throw new NullPointerException("校验的值null!");
        } else {
            boolean isExist = false;

            for(int i = 0; i < objects.length; ++i) {
                Object value = objects[i];
                if (obj.equals(value)) {
                    isExist = true;
                    break;
                }
            }

            if (!isExist) {
                throw new IllegalArgumentException(message);
            }
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean expression) {
        isTrue(expression, "[Assertion failed] - this expression must be true");
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNull(Object object) {
        isNull(object, "[Assertion failed] - the object argument must be null");
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object) {
        notNull(object, "[Assertion failed] - this argument is required; it must not be null");
    }

    public static void noNullElements(Object[] array, String message) {
        if (array != null) {
            for(int i = 0; i < array.length; ++i) {
                if (array[i] == null) {
                    throw new IllegalArgumentException(message);
                }
            }
        }

    }

    public static void noNullElements(Object[] array) {
        noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
    }

    public static void isInstanceOf(Class<?> clazz, Object obj) {
        isInstanceOf(clazz, obj, "");
    }

    public static void isInstanceOf(Class<?> type, Object obj, String message) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            throw new IllegalArgumentException(message + "Object of class [" + (obj != null ? obj.getClass().getName() : "null") + "] must be an instance of " + type);
        }
    }

    public static void isAssignable(Class<?> superType, Class<?> subType) {
        isAssignable(superType, subType, "");
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
        notNull(superType, "Type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new IllegalArgumentException(message + subType + " is not assignable to " + superType);
        }
    }

    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    public static void state(boolean expression) {
        state(expression, "[Assertion failed] - this state invariant must be true");
    }

    public static void main(String[] args) {
        Integer a = null;
        Assert.notNull(a);
    }
}

