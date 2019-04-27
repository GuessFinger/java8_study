package com.guessfinger.day12;


import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * create by GuessFinger on 2019/4/27
 **/
public class OptionalDemo {
    public static void main(String[] args){
        // 1.声明一个空的Optional 我们可以通过Optional.empty()创建一个空的Optional对象
        Optional<Car> optionalCar = Optional.empty();
        // 2.依据一个非空值创建Optional 我们可以使用静态工厂方法Optional.of 依据非空值创建一个Optional对象
        // 就像下面演示的  如果car是一个null的话 Optional.of 就会抛出一个空指针异常
        // 所以说这里面的值一定要是非空的
        Car car = null;
//        Optional<Car> optionalCar1 = Optional.of(car);
//        System.out.println(optionalCar1);
        // 3.可接受null的Optional  我们使用Optional.ofNullable 创建一个允许null的Optional对象
        // 使用Optional.ofNullable 我们可以用它创建非null的值 但是商检的那个 一定不能传入null值
        Car car1 = null;
        Car car2 = new Car();
        Optional<Car> optionalCar2 = Optional.ofNullable(car2);
        Optional<Car> optionalCar3 = Optional.ofNullable(car1);
        System.out.println(optionalCar2);
        System.out.println(optionalCar3);
        // 这里需要说明一下 如果你对一个空的Optional使用get方法 那么依旧会报空指针异常的

        // 使用map从Optional对象中提取和转换值
        Insurance insurance = new Insurance();
        // 如果按照之前的方式
        String name = null;
        if (insurance != null) {
            name = insurance.getName();
        }
        // 为了支持这种模式Optional提供了一map方法
        // 这里就是把Optional当成一个特殊的集合对象 至多包含一个元素 这个和之前的集合的流差不多
        // 如果没有值的话就不进行处理
        Optional<Insurance> insurance1 = Optional.ofNullable(insurance);
        Optional<String> optName = insurance1.map(Insurance::getName);
        // 我们之前还有一个需求 求这个人的车的保险名称 我们如果按照之前的方式定义Person Car Insurance
        // 那么方式就是这个样子的 return person.getCar().getInsurance().getName();
        // 像上面那个写法很容易有问题的 就是空指针异常 我们按照刚刚学习的写法


        Optional<String> optionalS = Optional.of("ss");
        Optional<Integer> optionalInteger = optionalS.map(Integer::parseInt);
        System.out.println(optionalInteger);

    }

    // 按理说我们是应该使用这样的方法 获取这个人的车的保险名称的 但是这个无法编译通过 为什么呢？
    // 因为你调用person.getCar()方法 返回的是一个Optional<Car>的对象 这个对象当然是没有getInsurance()
    // 这个方法了 Optional<Optional<Car>> 这个就是你调用方法后的返回值
    // 下面的实例就能很好的说明这个问题 我们使用map 和flatMap 之间返回的对象是什么
    // 等到用的时候把这个注释放开就可以查看了 我记得之前使用flatMap 主要点就是将各个流 转成一个扁平化的流
    // String list 中将每个字母排序的问题
    // Person person = new Person();
    // Optional<Optional<Car>> optionMapCar = Optional.ofNullable(person).map(Person::getCar);
    // Optional<Car> flatMapCar =Optional.ofNullable(person).flatMap(Person::getCar);


    public String getPersonCarInsurance(Person person) throws Exception {

    //   return "";

    //   return Optional.ofNullable(person).map(Person::getCar)
    //                                       .map(Car::getInsurance)
    //                                       .map(Insurance::getName);

    // 我们现在使用上面的flatMap来进行方法的改造 这里面如果不添加orElse 这个就会报错的
    // 这里的理解的就是如果Optional是null的话 null不是String类型的 所以需要添加一个默认返回值
        return Optional.ofNullable(person).flatMap(Person::getCar)
                                          .flatMap(Car::getInsurance)
                                          .map(Insurance::getName)
                                          .orElse("unknow")
    //                                    .orElseGet((Supplier<String>)()  -> "xxx");
    //                                    .orElseThrow(exceptionSupplier());
                                          ;
    }


    public Supplier<? extends String> defalutName() {
        return (Supplier<String>) () -> "xxx";
    }

    public Supplier<? extends Exception> exceptionSupplier() {
        return new Supplier() {
            @Override
            public Object get() {
                return null;
            }
        };
    }

}
