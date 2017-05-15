package http;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClient {

	public void get() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httpget.
//			HttpGet httpget = new HttpGet("http://www.baidu.com/");
			HttpGet httpget = new HttpGet("https://segmentfault.com/");
			
			
			System.out.println("executing request " + httpget.getURI());
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				System.out.println("--------------------------------------");
				// 打印响应状态
				System.out.println(response.getStatusLine());
				if (entity != null) {
					// 打印响应内容长度
					System.out.println("Response content length: " + entity.getContentLength());
					// 打印响应内容
					System.out.println("Response content: " + EntityUtils.toString(entity));
				}
				System.out.println("------------------------------------");
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void get1() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httpget.
			String url="https://segmentfau|lt.com?aaa=123|Cddd=11|";
			String url_decode =java.net.URLDecoder.decode(url, "GBK");
			
//			Pattern pattern = Pattern.compile("|");
			String newUrl = url_decode.replaceAll("\\|", "%7C");
			
			System.out.println(url_decode);
			System.out.println(newUrl);
			
			URI uri = new URIBuilder().build();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void cookieTest() throws ClientProtocolException, IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();

		HttpGet httpGet = new HttpGet("http://www.jianshu.com/");

		HttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();

		System.out.println("response.getStatusLine()=" + response.getStatusLine());

		if (entity != null) {
			entity.consumeContent();
		}

		System.out.println("Initial set of cookies:");

		List<Cookie> cookies = httpClient.getCookieStore().getCookies();
		if (cookies.isEmpty()) {
			System.out.println("No cookie store");
		} else {
			for (int i = 0; i < cookies.size(); i++) {
				// System.out.println("- "+cookies.get(i).toString());
				System.out.println("- " + cookies.get(i).getName());
				System.out.println("- " + cookies.get(i).getValue());
				System.out.println("getPath- " + cookies.get(i).getPath());
				System.out.println("getDomain- " + cookies.get(i).getDomain());
				System.out.println("getVersion- " + cookies.get(i).getVersion());
				System.out.println("getExpiryDate- " + cookies.get(i).getExpiryDate());
				System.out.println("getPorts- " + cookies.get(i).getPorts());
				System.out.println("================");
			}
		}
	}

	public static void main(String[] args) {

		HttpClient client = new HttpClient();
		client.get1();
	}

}
