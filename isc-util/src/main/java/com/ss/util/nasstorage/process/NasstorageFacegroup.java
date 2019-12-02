package com.ss.util.nasstorage.process;

import com.ss.util.nasstorage.config.properties.NasstorageProperties;
import com.ss.util.nasstorage.enums.EnumClass;
import com.ss.util.nasstorage.file.FileUtil;
import com.ss.util.nasstorage.file.NasstorageFile;
import com.ss.util.nasstorage.file.NasstorageFileBuilder;
import com.ss.util.nasstorage.node.NasstorageNode;
import com.ss.util.nasstorage.node.NasstorageNodeChoice;
import com.ss.util.nasstorage.uuid.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;


public class NasstorageFacegroup {

    private static final Logger logger = LoggerFactory.getLogger(NasstorageFacegroup.class);


    public NasstorageFile nasstorageFacegroupImg(NasstorageProperties properties, String facegroupId, String imageData, String imageUrl) {
        NasstorageFile nasstorageFile = null;
        if (null == properties || StringUtils.isEmpty(facegroupId) || (
                StringUtils.isEmpty(imageData) && StringUtils.isEmpty(imageUrl))) {
            logger.error("param is error!properties,facegroupId,imageData,imageUrl");
            return null;
        }

        try {
            Integer faceGroupSubDirCount = Integer.valueOf((properties.getFaceGroupSubDirCount() == null) ? 0 : properties
                    .getFaceGroupSubDirCount().intValue());
            if (StringUtils.isEmpty(imageData)) {
                byte[] imgData = FileUtil.getImgDataByUrl(imageUrl);
                if (imgData == null) return null;
                imageData = Base64Utils.encodeToString(imgData);
            }
            String filename = UUIDUtils.get32UUID() + "." + FileUtil.getFileSuffix(imageData);
            String rootDir = EnumClass.StorageImageType.STORAGE_IMAGE_TYPE_GROUPDB.getType();
            NasstorageNode node = (new NasstorageNodeChoice()).getNasstorageNode(properties);
            nasstorageFile = (new NasstorageFileBuilder()).nasstorageFacegroupFile(node, rootDir, facegroupId, filename, faceGroupSubDirCount);

            FileUtil.saveBase64ToFile(imageData, nasstorageFile.getStorageAbsolutePath());
        } catch (Exception e) {
            logger.error("nas存储图片失败，原因:{}", e.getMessage());
            return null;
        }
        return nasstorageFile;
    }

}
