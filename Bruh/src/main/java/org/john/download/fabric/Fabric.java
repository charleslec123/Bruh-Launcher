package org.john.download.fabric;

import org.john.download.CallBack;
import org.john.download.DownloadGame;
import org.john.download.exceptions.Exception;
import org.john.download.exceptions.NotSupportFabricOrForgeException;
import org.to2mbn.jmccc.mcdownloader.MinecraftDownloader;
import org.to2mbn.jmccc.mcdownloader.MinecraftDownloaderBuilder;
import org.to2mbn.jmccc.mcdownloader.provider.DownloadProviderChain;
import org.to2mbn.jmccc.mcdownloader.provider.fabric.FabricDownloadProvider;
import org.to2mbn.jmccc.mcdownloader.provider.fabric.FabricVersionList;
import org.to2mbn.jmccc.option.MinecraftDirectory;
import org.to2mbn.jmccc.version.Version;

import static org.john.global.globalVariables.*;

public abstract class Fabric implements DownloadGame {
    @Override
    public void downloadFabric(FabricDownloadProvider downloadProvider, String version, Boolean downloadIncrementally, Boolean download) throws NotSupportFabricOrForgeException {
        try {
            if (downloadIncrementally) {
                downloader.downloadIncrementally(dir, version, new CallBack<Version>().adapter(downloader));
            } else if (download) {
                downloader.download(providerFabric.fabricVersionList(), new CallBack<FabricVersionList>().adapter(downloader));
            }
        } catch (NotSupportFabricOrForgeException e) {
            throw new Exception(e);
        }
    }
}