package com.example.fc.chat.chatController;

import com.example.fc.chat.chatStorage.UserStorage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@Controller
public class UserController {

    @GetMapping("/registration/${userName}")
    public ResponseEntity<Void> register(@PathVariable String userName){
        System.out.println("usercontroller resigster user" + userName);
        try {
            UserStorage.getInstance().setUsers(userName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); //뭐진 몰라도 실패 했을 때.
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fetchAllUsers")
    public Set<String> fetchAll(){
        return UserStorage.getInstance().getUsers();


    }


    @GetMapping("/aa")
    public String aa(){
        return "chat/view/userChat";
    }

}
