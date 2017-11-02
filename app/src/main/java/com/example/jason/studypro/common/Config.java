package com.example.jason.studypro.common;

import com.example.jason.studypro.BuildConfig;

/**
 * @author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/13$ 15:13$
 * <p/>
 */
public class Config {
    public static       Observable<String>  PATH     = new Observable<>(null);
    public static       Observable<Boolean> isDebug  = new Observable<>();
    public static final String              API_HOST = BuildConfig.API_HOST;
}
