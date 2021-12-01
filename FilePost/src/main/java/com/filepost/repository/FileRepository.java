package com.filepost.repository;

import com.filepost.dao.FileDocument; 
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends MongoRepository<FileDocument,String> {
}
