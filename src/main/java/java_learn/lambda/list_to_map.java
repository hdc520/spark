package java_learn.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class list_to_map {
    private static List<Person> personList=new ArrayList<>();
    static {
        personList.add(new Person.Builder().id(1001).name("hdc").address("BJUT").build());
        personList.add(new Person.Builder().id(1002).name("Tom").address("ZASD").build());
    }

    public static void main(String[] args) {
        Map<Integer,Person>list_map1=personList.stream()
                .collect(Collectors.toMap((key->key.getId()),value->value));
        list_map1.forEach((x,y)->System.out.println("Key:"+x+" value:"+y));

        Map<Integer,String>list_map2=personList.stream().collect(Collectors.toMap(
                Person::getId,Person::getName
        ));
        System.out.println("list_map2:"+list_map2);
        list_map2.forEach((x,y)->System.out.println("Key："+x+" value："+y));
    }
}
