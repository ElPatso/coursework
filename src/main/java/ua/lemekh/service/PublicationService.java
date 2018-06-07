package ua.lemekh.service;

import ua.lemekh.dto.NewPublicationDTO;
import ua.lemekh.dto.PublicationDTO;
import ua.lemekh.model.Publication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Ostap on 16.06.2017.
 */
public interface PublicationService {
    List<Publication> getProductList(Integer offset, Integer maxResult);

    List<Publication> getProductListByCurrentUser(Integer offset, Integer maxResult);

    Long count();

    Long countByCurrentUser();

    PublicationDTO getProductDTOById(int id);

    Publication getProductById(int id);

    Publication createPublication(HttpServletRequest request,NewPublicationDTO newPublicationDTO);

    List<Publication> getProductListBySearch(String search);

    List<Publication> getProductsByCategory(String category, Integer offset, Integer maxResult);

    Long CountForCategory(String category);

    void updateproduct(Publication products);

    void deleteProduct(int id);

    void setRateForPublication(Integer publicationId, Integer rate);
}
