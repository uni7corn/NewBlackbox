package top.niunaijun.bcore.arthook;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.PathClassLoader;


public class ArtHookInit {


    private volatile static boolean isHookTargetApkClassOk = false;

    private final static List<IAPIHook> apiHookList = new ArrayList<IAPIHook>(){
        {

        }
    };

    private static String vappPkg;

    public static void init(String pkg){
        log("ArtHookInit::init " + pkg);
        vappPkg = pkg;
        for (IAPIHook hook : apiHookList){
            hook.init();
        }
    }

    public static void hookAll(Application application){
        try {
        log("ArtHookInit::hookAll");
        hookAndroidAPI();
        hookTargetApkCustomAPI(application);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void hookAndroidAPI(){
        for (IAPIHook hook : apiHookList){
            hook.hookAndroidAPI();
        }
    }

    private static void hookTargetApkCustomAPI(Object app) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (isHookTargetApkClassOk){
            return;
        }

        for (IAPIHook hook : apiHookList){
            hook.hookCustomAPI(app.getClass().getClassLoader(), vappPkg);
        }

        isHookTargetApkClassOk = true;
    }

    public static void unhookAll(){
        log("ArtHookInit::unhookAll");
        ArtHookManager.unhookAll();
    }

    private static void log(String msg){
        ArtHookManager.log(msg);
    }
}
