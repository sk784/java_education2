import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Test2 {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{4, 1, 4, 1}, true},
                {new int[]{1, 3, 4}, false},
                {new int[]{1, 1}, false},
                {new int[]{4, 4}, false},
        });
    }

    private int[] in;
    private boolean out;

   public Test2(int[] in, boolean out) {
        this.in = in;
        this.out = out;
    }

    @Test
    public void checkArrayFor1and4Test() {
        Assert.assertEquals(out, MainClass.checkArrayFor1and4(in));
    }
}
