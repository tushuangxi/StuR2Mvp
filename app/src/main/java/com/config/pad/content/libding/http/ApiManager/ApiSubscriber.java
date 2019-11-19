package com.config.pad.content.libding.http.ApiManager;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Function：过滤onComplete()
 */

public abstract class ApiSubscriber<T> extends ResourceSubscriber<T> {

    @Override
    public void onComplete() {

    }

}
