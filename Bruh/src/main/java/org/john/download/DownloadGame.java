package org.john.download;

import org.john.download.exceptions.NotSupportFabricOrForgeException;
import org.to2mbn.jmccc.mcdownloader.provider.fabric.FabricDownloadProvider;
import org.to2mbn.jmccc.mcdownloader.provider.forge.ForgeDownloadProvider;

public interface DownloadGame {
    void downloadForge(ForgeDownloadProvider downloadProvider, String version, Boolean downloadIncrementally, Boolean download) throws NotSupportFabricOrForgeException;

    void downloadFabric(FabricDownloadProvider downloadProvider, String version, Boolean downloadIncrementally, Boolean download) throws NotSupportFabricOrForgeException;
}
