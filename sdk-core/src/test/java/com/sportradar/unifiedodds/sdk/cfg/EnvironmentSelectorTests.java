/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.unifiedodds.sdk.cfg;

import com.sportradar.unifiedodds.sdk.SDKConfigurationPropertiesReader;
import com.sportradar.unifiedodds.sdk.SDKConfigurationYamlReader;
import com.sportradar.unifiedodds.sdk.impl.UnifiedFeedConstants;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created on 27/03/2018.
 * // TODO @eti: Javadoc
 */
public class EnvironmentSelectorTests {
    private static SDKConfigurationPropertiesReader samplePropertiesReader;
    private static SDKConfigurationYamlReader sampleYamlReader;

    @BeforeClass
    public static void init() {
        samplePropertiesReader = Mockito.mock(SDKConfigurationPropertiesReader.class);
        sampleYamlReader = Mockito.mock(SDKConfigurationYamlReader.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void environmentSelectorConstructFailure() {
        EnvironmentSelector environmentSelector = new EnvironmentSelectorImpl(null, samplePropertiesReader, sampleYamlReader);
    }

    @Test(expected = NullPointerException.class)
    public void environmentSelectorConstructFailure2() {
        EnvironmentSelector environmentSelector = new EnvironmentSelectorImpl("sample-token", null, sampleYamlReader);
    }

    @Test(expected = NullPointerException.class)
    public void environmentSelectorConstructFailure3() {
        EnvironmentSelector environmentSelector = new EnvironmentSelectorImpl("sample-token", samplePropertiesReader, null);
    }

    @Test
    public void selectStagingEnvironmentReturnTest() {
        EnvironmentSelector environmentSelector = new EnvironmentSelectorImpl("sample-token", samplePropertiesReader, sampleYamlReader);

        ConfigurationBuilder configurationBuilder = environmentSelector.selectStaging();

        Assert.assertNotNull(configurationBuilder);
    }

    @Test
    public void selectProductionEnvironmentReturnTest() {
        EnvironmentSelector environmentSelector = new EnvironmentSelectorImpl("sample-token", samplePropertiesReader, sampleYamlReader);

        ConfigurationBuilder configurationBuilder = environmentSelector.selectProduction();

        Assert.assertNotNull(configurationBuilder);
    }

    @Test
    public void selectReplayEnvironmentReturnTest() {
        EnvironmentSelector environmentSelector = new EnvironmentSelectorImpl("sample-token", samplePropertiesReader, sampleYamlReader);

        ReplayConfigurationBuilder configurationBuilder = environmentSelector.selectReplay();

        Assert.assertNotNull(configurationBuilder);
    }

    @Test
    public void selectCustomEnvironmentReturnTest() {
        EnvironmentSelector environmentSelector = new EnvironmentSelectorImpl("sample-token", samplePropertiesReader, sampleYamlReader);

        CustomConfigurationBuilder configurationBuilder = environmentSelector.selectCustom();

        Assert.assertNotNull(configurationBuilder);
    }

    @Test
    public void stagingEnvironmentResultValidation() {
        EnvironmentSelector environmentSelector = new EnvironmentSelectorImpl("sample-token", samplePropertiesReader, sampleYamlReader);

        OddsFeedConfiguration cfg = environmentSelector.selectStaging().build();

        Assert.assertNotNull(cfg);
        Assert.assertEquals(cfg.getAccessToken(), "sample-token");
        Assert.assertEquals(cfg.getMessagingHost(), UnifiedFeedConstants.STAGING_MESSAGING_HOST);
        Assert.assertEquals(cfg.getAPIHost(), UnifiedFeedConstants.STAGING_API_HOST);
        Assert.assertEquals(cfg.getMessagingVirtualHost(), null);
        Assert.assertEquals(cfg.getMessagingUsername(), null);
        Assert.assertEquals(cfg.getMessagingPassword(), null);
        Assert.assertEquals(cfg.getPort(), 5671);
        Assert.assertEquals(cfg.getUseMessagingSsl(), true);
        Assert.assertEquals(cfg.getUseApiSsl(), true);
    }

    @Test
    public void productionEnvironmentResultValidation() {
        EnvironmentSelector environmentSelector = new EnvironmentSelectorImpl("sample-token", samplePropertiesReader, sampleYamlReader);

        OddsFeedConfiguration cfg = environmentSelector.selectProduction().build();

        Assert.assertNotNull(cfg);
        Assert.assertEquals(cfg.getAccessToken(), "sample-token");
        Assert.assertEquals(cfg.getMessagingHost(), UnifiedFeedConstants.PRODUCTION_MESSAGING_HOST);
        Assert.assertEquals(cfg.getAPIHost(), UnifiedFeedConstants.PRODUCTION_API_HOST);
        Assert.assertEquals(cfg.getMessagingVirtualHost(), null);
        Assert.assertEquals(cfg.getMessagingUsername(), null);
        Assert.assertEquals(cfg.getMessagingPassword(), null);
        Assert.assertEquals(cfg.getPort(), 5671);
        Assert.assertEquals(cfg.getUseMessagingSsl(), true);
        Assert.assertEquals(cfg.getUseApiSsl(), true);
    }

    @Test
    public void replayEnvironmentResultValidation() {
        EnvironmentSelector environmentSelector = new EnvironmentSelectorImpl("sample-token", samplePropertiesReader, sampleYamlReader);

        OddsFeedConfiguration cfg = environmentSelector.selectReplay().build();

        Assert.assertNotNull(cfg);
        Assert.assertEquals(cfg.getAccessToken(), "sample-token");
        Assert.assertEquals(cfg.getMessagingHost(), UnifiedFeedConstants.REPLAY_MESSAGING_HOST);
        Assert.assertEquals(cfg.getAPIHost(), UnifiedFeedConstants.REPLAY_API_HOST);
        Assert.assertEquals(cfg.getMessagingVirtualHost(), null);
        Assert.assertEquals(cfg.getMessagingUsername(), null);
        Assert.assertEquals(cfg.getMessagingPassword(), null);
        Assert.assertEquals(cfg.getPort(), 5671);
        Assert.assertEquals(cfg.getUseMessagingSsl(), true);
        Assert.assertEquals(cfg.getUseApiSsl(), true);
    }

    @Test
    public void customEnvironmentDefaultResultValidation() {
        EnvironmentSelector environmentSelector = new EnvironmentSelectorImpl("sample-token", samplePropertiesReader, sampleYamlReader);

        OddsFeedConfiguration cfg = environmentSelector.selectCustom().build();

        Assert.assertNotNull(cfg);
        Assert.assertEquals(cfg.getAccessToken(), "sample-token");
        Assert.assertEquals(cfg.getMessagingHost(), UnifiedFeedConstants.STAGING_MESSAGING_HOST);
        Assert.assertEquals(cfg.getAPIHost(), UnifiedFeedConstants.STAGING_API_HOST);
        Assert.assertEquals(cfg.getMessagingVirtualHost(), null);
        Assert.assertEquals(cfg.getMessagingUsername(), null);
        Assert.assertEquals(cfg.getMessagingPassword(), null);
        Assert.assertEquals(cfg.getPort(), 5671);
        Assert.assertEquals(cfg.getUseMessagingSsl(), true);
        Assert.assertEquals(cfg.getUseApiSsl(), true);
    }
}
