package com.dev.marcio.biscocos2d.util;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

/**
 * Created by marcio on 09/06/17.
 */
public class DeviceSettings {

    public static CGPoint screenResolution(CGPoint point) {
        return point;
    }


    public static float screenWidth () {
        return winSize().width;
    }

    public static float screenHeight () {
        return winSize().height;
    }

    public static CGSize winSize() {
        return CCDirector.sharedDirector().winSize();
    }

}
