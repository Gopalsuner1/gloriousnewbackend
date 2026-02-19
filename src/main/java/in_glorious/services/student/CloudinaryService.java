package in_glorious.services.student;

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
    private Cloudinary cloudinary;
    public String uploadImage(MultipartFile file) throws IOException {
        Map<?,?> uploadResult = cloudinary.uploader()
                .upload(file.getBytes(), ObjectUtils.emptyMap());
        String public_url = (String) uploadResult.get("public_id");
        String format = (String) uploadResult.get("format");
        String imageUrl = public_url+format;
        return imageUrl;
    }
    public boolean deleteImage(String oldUrl) throws Exception{
            if(!oldUrl.contains(".")) throw new RuntimeException("Wrong Url");
            String public_id = oldUrl.split("\\.")[0];
            Map<?,?> result = cloudinary.uploader().destroy(
                    public_id,
                    Map.of("invalidate", true)
            );
            return "ok".equals(result.get("result"));
    }
}