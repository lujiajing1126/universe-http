package io.megrez.universe.response;

import io.megrez.universe.request.UniverseRequest;

/**
 * Created by megrez on 15/1/16.
 */
public interface UniverseResponseHandle<T> {
  UniverseRequest<T> getRequest();
}
