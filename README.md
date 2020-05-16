![Maven Central](https://img.shields.io/maven-central/v/com.appbrain/appbrain-api-client)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/appbrain/appbrain-api-client-java)
# AppBrain API Client library for Java / Kotlin

The [AppBrain API](https://www.appbrain.com/info/help/api/appbrain-api.html) allows you to programmatically access data about apps on Google Play. Queries for specific apps, search and browse are supported. AppBrain advertisers can manage their campaign.

## Installation

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>com.appbrain</groupId>
  <artifactId>appbrain-api-client</artifactId>
  <version>1.0.0</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
implementation "com.appbrain:appbrain-api-client:1.0.0"
```

### Others

At first generate the JAR by executing:

```shell
./gradlew build
```

Then manually install the following JARs:

* `build/libs/appbrain-api-client-1.0.0.jar`
* Also the required dependency JAR files 

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

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


```

## Documentation for AppBrain API

Please see here:
https://www.appbrain.com/info/help/api/specification.html

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author
AppBrain API Team - contact@appbrain.com

