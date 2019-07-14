package com.neo.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Description: HTTP通讯帮助类
 * Created on 2018/8/13 下午3:05
 *
 * @author 王建华(jianhua.wang @ ucarinc.com)
 */
public class DefaultHttpClient {
    public static final Logger LOG = LoggerFactory.getLogger(DefaultHttpClient.class);
    public static final String DEFAULT_CHARSET = "UTF-8";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_GET = "GET";
    private static final int MAX_SIZE=1024;
    private DefaultHttpClient() {}

    /**
     *  执行HTTP POST请求。<br/> Created on 2013-9-9 上午10:37:13
     * @author  litao(litao02@zhuche.com)
     * @since 3.2
     * @param url 请求地址
     * @param params 请求参数
     * @param connectTimeout 客户端连接时间
     * @param readTimeout  服务端响应时间
     * @return 响应字符串
     * @throws RpcRuntimeException
     */
    public static String doPost(String url, Map<String, String> params,int connectTimeout,int readTimeout) throws RpcRuntimeException {
        return doPost(url, params, DEFAULT_CHARSET,connectTimeout,readTimeout);
    }

    /**
     * 执行HTTP POST请求 <br/> Created on 2013-9-9 上午10:38:34
     * @author  litao(litao02@zhuche.com)
     * @since 3.2
     * @param url 请求地址
     * @param params 请求参数
     * @param charset 字符集，如UTF-8, GBK, GB2312
     * @param connectTimeout 客户端连接时间
     * @param readTimeout 服务端响应时间
     * @return 响应字符串
     * @throws RpcRuntimeException
     */
    public static String doPost(String url, Map<String, String> params, String charset,int connectTimeout,int readTimeout)
            throws RpcRuntimeException {
        String ctype = "application/x-www-form-urlencoded;charset=" + charset;
        String query = buildQuery(params, charset);
        byte[] content={};
        if(query!=null){
            try {
                content=query.getBytes(charset);
            } catch (IOException e) {
                throw new RpcRuntimeException(e);
            }
        }
        return doPost(url, ctype, content, connectTimeout, readTimeout);
    }
    /**
     * 执行HTTP POST请求<br/> Created on 2013-9-9 上午10:39:31
     * @author  litao(litao02@zhuche.com)
     * @since 3.2
     * @param url 请求地址
     * @param ctype 请求类型
     * @param content 请求字节数组
     * @param connectTimeout 客户端连接时间
     * @param readTimeout 服务端响应时间
     * @return 响应字符串
     * @throws RpcRuntimeException
     */
    public static String doPost(String url, String ctype, byte[] content,int connectTimeout,int readTimeout) throws RpcRuntimeException {
        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        try {
            conn = getConnection(new URL(url), METHOD_POST, ctype);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            out = conn.getOutputStream();
            out.write(content);
            rsp = getResponseAsString(conn);
        }catch(IOException e){
            Map<String, String> map=getParamsFromUrl(url);
            LOG.error(map.get("serviceId")+"访问出现错误.");
            throw new RpcRuntimeException("访问远程服务失败",e);
        }
        finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RpcRuntimeException(e);
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rsp;
    }
    /**
     * 执行HTTP GET请求<br/> Created on 2013-9-9 上午10:41:07
     * @author  litao(litao02@zhuche.com)
     * @since 3.2
     * @param url 请求地址
     * @param params 请求参数
     * @return  响应字符串
     * @throws RpcRuntimeException
     */
    public static String doGet(String url, Map<String, String> params) throws RpcRuntimeException{
        return doGet(url, params, DEFAULT_CHARSET);
    }
    /**
     * 执行HTTP GET请求<br/> Created on 2013-9-9 上午10:41:44
     * @author  litao(litao02@zhuche.com)
     * @since 3.2
     * @param url 请求地址
     * @param params 请求参数
     * @param charset 字符集，如UTF-8, GBK, GB2312
     * @return 响应字符串
     * @throws RpcRuntimeException
     */
    public static String doGet(String url, Map<String, String> params, String charset)
            throws RpcRuntimeException {
        HttpURLConnection conn = null;
        String rsp = null;
        try {
            String ctype = "application/x-www-form-urlencoded;charset=" + charset;
            String query = buildQuery(params, charset);
            try{
                conn = getConnection(buildGetUrl(url, query), METHOD_GET, ctype);
            }catch(IOException e){
                Map<String, String> map=getParamsFromUrl(url);
                LOG.error(map.get("serviceId")+"访问出现错误.");
                throw new RpcRuntimeException(e);
            }
            rsp = getResponseAsString(conn);

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rsp;
    }
    /**
     * 获取http连接<br/> Created on 2013-9-9 上午10:44:13
     * @author  litao(litao02@zhuche.com)
     * @since 3.2
     * @param url 请求地址
     * @param method 请求方法
     * @param ctype 请求类型
     * @return
     */
    public static HttpURLConnection getConnection(URL url, String method, String ctype)
    {
        LOG.info("url is "+String.valueOf(url));
        LOG.info("method is "+method);
        LOG.info("ctype is"+ctype);
        try{
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html,text/plain,*/*;");
            conn.setRequestProperty("User-Agent", "remote-zuche-java");
            conn.setRequestProperty("Content-Type", ctype);
            conn.setRequestProperty("Accept-Language", "zh-CN");
            return conn;
        }
        catch(IOException e){
            LOG.error("execute open connection error", e);
        }
        return null;

    }


    private static URL buildGetUrl(String strUrl, String query) throws IOException {
        StringBuffer buffer = new StringBuffer(strUrl);
        URL url = new URL(strUrl);
        if (StringUtils.isEmpty(query)) {
            return url;
        }

        if (StringUtils.isEmpty(url.getQuery())) {
            if (strUrl.endsWith("?")) {
                buffer.append(query);
            } else {
                buffer.append("?").append(query);
            }
        } else {
            if (strUrl.endsWith("&")) {
                buffer.append(query);
            } else {
                buffer.append("&").append(query);
            }
        }

        return new URL(buffer.toString());
    }
    /**
     * 构建http查询<br/> Created on 2013-9-9 上午10:44:56
     * @author  litao(litao02@zhuche.com)
     * @since 3.2
     * @param params 请求参数
     * @param charset 字符集，如UTF-8, GBK, GB2312
     * @return 查询字符串
     * @throws RpcRuntimeException
     */
    public static String buildQuery(Map<String, String> params, String charset) throws RpcRuntimeException {
        if (params == null || params.isEmpty()) {
            return null;
        }

        StringBuilder query = new StringBuilder();
        Set<Map.Entry<String, String>> entries = params.entrySet();
        boolean hasParam = false;

        for (Map.Entry<String, String> entry : entries) {
            String name = entry.getKey();
            String value = entry.getValue();
            // 忽略参数名或参数值为空的参数
            if (StringUtils.isEmpty(name) || StringUtils.isEmpty(value)) {
                if (hasParam) {
                    query.append("&");
                } else {
                    hasParam = true;
                }

                try {
                    query.append(name).append("=").append(URLEncoder.encode(value, charset));
                } catch (IOException e) {
                    throw new RpcRuntimeException(e);
                }
            }
        }

        return query.toString();
    }

    protected static String getResponseAsString(HttpURLConnection conn) throws RpcRuntimeException {
        String charset = getResponseCharset(conn.getContentType());
        InputStream es = conn.getErrorStream();
        String msg;
        if (es == null) {
            try {
                return getStreamAsString(conn.getInputStream(), charset);
            } catch (IOException e) {
                throw new RpcRuntimeException(e);
            }
        } else {
            msg = getStreamAsString(es, charset);
            if (StringUtils.isEmpty(msg)) {
                throw new RpcRuntimeException("返回的内容为空"+msg);
            }
        }
        return msg;
    }

    private static String getStreamAsString(InputStream stream, String charset) throws RpcRuntimeException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter writer = new StringWriter();

            char[] chars = new char[MAX_SIZE];
            int count = 0;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }
            return writer.toString();
        } catch (IOException e) {
            throw new RpcRuntimeException(e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    throw new RpcRuntimeException(e);
                }
            }
        }
    }

    private static String getResponseCharset(String ctype) {
        String charset = DEFAULT_CHARSET;

        if (!StringUtils.isEmpty(ctype)) {
            String[] params = ctype.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2 && !StringUtils.isEmpty(pair[1])) {
                        charset = pair[1].trim();
                    }
                    break;
                }
            }
        }

        return charset;
    }
    /**
     * 使用默认的UTF-8字符集反编码请求参数值<br/> Created on 2013-9-9 上午10:52:28
     * @author  litao(litao02@zhuche.com)
     * @since 3.2
     * @param value 参数值
     * @return 反编码后的参数值
     */
    public static String decode(String value) {
        return decode(value, DEFAULT_CHARSET);
    }
    /**
     * 使用默认的UTF-8字符集编码请求参数值<br/> Created on 2013-9-9 上午10:52:06
     * @author  litao(litao02@zhuche.com)
     * @since 3.2
     * @param value 参数值
     * @return 编码后的参数值
     */
    public static String encode(String value) {
        return encode(value, DEFAULT_CHARSET);
    }
    /**
     * 使用指定的字符集反编码请求参数值<br/> Created on 2013-9-9 上午10:50:50
     * @author  litao(litao02@zhuche.com)
     * @since 3.2
     * @param value 参数值
     * @param charset 字符集
     * @return 反编码后的参数值
     */
    public static String decode(String value, String charset) {
        String result = null;
        if (!StringUtils.isEmpty(value)) {
            try {
                result = URLDecoder.decode(value, charset);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    /**
     * 使用指定的字符集编码请求参数值<br/> Created on 2013-9-9 上午10:50:25
     * @author  litao(litao02@zhuche.com)
     * @since 3.2
     * @param value 参数值
     * @param charset 字符集
     * @return  编码后的参数值
     */
    public static String encode(String value, String charset) {
        String result = null;
        if (!StringUtils.isEmpty(value)) {
            try {
                result = URLEncoder.encode(value, charset);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }


    private static Map<String, String> getParamsFromUrl(String url) {
        Map<String,String> map=null;
        if(url!=null&&url.indexOf('?')!=-1){
            map=splitUrlQuery(url.substring(url.indexOf('?')+1));
        }
        if(map==null){
            map=new HashMap<String,String>();
        }
        return map;
    }
    /**
     * 从URL中提取所有的参数。<br/> Created on 2013-9-9 上午10:49:40
     * @author  litao(litao02@zhuche.com)
     * @since 3.2
     * @param query query URL地址
     * @return 参数映射
     */
    public static Map<String, String> splitUrlQuery(String query) {
        Map<String, String> result = new HashMap<String, String>();

        String[] pairs = query.split("&");
        if (pairs != null && pairs.length > 0) {
            for (String pair : pairs) {
                String[] param = pair.split("=", 2);
                if (param != null && param.length == 2) {
                    result.put(param[0], param[1]);
                }
            }
        }

        return result;
    }

}
