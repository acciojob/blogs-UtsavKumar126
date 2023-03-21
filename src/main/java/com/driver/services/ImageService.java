package com.driver.services;
import com.driver.Entity.Blog;
import com.driver.Entity.Image;
import com.driver.Repository.BlogRepository;
import com.driver.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ImageService {
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    ImageRepository imageRepository;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog=blogRepository.findById(blogId).get();

        Image image=new Image();
        image.setDescription(description);
        image.setDimension(dimensions);
        image.setBlog(blog);

        blog.getImageList().add(image);

        blogRepository.save(blog);

        return image;
    }

    public void deleteImage(Integer id){
        Image image=imageRepository.findById(id).get();

        Blog blog=blogRepository.findById(image.getBlog().getId()).get();

        blog.getImageList().remove(image);
        blogRepository.save(blog);
        imageRepository.deleteById(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        Image image=imageRepository.findById(id).get();

        String imageDim[]=image.getDimension().split("X");
        String screenDim[]=screenDimensions.split("X");

        int imageDimension=(Integer.valueOf(imageDim[0])*Integer.valueOf(imageDim[1]));
        int screenDimension=(Integer.valueOf(screenDim[0])*Integer.valueOf(screenDim[1]));

        int count=screenDimension/imageDimension;

        return count;
    }
}
