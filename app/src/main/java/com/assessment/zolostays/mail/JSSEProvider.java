package com.assessment.zolostays.mail;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;

/**
 * Created by DELL on 22-07-2017.
 */

public final class JSSEProvider extends Provider {


    public JSSEProvider(){
        super("HarmonyJSSE", 1.0, "Harmony JSSE Provider");

        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            @Override
            public Void run() {
                put("SSLContext.TLS",

                        "org.apache.harmony.xnet.provider.jsse.SSLContextImpl");

                put("Alg.Alias.SSLContext.TLSv1", "TLS");

                put("KeyManagerFactory.X509",

                        "org.apache.harmony.xnet.provider.jsse.KeyManagerFactoryImpl");

                put("TrustManagerFactory.X509",

                        "org.apache.harmony.xnet.provider.jsse.TrustManagerFactoryImpl");

                return null;
            }
        });
    }
}
