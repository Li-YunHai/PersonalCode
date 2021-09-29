package com.learn.notes.pattern;

import java.io.*;

/**
 * 浅拷贝：原始属性拷贝时，进行值传递，也就是将属性值复制一份新的。引用属性拷贝时，进行引用传递，只是将对象的地址复制给了新对象。
 * 深拷贝：原始属性拷贝时，进行值传递，也就是将属性值复制一份新的。引用属性拷贝时，进行引用的拷贝，将拷贝出来的引用对象复制给新对象。
 * 深拷贝实现方式：
 * 		1、重写clone方法
 * 		2、通过对象的序列化
 * 使用实例：当SpringBean配置的作用域是property时，ApplicationContext.getBean() -> ApplicationContext.doGetBean()
 *         -> beanFactory.createBean(), createBean方法会判断当前对象是不是property
 */
public class DeepClone implements Serializable,Cloneable {

    String name;

    CloneObject cloneObject;

    /**
     * 重写clone方法实现深度克隆
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        DeepClone deepClone = (DeepClone) super.clone();
        deepClone.cloneObject = (CloneObject) cloneObject.clone();
        return deepClone;
    }

    /**
     * 通过对象的序列化实现深度克隆
     * @return
     * @throws CloneNotSupportedException
     */
    public Object serialClone() throws CloneNotSupportedException {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;

        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            Object result = ois.readObject();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

class CloneObject  implements Serializable,Cloneable {

    String tips;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}