package advance3;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) {

        Class<Entity> obj = Entity.class;

        for (Method method : obj.getDeclaredMethods()) {

            System.out.println(method.getName() + " " + Arrays.toString(method.getAnnotations()));
        }
    }
}
