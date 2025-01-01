package com.icboluo.common;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.ClassClassPath;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtField;
import org.apache.ibatis.javassist.bytecode.AnnotationsAttribute;
import org.apache.ibatis.javassist.bytecode.ConstPool;
import org.apache.ibatis.javassist.bytecode.FieldInfo;
import org.apache.ibatis.javassist.bytecode.annotation.Annotation;
import org.apache.ibatis.javassist.bytecode.annotation.ArrayMemberValue;
import org.apache.ibatis.javassist.bytecode.annotation.MemberValue;
import org.springframework.data.util.CastUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * @author icboluo
 * @since 2024-12-31 23:33
 */
@Slf4j
public class Javassist {
    /**
     *     Function<ConstPool, Annotation> generateAnno = constPool -> {
     *         org.apache.ibatis.javassist.bytecode.annotation.Annotation annotation
     *                 = new org.apache.ibatis.javassist.bytecode.annotation.Annotation(ExcelProperty.class.getCanonicalName(),
     *                 constPool);
     *         // 注意：此块传参和其他方法传参是反着的
     *         annotation.addMemberValue("index", new IntegerMemberValue(constPool, excel.columnIndex()));
     *         return annotation;
     *     };
     * BeanUtil.addAnnotation(field, generateAnno);
     * 给字段增加注解（直接修改.class文件，危险函数
     *
     * @param field 字段
     * @param generateAnno 注解生成函数
     */
    @SneakyThrows
    public static synchronized void addAnnotation(Field field,
                                                  Function<ConstPool, Annotation> generateAnno) {
        // 解冻
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(field.getDeclaringClass()));
        CtClass ctClass = pool.getCtClass(field.getDeclaringClass().getName());
        ctClass.defrost();
        ConstPool constPool = ctClass.getClassFile().getConstPool();
        CtField ctField = ctClass.getDeclaredField(field.getName());
        // 判断注解是否已存在
        org.apache.ibatis.javassist.bytecode.annotation.Annotation annotation = generateAnno.apply(constPool);
        if (ctField.hasAnnotation(annotation.getTypeName())) {
            return;
        }
        // 增加注解
        FieldInfo fieldInfo = ctField.getFieldInfo();
        AnnotationsAttribute annotationsAttribute = (AnnotationsAttribute) fieldInfo.getAttribute(
                AnnotationsAttribute.visibleTag);
        if (annotationsAttribute == null) {
            annotationsAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        }
        annotationsAttribute.addAnnotation(annotation);
        fieldInfo.addAttribute(annotationsAttribute);
    }

    /**
     * 给注解赋值（直接修改.class文件，危险函数
     *
     * @param field 字段
     * @param annoClazz 注解类型
     * @param property 注解的属性
     * @param generateMemberValue 赋值函数
     * @param <A> 注解类型
     */
//    @SneakyThrows
//    public static synchronized <A extends Annotation> void updateAnnotation(Field field, Class<A> annoClazz,
//                                                                            String property, Function<ConstPool, MemberValue> generateMemberValue) {
//        // 判断注解是否存在
//        if (!field.isAnnotationPresent(annoClazz)) {
//            return;
//        }
//        // 解冻并修改注解
//        ClassPool pool = ClassPool.getDefault();
//        pool.insertClassPath(new ClassClassPath(field.getDeclaringClass()));
//        CtClass ctClass = pool.getCtClass(field.getDeclaringClass().getName());
//        ctClass.defrost();
//        ConstPool constPool = ctClass.getClassFile().getConstPool();
//
//        CtField ctField = ctClass.getDeclaredField(field.getName());
//        FieldInfo fieldInfo = ctField.getFieldInfo();
//        AnnotationsAttribute annotationsAttribute = (AnnotationsAttribute) fieldInfo.getAttribute(
//                AnnotationsAttribute.visibleTag);
//        org.apache.ibatis.javassist.bytecode.annotation.Annotation annotation = annotationsAttribute.getAnnotation(
//                annoClazz.getTypeName());
//        MemberValue memberValue = generateMemberValue.apply(constPool);
//        // 如果新旧注解值相等，跳过
//        Object old = getMemberValues(field.getAnnotation(annoClazz)).get(property);
//        Object newData = memberValueGetValue(annoClazz, property, memberValue);
//        if (org.springframework.util.ObjectUtils.nullSafeEquals(old, newData)) {
//            return;
//        }
//        annotation.addMemberValue(property, memberValue);
//        annotationsAttribute.addAnnotation(annotation);
//        fieldInfo.addAttribute(annotationsAttribute);
//        // 更新缓存值
//        updateAnnoVal(field, annoClazz, property, newData);
//    }

    private static <A extends Annotation, T> Object memberValueGetValue(Class<A> annoClazz, String property,
                                                                        MemberValue memberValue) throws InvocationTargetException, IllegalAccessException {
        try {
            // 数组类型兼容处理
            if (memberValue instanceof ArrayMemberValue) {
                MemberValue[] value = ((ArrayMemberValue) memberValue).getValue();
                Class<T> type = CastUtils.cast(annoClazz.getMethod(property).getReturnType());
                // 创建泛型数组
                T[] arr = CastUtils.cast(Array.newInstance(type.getComponentType(), value.length));
                for (int i = 0; i < value.length; i++) {
                    MemberValue temp = value[i];
                    Method tempGetValue = temp.getClass().getMethod("getValue");
                    arr[i] = CastUtils.cast(tempGetValue.invoke(temp));
                }
                return arr;
            } else {
                Method getValue = memberValue.getClass().getMethod("getValue");
                return getValue.invoke(memberValue);
            }
        } catch (NoSuchMethodException e) {
            log.error("member value no have getValue method ", e);
        }
        return null;
    }
}
