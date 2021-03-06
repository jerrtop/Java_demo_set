/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.cxf.transport.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.common.util.ReflectionUtil;
import org.apache.cxf.helpers.CastUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class CXFServlet extends CXFNonSpringServlet
    implements ApplicationListener<ContextRefreshedEvent> {
    private static final long serialVersionUID = -5922443981969455305L;
    private boolean busCreated;
    private XmlWebApplicationContext createdContext; 
    
    public CXFServlet() {
    }

    @Override
    protected void loadBus(ServletConfig sc) {
        ApplicationContext wac = WebApplicationContextUtils.
            getWebApplicationContext(sc.getServletContext());
        
        if (wac instanceof AbstractApplicationContext) {
            addListener((AbstractApplicationContext)wac);
        }
        
        String configLocation = sc.getInitParameter("config-location");
        if (configLocation == null) {
            try {
                InputStream is = sc.getServletContext().getResourceAsStream("/WEB-INF/cxf-servlet.xml");
                if (is != null && is.available() > 0) {
                    is.close();
                    configLocation = "/WEB-INF/cxf-servlet.xml";
                }
            } catch (Exception ex) {
                //ignore
            }
        }
        if (configLocation != null) {
            wac = createSpringContext(wac, sc.getServletContext(), configLocation);
        }
        if (wac != null) {
            setBus((Bus)wac.getBean("cxf", Bus.class));
        } else {
            busCreated = true;
            setBus(BusFactory.newInstance().createBus());
        }
    }

    protected void addListener(AbstractApplicationContext wac) {
        try {
            //spring 2 vs spring 3 return type is different
            Method m = wac.getClass().getMethod("getApplicationListeners");
            Collection<Object> c = CastUtils.cast((Collection<?>)ReflectionUtil
                                                      .setAccessible(m).invoke(wac));
            c.add(this);
        } catch (Throwable t) {
            //ignore.
        }
    }

    /**
     * Try to create a spring application context from the config location.
     * Will first try to resolve the location using the servlet context.
     * If that does not work then the location is given as is to spring
     * 
     * @param ctx
     * @param sc
     * @param configLocation
     * @return
     */
    private ApplicationContext createSpringContext(ApplicationContext ctx,
                                                   final ServletContext sc,
                                                   String location) {
        XmlWebApplicationContext ctx2 = new XmlWebApplicationContext();
        createdContext = ctx2;
        ctx2.setServletConfig(new ServletConfig() {
            public String getServletName() {
                return "CXF";
            }
            public ServletContext getServletContext() {
                return sc;
            }
            public String getInitParameter(String name) {
                return sc.getInitParameter(name);
            }
            public Enumeration<String> getInitParameterNames() {
                return sc.getInitParameterNames();
            }
        });
        Resource r = ctx2.getResource(location);
        try {
            InputStream in = r.getInputStream();
            in.close();
        } catch (IOException e) {
            //ignore
            r = ctx2.getResource("classpath:" + location);
            try {
                r.getInputStream().close();
            } catch (IOException e2) {
                //ignore
                r = null;
            }
        }
        try {
            if (r != null) {
                location = r.getURL().toExternalForm();
            }
        } catch (IOException e) {
            //ignore
        }        
        if (ctx != null) {
            ctx2.setParent(ctx);
            String names[] = ctx.getBeanNamesForType(Bus.class);
            if (names == null || names.length == 0) {
                ctx2.setConfigLocations(new String[] {"classpath:/META-INF/cxf/cxf.xml",
                                                      location});                
            } else {
                ctx2.setConfigLocations(new String[] {location});                                
            }
        } else {
            ctx2.setConfigLocations(new String[] {"classpath:/META-INF/cxf/cxf.xml",
                                                  location});
            createdContext = ctx2;
        }
        ctx2.refresh();
        return ctx2;
    }
    public void destroyBus() {
        if (busCreated) {
            //if we created the Bus, we need to destroy it.  Otherwise, spring will handleit.
            getBus().shutdown(true);
            setBus(null);
        }
        if (createdContext != null) {
            createdContext.close();
        }
    }

    public void onApplicationEvent(ContextRefreshedEvent event) {
        destroy();
        setBus(null);
        try {
            init(getServletConfig());
        } catch (ServletException e) {
            throw new RuntimeException("Unable to reinitialize the CXFServlet", e);
        }
    }

}
