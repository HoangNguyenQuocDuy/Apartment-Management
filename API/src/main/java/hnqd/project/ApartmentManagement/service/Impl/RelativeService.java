package hnqd.project.ApartmentManagement.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import hnqd.project.ApartmentManagement.entity.Relative;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import hnqd.project.ApartmentManagement.repository.IRelativeRepo;
import hnqd.project.ApartmentManagement.service.IRelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RelativeService implements IRelativeService {

    @Autowired
    private IRelativeRepo relativeRepo;

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
    public Relative updateRelative(Relative relative) {
        return null;
    }

    @Override
    public List<Relative> getRelatives(Map<String, String> params) {
        return relativeRepo.findAll();
    }

    @Override
    public Relative getRelativeById(int id) throws CommonException.NotFoundException {
        Optional<Relative> relativeOpt = relativeRepo.findById(id);

        return relativeOpt.orElseThrow(() -> new CommonException.NotFoundException(
                "Relative not found with ID: " + id));
    }

    @Override
    public void deleteRelative(int id) {
        relativeRepo.deleteById(id);
    }
}
