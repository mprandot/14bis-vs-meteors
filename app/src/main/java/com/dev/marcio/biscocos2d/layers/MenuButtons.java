package com.dev.marcio.biscocos2d.layers;

import android.bluetooth.BluetoothClass;
import android.util.Log;

import com.dev.marcio.biscocos2d.delegates.ButtonDelegate;
import com.dev.marcio.biscocos2d.util.Assets;

import static com.dev.marcio.biscocos2d.util.DeviceSettings.screenResolution;
import static com.dev.marcio.biscocos2d.util.DeviceSettings.screenHeight;
import static com.dev.marcio.biscocos2d.util.DeviceSettings.screenWidth;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.types.CGPoint;

/**
 * Created by marcio on 09/06/17.
 */
public class MenuButtons extends CCLayer implements ButtonDelegate {

    private Button play;
    private Button score;
    private Button help;

    public MenuButtons() {
        play = new Button(Assets.PLAY);
        score = new Button(Assets.HIGHSCORE);
        help = new Button(Assets.HELP);

        setPositionButtons();

        addChild(play);
        addChild(score);
        addChild(help);

        play.setDelegate(this);
        score.setDelegate(this);
        help.setDelegate(this);
    }

    public void setPositionButtons() {

        play.setPosition(
                screenResolution(CGPoint.ccp(
                        ((int) screenWidth()) >> 1,
                        screenHeight() - 250
                ))
        );

        score.setPosition(
                screenResolution(CGPoint.ccp(
                        ((int) screenWidth()) >> 1,
                        screenHeight() - 300
                ))
        );

        help.setPosition(
                screenResolution(CGPoint.ccp(
                        ((int) screenWidth()) >> 1,
                        screenHeight() - 350
                ))
        );

    }


    @Override
    public void buttonClicked(Button button) {

        if (button.equals(play)) {
            Log.d("JOGO", "PLAY");

        } else if (button.equals(score)) {
            Log.d("JOGO", "score");

        } else if (button.equals(help)) {
            Log.d("JOGO", "help");

        }
    }

}
