/*
 * Copyright (c) 2010-2018 Osman Shoukry
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.openpojo.reflection.java.packageloader.impl;

import com.openpojo.reflection.exception.ReflectionException;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author oshoukry
 */
public class URLToFileSystemAdapter {
  private static final String FILE_PROTOCOL = "file";
  private static final String JAR_PROTOCOL = "jar";
  private static final String PROTOCOL_SEPARATOR = "://";
  private static final String PROTOCOL_SEPARATOR_SHORT = ":";

  private final URL url;

  public URLToFileSystemAdapter(final URL url) {
    if (url == null) {
      throw ReflectionException.getInstance("Null URL not allowed");
    }
    this.url = url;

  }

  /*
   * Nasty hack because URI doesn't understand jar:file:// scheme due to the following:
   * 1. Scheme "jar" is not a protocol
   * 2. Scheme-specific-part starts with file:// and therefore isn't treated as hierarchical
   *
   * The fix here is to switch the protocol to file://.
   */
  //  private void fixProtocolAndPath() {
  //    if (protocol.equals(JAR_PROTOCOL) && path.startsWith(FILE_PROTOCOL)) {
  //      this.protocol = FILE_PROTOCOL;
  //      int length = FILE_PROTOCOL.length() + (path.contains(PROTOCOL_SEPARATOR) ? PROTOCOL_SEPARATOR.length()
  //              : PROTOCOL_SEPARATOR_SHORT.length());
  //      this.path = path.substring(length);
  //    }
  //  }

  private String determineFixedPath() {
    int length = FILE_PROTOCOL.length() + (url.getPath().contains(PROTOCOL_SEPARATOR) ? PROTOCOL_SEPARATOR.length()
            : PROTOCOL_SEPARATOR_SHORT.length());
    return url.getPath().substring(length);
  }

  public URI getAsURI() {
    try {

      if (JAR_PROTOCOL.equalsIgnoreCase(url.getProtocol()) && url.getPath().startsWith(FILE_PROTOCOL)) {
        return new URI(FILE_PROTOCOL, url.getAuthority(), url.getHost(), url.getPort(), determineFixedPath(), url.getQuery(), url.getRef());
      }

      return url.toURI();
    } catch (final URISyntaxException uriSyntaxException) {
      throw ReflectionException.getInstance(uriSyntaxException.getMessage(), uriSyntaxException);
    }
  }

  public File getAsFile() {
    URI uri = getAsURI();

    // to handle windows paths i.e. //host_server/path/class, need a way to put the authority section back in
    // the path

    File directory;
    if (uri.getAuthority() != null)
      directory = new File("//" + uri.getAuthority() + uri.getPath());
    else
      directory = new File(uri.getPath());
    return directory;
  }

  private static String decodeString(String path) {

    int pos = 0;
    StringBuilder decodedString = new StringBuilder(path.length());
    while (pos < path.length()) {
      char ch = path.charAt(pos);
      if (ch == '%' && pos + 2 < path.length()) {
        String hexStr = path.substring(pos + 1, pos + 3);
        pos += 2;
        ch = (char) Integer.parseInt(hexStr, 16);
      }
      decodedString.append(ch);
      pos++;
    }
    return decodedString.toString();
  }
}
