package io.megrez.universe.http;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

/**
 * Created by megrez on 15/1/16.
 */
public class UniverseHttpClientConfiguration {
  private Context mContext;
  private RequestQueue requestQueue;
  private Gson gson;

  public UniverseHttpClientConfiguration(Context context, RequestQueue requestQueue,Gson gson) {
    this.mContext = context;
    this.requestQueue = requestQueue;
    this.gson = gson;
  }

  public RequestQueue getRequestQueue() {
    return requestQueue;
  }

  public Context getContext() {
    return mContext;
  }

  public Gson getGson() {
    return gson;
  }

  public static class Builder {
    private Context mContext;
    private RequestQueue requestQueue;
    private Gson gson = new Gson();
    public Builder(Context context) {
      this.mContext = context;
    }

    public Builder registerAdapter() {
      this.requestQueue = Volley.newRequestQueue(mContext);
      return this;
    }

    public UniverseHttpClientConfiguration build() {
      return new UniverseHttpClientConfiguration(mContext,requestQueue,gson);
    }

  }
}
