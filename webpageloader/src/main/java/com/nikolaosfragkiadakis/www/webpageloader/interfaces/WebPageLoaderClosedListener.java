package com.nikolaosfragkiadakis.www.webpageloader.interfaces;

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

/**
 * {@link WebPageLoaderClosedListener} is the custom closed listener interface of the
 * "Web Page Loader" SDK.
 */
public interface WebPageLoaderClosedListener {
    /**
     * The "onWebPageLoaderClosed" callback method is triggered when the overlay layout of
     * the "Web Page Loader" SDK get closed.
     *
     * @param userId       the client application's current user id, also represents "Param 3".
     * @param userName     the client application's current user name, also represents "Param 4".
     * @param userMessage  the client application's current user message, also represents "Param 5".
     */
    void onWebPageLoaderClosed (String userId, String userName, String userMessage);
}
