package io.tulaa.tulaasolution.application;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
// Singleton TulaaApplication class that we will use to instantiate
// Application for Tulaa
public class TulaaApplication extends android.app.Application {
    private static TulaaApplication mInstance;
    private static RequestQueue mRequestQueue;

    public static TulaaApplication getmInstance(){
        if(mInstance !=null){
            return mInstance;
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static RequestQueue getRequestQueue(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }

        return mRequestQueue;
    }
}
