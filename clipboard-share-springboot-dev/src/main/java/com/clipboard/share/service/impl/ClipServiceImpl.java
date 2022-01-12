package com.clipboard.share.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clipboard.share.entity.Clip;
import com.clipboard.share.mapper.ClipMapper;
import com.clipboard.share.service.IClipService;
import org.springframework.stereotype.Service;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 * @description : []
 * @createTime : [2021/10/27 8:31]
 */
@Service
public class ClipServiceImpl extends ServiceImpl<ClipMapper,Clip> implements IClipService {
}
