# universe-http
The Framework is still in developing ,it will be the http-framework for Whosv Android 3.0 instead of [Whosv/io-nothing-http](https://github.com/whosv/android-nothing-http), so it well be similar in many ways, especially the API

[![Hex.pm](https://img.shields.io/badge/license-Apache2-blue.svg)]()

## Design

The framwork is based on Google Volley Http Request lib, and we prepare to subsitute [loopj/android-async-http](https://github.com/loopj/android-async-http)

The main purpose is to support protobuf serialize and deserialize and provide a series of easy-use APIs for agile android develop

## Install

```bash
git clone git@github.com:lujiajing1126/universe-http.git
```

## Quick Start

```java
// init the plugin in Your Application
@Override
public void onCreate() {
  super.onCreate();
  universeHttpClientConfiguration = new UniverseHttpClientConfiguration.Builder(this).registerAdapter().build();
  UniverseHttpClient.initialize(universeHttpClientConfiguration);
}
```

And you can get the instance of UniverseHttpClient everywhere in you APP

## LISENCE

APACHE

## RoadMap

- [x] HTTPClient
- [x] Gson(JSON) Support
- [x] String Response Support
- [x] AOP Handler
- [ ] ProtoBuf Serialize and Deserialize
- [ ] Download File
- [ ] Cancel Specified Request
- [ ] Upload File
- [ ] Precise Cache Support
