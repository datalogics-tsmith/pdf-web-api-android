package com.datalogics.pdf.web;

import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import java.io.File;

/**
 * Created by bhaugen on 7/1/14.
 */
public class DecorateDocumentRequest extends BasicRequest {
    final static String REQUEST_PATH = "decorate/document";

    public String decorationDataFile;

    public DecorateDocumentRequest(String applicationID, String applicationKey, String inputFile, String decorationDataFile) {
        super(applicationID, applicationKey, inputFile);

        this.decorationDataFile = decorationDataFile;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        // build the parts of the multipart form that are specific to the decorate document request
        MultipartEntityBuilder entity = MultipartEntityBuilder.create();

        entity.addPart("input", new FileBody(new File(inputFile)));

        entity.addPart("decorationData", new FileBody(new File(decorationDataFile)));

        // create a new objects array to pass the info we need to down to the BasicRequest class
        objects = new Object[] {BasicRequest.WEB_API_URL + REQUEST_PATH, entity};

        // Then call BasicRequest.doInBackground
        return super.doInBackground(objects);
    }
}
