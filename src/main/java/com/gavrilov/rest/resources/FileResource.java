package com.gavrilov.rest.resources;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

@Path("file")
public class FileResource {

    private static final String UPLOAD_FILE_SERVER = "E:/uploadTest/";

    @RolesAllowed("ADMIN")
    @GET
    @Path("/download")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public Response downloadFile() {
        File file = new File("E:/TestDocument.docx");
        Response.ResponseBuilder responseBuilder = Response.ok(file);
        responseBuilder.header("Content-Disposition", "attachment; filename=\"TestDocument.docx\"");
        return responseBuilder.build();
    }

    @RolesAllowed("ADMIN")
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@NotNull @FormDataParam("uploadFile") InputStream fileInputStream,
                               @NotNull @FormDataParam("uploadFile") FormDataContentDisposition fileFormDataContentDisposition) {
        String fileName;
        String uploadFilePath = null;

        try {
            fileName = fileFormDataContentDisposition.getFileName();
            uploadFilePath = writeToFileServer(fileInputStream, fileName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return Response.ok("File uploaded successfully at " + uploadFilePath).build();
    }

    private String writeToFileServer(InputStream inputStream, String fileName) throws IOException {

        String qualifiedUploadFilePath = UPLOAD_FILE_SERVER + fileName;

        try (OutputStream outputStream = new FileOutputStream(new File(qualifiedUploadFilePath))) {
            int read;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        //release resource, if any
        return qualifiedUploadFilePath;
    }
}
