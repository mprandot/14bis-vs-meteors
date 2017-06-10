package com.dev.marcio.biscocos2d.sprites;

import com.dev.marcio.biscocos2d.R;
import com.dev.marcio.biscocos2d.delegates.ShootEngineDelegate;
import com.dev.marcio.biscocos2d.util.Assets;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;

import static com.dev.marcio.biscocos2d.util.DeviceSettings.screenResolution;

/**
 * Created by marcio on 10/06/17.
 */
public class Shoot extends CCSprite {

    private ShootEngineDelegate delegate;
    float positionX, positionY;

    public Shoot(float positionX, float positionY) {
        super(Assets.SHOOT);
        this.positionX = positionX;
        this.positionY = positionY;
        setPosition(this.positionX, this.positionY);
        this.schedule("update");
        start();
    }

    public void update(float dt) {
        positionY += 3;
        this.setPosition(
                screenResolution(
                        CGPoint.ccp(positionX, positionY)
                )
        );


    }

    public void setDelegate(ShootEngineDelegate delegate) {
        this.delegate = delegate;
    }

    public void explode() {
        this.delegate.removeShoot(this);
        this.unschedule("update");
        float dt = 0.2f;
        CCScaleBy a1 = CCScaleBy.action(dt, 2f);
        CCFadeOut a2 = CCFadeOut.action(dt);
        CCSpawn s1 = CCSpawn.actions(a1, a2);
        CCCallFunc c1 = CCCallFunc.action(this, "removeMe");
        this.runAction(CCSequence.actions(s1, c1));
    }

    public void removeMe() {
        this.removeFromParentAndCleanup(true);
    }

    public void start() {
        SoundEngine.sharedEngine().playEffect(
                CCDirector.sharedDirector().getActivity(), R.raw.shoot);
    }
}
