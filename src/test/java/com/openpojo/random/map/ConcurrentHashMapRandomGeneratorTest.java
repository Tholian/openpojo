/*
 * Copyright (c) 2010-2015 Osman Shoukry
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Lesser General Public License as published by
 *    the Free Software Foundation, either version 3 of the License or any
 *    later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.openpojo.random.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.openpojo.random.ParameterizableRandomGenerator;
import com.openpojo.random.map.support.SimpleType1;
import com.openpojo.random.map.support.SimpleType2;

/**
 * @author oshoukry
 */
public class ConcurrentHashMapRandomGeneratorTest extends BaseMapRandomGeneratorTest {

    protected ParameterizableRandomGenerator getInstance() {
        return ConcurrentHashMapRandomGenerator.getInstance();
    }

    protected Class<? extends ParameterizableRandomGenerator> getGeneratorClass() {
        return ConcurrentHashMapRandomGenerator.class;
    }

    protected Class<? extends Map> getExpectedTypeClass() {
        return ConcurrentHashMap.class;
    }

    protected Class<? extends Map> getGeneratedTypeClass() {
        return ConcurrentHashMap.class;
    }

    protected Class<?> getGenericType1() {
        return SimpleType1.class;
    }

    protected Class<?> getGenericType2() {
        return SimpleType2.class;
    }
}
