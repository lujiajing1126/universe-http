package io.megrez.universe.http;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import io.megrez.universe.request.UniverseParam;
import io.megrez.universe.request.UniverseParamManager;
import io.megrez.universe.response.IUniverseResponse;
import io.megrez.universe.exceptions.UniverseHttpClientNotInitializedException;
import io.megrez.universe.response.JSONResponse;
import io.megrez.universe.response.UniverseJSONArrayResponseHandle;
import io.megrez.universe.response.UniverseJSONResponseHandle;
import io.megrez.universe.response.UniverseStringResponseHandle;

/**
 * Created by megrez on 15/1/16.
 */
public class UniverseHttpClient implements UniverseHttpRequest {
  private static final String TAG = UniverseHttpClient.class.getSimpleName();
  private static UniverseHttpClientConfiguration mHttpClientConfiguration;
  private static UniverseHttpClient httpClient;
  private static RequestQueue requestQueue;
  private static Gson gson;
  private static boolean isInitialized = false;
  private static Context mContext;

  public static synchronized void initialize(UniverseHttpClientConfiguration httpClientConfiguration) {
    if (isInitialized) {
      Log.w(TAG, "Universe has already initialized");
      return;
    }
    mHttpClientConfiguration = httpClientConfiguration;
    mContext = mHttpClientConfiguration.getContext();
    requestQueue = mHttpClientConfiguration.getRequestQueue();
    gson = mHttpClientConfiguration.getGson();
    httpClient = new UniverseHttpClient();
    isInitialized = true;
  }

  public static UniverseHttpClient getInstance() {
    checkIfHasInitialized();
    return httpClient;
  }

  public Gson getGson() {
    return gson;
  }

  private static synchronized void checkIfHasInitialized() {
    if (!isInitialized) {
      throw new UniverseHttpClientNotInitializedException();
    }
  }

  @Override
  public void post(String url, IUniverseResponse<String> universeResponse, Map<String, String> headers, UniverseParam... universeParams) {
    post(url, headers, UniverseParamManager.buildParams(universeParams), universeResponse);
  }

  @Override
  public void get(String url, IUniverseResponse<String> universeResponse, Map<String, String> headers, UniverseParam... universeParams) {
    get(url, headers, UniverseParamManager.buildParams(universeParams), universeResponse);
  }

  @Override
  public <T extends JSONResponse<T>> void postJSON(String url, IUniverseResponse<T> universeResponse, Map<String, String> headers,Class<?> clazz, UniverseParam... universeParams) {
    Request<T> request = new UniverseJSONResponseHandle<>(Request.Method.POST, url, headers, UniverseParamManager.buildParams(universeParams), universeResponse,clazz).getRequest();
    requestQueue.add(request);
  }

  @Override
  public  <T extends JSONResponse<T>> void getJSON(String url, IUniverseResponse<T> universeResponse, Map<String, String> headers,Class<?> clazz, UniverseParam... universeParams) {
    Request<T> request = new UniverseJSONResponseHandle<>(Request.Method.GET, url, headers, UniverseParamManager.buildParams(universeParams), universeResponse,clazz).getRequest();
    requestQueue.add(request);
  }

  @Override
  public <T extends JSONResponse<T>> void getJSONArray(String url, IUniverseResponse<T> universeResponse, Map<String, String> headers,Class<?> clazz, UniverseParam... universeParams) {
    Request<List<T>> request = new UniverseJSONArrayResponseHandle<>(Request.Method.GET, url, headers, UniverseParamManager.buildParams(universeParams), universeResponse,clazz).getRequest();
    requestQueue.add(request);
  }

  @Override
  public <T extends JSONResponse<T>> void postJSONArray(String url, IUniverseResponse<T> universeResponse, Map<String, String> headers,Class<?> clazz, UniverseParam... universeParams) {
    Request<List<T>> request = new UniverseJSONArrayResponseHandle<>(Request.Method.POST, url, headers, UniverseParamManager.buildParams(universeParams), universeResponse,clazz).getRequest();
    requestQueue.add(request);
  }



  /**
   * Post String Response
   *
   * @param url
   * @param headers
   * @param params
   * @param universeResponse
   */
  private void post(String url, final Map<String, String> headers, final Map<String, String> params, IUniverseResponse<String> universeResponse) {
    Request<String> request = new UniverseStringResponseHandle(Request.Method.POST, url, headers, params, universeResponse).getRequest();
    requestQueue.add(request);
  }

  /**
   * Get String Response
   *
   * @param url
   * @param headers
   * @param params
   * @param universeResponse
   */
  private void get(String url, final Map<String, String> headers, final Map<String, String> params, IUniverseResponse<String> universeResponse) {
    Request<String> request = new UniverseStringResponseHandle(Request.Method.GET, url, headers, params, universeResponse).getRequest();
    requestQueue.add(request);
  }
}
