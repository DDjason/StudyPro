package com.example.jason.studypro.greenDaoTool;

import android.database.sqlite.SQLiteDatabase;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/25$ 16:47$
 * <p/>
 */
public class GreenDaoHelper {
    private static DaoMaster.DevOpenHelper devOpenHelper;
    private static SQLiteDatabase          database;
    private static DaoMaster               daoMaster;
    private static DaoSession              daoSession;
    //单例模式
    private static GreenDaoHelper          mInstance;

    private GreenDaoHelper() {
        //初始化建议放在Application中进行
        if (mInstance == null) {
            //创建数据库"info.db"
            // devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.getContext(), "info.db", null);
            //获取可写数据库
            database = devOpenHelper.getWritableDatabase();
            //获取数据库对象
            daoMaster = new DaoMaster(database);
            //获取Dao对象管理者
            daoSession = daoMaster.newSession();
        }
    }

    public static GreenDaoHelper getInstance() {
        if (mInstance == null) {
            //保证异步处理安全操作
            synchronized (GreenDaoHelper.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoHelper();
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
