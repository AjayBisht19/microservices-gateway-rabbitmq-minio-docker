package com.consumer;

//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.InputStream;
//
//public class FileDocument {
//    private String id;
//    private String fileName;
//    private String createdAt;
//    private String type;
//
//    private String user;
//
//    public FileDocument() {
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getFileName() {
//        return fileName;
//    }
//
//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }
//
//    public String getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(String createdAt) {
//        this.createdAt = createdAt;
//    }
//
//
//    public String getUser() {
//        return user;
//    }
//
//    public void setUser(String user) {
//        this.user = user;
//    }
//
//
//	@Override
//	public String toString() {
//		return "FileDocument [id=" + id + ", fileName=" + fileName + ", createdAt=" + createdAt + ", type=" + type
//				+ ", user=" + user + "]";
//	}
//
//}




public class FileDocument {
    private String id;
    private String fileName;
    private String createdAt;
    private String type;
    private byte[] content;
    private String user;


    public FileDocument(String id, String fileName, String createdAt, String type, String user) {
        super();
        this.id = id;
        this.fileName = fileName;
        this.createdAt = createdAt;
        this.type = type;
        this.user = user;
    }


    public FileDocument() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "FileDocument [id=" + id + ", fileName=" + fileName + ", createdAt=" + createdAt + ", type=" + type
                + ", user=" + user + "]";
    }

}

