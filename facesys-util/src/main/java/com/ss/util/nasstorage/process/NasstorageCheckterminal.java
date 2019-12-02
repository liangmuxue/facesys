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


public class NasstorageCheckterminal {

    private static final Logger logger = LoggerFactory.getLogger(NasstorageCheckterminal.class);


    public NasstorageFile nasstorageCheckterminalImg(NasstorageProperties properties, Integer imgType, String deviceNo, String imageData, String imageUrl, Long captureTime) {
        NasstorageFile nasstorageFile = null;
        if (null == properties || StringUtils.isEmpty(deviceNo) || (
                StringUtils.isEmpty(imageData) && StringUtils.isEmpty(imageUrl))) {
            logger.error("param is error!properties,deviceNo,imageData,imageUrl");
            return null;
        }
        try {
            if (StringUtils.isEmpty(imageData)) {
                byte[] imgData = FileUtil.getImgDataByUrl(imageUrl);
                if (imgData == null) return null;
                imageData = Base64Utils.encodeToString(imgData);
            }
            String fileName = UUIDUtils.get32UUID() + "." + FileUtil.getFileSuffix(imageData);
            String faceDir = EnumClass.StorageImageType.STORAGE_IMAGE_TYPE_CT_FACEPIC.getType();
            if (null != imgType && 2 == imgType.intValue()) {
                faceDir = EnumClass.StorageImageType.STORAGE_IMAGE_TYPE_CT_CARD.getType();
            }
            NasstorageNode node = (new NasstorageNodeChoice()).getNasstorageNode(properties);
            nasstorageFile = (new NasstorageFileBuilder()).nasstorageCheckterminalFile(node, faceDir, deviceNo, fileName, captureTime);

            FileUtil.saveBase64ToFile(imageData, nasstorageFile.getStorageAbsolutePath());
        } catch (Exception e) {
            logger.error("nas存储图片失败，原因:{}", e.getMessage());
            return null;
        }
        return nasstorageFile;
    }

}
