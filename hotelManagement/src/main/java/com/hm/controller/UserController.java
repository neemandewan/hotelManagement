package com.hm.controller;

import com.hm.dto.Response;
import com.hm.model.Role;
import com.hm.model.User;
import com.hm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Neeman on 09/10/2017.
 */

@Controller
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public @ResponseBody
    Response<List<User>> registration(){
        List<User> users = userService.findAllUsers();
        List<User> res = new ArrayList<>();
        for(User u: users) {
            Iterator iter = u.getRoles().iterator();
            if(iter.hasNext()) {
                Role role = (Role) iter.next();
                if(role.getRole().equals("USER")) {
                    res.add(u);
                }
            }
        }
        if (null != res) {
            return Response.ok(res, HttpStatus.OK.value(), HttpStatus.OK.name());
        } else {
            return Response.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
        }
    }
}
