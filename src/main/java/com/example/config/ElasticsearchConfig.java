package com.example.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author zh
 * @date 2021/7/7 16:22
 */

@Configuration
public class ElasticsearchConfig {

    @Value("${testelasticsearch.elasticsearch.hostList}")
    private String hostList;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        String[] hostArr = hostList.split(",");

        // 创建HttpHost数组，其中存放es主机和端口的配置信息
        HttpHost[] httpHosts = new HttpHost[hostArr.length];
        for (int i = 0; i < hostArr.length; i++) {
            String hostName = hostArr[i].split(":")[0];
            String port = hostArr[i].split(":")[1];
            httpHosts[i] = new HttpHost(hostName, Integer.parseInt(port), "http");
        }

        return new RestHighLevelClient(RestClient.builder(httpHosts));
    }

}
