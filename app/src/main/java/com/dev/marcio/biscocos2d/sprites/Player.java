package com.dev.marcio.biscocos2d.sprites;

import com.dev.marcio.biscocos2d.delegates.ShootEngineDelegate;
import com.dev.marcio.biscocos2d.util.Assets;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import static com.dev.marcio.biscocos2d.util.DeviceSettings.screenWidth;

/**
 * Created by marcio on 10/06/17.
 */
public class Player extends CCSprite {

    ShootEngineDelegate delegate;

    float positionX = screenWidth() / 2;
    float positionY = 50;

    public void start() {
        this.schedule("update");
    }


    public Player() {
        super(Assets.NAVE);
        setPosition(positionX, positionY);

        start();
    }

    public void setDelegate(ShootEngineDelegate delegate) {
        this.delegate = delegate;
    }

    public void moveLeft() {
        if (positionX > 30) {
            positionX -= 20;
        }
        setPosition(positionX, positionY);
    }

    public void moveRight() {
        if (positionX < screenWidth() - 30) {
            positionX += 20;
        }
        setPosition(positionX, positionY);
    }

    public void update(float dt) {
        // Configura posicao do aviao
        this.setPosition(CGPoint.ccp(this.positionX, this.positionY));
    }

    public void shoot() {

        delegate.createShoot(
                new Shoot(positionX, positionY));
    }
}
