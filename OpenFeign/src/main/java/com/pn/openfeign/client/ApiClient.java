package com.pn.openfeign.client;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("apiService")
public interface ApiClient {
}
