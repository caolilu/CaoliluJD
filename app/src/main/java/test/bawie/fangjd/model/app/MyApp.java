package test.bawie.fangjd.model.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.socialize.PlatformConfig;



public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
    {
        PlatformConfig.setWeixin("wx93af82bd3e1f9f3b","39a5650f538d59931ec487d76ec47657");
        PlatformConfig.setQQZone("1106537438","PlXki3rXPx1syapW");
    }
}
