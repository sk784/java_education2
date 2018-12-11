import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Test1 {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{}, new int[]{}},
                {new int[]{1,2,3}, new int[]{}},
                {new int[]{1,2,3,4}, new int[]{}},
                {new int[]{1,2,3,4,5,6}, new int[]{5,6}},
                {new int[]{1,2,3,4,5,6,4,2,1}, new int[]{2,1}},
        });
    }

    private int[] in;
    private int[] out;

    public Test1(int[] in, int[] out) {
        this.in = in;
        this.out = out;
    }

    @Test
    public void splitArrayAfter4Test() {
        Assert.assertArrayEquals(out, MainClass.splitArrayAfter4(in));
    }

    @Test(expected = RuntimeException.class)
    public void splitArrayAfter4Test2() {
        Assert.assertArrayEquals(out, MainClass.splitArrayAfter4(in));
    }

    @Test(expected = NullPointerException.class)
    public void splitArrayAfter4Test3() {
        Assert.assertArrayEquals(out, MainClass.splitArrayAfter4(in));
    }
}
