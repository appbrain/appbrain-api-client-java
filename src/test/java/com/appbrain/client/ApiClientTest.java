package com.appbrain.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.appbrain.client.api.InformationApi;
import com.appbrain.client.api.PromotionCampaignsApi;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;

/**
 * Smoke tests for the generated API client. They verify that the code generator
 * and dependency wiring (OkHttp, gson) produce a client that instantiates and
 * configures correctly. These run fully offline and never contact the AppBrain API.
 */
class ApiClientTest {

    @Test
    void defaultClientExposesAnAppBrainBasePath() {
        ApiClient client = new ApiClient();
        assertNotNull(client.getBasePath());
        assertTrue(client.getBasePath().startsWith("https://"), client.getBasePath());
    }

    @Test
    void setBasePathIsFluentAndStored() {
        ApiClient client = new ApiClient();
        ApiClient returned = client.setBasePath("https://example.test/v2");
        assertSame(client, returned, "setBasePath should return the same client for chaining");
        assertEquals("https://example.test/v2", client.getBasePath());
    }

    @Test
    void httpClientIsBackedByOkHttp() {
        OkHttpClient http = new ApiClient().getHttpClient();
        assertNotNull(http);
    }

    @Test
    void apiKeyAuthCanBeConfigured() {
        // Verifies the apiKey security scheme was generated and accepts a value
        // without throwing; the client is never used to make a request here.
        ApiClient client = new ApiClient();
        client.setApiKey("test-api-key");
    }

    @Test
    void apisShareTheProvidedClient() {
        ApiClient client = new ApiClient();
        InformationApi information = new InformationApi(client);
        PromotionCampaignsApi campaigns = new PromotionCampaignsApi(client);
        assertSame(client, information.getApiClient());
        assertSame(client, campaigns.getApiClient());
    }

    @Test
    void configurationProvidesADefaultClient() {
        assertNotNull(Configuration.getDefaultApiClient());
    }
}
