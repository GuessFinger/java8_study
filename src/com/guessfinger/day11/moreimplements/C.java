package com.guessfinger.day11.moreimplements;

/**
 * create by GuessFinger on 2019/4/24
 **/
public class C extends D implements A, B {
    public static void main(String[] args) {
        // 像这种情况就是满足记录中的规则2 因为本类以及父类中没有定义hello方法
        // 我们只能去找默认的方法 因为B比A 更具体 所以打印的是B的默认方法
        new C().hello();
    }

    @Override
    public void hello() {

    }
}

// 如果新增一个类D
//    public class D implements A {
//
//    }
// 这时候改成这个这样子
//    public class C extends D implements A, B {
//        public static void main(String[] args){
//            new C().hello();
//        }
//    }
// 这时候打印什么内容呢？ 按理说类中的生命优先级别更高一点 我们去D中去找方法 但是D 没有覆盖默认的hello方法
// 所以还是回去B 和 A 进行抉择 因为B 更具体 所以和上面的结果是一样的
// 但是如果D 中覆盖了hello方法 那么调用的hello方法就是D 中的hello方法了 根据规则1

// 如果抽象类实现了A接口 C 又继承了D 实现A B接口 那么C 中必有显式的定义D中方法

// 还有一种就是在文本记录中说到的 问什么没有添加D呢？ 什么意思呢
// 假设 接口A B 没有任何的关联 但是他们都有默认的方法 hello 这时候C 实现了他们两个接口
// 这时候就会报一个错误“Error: class C inherits unrelated defaults for hello()from types B and A .”
// 这时候你怎么办呢？这时候你只能显示的决定你在C中想要调用那个方法
//    public class C implements A, B {
//        这个是java8引入的一种新的语法 X.super.m(...)  X 表示就是你要调用的方法所在的父类
//        void hello() {
//            B.super.hello();
//        }
//    }
