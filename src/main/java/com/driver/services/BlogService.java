package com.driver.services;
import com.driver.Entity.Blog;
import com.driver.Entity.User;
import com.driver.Repository.BlogRepository;
import com.driver.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        User user=userRepository.findById(userId).get();

        Blog blog=new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setUser(user);

        List<Blog> blogList=user.getBlogList();
        blogList.add(blog);
        user.setBlogList(blogList);

        userRepository.save(user);

        return blog;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        Blog blog=blogRepository.findById(blogId).get();

        User user=blog.getUser();
        user.getBlogList().remove(blog);

        userRepository.save(user);
        blogRepository.deleteById(blogId);
    }
}


