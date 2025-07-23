package org.john.global;

import org.to2mbn.jmccc.mcdownloader.MinecraftDownloader;
import org.to2mbn.jmccc.mcdownloader.MinecraftDownloaderBuilder;
import org.to2mbn.jmccc.mcdownloader.provider.DownloadProviderChain;
import org.to2mbn.jmccc.mcdownloader.provider.fabric.FabricDownloadProvider;
import org.to2mbn.jmccc.mcdownloader.provider.forge.ForgeDownloadProvider;
import org.to2mbn.jmccc.option.MinecraftDirectory;

public class globalVariables {
    public  static String launcherVersion = "developing..";
    public static MinecraftDownloader downloader = MinecraftDownloaderBuilder.buildDefault();
    public static FabricDownloadProvider providerFabric = new FabricDownloadProvider();
    public static ForgeDownloadProvider providerForge = new ForgeDownloadProvider();
    public static MinecraftDownloader downloaderFabric = MinecraftDownloaderBuilder.create()
            .providerChain(DownloadProviderChain.create()
                    .addProvider(providerFabric))
            .build();
    public static MinecraftDownloader downloaderForge = MinecraftDownloaderBuilder.create()
            .providerChain(DownloadProviderChain.create()
                    .addProvider(providerForge))
            .build();
    public static MinecraftDirectory dir = new MinecraftDirectory(".minecraft");
}
