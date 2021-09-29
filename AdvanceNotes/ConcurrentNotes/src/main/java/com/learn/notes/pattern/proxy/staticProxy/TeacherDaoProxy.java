package com.learn.notes.pattern.proxy.staticProxy;

/**
 * 静态代理：
 *      优点：在不修改目标对象的功能前提下，能通过代理对象对目标功能扩展。
 *      缺点：因为代理对象需要与目标对象实现一样的接口，所以会有很多代理类。一旦接口增加方法，目标对象与代理对象都要维护。
 */
public class TeacherDaoProxy implements ITeacher {

    private ITeacher iTeacher;

    public TeacherDaoProxy(ITeacher iTeacher) {
        this.iTeacher = iTeacher;
    }

    @Override
    public void teach() {
        System.out.println("老师备课");
        iTeacher.teach();
        System.out.println("老师结束讲课");
    }

    public static void main(String[] args) {
        //创建被代理对象（目标对象）
        TeacherDao teacherDao = new TeacherDao();
        //创建代理对象，同时将被代理对象传递给代理对象
        TeacherDaoProxy teacherDaoProxy = new TeacherDaoProxy(teacherDao);
        //通过代理对象，调用到被代理对象的方法
        teacherDaoProxy.teach();
    }
}