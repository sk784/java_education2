import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class MainClass {
    public static void main(String[] args) throws Exception {
        Class c = MyClass.class;
        Object obj = c.newInstance();
        ArrayList<Method> m = new ArrayList<>();

        Method beforeMethod = null;
        Method afterMethod = null;

        for (Method x : c.getDeclaredMethods()) {
            if (x.isAnnotationPresent(Test.class)) {
                m.add(x);
            }
            if (x.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeMethod == null)
                    beforeMethod = x;
                else throw new RuntimeException("Аннотация BeforeSuite уже есть");
            }
            if (x.isAnnotationPresent(AfterSuite.class)) {
                if (afterMethod == null)
                    afterMethod = x;
                else throw new RuntimeException("Аннотация AfterSuite уже есть");
            }

            m.sort(new Comparator<Method>() {
                @Override
                public int compare(Method o1, Method o2) {
                    return o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority();
                }
            });
        }

        if (beforeMethod != null)
            beforeMethod.invoke(obj, null);

        for (Method x : m)
            x.invoke(obj, null);

        if (afterMethod != null)
            afterMethod.invoke(obj, null);
   }
}