package com.dev.marcio.biscocos2d.layers;

import com.dev.marcio.biscocos2d.sprites.ScreenBackground;
import com.dev.marcio.biscocos2d.util.Assets;
import com.dev.marcio.biscocos2d.util.DeviceSettings;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import static com.dev.marcio.biscocos2d.util.DeviceSettings.screenResolution;
import static com.dev.marcio.biscocos2d.util.DeviceSettings.screenHeight;
import static com.dev.marcio.biscocos2d.util.DeviceSettings.screenWidth;

/**
 * Created by marcio on 09/06/17.
 */
public class TitleLayer extends CCLayer {

    private ScreenBackground background;

    public TitleLayer() {
        background = new ScreenBackground(Assets.BACKGROUND);

        //Anchor point (default center)

        CGPoint cgPoint = screenResolution(CGPoint.ccp(
                screenWidth() / 2,
                screenHeight() / 2)
        );

        background.setPosition(cgPoint);

        this.addChild(background);


        CCSprite logo = new CCSprite(Assets.LOGO);
        logo.setPosition(screenResolution(CGPoint.ccp(screenWidth() / 2, screenHeight() - 130)));
        this.addChild(logo);


        CCSprite play = new CCSprite(Assets.PLAY);
        play.setPosition(logo.getPosition().x, screenHeight() / 2);
        this.addChild(play);

        CCSprite score = new CCSprite(Assets.HIGHSCORE);
        score.setPosition(logo.getPosition().x, play.getPosition().y - 50);
        this.addChild(score);

        CCSprite help = new CCSprite(Assets.HELP);
        help.setPosition(logo.getPosition().x, score.getPosition().y - 50);
        this.addChild(help);

        CCSprite sound = new CCSprite(Assets.SOUND);
        sound.setPosition(50, 50);
        this.addChild(sound);


    }

    public CCScene scene() {
        CCScene scene = CCScene.node();
        scene.addChild(this);
        return scene;
    }

}
