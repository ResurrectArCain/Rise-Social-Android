package es.esy.resurrect.rise.risesocial;

/*
COPYRIGHT
RESURRECT (C) 2012 - 2016
RISE SOCIAL IS A TRADEMARK OF RESURRECT
*/

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Splash extends Activity {
    //WEB ACTIVITY

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rise);
        WebView myWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView = (WebView) findViewById(R.id.webView);
        myWebView.loadUrl("http://www.risesocial.esy.es/");
        WebView webView = (WebView) findViewById(R.id.webView);
        myWebView.setWebViewClient(new WebViewClient());

        //todo Remove this once we find a better alternative

        webView.setInitialScale(1);
        WebSettings settings = webView.getSettings();
        settings.setMinimumFontSize(19);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);

        webView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
    }

    public class WebAppInterface {
        Context mContext;

        /**
         * Instantiate the interface and set the context
         */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /**
         * Show a toast from the web page
         */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }

    private class myWebview extends WebViewClient {
        @Override
        //
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //    if (Uri.parse(url).getHost().length() == 0) return false;{
            //        return false;
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        WebView myWebView = (WebView) findViewById(R.id.webView);

        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack(); // Go to previous page
            return true;
        }
        // Use this as else part
        return super.onKeyDown(keyCode, event);
    }

    public static String changedHeaderHtml(String htmlText) {

        String head = "<head><meta name=\"viewport\" content=\"width=device-width, user-scalable=yes\" /></head>";

        String closedTag = "</body></html>";
        String changeFontHtml = head + htmlText + closedTag;
        return changeFontHtml;
    }

    public static void displayHtmlText(String htmlContent, String message,
                                       WebView webView,
                                       RelativeLayout videoLayout, LinearLayout standardLayout, LinearLayout webviewLayout) {

        webView.setWebChromeClient(new WebChromeClient());
        String changeFontHtml = Splash.changedHeaderHtml(htmlContent);
        webView.loadDataWithBaseURL(null, changeFontHtml,
                "text/html", "UTF-8", null);

        webviewLayout.setVisibility(View.VISIBLE);
        standardLayout.setVisibility(View.GONE);
        videoLayout.setVisibility(View.GONE);
    }
}