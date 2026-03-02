package in_glorious.services;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary; // ✅ must be final

    public String uploadImage(MultipartFile file) throws IOException {

        Map<?, ?> uploadResult = cloudinary.uploader()
                .upload(file.getBytes(), ObjectUtils.emptyMap());

        // ✅ Return secure_url instead of building manually
        return (String) uploadResult.get("secure_url");
    }

    public boolean deleteImage(String imageUrl) throws Exception {

        if (imageUrl == null || imageUrl.isBlank())
            return false;

        // Extract public_id from URL
        String publicId = imageUrl
                .substring(imageUrl.lastIndexOf("/") + 1)
                .split("\\.")[0];

        Map<?, ?> result = cloudinary.uploader()
                .destroy(publicId, Map.of("invalidate", true));

        return "ok".equals(result.get("result"));
    }
}