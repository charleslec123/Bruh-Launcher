package org.john.download;

import org.to2mbn.jmccc.mcdownloader.MinecraftDownloader;
import org.to2mbn.jmccc.mcdownloader.download.concurrent.CallbackAdapter;
import org.to2mbn.jmccc.mcdownloader.download.concurrent.DownloadCallback;
import org.to2mbn.jmccc.mcdownloader.download.tasks.DownloadTask;
import org.to2mbn.jmccc.version.Version;

public class CallBack<T> {
    public CallbackAdapter<T> adapter(MinecraftDownloader downloader) {
        showDownloadProgress progress = new showDownloadProgress();
        return new CallbackAdapter<T>() {
            @Override
            public void done(T result) {
                System.out.println("DONE" + result);
            }

            @Override
            public void failed(Throwable e) {
                System.out.println("Download Failed " + e);
                e.printStackTrace(); // Print full stack trace for debugging
                downloader.shutdown();
            }

            @Override
            public void cancelled() {
                System.out.println("Download cancelled");
            }

            @Override
            public void updateProgress(long done, long total) {
                showDownloadProgress progress = new showDownloadProgress();
                progress.update(done, total);
            }

            @Override
            public <R> DownloadCallback<R> taskStart(DownloadTask<R> task) {
                return new CallbackAdapter<R>() {
                    @Override
                    public void done(R result) {
                        System.out.println("Download finished");
                    }

                    @Override
                    public void failed(Throwable e) {
                        generateLogs.generateLog("error_log", "error_log.log", e);
                        downloader.shutdown();
                    }

                    @Override
                    public void cancelled() {
                        // when the sub download task cancels
                    }

                    @Override
                    public void updateProgress(long done, long total) {
                        try {
                            progress.update(done, total);
                        } catch (Exception e) {
                            System.out.println("Can't show the progress, game can't download");
                            e.printStackTrace();
                            downloader.shutdown();
                        }
                    }
                };
            }
        };
    }
}
