package com.yizhu.lj.utils.utils.function;


import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author qinchaoyue
 * @date 11:18 2018/8/3
 * @description
 */
public class ReflectUtils {

    /**
     * @param obj      反射的对象,获取对象的字段名和值
     * @param consumer 回调函数, 每反射出一个字段回调一次, 回调入参: 字段名称, 字段类型, 字段值, 字段
     * @return 返回实体类
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static <T> T reflect(T obj, Consumer4Utils<String, String, Object, Field> consumer) {
        if (obj == null) return null;

        Arrays.stream(obj.getClass().getDeclaredFields()).forEach(m -> {
            m.setAccessible(true);
            try {
                consumer.accept(m.getName(), m.getType().toString(), m.get(obj), m);
            } catch (IllegalAccessException e) {
                consumer.accept(m.getName(), m.getType().toString(), null, m);
            }
        });
        return obj;
    }

    /**
     * @param obj 反射的对象,获取对象的字段名和值
     * @return 返回实体类
     */
    public static <T> T reflect(T obj, String[] copyFields) {

        Class<T> tClass = (Class<T>) obj.getClass();
        T instance = null;
        try {
            instance = tClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        if (instance == null) {
            return null;
        }

        T finalInstance = instance;
        ReflectUtils.reflect(obj, (name, type, value, field) -> {
            if (Arrays.asList(copyFields).contains(name)) {
                try {
                    Object myValue = field.get(obj);
                    if (myValue != null) {
                        field.set(finalInstance, myValue);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        return finalInstance;
    }
}
