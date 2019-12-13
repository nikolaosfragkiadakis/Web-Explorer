package com.nikolaosfragkiadakis.www.webpageloader.classes;

/*
 * MIT License
 *
 * Copyright 2019 © Nikolaos Fragkiadakis / www.nikolaosfragkiadakis.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import android.app.ActivityOptions;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.nikolaosfragkiadakis.www.webpageloader.utilities.Constants.OverlayLayoutConstants;
import com.nikolaosfragkiadakis.www.webpageloader.R;

/**
 * {@link OverlayLayout} is the main activity of the "Web Page Loader" SDK.
 */
public class OverlayLayout extends FragmentActivity {
    // The current "WebPage" instance reference.
    private WebPage currentWebPage;
    // The "ProgressBar" layout element reference.
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Figure out if the activity can be launched with an animation,
        // by checking if the current client app is running on Android 5.0 or higher.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            // The "requestFeature" method should get invoked before adding content
            // in the activity based on the documentation of the Android platform.
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

            // Set the desired animation for "enter" and "exit" transitions.
            ActivityOptions.makeCustomAnimation(this, R.anim.slide_in, R.anim.slide_out);
        }

        // Set the desired layout reference for the activity.
        setContentView(R.layout.overlay_layout);

        // Launch activity in "Full Screen" mode.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Get the reference of the current "WebPage" instance that has been initialized in the
        // client application's calling activity which is stored in the Singleton's "WebPage" instance.
        currentWebPage = WebPageLoaderManager.getInstance().getWebPage();

        // Notify all the receiver's that the "Overlay layout" has been opened.
        if (currentWebPage.getOpenedListener() != null) {
            currentWebPage.getOpenedListener().onWebPageLoaderOpened();
        }

        // Initialize the UI elements of the "Overlay layout".
        initializeOverlayLayout();

        // Set an "OnClickListener" for the custom "close button".
        ImageView closeButton = (ImageView) findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement the behavior of "Sliding back to the right corner and disappear"
                // for the "Overlay layout" by calling the "onBackPressed()" function.
                onBackPressed();
            }
        });
    }

    /**
     * Initialize the {@link OverlayLayout}.
     */
    public void initializeOverlayLayout() {
        // Find a reference to the "web_view" WebView on the UI.
        WebView webView = (WebView) findViewById(R.id.web_view);

        // Find a reference to the "progress_bar" ProgressBar on the UI.
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        // Update the text field of the "web_page_url" TexView with the current web page's url.
        // Web page's url represents the "Param 1".
        TextView webPageUrlTextView = (TextView) findViewById(R.id.web_page_url);
        webPageUrlTextView.setText(currentWebPage.getUrl());

        // Update the text field of the "web_page_description" TexView with the current web page's description.
        // Web page's description represents the "Param 2".
        TextView webPageDescriptionTextView = (TextView) findViewById(R.id.web_page_description);
        webPageDescriptionTextView.setText(currentWebPage.getDescription());

        // Check the current internet connection status of the user's device.
        if (checkInternetConnection(OverlayLayout.this)){
            // There is an established internet connection, thus the
            // requested web page's url can get loaded.

            // Set up the "WebViewClient" of the WebView.
            webView.setWebViewClient(new WebPageLoaderClient());

            // Set the current web page's url that must get loaded in the WebView.
            webView.loadUrl(currentWebPage.getUrl());

        } else {
            // There is no internet connection.
            // Hide the WebView because the requested web page's url cannot get loaded.
            webView.setVisibility(View.INVISIBLE);

            // Hide the "progress_bar".
            progressBar.setVisibility(View.INVISIBLE);

            // Notify the user by showing an appropriate message on the UI.
            Toast.makeText(OverlayLayout.this,
                    OverlayLayoutConstants.NO_INTERNET_CONNECTION_MESSAGE, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * {@link WebPageLoaderClient} is the "WebViewClient" for the WebView which will be used
     * in order to load the current web page's url.
     */
    private class WebPageLoaderClient extends WebViewClient {
        /**
         * Override the behavior of the WebView when the user performs a click on a link in the loaded
         * web page.
         *
         * 1. The first option is the new link to get loaded in a browser app which will be triggered
         * by an intent. In that case the "shouldOverrideUrlLoading" function should return "true".
         *
         * 2. The second option is the new link to get loaded into the WebView. In that case the
         * "shouldOverrideUrlLoading" function should return "false".
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            return false;
        }

        /**
         * Override the behavior of the WebView's "onPageStarted" callback method.
         *
         * @param view     the reference to the calling WebView.
         * @param url      the reference to the current loading url.
         * @param favicon  the reference to the favicon of the current web page.
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            // Hide the "web_view".
            view.setVisibility(View.INVISIBLE);

            // Find a reference to the "progress_bar" ProgressBar on the UI.
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            // Show the "progress_bar" till the web page's url get loaded.
            progressBar.setVisibility(View.VISIBLE);
        }

        /**
         * Override the behavior of the WebView's "onPageFinished" callback method.
         *
         * @param view  the reference to the calling WebView.
         * @param url   the reference to the current loading url.
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            // Show the "web_view".
            view.setVisibility(View.VISIBLE);

            // Find a reference to the "progress_bar" ProgressBar on the UI.
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            // Hide the "progress_bar".
            progressBar.setVisibility(View.INVISIBLE);
        }

        /**
         * Override the behavior of the WebView's "onReceivedError" callback method.
         *
         * @param view      the reference to the calling WebView.
         * @param request   ...
         * @param error     the reference to the error that has been occurred.
         */
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);

            // Hide the "web_view".
            view.setVisibility(View.INVISIBLE);

            // Find a reference to the "progress_bar" ProgressBar on the UI.
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            // Hide the "progress_bar".
            progressBar.setVisibility(View.INVISIBLE);

            // Notify the user by showing an appropriate message on the UI.
            Toast.makeText(OverlayLayout.this,
                    OverlayLayoutConstants.ERROR_MESSAGE, Toast.LENGTH_LONG).show();
        }

        /**
         * Override the behavior of the WebView's "onReceivedHttpError" callback method.
         *
         * @param view           the reference to the calling WebView.
         * @param request        ...
         * @param errorResponse  the reference to the error that has been occurred.
         */
        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            super.onReceivedHttpError(view, request, errorResponse);

            // Hide the "web_view".
            view.setVisibility(View.INVISIBLE);

            // Find a reference to the "progress_bar" ProgressBar on the UI.
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            // Hide the "progress_bar".
            progressBar.setVisibility(View.INVISIBLE);

            // Notify the user by showing an appropriate message on the UI.
            Toast.makeText(OverlayLayout.this,
                    OverlayLayoutConstants.ERROR_MESSAGE, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * The "checkInternetConnection" helper method checks the current internet connection status
     * of the current calling application's device. If there is an established internet connection the function
     * will return "true" else will return "false".
     *
     * @param context is the context of the calling activity.
     */
    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    /**
     * Override the behavior of the "onBackPressed()" function.
     */
    @Override
    public void onBackPressed() {
        // User clicked on the custom "back button".
        super.onBackPressed();

        // Notify all the receiver's that the "Overlay layout" has been closed.
        if (currentWebPage.getClosedListener() != null) {
            currentWebPage.getClosedListener()
                          .onWebPageLoaderClosed(currentWebPage.getUserId(),
                                                 currentWebPage.getUserName(),
                                                 currentWebPage.getUserMessage());
        }
    }
}
