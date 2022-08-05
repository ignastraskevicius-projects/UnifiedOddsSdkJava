package com.sportradar.unifiedodds.sdk.di;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.sportradar.uf.sportsapi.datamodel.*;
import com.sportradar.unifiedodds.sdk.SDKInternalConfiguration;
import com.sportradar.unifiedodds.sdk.impl.*;

/**
 * Created on 2019-03-29
 *
 * @author e.roznik
 */
public class DataProvidersModule extends AbstractModule {

    @Override
    protected void configure() {
        // empty
    }

    @Provides
    @Named("SummaryEndpointDataProvider")
    private DataProvider<Object> provideSummaryEndpointDataProvider(SDKInternalConfiguration cfg,
                                                                    LogFastHttpDataFetcher httpDataFetcher,
                                                                    @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        String nodeIdStr = cfg.getSdkNodeId() != null && cfg.getSdkNodeId() != 0
                ? "?node_id=" + cfg.getSdkNodeId()
                : "";

        String replaySummary = baseUrl(cfg) + "/replay/sports/%s/sport_events/%s/summary.xml" + nodeIdStr;

        return new DataProvider<>(
                cfg.isReplaySession()
                        ? replaySummary
                        : "/sports/%s/sport_events/%s/summary.xml",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    @Provides @Named("FixtureEndpointDataProvider")
    private DataProvider<SAPIFixturesEndpoint> provideFixtureEndpointDataProvider(SDKInternalConfiguration cfg,
                                                                                  LogHttpDataFetcher httpDataFetcher,
                                                                                  @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        return fixturesEndpointProvider(cfg, httpDataFetcher, deserializer, "fixture.xml");
    }

    @Provides @Named("FixtureChangeFixtureEndpointDataProvider")
    private DataProvider<SAPIFixturesEndpoint> provideFixtureChangeFixtureEndpointDataProvider(SDKInternalConfiguration cfg,
                                                                                               LogHttpDataFetcher httpDataFetcher,
                                                                                               @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        return fixturesEndpointProvider(cfg, httpDataFetcher, deserializer, "fixture_change_fixture.xml");
    }

    @Provides
    private DataProvider<SAPIFixtureChangesEndpoint> provideFixtureChangesDataProvider(SDKInternalConfiguration cfg,
                                                                                                     LogHttpDataFetcher httpDataFetcher,
                                                                                                     @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        return new DataProvider<>(
                "/sports/%s/fixtures/changes.xml%s",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    @Provides
    private DataProvider<SAPIResultChangesEndpoint> provideResultChangesDataProvider(SDKInternalConfiguration cfg,
                                                                                                     LogHttpDataFetcher httpDataFetcher,
                                                                                                     @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        return new DataProvider<>(
                "/sports/%s/results/changes.xml%s",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    @Provides
    private DataProvider<SAPITournamentsEndpoint> provideAllTournamentsEndpointDataProvider(SDKInternalConfiguration cfg,
                                                                                            LogHttpDataFetcher httpDataFetcher,
                                                                                            @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        return new DataProvider<>(
                "/sports/%s/tournaments.xml",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    @Provides
    private DataProvider<SAPISportsEndpoint> provideSportsEndpointDataProvider(SDKInternalConfiguration cfg,
                                                                               LogHttpDataFetcher httpDataFetcher,
                                                                               @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        return new DataProvider<>(
                "/sports/%s/sports.xml",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    @Provides @Named("DateScheduleEndpointDataProvider")
    private DataProvider<SAPIScheduleEndpoint> provideDateScheduleEndpointDataProvider(SDKInternalConfiguration cfg,
                                                                                       LogHttpDataFetcher httpDataFetcher,
                                                                                       @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        return new DataProvider<>(
                "/sports/%s/schedules/%s/schedule.xml",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    @Provides @Named("TournamentScheduleProvider")
    private DataProvider<Object> provideTournamentScheduleEndpointDataProvider(SDKInternalConfiguration cfg,
                                                                               LogHttpDataFetcher httpDataFetcher,
                                                                               @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        return new DataProvider<>(
                "/sports/%s/tournaments/%s/schedule.xml",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    @Provides
    private DataProvider<SAPIPlayerProfileEndpoint> providePlayerProfileEndpointDataProvider(SDKInternalConfiguration cfg,
                                                                                             LogFastHttpDataFetcher httpDataFetcher,
                                                                                             @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        return new DataProvider<>(
                "/sports/%s/players/%s/profile.xml",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    @Provides
    private DataProvider<SAPICompetitorProfileEndpoint> provideCompetitorProfileEndpointDataProvider(SDKInternalConfiguration cfg,
                                                                                                     LogFastHttpDataFetcher httpDataFetcher,
                                                                                                     @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        return new DataProvider<>(
                "/sports/%s/competitors/%s/profile.xml",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    @Provides
    private DataProvider<SAPISimpleTeamProfileEndpoint> provideSimpleTeamProfileEndpointDataProvider(SDKInternalConfiguration cfg,
                                                                                                     LogFastHttpDataFetcher httpDataFetcher,
                                                                                                     @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        return new DataProvider<>(
                "/sports/%s/competitors/%s/profile.xml",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    @Provides
    private DataProvider<SAPITournamentSeasons> provideTournamentSeasonsEndpointDataProvider(SDKInternalConfiguration cfg,
                                                                                             LogHttpDataFetcher httpDataFetcher,
                                                                                             @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        return new DataProvider<>(
                "/sports/%s/tournaments/%s/seasons.xml",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    @Provides
    private DataProvider<SAPIMatchTimelineEndpoint> provideMatchTimelineEndpointDataProvider(SDKInternalConfiguration cfg,
                                                                                             LogHttpDataFetcher httpDataFetcher,
                                                                                             @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        String nodeIdStr = cfg.getSdkNodeId() != null && cfg.getSdkNodeId() != 0
                ? "?node_id=" + cfg.getSdkNodeId()
                : "";

        String replayTimeline = baseUrl(cfg) + "/replay/sports/%s/sport_events/%s/timeline.xml" + nodeIdStr;

        return new DataProvider<>(
                cfg.isReplaySession()
                        ? replayTimeline
                        : "/sports/%s/sport_events/%s/timeline.xml",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    @Provides
    private DataProvider<SAPISportCategoriesEndpoint> provideSportCategoriesEndpointProvider(SDKInternalConfiguration cfg,
                                                                                             LogHttpDataFetcher httpDataFetcher,
                                                                                             @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        return new DataProvider<>(
                "/sports/%s/sports/%s/categories.xml",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    @Provides
    private DataProvider<SAPILotteries> provideLotteriesDataProvider(SDKInternalConfiguration cfg,
                                                                     LogHttpDataFetcher httpDataFetcher,
                                                                     @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        return new DataProvider<>(
                "/wns/sports/%s/lotteries.xml",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    @Provides
    private DataProvider<SAPIDrawSummary> provideDrawSummaryProvider(SDKInternalConfiguration cfg,
                                                                     LogHttpDataFetcher httpDataFetcher,
                                                                     @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        return new DataProvider<>(
                "/wns/sports/%s/sport_events/%s/summary.xml",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    @Provides
    private DataProvider<SAPIDrawFixtures> provideDrawFixtureProvider(SDKInternalConfiguration cfg,
                                                                      LogHttpDataFetcher httpDataFetcher,
                                                                      @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        return new DataProvider<>(
                "/wns/sports/%s/sport_events/%s/fixture.xml",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    @Provides
    private DataProvider<SAPILotterySchedule> provideLotteryScheduleProvider(SDKInternalConfiguration cfg,
                                                                             LogHttpDataFetcher httpDataFetcher,
                                                                             @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        return new DataProvider<>(
                "/wns/sports/%s/lotteries/%s/schedule.xml",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    @Provides @Named("ListSportEventsDataProvider")
    private DataProvider<SAPIScheduleEndpoint> provideListSportEventsProvider(SDKInternalConfiguration cfg,
                                                                              LogHttpDataFetcher httpDataFetcher,
                                                                              @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        return new DataProvider<>(
                "/sports/%s/schedules/pre/schedule.xml?start=%s&limit=%s",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    @Provides
    private DataProvider<SAPISportTournamentsEndpoint> provideSportTournamentsEndpointDataProvider(SDKInternalConfiguration cfg,
                                                                                            LogHttpDataFetcher httpDataFetcher,
                                                                                            @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        return new DataProvider<>(
                "/sports/%s/sports/%s/tournaments.xml",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    @Provides @Singleton
    private DataProvider<Producers> providesProducersDataProvider(
            SDKInternalConfiguration configuration,
            LogHttpDataFetcher logHttpDataFetcher,
            @Named("SportsApiJaxbDeserializer")  Deserializer deserializer
    ) {
        return new DataProvider<>("/descriptions/producers.xml", configuration, logHttpDataFetcher, deserializer);
    }

    @Provides
    private DataProvider<SAPIStagePeriodEndpoint> periodSummaryDataProvider(SDKInternalConfiguration cfg,
                                                                            LogHttpDataFetcher httpDataFetcher,
                                                                            @Named("SportsApiJaxbDeserializer") Deserializer deserializer) {
        //host/v1/sports/en/sport_events/sr:stage:{id}/period_summary.xml?competitors=sr:competitor:{id}&competitors=sr:competitor:{id}&periods=2&periods=3&periods=4
        return new DataProvider<>(
                "/sports/%s/sport_events/%s/period_summary.xml%s",
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    //Helpers:

    private static DataProvider<SAPIFixturesEndpoint> fixturesEndpointProvider(
            SDKInternalConfiguration cfg, LogHttpDataFetcher httpDataFetcher, Deserializer deserializer, String filename) {
        String nodeIdStr = cfg.getSdkNodeId() != null && cfg.getSdkNodeId() != 0
                ? "?node_id=" + cfg.getSdkNodeId()
                : "";

        String replayFixture = baseUrl(cfg) + "/replay/sports/%s/sport_events/%s/fixture.xml" + nodeIdStr;

        return new DataProvider<>(
                cfg.isReplaySession() ? replayFixture : "/sports/%s/sport_events/%s/" + filename,
                cfg,
                httpDataFetcher,
                deserializer
        );
    }

    private static String baseUrl(SDKInternalConfiguration cfg) {
        String httpHttps = cfg.getUseApiSsl() ? "https" : "http";
        return httpHttps + "://" + cfg.getApiHostAndPort() + "/v1";
    }
}
