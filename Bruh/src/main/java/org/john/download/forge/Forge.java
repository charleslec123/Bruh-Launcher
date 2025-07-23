package org.john.download.forge;

import org.john.download.CallBack;
import org.john.download.DownloadGame;
import org.john.download.exceptions.Exception;
import org.john.download.exceptions.NotSupportFabricOrForgeException;
import org.john.global.globalVariables;
import org.to2mbn.jmccc.mcdownloader.*;
import org.to2mbn.jmccc.mcdownloader.provider.DownloadProviderChain;

import org.to2mbn.jmccc.mcdownloader.provider.fabric.FabricDownloadProvider;
import org.to2mbn.jmccc.mcdownloader.provider.forge.ForgeDownloadProvider;

import org.to2mbn.jmccc.mcdownloader.provider.forge.ForgeVersionList;
import org.to2mbn.jmccc.option.MinecraftDirectory;
import org.to2mbn.jmccc.version.Version;

import static org.john.global.globalVariables.dir;
import static org.john.global.globalVariables.*;

public abstract class Forge implements DownloadGame {
    @Override
    public void downloadForge(ForgeDownloadProvider downloadProvider, String version, Boolean downloadIncrementally, Boolean download) throws NotSupportFabricOrForgeException {
        try {
            if (downloadIncrementally) {
                downloader.downloadIncrementally(dir, version, new CallBack<Version>().adapter(downloader));
            } else if (download) {
                downloader.download(providerForge.forgeVersionList(), new CallBack<ForgeVersionList>().adapter(downloader));
            }
        } catch (NotSupportFabricOrForgeException e) {
            throw new Exception(e);
        }
    }
}
