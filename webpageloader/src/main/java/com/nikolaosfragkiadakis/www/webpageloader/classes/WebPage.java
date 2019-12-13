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

import android.webkit.URLUtil;

import java.io.Serializable;

import com.nikolaosfragkiadakis.www.webpageloader.interfaces.WebPageLoaderClosedListener;
import com.nikolaosfragkiadakis.www.webpageloader.interfaces.WebPageLoaderOpenedListener;
import com.nikolaosfragkiadakis.www.webpageloader.utilities.Constants.WebPageConstants;

/**
 * The {@link WebPage} is the custom web page class of the "Web Page Loader" SDK that is used
 * in order to the requested web page's data be stored.
 */
public class WebPage implements Serializable {
    // The web page's url.
    private String mUrl;
    // The web page's description.
    private String mDescription;
    // The calling application's user ID.
    private String mUserId;
    // The calling application's user name.
    private String mUserName;
    // The calling application's user message.
    private String mUserMessage;
    // The "WebPageLoaderOpenedListener" interface instance reference.
    private WebPageLoaderOpenedListener mOpenedListener;
    // The "WebPageLoaderClosedListener" interface instance reference.
    private WebPageLoaderClosedListener mClosedListener;

    /**
     * {@link WebPage} is the default constructor for the custom web page class.
     *
     * @param url represents the new web page's url that should get loaded, it also represents the "Param 1"
     *            that will be shown at the top of the overlay layout.
     * @param description represents the description of the new web page, it also represents the "Param 2"
     *                           that will be shown at the bottom of the overlay layout.
     * @param userId represents the application's user ID, also represents the "Param 3"
     *               which will be passed back to the user after the overlay layout get closed.
     * @param userName represents the application's user name, also represents the "Param 4"
     *                 which will be passed back to the user after the overlay layout get closed.
     * @param userMessage represents the application's user message, also represents the "Param 5"
     *                    which will be passed back to the user after the overlay layout get closed.
     * @param openedListener represents the attached "WebPageLoaderOpenedListener" interface instance reference
     *                       of the new "WebPage" instance.
     * @param closedListener represents the attached "WebPageLoaderClosedListener" interface instance reference
     *                       of the new "WebPage" instance.
     */
    private WebPage(String url, String description, String userId, String userName, String userMessage,
                    WebPageLoaderOpenedListener openedListener, WebPageLoaderClosedListener closedListener){
        mUrl = url;
        mDescription = (description != null) ? description : WebPageConstants.DEFAULT_DESCRIPTION_TEXT;
        mUserId = (userId != null) ? userId : WebPageConstants.DEFAULT_USER_ID_TEXT;
        mUserName = (userName != null) ? userName : WebPageConstants.DEFAULT_USER_NAME_TEXT;
        mUserMessage = (userMessage != null) ? userMessage : WebPageConstants.DEFAULT_USER_MESSAGE_TEXT;
        mOpenedListener = openedListener;
        mClosedListener = closedListener;
    }

    // Get the web page's url.
    public String getUrl() { return mUrl; }

    // Get the web page's description.
    public String getDescription() { return mDescription; }

    // Get the calling application's user ID.
    public String getUserId() { return mUserId; }

    // Get the calling application's user name.
    public String getUserName() { return mUserName; }

    // Get the calling application's user message.
    public String getUserMessage() { return mUserMessage; }

    // Get the "WebPageLoaderOpenedListener" interface instance reference.
    public WebPageLoaderOpenedListener getOpenedListener() { return mOpenedListener; }

    // Get the "WebPageLoaderClosedListener" interface instance reference.
    public WebPageLoaderClosedListener getClosedListener() { return mClosedListener; }

    /**
     * The {@link Builder} method implements the builder constructor pattern for the WebPage class.
     */
    public static class Builder {
        // The web page's url.
        private String url;
        // The web page's description.
        private String description;
        // The calling application's user ID.
        private String userId;
        // The calling application's user name.
        private String userName;
        // The calling application's user message.
        private String userMessage;
        // The "WebPageLoaderOpenedListener" interface instance reference.
        private WebPageLoaderOpenedListener openedListener;
        // The "WebPageLoaderClosedListener" interface instance reference.
        private WebPageLoaderClosedListener closedListener;

        // Set the web page's url.
        public Builder setUrl(final String url) {
            this.url = url;
            return this;
        }

        // Set the web page's description.
        public Builder setDescription(final String description) {
            this.description = description;
            return this;
        }

        // Set the calling application's user ID.
        public Builder setUserId(final String userId) {
            this.userId = userId;
            return this;
        }

        // Set the calling application's user name.
        public Builder setUserName(final String userName) {
            this.userName = userName;
            return this;
        }

        // Set the calling application's user message.
        public Builder setUserMessage(final String userMessage) {
            this.userMessage = userMessage;
            return this;
        }

        // Set the "WebPageLoaderOpenedListener" interface instance reference.
        public Builder setOpenedListener (final WebPageLoaderOpenedListener openedListener) {
            this.openedListener = openedListener;
            return this;
        }

        // Set the "WebPageLoaderClosedListener" interface instance reference.
        public Builder setClosedListener (final WebPageLoaderClosedListener closedListener) {
            this.closedListener = closedListener;
            return this;
        }

        // Build the web page's instance.
        public WebPage build() throws IllegalStateException {
            // Figure out if the url value is a valid url address or not.
            if(this.url == null || !URLUtil.isValidUrl(this.url)) {
                // In case of an invalid url value throw an "IllegalStateException" exception.
                throw new IllegalStateException(WebPageConstants.INVALID_URL_VALUE_EXCEPTION_MESSAGE);
            }

            // Build and return a new "WebPage" instance.
            return new WebPage(this.url, this.description, this.userId, this.userName,
                                    this.userMessage, this.openedListener, this.closedListener);
        }
    }
}
