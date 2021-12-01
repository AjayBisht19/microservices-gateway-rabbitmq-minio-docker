package com.filehandler.repository;

import com.filehandler.dao.FileDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends MongoRepository<FileDocument,String> {

    List<FileDocument> findAllByUser(String userId);
}
