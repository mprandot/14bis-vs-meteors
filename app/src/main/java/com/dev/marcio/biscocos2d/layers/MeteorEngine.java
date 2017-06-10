package com.dev.marcio.biscocos2d.layers;

import com.dev.marcio.biscocos2d.delegates.MeteorsEngineDelegate;
import com.dev.marcio.biscocos2d.sprites.Meteor;
import com.dev.marcio.biscocos2d.util.Assets;

import org.cocos2d.layers.CCLayer;

import java.util.Random;

/**
 * Created by marcio on 10/06/17.
 */
public class MeteorEngine extends CCLayer {

    private MeteorsEngineDelegate delegate;

    public MeteorEngine() {
        this.schedule("meteorsEngine", 1.0f / 10f);
    }

    public void meteorsEngine(float dt) {
        // sorte: 1 em 30 gera um novo meteoro!
        if (new Random().nextInt(30) == 0) {
            this.getDelegate().createMeteor(new Meteor(Assets.METEOR));
        }
    }

    public void setDelegate(MeteorsEngineDelegate delegate) {
        this.delegate = delegate;
    }

    public MeteorsEngineDelegate getDelegate() {
        return delegate;
    }
}
