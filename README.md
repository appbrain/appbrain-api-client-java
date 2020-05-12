# appbrain-api-client

A Java client library to interact with the [AppBrain API](https://www.appbrain.com/info/help/api/appbrain-api.html).

## Requirements

Building the API client library requires:
1. Java 1.7+
2. Maven/Gradle

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn clean install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn clean deploy
```

Refer to the [OSSRH Guide](http://central.sonatype.org/pages/ossrh-guide.html) for more information.

### Maven users

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
mvn clean package
```

Then manually install the following JARs:

* `target/appbrain-api-client-1.0.0.jar`
* `target/lib/*.jar`

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

contact@appbrain.com

