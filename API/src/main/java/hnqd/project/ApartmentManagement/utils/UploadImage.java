package hnqd.project.ApartmentManagement.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
public class UploadImage {

    @Autowired
    private Cloudinary cloudinary;

    public String uploadToCloudinary(MultipartFile file) throws IOException {
        Map res = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder", "quanlychungcu"));

        return res.get("secure_url").toString();
    }
}
