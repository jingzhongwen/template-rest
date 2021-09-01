package com.jzw.template.DAO.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document("user")
@Data
public class UserMongo {

    @Indexed(unique = true)
    @Field("id")
    private Long id;

    @Field("name")
    private String name;
}
