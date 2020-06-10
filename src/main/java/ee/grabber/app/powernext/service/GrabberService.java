package ee.grabber.app.powernext.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class GrabberService {

  public ArrayList<ArrayList<String>> getData()
      throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
    SSLContextBuilder sslctxb = new SSLContextBuilder();

    sslctxb.loadTrustMaterial(KeyStore.getInstance(KeyStore.getDefaultType()),
        new TrustSelfSignedStrategy() {
          @Override
          public boolean isTrusted(X509Certificate[] chain, String authType) {
              return true;
          }
        });

    SSLContext sslctx = sslctxb.build();
    HttpClientBuilder hcb = HttpClients.custom();
    hcb.setSSLContext(sslctx);
    hcb.setSSLHostnameVerifier(new X509HostnameVerifier() {
      @Override
      public void verify(String host, SSLSocket ssl) {
      }

      @Override
      public void verify(String host, X509Certificate cert) {
      }

      @Override
      public void verify(String host, String[] cns, String[] subjectAlts) {
      }

      @Override
      public boolean verify(String hostname, SSLSession session) {
        return true;
      }
    });
    HttpClient httpClient = hcb.build();
    HttpGet httpGet = new HttpGet("https://www.powernext.com/table-feed/132743/153/17");
    HttpResponse response = httpClient.execute(httpGet);
    String respEntity = EntityUtils.toString(response.getEntity());
    String html = new Gson().fromJson(respEntity, JsonObject.class).get("html").getAsString();
    Document parse = Jsoup.parse(html);
    Elements tbodyRow = parse.getElementsByTag("tbody").select("tr");
    ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
    for (Element element : tbodyRow) {
      ArrayList<String> bodyList = new ArrayList<>();
      for (int j = 0; j < element.select("td").size(); j++) {
        bodyList.add(element.select("td").get(j).text());
      }
      arrayLists.add(new ArrayList<>(bodyList));
      bodyList.clear();
    }
    return arrayLists;
  }

}
