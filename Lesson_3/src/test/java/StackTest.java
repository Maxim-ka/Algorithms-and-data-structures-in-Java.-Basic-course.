import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class StackTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList (new Object[][]{
            {"{()()<[{()}](){<>}((()[][][][]<><>,;axaq{}{{{<{}>}}}", "Оставшиеся незакрытые скобки, позиция: 20(, позиция: 19(, позиция: 6<, позиция: 1{", "}}}>}{<{{{}{qaxa;,><><][][][][)(((}><{)(]})({[<)()({"},
            {"sm<{vol(;)[],{<sv>}}(<lsd{mls[dlmv]}>)>", null, ">)>}]vmld[slm{dsl<(}}>vs<{,][);(lov{<ms"},
            {"p][aspvc", "Нет [ позиция 2", "cvpsa[]p"},
            {"<{()}>[asc]<{{{()[]}}}}.>", "Отсутствует > позиции 12, текущая } позиция 23", ">.}}}}][)({{{<]csa[>})({<"},
            {"<", "< - нет пары", "<"},
            {"[{{<<(()>>}}]", "Отсутствует ) позиции 6, текущая > позиция 9", "]}}>>)((<<{{["},
            {"vdjs[ks<(){}>}vb", "Отсутствует ] позиции 5, текущая } позиция 14", "bv}>}{)(<sk[sjdv"},
            {"{}[<(<>)>][({}{})<>]([{[]}]<>)", null, ")><]}][{[(]><)}{}{([]>)><(<[}{"},
            {"{{<<(((()[][])<>)())>{}>}}}", "Нет { позиция 27", "}}}>}{>))()><)][][)((((<<{{"},
        });
    }

    private String in;
    private String error;
    private String reverse;

    public StackTest(String in, String error, String reverse){
        this.in = in;
        this.error = error;
        this.reverse = reverse;
    }

    private WorkWithStack stack;

    @Before
    public void createStack(){
        stack = new WorkWithStack();
    }

    @Test
    public void testParseSequences(){
        Assert.assertEquals(error,  stack.parseSequences(in));
    }

    @Test
    public void testReverse(){
        Assert.assertEquals(reverse, stack.reverse(in));
    }
}
