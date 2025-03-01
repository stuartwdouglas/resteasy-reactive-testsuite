/*
 * Copyright (c) 2013, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.ts.tests.jaxrs.spec.filter.interceptor;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import com.sun.ts.tests.jaxrs.common.impl.ReplacingOutputStream;

@Provider
public class EntityWriterInterceptor implements WriterInterceptor {

    @Override
    public void aroundWriteTo(WriterInterceptorContext context)
            throws IOException, WebApplicationException {
        MultivaluedMap<String, Object> headers = context.getHeaders();
        String header = (String) headers.getFirst(Resource.HEADERNAME);
        if (header != null && header.startsWith(getClass().getName())) {
            StringBuilder sb = new StringBuilder();
            sb.append("<interceptor>").append(getClass().getName());
            if (header.contains(Resource.DIRECTION))
                sb.append(Resource.DIRECTION);
            sb.append("</interceptor>");
            String content = sb.toString();
            ReplacingOutputStream stream = new ReplacingOutputStream(
                    context.getOutputStream(), content);
            context.setOutputStream(stream);
            headers.remove(Resource.HEADERNAME);
        }
        context.proceed();
    }
}
