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

package org.apache.cxf.ws.rm;

import java.io.IOException;

import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.io.CachedOutputStreamCallback;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageUtils;
import org.apache.cxf.ws.addressing.AddressingProperties;
import org.apache.cxf.ws.rm.persistence.RMMessage;
import org.apache.cxf.ws.rm.persistence.RMStore;
import org.apache.cxf.ws.rm.v200702.Identifier;

/**
 * 
 */
public class RetransmissionCallback implements CachedOutputStreamCallback {
    
    Message message;
    RMManager manager;
    
    RetransmissionCallback(Message m, RMManager mgr) {
        message = m;
        manager = mgr;
    }
    public void onClose(CachedOutputStream cos) {
        // make a copy as the original gets affected when its resetOut is called
        CachedOutputStream saved = new CachedOutputStream();
        saved.holdTempFile();
        try {
            cos.writeCacheTo(saved);            
            saved.lockOutputStream();
        } catch (IOException e) {
            // ignore
        }

        message.put(RMMessageConstants.SAVED_CONTENT, saved);
        manager.getRetransmissionQueue().addUnacknowledged(message);
        
        RMStore store = manager.getStore();
        if (null != store) {
            try {
                Source s = manager.getSource(message);
                RMProperties rmps = RMContextUtils.retrieveRMProperties(message, true);
                Identifier sid = rmps.getSequence().getIdentifier();
                SourceSequence ss = s.getSequence(sid);
                RMMessage msg = new RMMessage();
                msg.setMessageNumber(rmps.getSequence().getMessageNumber());
                if (!MessageUtils.isRequestor(message)) {
                    AddressingProperties maps = RMContextUtils.retrieveMAPs(message, false, true);
                    if (null != maps && null != maps.getTo()) {
                        msg.setTo(maps.getTo().getValue());
                    }
                }
                msg.setContent(saved);
                store.persistOutgoing(ss, msg);
            } catch (RMException e) {
                // ignore
            } 
        }
    }

    public void onFlush(CachedOutputStream cos) {
        
    }
}
