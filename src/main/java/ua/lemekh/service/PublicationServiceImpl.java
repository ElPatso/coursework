package ua.lemekh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.lemekh.dao.GroupDao;
import ua.lemekh.dao.PublicationDao;
import ua.lemekh.dao.RateRepository;
import ua.lemekh.dto.NewPublicationDTO;
import ua.lemekh.dto.PublicationDTO;
import ua.lemekh.mailEvent.MailSernder;
import ua.lemekh.model.Group;
import ua.lemekh.model.Publication;
import ua.lemekh.model.Rate;
import ua.lemekh.model.User;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static ua.lemekh.Util.DateTimeConverter.FORMATTER_FOR_SAVE;

/**
 * Created by Ostap on 16.06.2017.
 */
@Service
public class PublicationServiceImpl implements PublicationService {
    @Autowired
    private PublicationDao publicationDao;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private MailSernder mailSernder;

    @Transactional
    @Override
    public List<Publication> getProductList(Integer offset, Integer maxResult) {
        return publicationDao.getPublications(offset, maxResult, userService.getCurrentUser());
    }

    @Transactional
    @Override
    public Long count() {
        return publicationDao.count(userService.getCurrentUser());
    }

    @Transactional
    @Override
    public Publication getProductById(int id) {
        return publicationDao.getPublicationById(id);
    }

    @Transactional
    @Override
    public PublicationDTO getProductDTOById(int id) {
        Publication publication = publicationDao.getPublicationById(id);
        PublicationDTO publicationDTO = PublicationDTO.toDto(publication);
        if (!CollectionUtils.isEmpty(publication.getRates())) {
            publicationDTO.setRate(publication.getRates()
                    .stream()
                    .mapToInt(Rate::getRating).sum() / publication.getRates().size());
        }
        return publicationDTO;
    }


    @Transactional
    @Override
    public Publication createPublication(HttpServletRequest request, NewPublicationDTO newPublicationDTO) {
        Publication publication = NewPublicationDTO.fromDto(newPublicationDTO);
        List<Group> groups = newPublicationDTO.isPrivatePublication() ? groupDao.getGroupbByNames(newPublicationDTO.getGroupList()) : null;
        publication.setPublicatedBy(userService.getCurrentUser());
        publication.setGroups(groups);
        String logoUrl = request.getSession().getServletContext().getRealPath("/resources/img/");
        String fileUrl = request.getSession().getServletContext().getRealPath("/resources/files/");
        publication.setLogoUrl(uploadImage(logoUrl, newPublicationDTO.getLogo(), newPublicationDTO.getPublicationTitle()));
        publication.setFileUrl(uploadFile(fileUrl, newPublicationDTO.getFile()));

        if (!CollectionUtils.isEmpty(groups)){
            userService.getUsersByGroup(groups)
                    .forEach(mailSernder::sendToUsers);
        }
        return publicationDao.createPublication(publication);
    }

    @Transactional
    @Override
    public List<Publication> getProductListBySearch(String search) {
        return this.publicationDao.getPublicationsBySearch(search);
    }

    @Transactional
    @Override
    public List<Publication> getProductsByCategory(String category, Integer offset, Integer maxResult) {
        return this.publicationDao.getPublicationsByGroup(category, offset, maxResult);
    }

    @Transactional
    @Override
    public Long CountForCategory(String category) {
        return this.publicationDao.CountForCategory(category);
    }

    @Transactional
    @Override
    public void updateproduct(Publication products) {
        this.publicationDao.updatePublication(products);
    }

    @Transactional
    @Override
    public void deleteProduct(int id) {
        this.publicationDao.deletePublication(id);
    }

    @Override
    @Transactional
    public void setRateForPublication(final Integer publicationId, final Integer rate) {
        Publication publication = publicationDao.getPublicationById(publicationId);
        User currentUser = userService.getCurrentUser();
        Rate ratePerPublication = publication.getRates()
                .stream()
                .filter(r -> r.getPublication().getId() == publicationId && r.getRatedBy().getId() == currentUser.getId())
                .findFirst()
                .orElse(new Rate());
        ratePerPublication.setRating(rate);
        ratePerPublication.setRatedAt(LocalDateTime.now());
        ratePerPublication.setPublication(publication);
        ratePerPublication.setRatedBy(currentUser);
        rateRepository.save(ratePerPublication);
    }


    private String uploadImage(String filePath, MultipartFile multipartFile, String name) {
        Path path;
        path = Paths.get(filePath +
                name + LocalDateTime.now().format(FORMATTER_FOR_SAVE) + ".png");
        if (multipartFile != null && !multipartFile.isEmpty()) {
            try {
                multipartFile.transferTo(new File(path.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path.toString();
    }

    private String uploadFile(String filePath, MultipartFile multipartFile) {
        Path path;
        path = Paths.get(filePath + LocalDateTime.now().format(FORMATTER_FOR_SAVE) + multipartFile.getOriginalFilename());
        if (!multipartFile.isEmpty()) {
            try {
                multipartFile.transferTo(new File(path.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path.toString();
    }

}
