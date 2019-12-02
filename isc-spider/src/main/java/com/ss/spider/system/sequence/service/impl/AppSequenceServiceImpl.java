package com.ss.spider.system.sequence.service.impl;

import com.ss.exception.ServiceException;
import com.ss.service.AbstractCWServiceImpl;
import com.ss.spider.system.sequence.mapper.AppSequenceMapper;
import com.ss.spider.system.sequence.model.AppSequence;
import com.ss.spider.system.sequence.model.SeqCycleEnum;
import com.ss.spider.system.sequence.service.AppSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("appSequenceService")
public class AppSequenceServiceImpl
        extends AbstractCWServiceImpl<AppSequence>
        implements AppSequenceService<AppSequence> {

    @Autowired
    private AppSequenceMapper appSequenceMapper;

    @Override
    public Integer getCurrVal(String seqCode) {
        AppSequence seq = this.appSequenceMapper.get(seqCode);
        return (seq != null) ? seq.getCurrVal() : null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int save(AppSequence args) throws ServiceException {
        try {
            return this.appSequenceMapper.save(args);
        } catch (Exception e) {
            this.logger.error("创建系统序列失败，原因：", e);
            throw new ServiceException("创建系统序列失败", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int update(AppSequence args) throws ServiceException {
        try {
            return this.appSequenceMapper.update(args);
        } catch (Exception e) {
            this.logger.error("修改系统序列信息失败，原因：", e);
            throw new ServiceException("修改系统序列信息失败", e);
        }
    }

    /**
     * 更新app序列
     * @param seqCode
     * @return
     * @throws ServiceException
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Integer getNextVal(final String seqCode) throws ServiceException {
        AppSequence appSequence = new AppSequence();
        appSequence.setSeqCode(seqCode);
        if (this.appSequenceMapper.nextVal(appSequence) != 1) {
            throw new ServiceException("更新序列nextVal失败");
        }
        AppSequence seq = this.appSequenceMapper.get(seqCode);
        if (seq.getCurrVal() > seq.getMaxVal()) {

            if (seq.getIscycle() == SeqCycleEnum.CYCLE.getValue()) {
                seq.setCurrVal(seq.getStartVal());
                seq.setNextVal(seq.getStartVal() + seq.getStep());
                try {
                    this.appSequenceMapper.update(seq);
                } catch (Exception e) {
                    this.logger.error("重置序列到开始位置失败，原因：", e);
                    throw new ServiceException("重置序列到开始位置失败");
                }
                return seq.getCurrVal();
            }
            throw new ServiceException("序列已经超过最大值");
        }
        return seq.getCurrVal();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int remove(String seqCode) throws ServiceException {
        try {
            return this.appSequenceMapper.remove(seqCode);
        } catch (Exception e) {
            this.logger.error("删除队列失败，原因：", e);
            throw new ServiceException("删除队列失败", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public String getNextVal(String seqCode, char pad, int length) throws ServiceException {
        String formatter = "%" + pad + length + "d";
        try {
            return String.format(formatter, new Object[]{getNextVal(seqCode)});
        } catch (ServiceException e) {
            this.logger.error("生成序列失败，原因：", e);
            throw new ServiceException("生成序列失败", e);
        }
    }

}
