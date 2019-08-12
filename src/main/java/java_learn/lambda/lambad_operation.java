package java_learn.lambda;

import java.util.*;
import java.util.stream.Collectors;


public class lambad_operation {
    public static class Employee{
        private String name;
        private int salary;
        private String local;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public String getLocal() {
            return local;
        }

        public void setLocal(String local) {
            this.local = local;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", salary=" + salary +
                    ", office='" + local + '\'' +
                    '}';
        }

        public Employee(String name, int salary, String local) {
            this.name = name;
            this.salary = salary;
            this.local = local;
        }
    }

    private static List<Employee> list=new ArrayList<>();
    static {
        list.add(new Employee("hdc",24000,"Beijing"));
        list.add(new Employee("Tom",20000,"Shanghai"));
        list.add(new Employee("Mary",22000,"hangzhou"));
        list.add(new Employee("Sam",19000,"Beijing"));
        list.add(new Employee("Tony",18000,"shengzheng"));
    }

    private static Map<String,Integer>items=new HashMap<>();
    static {
        items.put("A",1001);
        items.put("B",1002);
        items.put("C",1003);
        items.put("D",1004);
        items.put("E",1005);
    }

    public static void main(String[] args) {
        System.out.println("打印员工的信息：");
        list.forEach(x->System.out.println(x.toString()));

        System.out.println("打印员工hdc的信息：");
        list.stream().filter(x->x.name.equals("hdc")).forEach(x->System.out.println(x.toString()));

        System.out.println("找出月薪超过20000的员工");
        list.stream().filter(x->x.salary>20000).forEach(System.out::println);

        System.out.println("lambda遍历map：");
        items.forEach((x,y)->System.out.println("Key:"+x+" value:"+y));
        //或者
        items.entrySet().forEach(x->System.out.println("key:"+x.getKey()+" value:"+x.getValue()));
        //或者
        items.entrySet().stream().forEach(x->System.out.println(x));

        System.out.println("lambda分组操作：");
        list.stream().collect(Collectors.groupingBy(x->x.local))
                .forEach((x,y)->System.out.println("组："+x+" 成员："+y.toString()));

        System.out.println("list转换为map:");
        System.out.println("list转换为map<String,Integer>:");
        Map<String,Integer>map=list.stream().
                collect(Collectors.toMap(Employee::getName,Employee::getSalary));
        map.forEach((x,y)->System.out.println("Key:"+x+" value:"+y));

        System.out.println("list转换为map<String,Employee>:");
        Map<String,Employee>map1=list.stream()
                .collect(Collectors.toMap((key->key.getName()),(value->value)));
        map1.forEach((key,value)->System.out.println("key:"+key+" value:"+value));

        System.out.println("打印出ID大于1002的员工：");
        items.entrySet().stream().filter(x->x.getValue()>1002)
                .forEach(x->System.out.println("key:"+x.getKey()+" value"+x.getValue()));

        Map<String,Integer>tempMap=items.entrySet().stream().filter(x->x.getValue()>1002)
                .collect(Collectors.toMap((key->key.getKey()),(value->value.getValue())));
        tempMap.forEach((key,value)->System.out.println("key:"+key+" value:"+value));

        System.out.println("打印处key=D的map：");
        items.entrySet().stream().filter(x->x.getKey().equals("D"))
                .forEach(x->System.out.println("key:"+x.getKey()+" value"+x.getValue()));

        System.out.println("Optional解决空指针异常:");
        String str=null;
        Optional<String> optional=Optional.ofNullable(str);
        System.out.println(optional.isPresent());

        System.out.println("anyMatch(匹配)只要有一个条件满足即返回true:");
        boolean isMatch1=list.stream().anyMatch(employee -> employee.getName().equals("hdc"));
        System.out.println("isMatch1:"+isMatch1);
        boolean isMatch2 =list.stream().anyMatch(employee -> employee.getName().equals("HDC"));
        System.out.println("isMatch2:"+isMatch2);

        System.out.println("allMatch(匹配)必须全部都满足才会返回true:");
        boolean Match1=list.stream().allMatch(employee -> employee.getName().equals("hdc"));
        System.out.println("allMatch:"+Match1);

        System.out.println("找出工资最高的员工：");
        Optional<Employee> Maxemployee=list.stream()
                .max((e1,e2)->Integer.compare(e1.salary,e2.salary));
        System.out.println(Maxemployee.toString());

        System.out.println("找出工资最少的员工：");
        Optional<Employee> Minemployee=list.stream()
                .min((e1,e2)->Integer.compare(e1.salary,e2.salary));
        System.out.println(Minemployee.toString());

        System.out.println("返回姓名列表：");
        List<String> name_list=list.stream().map(x->x.getName()).collect(Collectors.toList());
        Iterator<String>iteratorList=name_list.iterator();
        while (iteratorList.hasNext())
            System.out.print(iteratorList.next()+",");
        System.out.println();
        System.out.println(name_list);

        System.out.println("统计位置在Beijing的人数：");
        Long count=list.stream().filter(x->x.local.equals("Beijing")).count();
        System.out.println("count:"+count);

        System.out.println("位置在Beijing的人：");
        Optional<Employee>employee=list.stream()
                .filter(x->x.local.equals("Beijing")).findAny();
        System.out.println(employee);

        System.out.println("List转换为Set:");
        Set<String> set=list.stream().map(x->x.getLocal())
                .distinct().collect(Collectors.toSet());
        Iterator<String> iteratorSet=set.iterator();
        while (iteratorSet.hasNext())
            System.out.print(iteratorSet.next()+"  ");
        System.out.println();
        System.out.println(set);

        System.out.println("按照工资升序排列：");
        List<Employee>salary_list1=list.stream().
                sorted((e1,e2)->Integer.compare(e1.getSalary(),e2.getSalary()))
                .collect(Collectors.toList());
        System.out.println(salary_list1);

        System.out.println("按照工资降序排列：");
        List<Employee>salary_list2=list.stream().
                sorted((e1,e2)->Integer.compare(e2.getSalary(),e1.getSalary()))
                .collect(Collectors.toList());
        System.out.println(salary_list2);

        System.out.println("按照工资取最高的两位：");
        List<Employee>salary_list3=list.stream().
                sorted((e1,e2)->Integer.compare(e2.getSalary(),e1.getSalary()))
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(salary_list3);

        System.out.println("求平均工资：");
        OptionalDouble avg=list.stream().mapToInt(x->x.getSalary()).average();
        System.out.println("avg："+avg);
    }
}
