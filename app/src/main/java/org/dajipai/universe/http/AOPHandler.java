package org.dajipai.universe.http;

import org.dajipai.universe.models.ResultResponse;

import io.megrez.universe.response.IUniverseResponse;
import io.megrez.universe.utils.BaseHTTPCode;

/**
 * Created by megrez on 15/1/16.
 */
public class AOPHandler<T extends ResultResponse> extends DemoResponse<T> {
  private IUniverseResponse<T> universeResponse;
  public AOPHandler(IUniverseResponse<T> universeResponse) {
    this.universeResponse = universeResponse;
  }
  @Override
  public void onSuccess(T response) {
    if(response.getCode().equals(BaseHTTPCode.UNAUTHORIZED)) {
      //TODO
    } else {
      universeResponse.onSuccess(response);
    }
  }
}
