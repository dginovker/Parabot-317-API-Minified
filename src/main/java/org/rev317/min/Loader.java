package org.rev317.min;

import org.objectweb.asm.tree.ClassNode;
import org.parabot.core.Context;
import org.parabot.core.Core;
import org.parabot.core.Directories;
import org.parabot.core.asm.ASMClassLoader;
import org.parabot.core.asm.ASMUtils;
import org.parabot.core.asm.adapters.AddInterfaceAdapter;
import org.parabot.core.asm.hooks.HookFile;
import org.parabot.core.desc.ServerProviderInfo;
import org.parabot.core.ui.components.VerboseLoader;
import org.parabot.environment.api.utils.WebUtil;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.servers.ServerManifest;
import org.parabot.environment.servers.ServerProvider;
import org.parabot.environment.servers.Type;
import org.rev317.min.accessors.Client;
import org.rev317.min.script.ScriptEngine;
import org.rev317.min.ui.BotMenu;

import javax.swing.*;
import java.applet.Applet;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.net.URL;

/**
 * @author Everel, JKetelaar
 */
@ServerManifest(author = "Everel & JKetelaar", name = "Server name here", type = Type.INJECTION, version = 2.1)
public class Loader extends ServerProvider {
    private boolean extended = true;

    public static Client getClient() {
        return (Client) Context.getInstance().getClient();
    }

    @Override
    public Applet fetchApplet() {

        try {
            final Context        context     = Context.getInstance();
            final ASMClassLoader classLoader = context.getASMClassLoader();
            final Class<?>       clientClass = classLoader.loadClass(Context.getInstance().getServerProviderInfo().getClientClass());
            Constructor<?> cons = clientClass.getDeclaredConstructor();
            cons.setAccessible(true);
            Object               instance    = cons.newInstance();

            Class<?> classa = classLoader.loadClass("com.client.b.a");
            Method methoda = classa.getDeclaredMethod("a");
            methoda.setAccessible(true);
            methoda.invoke(classa);
            Method ba = clientClass.getDeclaredMethod("ba");
            ba.setAccessible(true);
            ba.invoke(clientClass);
            Class<?> classu = classLoader.loadClass("com.client.t");
            Field C = classu.getDeclaredField("C");
            C.setAccessible(true);
            C.set(classu, "Xeros");
            Field gw = clientClass.getDeclaredField("gw");
            gw.setAccessible(true);
            gw.setInt(clientClass, 1);
            Field bo = clientClass.getDeclaredField("bo");
            bo.setAccessible(true);
            bo.setInt(clientClass, 0);
            Method aX = clientClass.getDeclaredMethod("aX");
            aX.setAccessible(true);
            aX.invoke(clientClass);
            Field gx = clientClass.getDeclaredField("gx");
            gx.setAccessible(true);
            gx.setBoolean(clientClass, true);
            Class<?> classbs = classLoader.loadClass("com.client.bu");
            Method p = classbs.getDeclaredMethod("p");
            p.setAccessible(true);
            p.invoke(classbs);
            Field c = classa.getDeclaredField("d");
            c.setAccessible(true);
            c.setInt(classa, 32);
            Method methodaa = classa.getDeclaredMethod("a", InetAddress.class);
            methodaa.setAccessible(true);
            methodaa.invoke(classa, InetAddress.getLocalHost());
            Field Q = clientClass.getDeclaredField("Q");
            Q.setAccessible(true);
            Q.set(clientClass, instance);
            Field T = clientClass.getDeclaredField("T");
            T.setAccessible(true);
            T.set(clientClass, instance);




            Class<?> levelClass = classLoader.loadClass("ch.qos.logback.classic.Level");
            Method methodLogLevel = clientClass.getDeclaredMethod("a", levelClass);
            methodLogLevel.setAccessible(true);

            Field levelInfoField = levelClass.getDeclaredField("INFO");
            levelInfoField.setAccessible(true);

            methodLogLevel.invoke(instance, levelInfoField.get(levelInfoField));

            return (Applet) instance;
        } catch (Exception e) {
            e.printStackTrace();
        }



        return null;
    }

    @Override
    public URL getJar() {
        ServerProviderInfo serverProvider = Context.getInstance().getServerProviderInfo();

        File target = new File(Directories.getCachePath(), serverProvider.getClientCRC32() + ".jar");
        if (!target.exists()) {
            WebUtil.downloadFile(serverProvider.getClient(), target, VerboseLoader.get());
        }

        return WebUtil.toURL(target);
    }

    @Override
    public void addMenuItems(JMenuBar bar) {
        new BotMenu(bar);
    }

    @Override
    public void injectHooks() {
        AddInterfaceAdapter.setAccessorPackage("org/rev317/min/accessors/");
        try {
            super.injectHooks();
        } catch (Exception e) {
            if (Core.inVerboseMode()) {
                e.printStackTrace();
            }
            this.extended = false;
            super.injectHooks();
        }
    }

    @Override
    public void initScript(Script script) {
        ScriptEngine.getInstance().setScript(script);
        ScriptEngine.getInstance().init();
    }

    @Override
    public HookFile getHookFile() {
        if (false) {
            return new HookFile(Context.getInstance().getServerProviderInfo().getExtendedHookFile(), HookFile.TYPE_XML);
        } else {
            return new HookFile(Context.getInstance().getServerProviderInfo().getHookFile(), HookFile.TYPE_XML);
        }
    }

    public void unloadScript(Script script) {
        ScriptEngine.getInstance().unload();
    }

    @Override
    public void init() {
    }
}
