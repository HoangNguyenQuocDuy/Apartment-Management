package hnqd.project.ApartmentManagement.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import hnqd.project.ApartmentManagement.entity.Relative;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import hnqd.project.ApartmentManagement.repository.IRelativeRepo;
import hnqd.project.ApartmentManagement.service.IRelativeService;
import hnqd.project.ApartmentManagement.utils.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RelativeService implements IRelativeService {

    @Autowired
    private IRelativeRepo relativeRepo;
    @Autowired
    private UploadImage uploadImage;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Relative createRelative(Relative relative) throws IOException {
        if (relative.getFile() != null && !relative.getFile().isEmpty()) {
            Map res = cloudinary.uploader().upload(relative.getFile().getBytes(), ObjectUtils.asMap("folder", "quanlychungcu"));
            relative.setAvatar(res.get("secure_url").toString());
        }

        return relativeRepo.save(relative);
    }

    @Override
    public Relative updateRelative(Integer relativeId, MultipartFile file, Map<String, String> params) throws IOException {
        Relative storedRelative = relativeRepo.findById(relativeId).orElseThrow(
                () -> new CommonException.NotFoundException("Relative not found with ID: " + relativeId)
        );

        if (file != null && !file.isEmpty()) {
            storedRelative.setAvatar(uploadImage.uploadToCloudinary(file));
        }

        if (params.get("firstname") != null && !params.get("firstname").isEmpty()) {
            storedRelative.setFirstname(params.get("firstname"));
        }
        if (params.get("lastname") != null && !params.get("lastname").isEmpty()) {
            storedRelative.setLastname(params.get("lastname"));
        }
        if (params.get("type") != null && !params.get("type").isEmpty()) {
            storedRelative.setType(params.get("type"));
        }

        return relativeRepo.save(storedRelative);
    }

    @Override
    public List<Relative> getRelatives(Map<String, String> params) {
        List<Relative> relatives = new ArrayList<>();

        if (params.get("firstname") != null && !params.get("firstname").isEmpty()) {
            relatives.addAll(relativeRepo.findAllByFirstnameContaining(params.get("firstname")));
        }
        if (params.get("lastname") != null && !params.get("lastname").isEmpty()) {
            relatives.addAll(relativeRepo.findAllByLastnameContaining(params.get("lastname")));
        }
        if (params.get("type") != null && !params.get("type").isEmpty()) {
            relatives.addAll(relativeRepo.findAllByType(params.get("type")));
        }
        if (params.get("userId") != null && !params.get("userId").isEmpty()) {
            relatives.addAll(relativeRepo.findAllByUserId(Integer.parseInt(params.get("userId"))));
        }

        return relatives;
    }

    @Override
    public List<Relative> getRelatives() {
        return relativeRepo.findAll();
    }

    @Override
    public void deleteRelative(int id) {
        Relative storedRelative = relativeRepo.findById(id).orElseThrow(
                () -> new CommonException.NotFoundException("Relative not found with ID: " + id)
        );

        relativeRepo.deleteById(id);
    }
}
