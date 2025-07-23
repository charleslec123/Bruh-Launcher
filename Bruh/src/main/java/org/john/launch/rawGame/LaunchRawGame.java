package org.john.launch.rawGame;

import org.to2mbn.jmccc.auth.OfflineAuthenticator;
import org.to2mbn.jmccc.launch.LaunchException;
import org.to2mbn.jmccc.launch.Launcher;
import org.to2mbn.jmccc.launch.LauncherBuilder;
import org.to2mbn.jmccc.launch.ProcessListener;
import org.to2mbn.jmccc.option.LaunchOption;
import org.to2mbn.jmccc.option.MinecraftDirectory;
import org.to2mbn.jmccc.option.WindowSize;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LaunchRawGame {
    public LaunchRawGame(LaunchOption option) {
        Launcher launcher = LauncherBuilder.create()
                .build();
        try {
            launcher.launch(option, new ProcessListener() {
                @Override
                public void onLog(String s) {
                    System.out.println(s);
                }
                @Override
                public void onErrorLog(String s) {
                    System.err.println(s);
                }

                @Override
                public void onExit(int i) {
                    System.out.println("***EXITED*** " + i);
                }
            });
        } catch (LaunchException e) {
            e.printStackTrace();
        }
    }
}
