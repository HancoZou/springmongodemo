package pers.hanco.mongo.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author Hanco on 2020/5/24
 */
@Document(collection = "User")
@Data
public class UserDO {
    private Integer id;
    private String username;
    private String password;
    private Date createTime;
    private Integer age;
    private Profile profile;

    @Data
    public static class Profile {
        /**
         * 昵称
         */
        private String nickname;
        /**
         * 性别
         */
        private Integer gender;
    }
}
