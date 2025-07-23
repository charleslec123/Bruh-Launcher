package org.john.download;

public class showDownloadProgress {
    public void update(long done, long total) {
        if (total > 0) {
            int percent = (int) ((done * 100) / total);
            System.out.println("progress: " + percent + "% (" + done + "/" + total + " bytes)");
        } else {
            System.out.println("progress: " + done + " bytes");
        }
    }

}
