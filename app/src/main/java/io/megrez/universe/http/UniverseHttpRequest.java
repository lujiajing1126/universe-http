package io.megrez.universe.http;

import java.util.Map;
import io.megrez.universe.request.UniverseParam;
import io.megrez.universe.response.IUniverseResponse;
import io.megrez.universe.response.JSONResponse;

/**
 * Created by megrez on 15/1/16.
 */
public interface UniverseHttpRequest {
  public void post(String url,IUniverseResponse<String> universeResponse,Map<String,String> headers,UniverseParam... universeParams);
  public void get(String url,IUniverseResponse<String> universeResponse,Map<String,String> headers,UniverseParam... universeParams);
  public <T extends JSONResponse<T>> void getJSON(String url, IUniverseResponse<T> universeResponse, Map<String, String> headers,Class<?> clazz, UniverseParam... universeParams);
  public <T extends JSONResponse<T>> void postJSON(String url,IUniverseResponse<T> universeResponse,Map<String,String>headers,Class<?> clazz,UniverseParam... universeParams);
  public <T extends JSONResponse<T>> void getJSONArray(String url, IUniverseResponse<T> universeResponse, Map<String, String> headers,Class<?> clazz, UniverseParam... universeParams);
  public <T extends JSONResponse<T>> void postJSONArray(String url, IUniverseResponse<T> universeResponse, Map<String, String> headers,Class<?> clazz, UniverseParam... universeParams);
}
