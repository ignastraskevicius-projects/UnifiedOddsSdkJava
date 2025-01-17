/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.unifiedodds.sdk.caching;

import com.google.common.base.Preconditions;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 27/10/2017.
 * SDKCacheRemovalListener
 */
public class SDKCacheRemovalListener<K, V> implements RemovalListener<K, V> {
    private static final Logger logger = LoggerFactory.getLogger(SDKCacheRemovalListener.class);
    private final String cacheName;
    private final boolean useDebugLog;
    private boolean logRemoval;

    public SDKCacheRemovalListener(String cacheName) {
        this(cacheName, false);
    }

    public SDKCacheRemovalListener(String cacheName, boolean useDebugLog) {
        Preconditions.checkNotNull(cacheName);
        this.cacheName = cacheName;
        this.useDebugLog = useDebugLog;
        this.logRemoval = true;
    }

    @Override
    public void onRemoval(RemovalNotification<K, V> notification) {
        if(!logRemoval){
            return;
        }
        if (!useDebugLog) {
            logger.info("{}: CacheItem[{}] invalidated, reason: {}", cacheName, notification.getKey(), notification.getCause());
            return;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("{}: CacheItem[{}] invalidated, reason: {}", cacheName, notification.getKey(), notification.getCause());
        }
    }

    public void EnableLogRemoval(boolean logRemoval){
        this.logRemoval = logRemoval;
    }
}
