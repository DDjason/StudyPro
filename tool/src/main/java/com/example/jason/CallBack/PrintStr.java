package com.example.jason.CallBack;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/10/11$ 16:55$
 * <p/>
 */
//回答者
public class PrintStr {

    public void pt_Str(String str,MyCallBack doSome){
        doSome.onDoSome(str);
    }
}
