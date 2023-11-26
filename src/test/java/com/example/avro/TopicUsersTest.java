package com.example.avro;

import com.example.avro.producer.UserProducer;
import com.example.avro.schema.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TopicUsersTest {

    @Autowired
    UserProducer userProducer;

    @Test
    void testProduceMessageToUsers() {
        User user = new User("Mr. ABC", "Kuala Lumpur", 20);
        userProducer.send(user);
    }

}
