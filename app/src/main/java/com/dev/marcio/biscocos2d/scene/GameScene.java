package com.dev.marcio.biscocos2d.scene;

/**
 * Created by marcio on 10/06/17.
 */

import com.dev.marcio.biscocos2d.delegates.MeteorsEngineDelegate;
import com.dev.marcio.biscocos2d.layers.MeteorEngine;
import com.dev.marcio.biscocos2d.sprites.Meteor;
import com.dev.marcio.biscocos2d.sprites.ScreenBackground;
import com.dev.marcio.biscocos2d.util.Assets;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.types.CGPoint;

import java.util.ArrayList;
import java.util.List;

import static com.dev.marcio.biscocos2d.util.DeviceSettings.screenResolution;
import static com.dev.marcio.biscocos2d.util.DeviceSettings.screenHeight;
import static com.dev.marcio.biscocos2d.util.DeviceSettings.screenWidth;

public class GameScene extends CCLayer implements MeteorsEngineDelegate {
    private ScreenBackground background;

    private MeteorEngine meteorsEngine;
    private CCLayer meteorsLayer;
    private List meteorsArray;

    private GameScene() {
        this.background = new ScreenBackground(Assets.BACKGROUND);
        this.background.setPosition(
                screenResolution(
                        CGPoint.ccp(screenWidth() / 2.0f, screenHeight() / 2.0f)));
        this.addChild(this.background);

        meteorsLayer = CCLayer.node();
        this.addChild(meteorsLayer);

        addGameObjects();
    }

    public void addGameObjects(){
        meteorsArray = new ArrayList();
        meteorsEngine = new MeteorEngine();
    }

    @Override
    public void onEnter() {
        super.onEnter();
        startEngines();
    }

    public void startEngines(){
        addChild(meteorsEngine);
        meteorsEngine.setDelegate(this);
    }

    public static CCScene createGame() {
        CCScene scene = CCScene.node();
        GameScene layer = new GameScene();
        scene.addChild(layer);
        return scene;
    }

    @Override
    public void createMeteor(Meteor meteor) {
        meteor.setDelegate(this);
        meteorsLayer.addChild(meteor);
        meteor.start();
        meteorsArray.add(meteor);
    }

    @Override
    public void removeMeteor(Meteor meteor) {
        meteorsArray.remove(meteor);
        removeChild(meteor, true);
    }


}