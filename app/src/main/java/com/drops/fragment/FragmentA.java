package com.drops.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lovebobo.webview.BuildConfig;
import com.lovebobo.webview.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentA.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentA extends Fragment {

        private View view;
        private WebView webView;
        private WebSettings webSettings;
        //public static final String LOGIN_URL = BuildConfig.BASE_URL + "/api/user/login";
        private String url = "http://192.168.50.66:8082/app/index";
        // 单例(方法二)
        private static FragmentA fa;
        public static FragmentA getFragmentA() {
            if (fa == null) {
                fa = new FragmentA();
            }
            return fa;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_a,container,false);
            webView = (WebView)view.findViewById(R.id.webview);

            webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setLoadWithOverviewMode(true);
            webView.setWebViewClient(new MyWebViewClient());
            webView.loadUrl(url);
            return   view;
        }
        private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //    return super.shouldOverrideUrlLoading(view, url);
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            //     super.onReceivedError(view, errorCode, description, failingUrl);
            //  Toast.makeText(this,"网页加载错误！",0).show();
        }

    }


}
