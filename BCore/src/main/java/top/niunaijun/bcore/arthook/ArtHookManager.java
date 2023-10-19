package top.niunaijun.bcore.arthook;

import android.util.Log;

import org.lsposed.lsplant.LSPlantHooker;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Arrays;


public class ArtHookManager {

    private static final String TAG = "black";

    private static final ArrayList<LSPlantHooker> lspUnhookList = new ArrayList<>();


    public static void hook(HookCallback hookCallback, Class<?> clazz, String targetName, Class<?>... paramTypes){
        try{
            hook(hookCallback, getMethod(clazz, targetName, paramTypes));
        }catch (Exception e){
            log("hook fail, err=" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void hook(HookCallback hookCallback, Member method){
        log("hook " + method.getDeclaringClass().getSimpleName() + "#" + method.getName() + "()");

        LSPlantHooker lsPlantHooker = LSPlantHooker.hook(method, new LSPlantHooker.HookCallback() {
            @Override
            public void beforeHookedMethod(Member member, Object obj, Object[] objects) throws Throwable{
                MethodFrame mf = new MethodFrame(method);
                mf.thisObject = obj;
                mf.setArgs(objects);
                hookCallback.onBeforeCall(mf);
            }

            @Override
            public Object afterHookedMethod(Member member, Object obj, Object[] objects, Object o) throws Throwable{
                MethodFrame mf = new MethodFrame(method);
                mf.thisObject = obj;
                mf.setArgs(objects);
                mf.setResult(o);
                return hookCallback.onAfterCall(mf);
            }
        });
        lspUnhookList.add(lsPlantHooker);
    }

    public static void unhookAll(){
        for (LSPlantHooker unhook : lspUnhookList){
            unhook.unhook();
        }
        lspUnhookList.clear();
    }

    private static Member getMethod(Class<?> c, String targetName, Class<?>... paramTypes) throws NoSuchMethodException{
        if (targetName != null) {
            return ReflectionHelper.getMethod(c, targetName, paramTypes);
        } else {
            return ReflectionHelper.getConstructor(c, paramTypes);
        }
    }

    public interface HookCallback{
        default void onBeforeCall(MethodFrame frame) throws Throwable{
            log("onBeforeCall " + frame.getMethod().getDeclaringClass().getSimpleName() + "#"
                    + frame.getMethod().getName() + "() obj=" + frame.getThisObject()
                    + " arg=" + Arrays.toString(frame.getArgs()));
        }

        default Object onAfterCall(MethodFrame frame) throws Throwable{
            log("onAfterCall " + frame.getMethod().getDeclaringClass().getSimpleName() + "#"
                    + frame.getMethod().getName() + "() result=" + frame.getResult()
                    + " throwable=" + frame.getThrowable());
            return null;
        }
    }

    public static class MethodFrame{
        private final Member method;

        private Object thisObject;
        private Object[] args;
        private Object result;
        private Throwable throwable;

        public MethodFrame(Member method){
            this.method = method;
        }

        public Member getMethod() {
            return method;
        }

        public Object getThisObject() {
            return thisObject;
        }

        public void setThisObject(Object thisObject) {
            this.thisObject = thisObject;
        }

        public Object[] getArgs() {
            return args;
        }

        public void setArgs(Object[] args) {
            this.args = args;
        }

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
            this.result = result;
        }

        public Throwable getThrowable() {
            return throwable;
        }

        public void setThrowable(Throwable throwable) {
            this.throwable = throwable;
        }
    }

    public static void log(String msg){
        Log.e(TAG, msg);
//        if (BuildConfig.DEBUG){
//            Log.e(TAG, msg);
//        }
    }
}
