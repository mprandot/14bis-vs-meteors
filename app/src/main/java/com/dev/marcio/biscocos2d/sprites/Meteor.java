package com.dev.marcio.biscocos2d.sprites;

import com.dev.marcio.biscocos2d.R;
import com.dev.marcio.biscocos2d.delegates.MeteorsEngineDelegate;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;

import java.util.Random;

import static com.dev.marcio.biscocos2d.util.DeviceSettings.screenResolution;
import static com.dev.marcio.biscocos2d.util.DeviceSettings.screenHeight;
import static com.dev.marcio.biscocos2d.util.DeviceSettings.screenWidth;

/**
 * Created by marcio on 10/06/17.
 */
public class Meteor extends CCSprite {


    private float x, y;

    private MeteorsEngineDelegate delegate;

    public void setDelegate(MeteorsEngineDelegate delegate) {
        this.delegate = delegate;
    }

    public Meteor(String image) {
        super(image);
        x = new Random().nextInt(Math.round(screenWidth()));
        y = screenHeight();
    }

    public void start() {
        this.schedule("update");
    }

    public void update(float dt) {
        y -= 1;
        this.setPosition(screenResolution(CGPoint.ccp(x, y)));

        if(y < -100){
            delegate.removeMeteor(this);
            unschedule("update");
        }
    }

    public void shooted() {
        this.delegate.removeMeteor(this);
        this.unschedule("update");
        float dt = 0.2f;
        CCScaleBy a1 = CCScaleBy.action(dt, 0.5f);
        CCFadeOut a2 = CCFadeOut.action(dt);
        CCSpawn s1 = CCSpawn.actions(a1, a2);
        CCCallFunc c1 = CCCallFunc.action(this, "removeMe");
        this.runAction(CCSequence.actions(s1, c1));

        SoundEngine.sharedEngine().playEffect(
                CCDirector.sharedDirector().getActivity(), R.raw.bang);
    }

    public void removeMe() {
        removeFromParentAndCleanup(true);
    }
}
