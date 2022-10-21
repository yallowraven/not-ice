package com.yallowrvn.notice.repository;

import com.yallowrvn.notice.model.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagRepository extends MongoRepository<Tag, String> {
}
