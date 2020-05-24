package pers.hanco.mongo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;
import pers.hanco.mongo.SpringMongoApplication;
import pers.hanco.mongo.entity.UserDO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

@SpringBootTest(classes = SpringMongoApplication.class)
@RunWith(SpringRunner.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void insert() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 100; i++) {
            UserDO userDO = new UserDO();
            userDO.setId(i);
            userDO.setUsername("User-" + i);
            int password = ((int) ((Math.random() * 9 + 1) * 100000));
            userDO.setPassword(Integer.toString(password));
            userDO.setCreateTime(new Date());
            userDO.setAge(new Random().nextInt(100));

            UserDO.Profile profile = new UserDO.Profile();
            profile.setNickname("Nickname-" + i);
            profile.setGender(1);
            userDO.setProfile(profile);

            userRepository.insert(userDO);
        }
        stopWatch.stop();
        System.out.println("Time used :" + stopWatch.getTotalTimeMillis());
    }

    @Test
    public void update() {
        UserDO updateUser = new UserDO();
        int id = new Random().nextInt(100);
        updateUser.setId(id);
        System.out.println("update user id is :" + id);
        UserDO.Profile updateProfile = new UserDO.Profile();
        updateProfile.setNickname("Nickname-change-" + id);
        updateProfile.setGender(1);
        updateUser.setProfile(updateProfile);
        userRepository.update(updateUser);

        System.out.println(userRepository.findById(id));
    }

    @Test
    public void deleteById() {
        userRepository.deleteById(1);
    }

    @Test
    public void findById() {
        UserDO user = userRepository.findById(new Random().nextInt(100));
        System.out.println(user);
    }

    @Test
    public void findByUsername() {
        String username = "User-" + new Random().nextInt(100);
        UserDO user = userRepository.findByUsername(username);
        System.out.println(user);
    }

    @Test
    public void findAllByIds() {
        List<Integer> idList = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            idList.add(new Random().nextInt(100));
        }
        List<UserDO> userList = userRepository.findAllByIds(idList);
        userList.forEach(System.out::println);
    }
}