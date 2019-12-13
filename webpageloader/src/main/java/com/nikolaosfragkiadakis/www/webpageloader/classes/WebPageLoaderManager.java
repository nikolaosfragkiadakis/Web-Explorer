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

import android.content.Context;

/**
 * {@link WebPageLoaderManager} implements the Singleton Pattern of the Web Page Loader SDK using the
 *  "Eager Initialization" technique.
 */
class WebPageLoaderManager {
    // The context reference of the client application's calling activity.
    private static Context mContext;
    // The current "WebPage" instance reference which created in the client application.
    private static WebPage mWebPage;
    // The final "WebPageLoaderManager" instance reference.
    private static final WebPageLoaderManager mInstance = new WebPageLoaderManager();

    // The default constructor of the "WebPageLoaderManager".
    private WebPageLoaderManager() {}

    // Set the context reference of the Singleton.
    public void setContext(Context context) { mContext = context; }

    // Set the "WebPage" instance reference of the Singleton.
    public void setWebPage(WebPage webPage) { mWebPage = webPage; }

    // Get the final instance reference of the Singleton.
    public static WebPageLoaderManager getInstance() {
        return mInstance;
    }

    // Get the context reference of the Singleton.
    public Context getContext() { return  mContext; }

    // Get the "WebPage" instance reference of the Singleton.
    public WebPage getWebPage() { return mWebPage; }
}
