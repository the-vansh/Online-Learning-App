package com.Vansh.Online.Learning.App.Service;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

//@Service
//public class CloudinaryService {
//
//    @Autowired
//    private Cloudinary cloudinary;
//
//    public String deleteFile(String publicId) throws IOException {
//        try {
//            Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
//            String deleteResult = (String) result.get("result");
//
//            if ("ok".equals(deleteResult)) {
//                return "Deleted successfully";
//            } else if ("not found".equals(deleteResult)) {
//                return "File not found on Cloudinary";
//            } else {
//                return "Failed to delete: " + deleteResult;
//            }
//        } catch (IOException e) {
//            throw new IOException("Cloudinary deletion failed: " + e.getMessage(), e);
//        }
//    }
//}

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    public String deleteFile(String publicId, String resourceType) throws IOException {
        try {
            // Include resource_type and invalidate cache
            Map<String, Object> options = ObjectUtils.asMap(
                    "resource_type", resourceType, // Should be "image", "video", or "raw"
                    "invalidate", true // Invalidate CDN cache
            );

            Map result = cloudinary.uploader().destroy(publicId, options);
            String deleteResult = (String) result.get("result");

            if ("ok".equals(deleteResult)) {
                return "Deleted successfully";
            } else if ("not found".equals(deleteResult)) {
                return "File not found on Cloudinary";
            } else {
                return "Failed to delete: " + deleteResult;
            }
        } catch (IOException e) {
            throw new IOException("Cloudinary deletion failed: " + e.getMessage(), e);
        }
    }
}
