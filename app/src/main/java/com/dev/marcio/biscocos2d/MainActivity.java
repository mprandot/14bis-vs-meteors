package com.dev.marcio.biscocos2d;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dev.marcio.biscocos2d.layers.TitleLayer;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        CCGLSurfaceView ccglSurfaceView  = new CCGLSurfaceView(this);

        setContentView(ccglSurfaceView);

        CCDirector.sharedDirector().attachInView(ccglSurfaceView);

        CCDirector.sharedDirector().setScreenSize(320, 480);

        CCDirector.sharedDirector().runWithScene(new TitleLayer().scene());
    }
}
