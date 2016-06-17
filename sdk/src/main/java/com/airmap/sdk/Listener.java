package com.airmap.sdk;

/**
 * Created by Vansh Gandhi on 6/17/16.
 */
public interface Listener {
    void onError();
    void onSuccess(String flightRules);
}
