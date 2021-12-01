package com.fileupdate.repository;

import com.fileupdate.dao.FileDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends MongoRepository<FileDocument,String> {
}
