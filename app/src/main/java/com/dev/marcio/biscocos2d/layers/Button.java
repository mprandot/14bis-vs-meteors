package com.dev.marcio.biscocos2d.layers;

import android.view.MotionEvent;

import com.dev.marcio.biscocos2d.delegates.ButtonDelegate;

import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

/**
 * Created by marcio on 09/06/17.
 */
public class Button extends CCLayer {


    private CCSprite buttonImg;

    private ButtonDelegate delegate;

    public Button(String img) {
        setIsTouchEnabled(true);
        buttonImg = new CCSprite(img);
        addChild(buttonImg);
    }

    public void setDelegate(ButtonDelegate delegate) {
        this.delegate = delegate;
    }


    @Override
    protected void registerWithTouchDispatcher() {
        //manage touch event
        CCTouchDispatcher.sharedDispatcher().addTargetedDelegate(this, 0, false);
    }


    @Override
    public boolean ccTouchesBegan(MotionEvent event) {
        //run touch event
        CGPoint touchLocation = CGPoint.make(event.getX(), event.getY());
        touchLocation = CCDirector.sharedDirector().convertToGL(touchLocation);
        touchLocation = this.convertToNodeSpace(touchLocation);

        // verify button touch
        if (CGRect.containsPoint(buttonImg.getBoundingBox(), touchLocation)) {
            delegate.buttonClicked(this);
        }

        return true;
    }
}
