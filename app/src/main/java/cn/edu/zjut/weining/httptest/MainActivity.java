package cn.edu.zjut.weining.httptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.tapadoo.alerter.Alerter;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView)findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.canGoBack();
        webView.loadUrl("http://www.qq.com");

        Button button_info = (Button) findViewById(R.id.button_info);
        Button button_back = (Button) findViewById(R.id.button_back);
        final Intent intent = new Intent(this,MyService.class);
        button_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currenturl=webView.getUrl();
                Alerter.create(MainActivity.this)
                        .setTitle("Alert Title")
                        .setText("Alert text...")
                        .setBackgroundColor(R.color.colorAccent)
                        .setIcon(R.drawable.ic_face)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                webView.loadUrl("http://www.baidu.com");
                            }
                        })
                        .show();
                webView.goBack();
                Toast.makeText(getApplicationContext(), currenturl, Toast.LENGTH_SHORT).show();
            }
        });
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });
    }

}
