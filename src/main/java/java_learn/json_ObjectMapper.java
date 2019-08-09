package java_learn;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.IOException;

class Student
{
    private String name;
    private int age;
    public Student(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

//ObjectMapper类是Jackson库的主要类，提供功能是将java对象匹配成json结构，或者将json结构转换成java对象
public class json_ObjectMapper {
    public static void main(String[] args) {
        ObjectMapper mapper=new ObjectMapper();
        String jsonString="{\"name\":\"hdc\",\"age\":21}";
        //map json to student
        try {
            Student student=mapper.readValue(jsonString,Student.class);
            System.out.println("将jsonString转换成对象："+student);
            System.out.println("---------------------------------------");
            mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
            jsonString=mapper.writeValueAsString(student);
            System.out.println("将对像转换成json格式的字符串："+jsonString);
        }catch (JsonParseException e){
            e.printStackTrace();
        }catch (JsonMappingException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}












