package com.df.commoncore.utilities;

import com.sun.jndi.toolkit.url.Uri;

import java.net.MalformedURLException;

public class UriParser {
    public Uri parse(String ip) throws MalformedURLException {
        return new Uri("http://" + ip);
    }
}
