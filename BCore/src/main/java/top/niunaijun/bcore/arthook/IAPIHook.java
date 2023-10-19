package top.niunaijun.bcore.arthook;

public interface IAPIHook {
    default void init(){}
    default void hookCustomAPI(ClassLoader classLoader, String appPackageName){}
    default void hookAndroidAPI(){}
    default void log(String msg){
        ArtHookManager.log(msg);
    }
}
