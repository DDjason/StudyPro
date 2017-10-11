package com.example.jason.studypro;

import com.google.gson.Gson;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/28$ 11:36$
 * <p/>
 */
public class MytestJava {
    public static void main(String[] args) throws Throwable {
        MyService service = (MyService) Proxy.newProxyInstance(MyService.class.getClassLoader(), new Class<?>[]{MyService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("Method-----------:" + method.getName());
                System.out.println(new Gson().toJson(objects));
                return null;
            }
        });
        service.getResulr("32");


        String   input = "13 15 16 16 19 20 20 21 22 22 25 25 25 25 30 33 33 35 35 35 35 36 40 45 46 52 70";
        String[] array = input.split(" ");
        HashMap  map   = new HashMap<Integer, Integer>();
        int      sum   = 0;
        for (String age : array) {
            Integer temp = Integer.valueOf(age);
            if (map.containsKey(temp)) {
                Integer mapt = (Integer) map.get(temp);
                int     t    = mapt.intValue();
                t++;
                map.put(temp, Integer.valueOf(t));
            }
            sum += temp;
        }
        int      zs       = 0;
        int      max      = 0;
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer sss  = (Integer) iterator.next();
            Integer inte = (Integer) map.get(sss);
            if (inte.intValue() > max) {
                max = inte.intValue();
                zs = inte.intValue();
            }
        }
        System.out.println("均值: " + 1.0 * sum / array.length + "中值: " + array[array.length / 2] + "   " + zs);
        SimplePoJo poJo = new SimplePoJo("Tom", "23");
        String     json = new Gson().toJson(poJo);
        System.out.println(json);
        SimplePoJo newPojo = new Gson().fromJson(json, SimplePoJo.class);
        System.out.println(new Gson().toJson(newPojo));
    }
}
