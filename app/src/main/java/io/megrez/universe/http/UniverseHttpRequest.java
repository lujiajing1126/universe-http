package io.megrez.universe.http;

import java.util.Map;

import io.megrez.universe.request.UniverseParam;
import io.megrez.universe.response.IUniverseResponse;

/**
 * Created by megrez on 15/1/16.
 */
public interface UniverseHttpRequest {
  public void post(String url,IUniverseResponse<String> universeResponse,Map<String,String> headers,UniverseParam... universeParams);
  public void get(String url,IUniverseResponse<String> universeResponse,Map<String,String> headers,UniverseParam... universeParams);
}
