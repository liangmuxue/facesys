package com.ss.facesys.data.system.client;

import com.ss.exception.ServiceException;
import com.ss.facesys.data.system.common.dto.ImgCollectionResultDTO;
import com.ss.facesys.data.system.common.model.ImgCollection;

import java.util.List;

/**
 * IImgCollectionService
 *
 * @author FrancisYs
 * @date 2020/2/18
 * @email yaoshuai@ss-cas.com
 */
public interface IImgCollectionService {

    List<ImgCollectionResultDTO> pages(ImgCollection query, int currentPage, int pageSize);

    String collect(ImgCollection imgCollection) throws ServiceException;

    String remove(ImgCollection imgCollection) throws ServiceException;

    ImgCollectionResultDTO detail(ImgCollection imgCollection) throws ServiceException;

}
