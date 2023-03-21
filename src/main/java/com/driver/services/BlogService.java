package com.driver.services;
import com.driver.models.Blog;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content) throws Exception {

        //create a blog at the current time
        User user;
        try {
            user = userRepository1.findById(userId).get();
        }
        catch (Exception e){
            throw new Exception();
        }
        Blog blog=new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setUser(user);

        user.getBlogList().add(blog);

        userRepository1.save(user);

        return blog;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        blogRepository.deleteById(blogId);
    }
}


