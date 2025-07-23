package org.john.download.rawGame;

import org.john.download.generateLogs;
import org.john.download.showDownloadProgress;
import org.john.global.globalVariables;
import org.to2mbn.jmccc.mcdownloader.*;
import org.to2mbn.jmccc.mcdownloader.download.concurrent.CallbackAdapter;
import org.to2mbn.jmccc.mcdownloader.download.concurrent.DownloadCallback;
import org.to2mbn.jmccc.mcdownloader.download.tasks.DownloadTask;
import org.to2mbn.jmccc.option.MinecraftDirectory;
import org.to2mbn.jmccc.version.Version;

public class DownloadRawGame {
    public void downloadRawGame(String version) {
        showDownloadProgress progress = new showDownloadProgress();
        try {
            MinecraftDirectory dir = new MinecraftDirectory(".minecraft");
            globalVariables.downloader.downloadIncrementally(dir, version, new CallbackAdapter<Version>() {

                @Override
                public void failed(Throwable e) {
                    System.out.println("Download Failed " + e);
                    e.printStackTrace(); // Print full stack trace for debugging
                    globalVariables.downloader.shutdown();
                }

                @Override
                public void done(Version result) {
                    System.out.println("The game is downloaded " + result);
                }

                @Override
                public void cancelled() {
                    globalVariables.downloader.shutdown();
                }

                @Override
                public <R> DownloadCallback<R> taskStart(DownloadTask<R> task) {
                    // when a new sub download task starts
                    // return a DownloadCallback to listen the status of the task
                    return new CallbackAdapter<R>() {

                        @Override
                        public void done(R result) {
                            System.out.println("Download finished");
                        }

                        @Override
                        public void failed(Throwable e) {
                            generateLogs.generateLog("error_log", "error_log.log", e);
                            globalVariables.downloader.shutdown();
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
                                globalVariables.downloader.shutdown();
                            }

                        }

                        @Override
                        public void retry(Throwable e, int current, int max) {
                            // when the sub download task fails, and the downloader decides to retry the task
                            // in this case, failed() won't be called
                        }
                    };
                }
            });
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }
}
