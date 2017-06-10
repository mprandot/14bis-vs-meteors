package com.dev.marcio.biscocos2d.scene;

/**
 * Created by marcio on 10/06/17.
 */

import com.dev.marcio.biscocos2d.delegates.MeteorsEngineDelegate;
import com.dev.marcio.biscocos2d.delegates.ShootEngineDelegate;
import com.dev.marcio.biscocos2d.layers.GameButtons;
import com.dev.marcio.biscocos2d.layers.MeteorEngine;
import com.dev.marcio.biscocos2d.sprites.Meteor;
import com.dev.marcio.biscocos2d.sprites.Player;
import com.dev.marcio.biscocos2d.sprites.ScreenBackground;
import com.dev.marcio.biscocos2d.sprites.Shoot;
import com.dev.marcio.biscocos2d.util.Assets;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.dev.marcio.biscocos2d.util.DeviceSettings.screenHeight;
import static com.dev.marcio.biscocos2d.util.DeviceSettings.screenResolution;
import static com.dev.marcio.biscocos2d.util.DeviceSettings.screenWidth;

public class GameScene extends CCLayer implements MeteorsEngineDelegate, ShootEngineDelegate {

    private ScreenBackground background;

    private MeteorEngine meteorsEngine;
    private CCLayer meteorsLayer;
    private List meteorsArray;


    private CCLayer playerLayer;
    private Player player;

    private GameButtons gameButtonsLayer;

    private CCLayer shootsLayer;
    private ArrayList shootsArray;


    private GameScene() {
        this.background = new ScreenBackground(Assets.BACKGROUND);
        this.background.setPosition(
                screenResolution(
                        CGPoint.ccp(screenWidth() / 2.0f, screenHeight() / 2.0f)));
        this.addChild(this.background);

        meteorsLayer = CCLayer.node();
        this.addChild(meteorsLayer);


        playerLayer = CCLayer.node();
        addChild(playerLayer);

        gameButtonsLayer = new GameButtons();
        gameButtonsLayer.setDelegate(this);
        addChild(gameButtonsLayer);


        shootsLayer = CCLayer.node();
        addChild(shootsLayer);

        addGameObjects();
    }

    public void addGameObjects() {
        meteorsArray = new ArrayList();
        meteorsEngine = new MeteorEngine();

        player = new Player();
        player.setRotation(180);
        playerLayer.addChild(player);
        player.setDelegate(this);

        shootsArray = new ArrayList();

    }

    @Override
    public void onEnter() {
        super.onEnter();
        startEngines();
        schedule("checkHits");
    }

    public void startEngines() {
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


    public boolean shoot() {
        player.shoot();
        return true;
    }

    public void moveLeft() {
        player.moveLeft();
    }

    public void moveRight() {
        player.moveRight();
    }

    @Override
    public void removeShoot(Shoot shoot) {
        this.shootsArray.remove(shoot);
    }

    @Override
    public void createShoot(Shoot shoot) {
        this.shootsLayer.addChild(shoot);
        shoot.setDelegate(this);
        shoot.start();
        this.shootsArray.add(shoot);
    }


    public CGRect getBoarders(CCSprite object) {
        CGRect rect = object.getBoundingBox();
        return rect;
    }

    private boolean checkRadiusHitsOfArray(List<? extends CCSprite> array1,
                                           List<? extends CCSprite> array2,
                                           GameScene gameScene,
                                           String hit) {
        boolean result = false;
        for (int i = 0; i < array1.size(); i++) {
            // Pega objeto do primeiro array
            CGRect rect1 = getBoarders(array1.get(i));
            for (int j = 0; j < array2.size(); j++) {
                // Pega objeto do segundo array
                CGRect rect2 = getBoarders(array2.get(j));
                // Verifica colisão
                if (CGRect.intersects(rect1, rect2)) {

                    result = true;
                    Method method;

                    try {
                        method = GameScene.class.getMethod(hit, CCSprite.class, CCSprite.class);
                        method.invoke(gameScene, array1.get(i), array2.get(j));
                    } catch (SecurityException e1) {
                        e1.printStackTrace();
                    } catch (NoSuchMethodException e1) {
                        e1.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }


    public void checkHits(float dt) {
        this.checkRadiusHitsOfArray(this.meteorsArray,
                this.shootsArray, this, "meteoroHit");
//        this.checkRadiusHitsOfArray(this.meteorsArray,
//                this.playersArray, this, "playerHit");
    }

    public void meteoroHit(CCSprite meteor, CCSprite shoot) {
        ((Meteor) meteor).shooted();
        ((Shoot) shoot).explode();
//        this.score.increase();
    }

//    public void playerHit(CCSprite meteor, CCSprite player) {
//        ((Meteor) meteor).shooted();
//        ((Player) player).explode();
//    }


}