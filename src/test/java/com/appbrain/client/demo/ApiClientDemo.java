package com.appbrain.client.demo;

import com.appbrain.client.ApiClient;
import com.appbrain.client.ApiException;
import com.appbrain.client.Configuration;
import com.appbrain.client.api.InformationApi;
import com.appbrain.client.model.AppInfoList;

public class ApiClientDemo {

    public static void main(String[] args) {
        ApiClient apiClient = Configuration.getDefaultApiClient();

        // Get the API key from https://developers.appbrain.com/dev_dashboard#DevPagePlace:page=api
        // for more information see: https://developers.appbrain.com/info/help/api/appbrain-api.html
        apiClient.setApiKey("<APIKEY>");

        InformationApi api = new InformationApi(apiClient);
        String sort = null;
        String filter = null;
        String category = null;

        try {
            AppInfoList result = api.searchApps("youtube", sort, filter, category, 0, null);
            System.out.println(result.getApps().get(0));
        } catch (ApiException e) {
            System.err.println(" " + e);
            System.err.println(" " + e.getResponseBody());
        }
    }
}
