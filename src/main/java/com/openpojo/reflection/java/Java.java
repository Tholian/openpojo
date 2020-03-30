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

package com.openpojo.reflection.java;

import java.io.File;

/**
 * @author oshoukry
 */
public final class Java {
  public static final String PACKAGE_DELIMITER = ".";
  public static final String PATH_DELIMITER = "/";
  public static final char JAR_FILE_PATH_SEPARATOR = '!';
  public static final String CLASS_EXTENSION = ".class";
  public static final String PACKAGE_INFO = "package-info";
  public static final String CLASSPATH_DELIMITER = File.pathSeparator;

  private Java() {
    throw new UnsupportedOperationException(Java.class.getName() + " should not be constructed!");
  }
}