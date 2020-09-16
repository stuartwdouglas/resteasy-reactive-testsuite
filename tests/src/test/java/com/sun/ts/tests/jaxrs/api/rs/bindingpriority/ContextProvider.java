/*
 * Copyright (c) 2012, 2020 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.ts.tests.jaxrs.api.rs.bindingpriority;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.Provider;

import com.sun.ts.tests.jaxrs.common.JAXRSCommonClient.Fault;

@Provider
@org.junit.jupiter.api.extension.ExtendWith(com.sun.ts.tests.TckExtention.class)
public class ContextProvider implements ClientRequestFilter {

  protected void checkFilterContext(ClientRequestContext context) throws Fault {
    throw new Fault("this TCK method is not implemented yet");
  }

  @Override
  public void filter(ClientRequestContext context) throws IOException {
    try {
      checkFilterContext(context);
    } catch (Fault e) {
      throw new IOException(e);
    }
  }

  /**
   * @param conditionTrue
   * @param message
   * @throws Fault
   *           when conditionTrue is not met with message provided
   */
  protected static void //
      assertFault(boolean conditionTrue, Object... message) throws Fault {
    if (!conditionTrue) {
      StringBuilder sb = new StringBuilder();
      for (Object msg : message)
        sb.append(msg).append(" ");
      throw new Fault(sb.toString());
    }
  }

}