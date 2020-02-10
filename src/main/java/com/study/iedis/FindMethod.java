package com.study.iedis;

import com.google.common.collect.Lists;
import com.seventh7.widget.iedis.B;
import javassist.*;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by fengyiwei on 2017/11/19.
 */
public class FindMethod {

    public ClassPool pool = ClassPool.getDefault();

    @Before
    public void init() throws NotFoundException {
        pool.insertClassPath("/Users/liangyuanbing/Documents/soft/idea/plugins/iedis-3.0.jar");
    }

    @Test
    public void test() throws Exception {
        solve("com.seventh7.widget.iedis.L", "L", Lists.<Pair<Integer, Integer>>newArrayList(
                new Pair(28593, 19027),
                new Pair(28595, -18585),
                new Pair(28592, -24596),
                new Pair(28594, 8963)));

        solve("com.seventh7.widget.iedis.i.H", "a", Lists.<Pair<Integer, Integer>>newArrayList(
                new Pair(32541, 2798),
                new Pair(32540, 14797),
                new Pair(32543, 11765),
                new Pair(32538, -8699)));

        solve("com.seventh7.widget.iedis.I", "a", Lists.<Pair<Integer, Integer>>newArrayList(
                new Pair(28963, 29033),
                new Pair(28960, -10657),
                new Pair(28961, -27259),
                new Pair(28964, -2555),
                new Pair(28962, 3702)
        ));


//        通过机器特征生成MD5
        solve("com.seventh7.widget.iedis.B", "a", Lists.<Pair<Integer, Integer>>newArrayList(
                new Pair(-23112, -14364),
                new Pair(-23132, -10875),
                new Pair(-23105, -18930),
                new Pair(-23120, 22859),
                new Pair(-23117, -11551),
                new Pair(-23110, -113),
                new Pair(-23109, 2830),
                new Pair(-23107, 6209),
                new Pair(-23113, -15762),
                new Pair(-23116, -11570),
                new Pair(-23115, 25731),
                new Pair(-23133, 18247),
                new Pair(-23136, -19246),
                new Pair(-23134, -1566),
                new Pair(-23119, 12053),
                new Pair(-23135, 1150),
                new Pair(-23111, 2790),
                new Pair(-23131, 26461),
                new Pair(-23118, 31407),
                new Pair(-23108, 20442),
                new Pair(-23106, -31881),
                new Pair(-23114, -20882)
        ));

        solve("com.seventh7.widget.iedis.G", "a", Lists.<Pair<Integer, Integer>>newArrayList(
                new Pair(-13308, 8317),
                new Pair(-13307, 32547)
        ));

//        solve("com.seventh7.widget.iedis.i.l", "b", Lists.newArrayList(
//                new Pair(-8825, 14388),
//                new Pair(-8826, 31835)
//        ));

        solve("com.seventh7.widget.iedis.f.b", "a", Lists.<Pair<Integer, Integer>>newArrayList(
                new Pair(-5463, 17601),
                new Pair(-5464, 31949),
                new Pair(-5461, 21115)
        ));

        solve("com.seventh7.widget.iedis.f.a", "a", Lists.<Pair<Integer, Integer>>newArrayList(
                new Pair(-17466, -13932),
                new Pair(-17465, 18523)
        ));

        solve("com.seventh7.widget.iedis.d.n", "b", Lists.<Pair<Integer, Integer>>newArrayList(
                new Pair(-28712, 2725),
                new Pair(-28711, 9088)
        ));

        solve("com.seventh7.widget.iedis.d.r", "b", Lists.<Pair<Integer, Integer>>newArrayList(
                new Pair(-10114, 25401),
                new Pair(-10113, -7803)
        ));

        solve("com.seventh7.widget.iedis.d.p", "c", Lists.<Pair<Integer, Integer>>newArrayList(
                new Pair(8465, 685),
                new Pair(8466, -9627),
                new Pair(8467, 1964)
        ));


        solve("com.seventh7.widget.iedis.d.q", "b", Lists.<Pair<Integer, Integer>>newArrayList(
                new Pair(1534, 14789)
        ));

        solve("com.seventh7.widget.iedis.i.b.b", "a", Lists.<Pair<Integer, Integer>>newArrayList(
                new Pair(29876, -12255),
                new Pair(29877, -31752)
        ));

        //com.seventh7.widget.iedis.a.ar
        //com.seventh7.widget.iedis.a.aC


        // 调用下面的类了
        // 未注册提示信息
        solve("com.seventh7.widget.iedis.i.E", "a", Lists.<Pair<Integer, Integer>>newArrayList(
                new Pair(-17138, 15666),
                new Pair(-17139, -8523),
                new Pair(-17140, -28592),
                new Pair(-17137, 12777)
        ));

        solve("com.seventh7.widget.iedis.b.ax", "a", Lists.<Pair<Integer, Integer>>newArrayList(
                new Pair(31098, 6775),
                new Pair(31097, 12235),
                new Pair(31099, 1283),
                new Pair(31101, 21506),
                new Pair(31096, 13571)
        ));

        solve2("com.seventh7.widget.iedis.a.p", "b", Lists.<Pair<Integer, Integer>>newArrayList(
                new Pair(-11577, 30300),
                new Pair(-11578, -2365),
                new Pair(-11579, -20538),
                new Pair(-11580, -16983)
        ));

        solve2("com.seventh7.widget.iedis.a.o", "a", Lists.<Pair<Integer, Integer>>newArrayList(
                new Pair(-32161, -14011),
                new Pair(-32162, -14706),
                new Pair(-32164, 561),
                new Pair(-32163, 25879)
        ));

//        System.out.println("\ncom.seventh7.widget.iedis.b.aq\n");
//        for (String i : a) {
//            System.out.println(i);
//        }


        System.out.println("\ncom.seventh7.widget.iedis.B\n");
        //根据机器信息生成的md5值
        System.out.println(B.a());

        //http请求注册状态链接
//        System.out.println(com.seventh7.widget.iedis.L.decide(B.a() + ":" + 2));
//        System.out.println(com.seventh7.widget.iedis.L.b(B.a() + ":" + 2));

        //com.seventh7.widget.iedis.g.r 调用 com.seventh7.widget.iedis.L.a(aC.class, this.a) 创建 com.seventh7.widget.iedis.a.aC对象

    }


    public void solve(String className, String methodName, List<Pair<Integer, Integer>> pairList) throws Exception {
        CtClass ctClass = pool.get(className);

        if (className.equals("com.seventh7.widget.iedis.I") == false
                && className.equals("com.seventh7.widget.iedis.d.n") == false
                && className.equals("com.seventh7.widget.iedis.d.r") == false
                && className.equals("com.seventh7.widget.iedis.d.p") == false
                && className.equals("com.seventh7.widget.iedis.d.o") == false
                && className.equals("com.seventh7.widget.iedis.d.q") == false
                && className.equals("com.seventh7.widget.iedis.i.e") == false
                ) {
            CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, ctClass);
            ctConstructor.setBody("{}");
            ctClass.addConstructor(ctConstructor);
        } else if (className.equals("com.seventh7.widget.iedis.i.e")) {
            ctClass = pool.get("com.seventh7.widget.iedis.i.E");
            CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, ctClass);
            ctConstructor.setBody("{}");
            ctClass.addConstructor(ctConstructor);
            CtMethod ctm = new CtMethod(pool.get("java.lang.String"), "tst", null, ctClass);
            StringBuilder sb = new StringBuilder("{");
            for (Pair<Integer, Integer> integerIntegerPair : pairList) {
                sb.append(String.format("System.out.println(\"[ %d,%d ==> \" + %s.%s(%d, %d) + \" ]\");", integerIntegerPair.getFirst(), integerIntegerPair.getSecond(), className, methodName, integerIntegerPair.getFirst(), integerIntegerPair.getSecond()));
            }
            sb.append("return \"123\";}");
            ctm.setBody(sb.toString());

            ctClass.addMethod(ctm);
            ctClass.toClass();

            Method method = Class.forName(className).getDeclaredMethod("tst", new Class[]{});
            System.out.println("\n" + className);
            method.invoke(Class.forName(className).newInstance(), null);
        }

        if (className.equals("com.seventh7.widget.iedis.i.e") == false) {
            CtMethod ctm = new CtMethod(pool.get("java.lang.String"), "tst", null, ctClass);
            StringBuilder sb = new StringBuilder("{");
            for (Pair<Integer, Integer> integerIntegerPair : pairList) {
                sb.append(String.format("System.out.println(\"[ %d,%d ==> \" + %s.%s(%d, %d) + \" ]\");", integerIntegerPair.getFirst(), integerIntegerPair.getSecond(), className, methodName, integerIntegerPair.getFirst(), integerIntegerPair.getSecond()));
            }
            sb.append("return \"123\";}");
            ctm.setBody(sb.toString());

            ctClass.addMethod(ctm);
            ctClass.toClass();

            Method method = Class.forName(className).getDeclaredMethod("tst", new Class[]{});
            System.out.println("\n" + className);
            method.invoke(Class.forName(className).newInstance(), null);
        }

    }

    public void solve2(String className, String methodName, List<Pair<Integer, Integer>> pairList) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method b = Class.forName(className).getDeclaredMethod(methodName, new Class[]{int.class, int.class});
        b.setAccessible(true);
        System.out.println("\n" + className);
        for (Pair<Integer, Integer> integerIntegerPair : pairList) {
            System.out.println(String.format("[ %d,%d ==> %s ]", integerIntegerPair.getFirst(), integerIntegerPair.getSecond(), b.invoke(null, new Object[]{integerIntegerPair.getFirst(), integerIntegerPair.getSecond()})));
        }
    }
}