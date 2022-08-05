/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.unifiedodds.sdk.cfg;

import com.sportradar.unifiedodds.sdk.ExceptionHandlingStrategy;

import java.util.List;
import java.util.Locale;

/**
 * Base method definitions for custom and general configuration builders
 */
public interface ConfigurationBuilderBase<T> {
    /**
     * Sets the general configuration properties to values read from configuration file. Only value which can be set
     * through {@link ConfigurationBuilderBase} methods are set. Any values already set by methods on the current instance
     * are overridden.
     *
     * The properties file should be named "UFSdkConfiguration.properties" and localed in the application resources folder
     *
     * @return a {@link ConfigurationBuilderBase} derived instance used to set general configuration properties
     */
    T loadConfigFromSdkProperties();

    /**
     * Sets the general configuration properties to values read from configuration file. Only value which can be set
     * through {@link ConfigurationBuilderBase} methods are set. Any values already set by methods on the current instance
     * are overridden.
     *
     * The YAML file should be named "application.yml" and localed in the application resources folder
     *
     * @return a {@link ConfigurationBuilderBase} derived instance used to set general configuration properties
     */
    T loadConfigFromApplicationYml();

    /**
     * Sets the default language for the translatable data
     *
     * @param defaultLocale a {@link Locale} which will be used as default
     * @return a {@link ConfigurationBuilderBase} derived instance used to set general configuration properties
     */
    T setDefaultLocale(Locale defaultLocale);

    /**
     * Sets the languages in which translatable data is available
     *
     * @param supportedLocales a {@link List} of {@link Locale}s in which translatable data should be available
     * @return a {@link ConfigurationBuilderBase} derived instance used to set general configuration properties
     */
    T setDesiredLocales(List<Locale> supportedLocales);

    /**
     * Sets the value specifying how exceptions thrown in the SDK are handled
     *
     * @param exceptionHandlingStrategy a {@link ExceptionHandlingStrategy} enum specifying how exceptions thrown in the SDK are handled
     * @return a {@link ConfigurationBuilderBase} derived instance used to set general configuration properties
     */
    T setExceptionHandlingStrategy(ExceptionHandlingStrategy exceptionHandlingStrategy);

    /**
     * Sets the node id used to separate between SDK instances associated with the same account
     * MTS customer must set this value! Use only positive numbers; negative are reserved for internal use.
     *
     * @param nodeId the node id to be set
     * @return a {@link ConfigurationBuilderBase} derived instance used to set general configuration properties
     */
    T setSdkNodeId(int nodeId);

    /**
     * Specifies the producers which should be disabled (i.e. no recovery, messages get discarded, ...)
     *
     * @param producerIds the list of producer ids specifying the producers which should be disabled
     * @return a {@link RecoveryConfigurationBuilder} derived instance used to set general configuration properties
     */
    T setDisabledProducers(List<Integer> producerIds);

    /**
     * Builds and returns a {@link OddsFeedConfiguration} instance
     *
     * @return the constructed {@link OddsFeedConfiguration} instance
     */
    OddsFeedConfiguration build();

    /**
     * Sets the timeout which should be used on HTTP requests(seconds)
     *
     * @return a {@link ConfigurationBuilderBase} derived instance used to set general configuration properties
     */
    T setHttpClientTimeout(Integer httpClientTimeout);

    /**
     * Sets connection pool size for http client.
     * Should be set to low value to avoid resource overuse.
     * Default: 20
     *
     * @return a {@link ConfigurationBuilderBase} derived instance used to set general configuration properties
     */
    T setHttpClientMaxConnTotal(Integer httpClientMaxConnTotal);

    /**
     * Sets maximum number of concurrent connections per route for http client.
     * Should be set to low value to avoid resource overuse.
     * Default: 15
     *
     * @return a {@link ConfigurationBuilderBase} derived instance used to set general configuration properties
     */
    T setHttpClientMaxConnPerRoute(Integer httpClientMaxConnPerRoute);

    /**
     * Sets the timeout which should be used on HTTP requests for recovery endpoints(seconds)
     *
     * @return a {@link ConfigurationBuilderBase} derived instance used to set general configuration properties
     */
    T setRecoveryHttpClientTimeout(Integer recoveryHttpClientTimeout);

    /**
     * Sets connection pool size for recovery http client.
     * Should be set to low value to avoid resource overuse.
     * Default: 20
     *
     * @return a {@link ConfigurationBuilderBase} derived instance used to set general configuration properties
     */
    T setRecoveryHttpClientMaxConnTotal(Integer recoveryHttpClientMaxConnTotal);

    /**
     * Sets maximum number of concurrent connections per route for recovery http client
     * Should be set to low value to avoid resource overuse.
     * Default: 15
     *
     * @return a {@link ConfigurationBuilderBase} derived instance used to set general configuration properties
     */
    T setRecoveryHttpClientMaxConnPerRoute(Integer recoveryHttpClientMaxConnPerRoute);

    /**
     * Enables use of concurrent OddsFeedListener.
     * Default: true
     *
     * @return a {@link ConfigurationBuilderBase} derived instance used to set general configuration properties
     */
    T setConcurrentListenerEnabled(boolean enable);

    /**
     * Sets the number of threads to use for concurrent OddsFeedListener.
     * Default: 10
     *
     * @return a {@link ConfigurationBuilderBase} derived instance used to set general configuration properties
     */
    T setConcurrentListenerThreads(int threadCount);

    /**
     * Sets the queue size to use for concurrent listener.
     * Default: 10000
     *
     * @return a {@link ConfigurationBuilderBase} derived instance used to set general configuration properties
     */
    T setConcurrentListenerQueueSize(int queueSize);

    /**
     * Enables the handling of errors asynchronously for concurrent listener.
     * Default: true
     *
     * @return a {@link ConfigurationBuilderBase} derived instance used to set general configuration properties
     */
    T setConcurrentListenerHandleErrorsAsynchronously(boolean handleErrorsAsynchronously);
}
