package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.entity.EntryRight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IEntryRightService {
    EntryRight createEntryRight(int relativeId);

    List<EntryRight> getEntryRights(Map<String, String> params);

    EntryRight updateEntryRight(int eId, String status);
    Page<EntryRight> getEntryRightsPaging(Map<String, String> params);
}
