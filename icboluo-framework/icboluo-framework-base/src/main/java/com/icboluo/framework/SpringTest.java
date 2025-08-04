package com.icboluo.framework;

import com.icboluo.object.Archives;
import com.icboluo.object.CodeName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.*;
import org.springframework.web.util.HtmlUtils;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author icboluo
 * @since 2023-01-08 0:16
 */
public class SpringTest {
    @Test
    public void classUtils() {
        // 获取对象的所有接口
        Class<?>[] allInterfaces = ClassUtils.getAllInterfaces(new LinkedList<>());
        // 获取类所在的包名
        String packageName = ClassUtils.getPackageName(LinkedList.class);
        // 判断某个类是否是内部类
        boolean innerClass = ClassUtils.isInnerClass(LinkedList.class);
        // 判断对象是否为代理对象
        boolean cglibProxy = ClassUtils.isCglibProxy(new LinkedList<>());
        List<String> list = new ArrayList<>();
        // 返回集合中的元素类型，如果集合.isEmpty | 拥有多种元素类型，返回的结果为null
        System.out.println(CollectionUtils.findCommonElementType(list));
    }

    @Test
    public void http() {
        // HttpStatus Spring包下也有
    }

    @Test
    public void reflection() {
        Method add = ReflectionUtils.findMethod(LinkedList.class, "add");
        Field field = ReflectionUtils.findField(LinkedList.class, "field");
    }

    @Test
    public void ioUtils() throws IOException {
        // IOUtils
        // 是一个比较好用的api
        StreamUtils.copy(new byte[]{}, null);
        byte[] bytes = StreamUtils.copyToByteArray(null);

        byte[] bytes2 = FileCopyUtils.copyToByteArray(new File(""));
        String s = FileCopyUtils.copyToString(null);
        // 还有好多输入输出的copy方法
    }

    @Test
    public void dd() throws IOException {
// 读取classpath下文件
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "application.yml");
        assert file.exists();
// 读取文件系统文件
        File file2 = ResourceUtils.getFile(ResourceUtils.FILE_URL_PREFIX + "D:/software_asset.csv");
        assert file2.exists();

        // 操作数组
        Object[] arr = ObjectUtils.addObjectToArray(new Object[]{"a"}, "b");
        assert arr.length == 2;
        assert ObjectUtils.containsElement(arr, "b");

        // 数字类型转换
        Integer i = NumberUtils.parseNumber("10", Integer.class);
        BigDecimal bigDecimal = NumberUtils.parseNumber("10.1", BigDecimal.class);
        assert i == 10;
        assert bigDecimal.doubleValue() == 10.1d;

        // 流拷贝
        // StreamUtils.copy(new FileInputStream(""), new FileOutputStream(""));

        // 集合拼接字符串
        String str = StringUtils.collectionToDelimitedString(new ArrayList<>(), ",");
        assert str.isEmpty();

        // 判断2个对象是否相等，如果只有一个为null返回false; 也可以判断数组
        boolean eq = ObjectUtils.nullSafeEquals(new Object(), null);
        assert !eq;
        boolean eqa = ObjectUtils.nullSafeEquals(new Integer[]{1, 2}, new Integer[]{1, 2});
        assert eqa;

        // 获取对象的hashCode,2个对象的hashCode是不相等的
        String identityHexString1 = ObjectUtils.getIdentityHexString(new CodeName());
        String identityHexString2 = ObjectUtils.getIdentityHexString(new CodeName());
        assert !identityHexString1.equals(identityHexString2);

        // BeanUtils的反射功能
        Method setId = BeanUtils.findDeclaredMethod(Archives.class, "setId", Object.class);
        assert setId != null && setId.getName().equals("setId");
        // 不清楚为什么set方法无法使用（如果说只能查找类的声明方法，不包括继承，那么为什么可以调用getId）
        Method getId = BeanUtils.findDeclaredMethod(CodeName.class, "getId");
        assert getId != null && getId.getName().equals("getId");
        // 不知道干嘛的（说是获取方法的参数，可是get方法没参数啊）
        PropertyDescriptor propertyForMethod = BeanUtils.findPropertyForMethod(getId);
        assert propertyForMethod != null && propertyForMethod.getName().equals("id");

        // 反射调用
        ReflectionUtils.invokeMethod(setId, new Archives<Integer, String>(), 1);
        // 是否是静态final的常量
        Field field = ReflectionUtils.findField(CodeName.class, "id");
        assert field != null;
        boolean publicStaticFinal = ReflectionUtils.isPublicStaticFinal(field);
        assert !publicStaticFinal;

        // 序列化，需要实现序列化接口，方法过时
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        byte[] serialize = SerializationUtils.serialize(map);
        Object deserialize = SerializationUtils.deserialize(serialize);
        System.out.println("deserialize = " + deserialize);

        // http 状态码
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        assert notFound.is4xxClientError();

        // 转义
        String s = HtmlUtils.htmlEscape("<div>test1;test2</div>");
        assert s.equals("&lt;div&gt;test1;test2&lt;/div&gt;");

        // MultiValueMap
        // 文件系统工具，可以递归复制、删除一个目录
        // FileSystemUtils.copyRecursively(null, null);
    }
}
