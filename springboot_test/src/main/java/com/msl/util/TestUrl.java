package com.msl.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/5/6 15:48
 */
public class TestUrl implements Runnable{
    private CountDownLatch cdl;
    public TestUrl(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    public TestUrl() {
    }

    @Override
    public void run() {
//        client和response需要关闭资源
        // 创建client,放入try()中自动释放,不需要finally
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

            // 执行得到response
            try (CloseableHttpResponse response = client.execute(new HttpGet("http://vae.anything-soft.com/tools/viewcount_ajax.ashx?tname=dt_channel_article_z_jcjs&aid=23"))) {
                // 获取statusCode
                Integer statusCode = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // body
                    String bodyAsString = EntityUtils.toString(entity);
                    // Media Type
                    String contentMimeType = ContentType.getOrDefault(entity).getMimeType();
                    // head
                    Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);
                    // 输出
//                    System.out.println("statusCode:" + statusCode);
//                    System.out.println("contentMinmeType:" + contentMimeType);
//                    System.out.println("body:" + bodyAsString);
                    System.out.println(bodyAsString);
//                    System.out.println("head" + headers);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            cdl.countDown();
            System.out.println(cdl);
        }
    }

    public void test() {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

            // 执行得到response
            try (CloseableHttpResponse response = client.execute(new HttpGet("http://vae.anything-soft.com/tools/viewcount_ajax.ashx?tname=dt_channel_article_z_jcjs&aid=23"))) {

            }
        } catch (IOException e) {
            e.printStackTrace();
    }}
}
