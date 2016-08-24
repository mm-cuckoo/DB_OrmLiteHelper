package com.cfox.ormlitelib.dbUtils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * <br/>************************************************
 * <br/>PROJECT_NAME : DB_OrmLiteHelper
 * <br/>PACKAGE_NAME : com.cfox.ormlitelib.dbUtils
 * <br/>AUTHOR : Machao
 * <br/>DATA : 2016/8/24 0024
 * <br/>TIME : 11:36
 * <br/>MSG :
 * <br/>************************************************
 */
public class StorageUtils {
    /**
     * 判断 SD 卡是否可用
     * @return
     */
    public static boolean existSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }

    /**
     * 获取 SD 卡中剩余空间
     * @return
     */
    public long getSDFreeSize(){
        //取得SD卡文件路径
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        //获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();
        //空闲的数据块的数量
        long freeBlocks = sf.getAvailableBlocks();
        //返回SD卡空闲大小
        //return freeBlocks * blockSize;  //单位Byte
        //return (freeBlocks * blockSize)/1024;   //单位KB
        return (freeBlocks * blockSize)/1024 /1024; //单位MB
    }

    /**
     * 获取 SD 卡总容量
     * @return
     */
    public long getSDAllSize(){
        //取得SD卡文件路径
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        //获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();
        //获取所有数据块数
        long allBlocks = sf.getBlockCount();
        //返回SD卡大小
        //return allBlocks * blockSize; //单位Byte
        //return (allBlocks * blockSize)/1024; //单位KB
        return (allBlocks * blockSize)/1024/1024; //单位MB
    }
}
