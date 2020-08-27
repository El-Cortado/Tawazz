package com.example.tawazz.download;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;

// unused!
public class UriFilesDownloader {
    private Context mContext;

    public UriFilesDownloader(Context context) {
        this.mContext = context;
    }

    public void downloadUriFiles(String filename, String fileExtension, String destinationDir, Uri url) {
        DownloadManager downloadManager = (DownloadManager)mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url.toString());
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setDestinationInExternalFilesDir(mContext, destinationDir, filename + fileExtension);

        downloadManager.enqueue(request);
    }
}
