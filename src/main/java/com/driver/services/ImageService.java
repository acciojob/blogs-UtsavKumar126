package com.driver.services;
import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions) throws Exception {
        //add an image to the blog
        Blog blog;
        try {
             blog = blogRepository2.findById(blogId).get();
        }
        catch (Exception e){
            throw new Exception();
        }
        Image image=new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);

        blog.getImageList().add(image);

        blogRepository2.save(blog);

        return image;
    }

    public void deleteImage(Integer id) throws Exception {
        Image image;
        try {
            image = imageRepository2.findById(id).get();
        }
        catch (Exception e){
            throw new Exception();
        }

        Blog blog=blogRepository2.findById(image.getBlog().getId()).get();

        blog.getImageList().remove(image);
        blogRepository2.save(blog);
        imageRepository2.deleteById(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) throws Exception {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        Image image;
        try {
            image = imageRepository2.findById(id).get();
        }
        catch (Exception e){
            throw new Exception();
        }

        String imageDim[]=image.getDimensions().split("X");
        String screenDim[]=screenDimensions.split("X");

        int imageDimensionhori=Integer.valueOf(imageDim[0]);
        int imageDimensionvert=Integer.valueOf(imageDim[1]);
        int screenDimensionhori=Integer.valueOf(screenDim[0]);
        int screenDimensionveri=Integer.valueOf(screenDim[1]);
        int count=(screenDimensionhori/imageDimensionhori)*(screenDimensionveri/imageDimensionvert);

        return count;
    }
}
