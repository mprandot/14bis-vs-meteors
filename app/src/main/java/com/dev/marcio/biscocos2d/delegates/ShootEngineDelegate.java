package com.dev.marcio.biscocos2d.delegates;

import com.dev.marcio.biscocos2d.sprites.Shoot;

/**
 * Created by marcio on 10/06/17.
 */
public interface ShootEngineDelegate {

    public void createShoot(Shoot shoot);

    public void removeShoot(Shoot shoot);
}