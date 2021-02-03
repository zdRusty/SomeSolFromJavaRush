package Level_32.task3205;


import java.lang.reflect.Proxy;

/*
Создание прокси-объекта
*/

public class Solution {
    public static void main(String[] args) {
        SomeInterfaceWithMethods obj = getProxy();
        obj.stringMethodWithoutArgs();
        obj.voidMethodWithIntArg(1);

        /* expected output
        stringMethodWithoutArgs in
        inside stringMethodWithoutArgs
        stringMethodWithoutArgs out
        voidMethodWithIntArg in
        inside voidMethodWithIntArg
        inside voidMethodWithoutArgs
        voidMethodWithIntArg out
        */
    }

    public static SomeInterfaceWithMethods getProxy() {
        SomeInterfaceWithMethods someInterface = new SomeInterfaceWithMethodsImpl();
        ClassLoader classLoader = someInterface.getClass().getClassLoader();
        Class[] classes = new Class[]{SomeInterfaceWithMethods.class};
        return (SomeInterfaceWithMethods) Proxy.newProxyInstance(classLoader,classes,new CustomInvocationHandler(someInterface));
    }
}